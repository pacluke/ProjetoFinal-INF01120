package project.services.test;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.DataBase.dataBaseImpl;
import project.domain.Credential;
import project.domain.User;
import project.services.UserServicesImpl;

public class UserServicesImplTest {

	User userAnon;
	User userReg;
	User userMod;
	User userAdm;
	dataBaseImpl database;
	UserServicesImpl userService;
	
	
	@Before
	public void setUp() throws Exception{
		
		userAnon = new User();
		userReg = new User("Joao", "joao@gmail.com", 001, "qwerqwe", false, Credential.REGISTERED_USER);
		userMod = new User("Marisa", "marisa@bol.com", 002, "ewrwer", false, Credential.MODERATOR);
		userAdm = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);
		
		 database = new dataBaseImpl();
		 userService = new UserServicesImpl(database, userAnon);
	}
	
	
	@Test
	public void RegisterTest(){
		
		User userTest = userService.Register( "Joao", "joao@gmail.com",001, "qwerqwe");
		
		assertTrue(userTest.getCredential() == Credential.REGISTERED_USER);
		
	}
	
	@Test
	public void LoginTest(){
	
		//do not exists:
		User userTestNotExists = userService.Login("filomena@gmail.com", "hallellujah");
		assertFalse(userTestNotExists.getCredential() != Credential.ANONYMOUS);
		//exists
		userService.Register("joao", "joao@gmail.com", 101, "qwerqwe");
		User userTest = userService.Login("joao@gmail.com", "qwerqwe");
		assertTrue(userTest.getCredential() != Credential.ANONYMOUS);
		
		
				
	}
	
	
	
	@Test
	public void isLoggedInTest(){
		
		assertFalse(userService.isLoggedIn(userAnon));
		assertTrue(userService.isLoggedIn(userAdm));
		assertTrue(userService.isLoggedIn(userMod));
		assertTrue(userService.isLoggedIn(userReg));
	}
	
	
	@Test
	public void logOutTest(){
		
		assertTrue( userService.LogOut(userAdm).getCredential() == Credential.ANONYMOUS);
		assertTrue( userService.LogOut(userAnon).getCredential() == Credential.ANONYMOUS );
		assertFalse( userService.LogOut(userAnon).getCredential() == Credential.ADMIN );
		assertFalse( userService.LogOut(userAnon).getCredential() == Credential.MODERATOR );
		assertFalse( userService.LogOut(userAnon).getCredential() == Credential.REGISTERED_USER );
		
	}
	
	@Test
	public void  checkPermissionTest(){
		
		assertFalse(userService.checkPermission(userAnon, Credential.ADMIN));
		assertFalse(userService.checkPermission(userAnon, Credential.MODERATOR));
		assertFalse(userService.checkPermission(userAnon, Credential.REGISTERED_USER));
		assertTrue(userService.checkPermission(userAnon, Credential.ANONYMOUS));
		
		assertFalse(userService.checkPermission(userReg, Credential.ADMIN));
		assertFalse(userService.checkPermission(userReg, Credential.MODERATOR));
		assertTrue(userService.checkPermission(userReg, Credential.REGISTERED_USER));
		assertTrue(userService.checkPermission(userReg, Credential.ANONYMOUS));
		
		assertFalse(userService.checkPermission(userMod, Credential.ADMIN));
		assertTrue(userService.checkPermission(userMod, Credential.MODERATOR));
		assertTrue(userService.checkPermission(userMod, Credential.REGISTERED_USER));
		assertTrue(userService.checkPermission(userMod, Credential.ANONYMOUS));
		
		assertTrue(userService.checkPermission(userAdm, Credential.ADMIN));
		assertTrue(userService.checkPermission(userAdm, Credential.MODERATOR));
		assertTrue(userService.checkPermission(userAdm, Credential.REGISTERED_USER));
		assertTrue(userService.checkPermission(userAdm, Credential.ANONYMOUS));
		
		
		
	}

	@Test
	public void BlockUserTest(){
		
		userService.actualUser = userAdm;
		
		assertTrue(userService.BlockUser(userReg));
		assertTrue(userService.BlockUser(userMod));
		assertFalse(userService.BlockUser(userAnon));
		assertFalse(userService.BlockUser(userAdm));
		
		
		userService.actualUser = userReg;
		
		assertFalse(userService.BlockUser(userReg));
		assertFalse(userService.BlockUser(userMod));
		assertFalse(userService.BlockUser(userAnon));
		assertFalse(userService.BlockUser(userAdm));
	}
	
	
	
	@Test
	public void removeModerationTest(){
		
		userService.actualUser = userAdm;
		assertFalse(userService.removeModeration(userReg));
		assertTrue(userService.removeModeration(userMod));
		assertFalse(userService.removeModeration(userAnon));
		assertTrue(userService.removeModeration(userAdm));
		
		
		userService.actualUser = userReg;
		assertFalse(userService.removeModeration(userReg));
		assertFalse(userService.removeModeration(userMod));
		assertFalse(userService.removeModeration(userAnon));
		assertFalse(userService.removeModeration(userAdm));
	}
	
	

	@Test
	public void removeAdminTest(){
	//just admin can removeAdmin
		
		userService.actualUser = userAdm;
		assertFalse(userService.removeAdmin(userReg));
		assertFalse(userService.removeAdmin(userMod));
		assertFalse(userService.removeAdmin(userAnon));
		assertTrue(userService.removeAdmin(userAdm));
		
		userService.actualUser = userMod;
		assertFalse(userService.removeAdmin(userReg));
		assertFalse(userService.removeAdmin(userMod));
		assertFalse(userService.removeAdmin(userAnon));
		assertFalse(userService.removeAdmin(userAdm));
		
		userService.actualUser = userReg;
		assertFalse(userService.removeAdmin(userReg));
		assertFalse(userService.removeAdmin(userMod));
		assertFalse(userService.removeAdmin(userAnon));
		assertFalse(userService.removeAdmin(userAdm));
	}
	
	
	@Test
	public void concedeModerationTest(){

		//just admin can removeAdmin
		userService.actualUser = userAdm;
		assertTrue(userService.concedeModeration(userReg));
		assertTrue(userService.concedeModeration(userMod));
		assertTrue(userService.concedeModeration(userAdm));
		assertFalse(userService.concedeModeration(userAnon));
		
		
		userService.actualUser = userMod;
		assertFalse(userService.concedeModeration(userReg));
		assertFalse(userService.concedeModeration(userMod));
		assertFalse(userService.concedeModeration(userAdm));
		assertFalse(userService.concedeModeration(userAnon));
		
	}
	
	@Test
	public void concedeAdmin(){
		

		//just admin can removeAdmin
		userService.actualUser = userReg;
		assertFalse(userService.concedeAdmin(userReg));
		assertFalse(userService.concedeAdmin(userMod));
		assertFalse(userService.concedeAdmin(userAdm));
		assertFalse(userService.concedeAdmin(userAnon));
		
		userService.actualUser = userAdm;
		assertTrue(userService.concedeAdmin(userReg));
		assertTrue(userService.concedeAdmin(userMod));
		assertTrue(userService.concedeAdmin(userAdm));
		assertFalse(userService.concedeAdmin(userAnon));
		
		

	}
	
}
