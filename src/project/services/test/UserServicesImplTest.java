package project.services.test;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.DataBase.DataBaseImpl;
import project.domain.Credential;
import project.domain.User;
import project.Permissions.Permissions;
import project.services.UserServicesImpl;

public class UserServicesImplTest {

	User userAnon;
	User userReg;
	User userMod;
	User userAdm;
	DataBaseImpl database;
	UserServicesImpl userService;
	
	
	@Before
	public void setUp() throws Exception{
		
		userAnon = new User();
		userReg = new User("Joao", "joao@gmail.com", 001, "qwerqwe", false, Credential.REGISTERED_USER);
		userMod = new User("Marisa", "marisa@bol.com", 002, "ewrwer", false, Credential.MODERATOR);
		userAdm = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);
		
		 database = new DataBaseImpl();
		 Permissions check = new Permissions();
		 userService = new UserServicesImpl(database, userAnon,check);
	}
	
	
	@Test
	public void RegisterTest(){
		
		User userTest = userService.register( "Joao", "joao@gmail.com",105, "qwerqwe");
		assertTrue(userTest.getCredential() == Credential.REGISTERED_USER);
		
		User userTest2 = userService.register( "Juliana", "juju@gmail.com",103, "asdzxc");
		assertTrue(userTest2.getCredential() == Credential.REGISTERED_USER);
		
	}
	
	
	@Test
	public void LoginTest(){
		userService.register("cassandra", "cassandra@gmail.com", 101, "asdzx");
		User userLog = userService.login("cassandra@gmail.com", "asdzx");
		assertTrue(userLog.getCredential() != Credential.ANONYMOUS);
	}
	
	
	@Test
	public void logOutTest(){
		assertTrue( userService.logOut(userAdm).getCredential() == Credential.ANONYMOUS);
		assertTrue( userService.logOut(userAnon).getCredential() == Credential.ANONYMOUS );
		assertTrue( userService.logOut(userMod).getCredential() == Credential.ANONYMOUS );
		assertTrue( userService.logOut(userReg).getCredential() == Credential.ANONYMOUS );
	}

	@Test
	public void BlockUserTest() throws Exception{
		
		userService.actualUser = userAdm;
		
		userService.blockUser(userReg);
		assertTrue(userReg.getIsBlocked() == true);
		userService.blockUser(userMod);
		assertTrue(userMod.getIsBlocked() == true);
		userService.blockUser(userAdm);
		assertTrue(userAdm.getIsBlocked() == false);
	}
	
	@Test
	public void removeModerationTest() throws Exception{
		
		userService.actualUser = userAdm;
		
		userService.removeModeration(userMod);
		assertTrue(userMod.getCredential() == Credential.REGISTERED_USER);
		userService.removeModeration(userAdm);
		assertTrue(userAdm.getCredential() == Credential.REGISTERED_USER);
	}
	

	@Test
	public void removeAdminTest() throws Exception{
	//just admin can removeAdmin
		
		userService.actualUser = userAdm;
		
		userService.removeAdmin(userAdm);
		assertTrue(userAdm.getCredential() == Credential.REGISTERED_USER);	
	}
	
	
	@Test
	public void concedeModerationTest() throws Exception{
		
		userService.actualUser = userAdm;
		
		userService.concedeModeration(userReg);
		assertTrue(userReg.getCredential() == Credential.MODERATOR);
		userService.concedeModeration(userAnon);
		assertFalse(userAnon.getCredential() == Credential.MODERATOR);
		userService.concedeModeration(userMod);
		assertTrue(userMod.getCredential() == Credential.MODERATOR);
		userService.concedeModeration(userAdm);
		assertTrue(userAdm.getCredential() == Credential.MODERATOR);
	}
	
	@Test
	public void concedeAdmin() throws Exception{
		
		userService.actualUser = userAdm;
		
		
		userService.concedeAdmin(userMod);
		assertTrue(userMod.getCredential() == Credential.ADMIN);
		userService.concedeAdmin(userAdm);
		assertTrue(userAdm.getCredential() == Credential.ADMIN);
		userService.concedeAdmin(userReg);
		assertTrue(userReg.getCredential() == Credential.ADMIN);
		userService.concedeAdmin(userAnon);
		assertFalse(userAnon.getCredential() == Credential.ADMIN);
	}
	
	
	@Test
	public void isLoggedInTest(){
		
		assertFalse(userService.isLoggedIn(userAnon));
		assertTrue(userService.isLoggedIn(userAdm));
		assertTrue(userService.isLoggedIn(userMod));
		assertTrue(userService.isLoggedIn(userReg));
	}
	
}
