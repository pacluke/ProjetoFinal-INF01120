package project.domain.test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.domain.Credential;
import project.domain.User;

import org.junit.Before;

public class UserTest {

	User userAnon;
	User userReg;
	User userMod;
	User userAdm;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception{
		userAnon = new User();
		userReg = new User("Joao", "joao@gmail.com", 001, false, Credential.REGISTERED_USER);
		userMod = new User("Marisa", "marisa@bol.com", 002, false, Credential.MODERATOR);
		userAdm = new User("Joana", "joana@yahoo.com", 003, false, Credential.ADMIN);
	}
	
	@Test
	public void getNameTest(){
		assertTrue(userAnon.getName() == "Anonymous");
		assertTrue(userReg.getName() == "Joao");
		assertTrue(userMod.getName() == "Marisa");
		assertTrue(userAdm.getName() == "Joana");
	}
	
	@Test
	public void setNameTest(){
		
		userAnon.setName("Adalberto");
		userReg.setName("Francesca");
		userMod.setName("Giovana");
		userAdm.setName("Mauricio");
		
		assertTrue(userAnon.getName() == "Adalberto");
		assertTrue(userReg.getName() == "Francesca");
		assertTrue(userMod.getName() == "Giovana");
		assertTrue(userAdm.getName() == "Mauricio");
	}
	
	@Test
	public void getEmailTest(){
		assertTrue(userAnon.getEmail() == "Anonymous");
		assertTrue(userReg.getEmail() == "joao@gmail.com");
		assertTrue(userMod.getEmail() == "marisa@bol.com");
		assertTrue(userAdm.getEmail() == "joana@yahoo.com");
	}
	
	@Test
	public void setEmailTest(){
		
		userAnon.setEmail("anono@gmail.com");
		userReg.setEmail("frano@gmail.com");
		userMod.setEmail("gigio@gmail.com");
		userAdm.setEmail("olaro@gmail.com");
		
		assertTrue(userAnon.getEmail() == "anono@gmail.com");
		assertTrue(userReg.getEmail() == "frano@gmail.com");
		assertTrue(userMod.getEmail() == "gigio@gmail.com");
		assertTrue(userAdm.getEmail() == "olaro@gmail.com");
	}
	
	@Test
	public void getStudentIDTest(){
		assertTrue(userAnon.getStudentID() == 0);
		assertTrue(userReg.getStudentID() == 001);
		assertTrue(userMod.getStudentID() == 002);
		assertTrue(userAdm.getStudentID() == 003);
	}
	
	@Test
	public void setStudentIDTest(){
		
		userAnon.setStudentID(1234);
		userReg.setStudentID(1234132);
		userMod.setStudentID(123423);
		userAdm.setStudentID(34234);
		
		assertTrue(userAnon.getStudentID() == 1234);
		assertTrue(userReg.getStudentID() == 1234132);
		assertTrue(userMod.getStudentID() == 123423);
		assertTrue(userAdm.getStudentID() == 34234);
	}
	
	@Test
	public void getCredentialTest(){
		assertTrue(userAnon.getCredential() == Credential.ANONYMOUS);
		assertTrue(userReg.getCredential() == Credential.REGISTERED_USER);
		assertTrue(userMod.getCredential() == Credential.MODERATOR);
		assertTrue(userAdm.getCredential() == Credential.ADMIN);
	}
	
	@Test
	public void setCredentialTest(){
		
		userAnon.setCredential(Credential.ADMIN);
		userReg.setCredential(Credential.ANONYMOUS);
		userMod.setCredential(Credential.REGISTERED_USER);
		userAdm.setCredential(Credential.MODERATOR);
		
		assertTrue(userAnon.getCredential() == Credential.ADMIN);
		assertTrue(userReg.getCredential() == Credential.ANONYMOUS);
		assertTrue(userMod.getCredential() == Credential.REGISTERED_USER);
		assertTrue(userAdm.getCredential() == Credential.MODERATOR);
	}
	
	@Test
	public void getIsBlockedTest(){
		assertTrue(userAnon.getIsBlocked() == null);
		assertTrue(!userReg.getIsBlocked());
		assertTrue(!userMod.getIsBlocked());
		assertTrue(!userAdm.getIsBlocked());
	}
	
	@Test
	public void setIsBlockedTest(){
		
		userAnon.setIsBlocked(true);
		userReg.setIsBlocked(true);
		userMod.setIsBlocked(true);
		userAdm.setIsBlocked(true);
		
		assertTrue(userAnon.getIsBlocked());
		assertTrue(userReg.getIsBlocked());
		assertTrue(userMod.getIsBlocked());
		assertTrue(userAdm.getIsBlocked());
	}
	
}
