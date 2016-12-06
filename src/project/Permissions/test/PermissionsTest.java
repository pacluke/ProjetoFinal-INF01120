package project.Permissions.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import project.Permissions.*;
import project.domain.*;
import org.junit.Test;

public class PermissionsTest {
	
	User userAnon;
	User userReg;
	User userMod;
	User userAdm;
	List<User> userList = new ArrayList<User>();

	@Before
	public void setUp() {
		userAnon = new User();
		userReg = new User("Arthur", "arthurL@gmail.com", 007, "jesus9", false, Credential.REGISTERED_USER);
		userMod = new User("Kath", "Kath@bol.com", 006, "helloe56", false, Credential.MODERATOR);
		userAdm = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);
		
		userList.add(userAdm);
		userList.add(userMod);
		userList.add(userReg);
		userList.add(userAnon);
		
	}
	
	@Test
	public void permissionsOkTest() throws Exception{
		Permissions check = new Permissions();	
		Boolean passed = false;
		
		for(int i = 0; i<userList.size()-1; i++)
			check.Permission(userList.get(i), Credential.REGISTERED_USER);
		
		for(int i = 0; i<userList.size()-2; i++)
			check.Permission(userList.get(i), Credential.MODERATOR);
		
		for(int i = 0; i<userList.size()-3; i++)
			check.Permission(userList.get(i), Credential.ADMIN);
		
		passed = true;
		assertTrue(passed);
	}
	
	@Test(expected = PermissionDenied.class)
	public void permissionsExceptionRegTest() throws Exception{
		Permissions check = new Permissions();	
		
		for(int i = 0; i<userList.size(); i++)
			check.Permission(userList.get(i), Credential.REGISTERED_USER);

	}
	
	@Test(expected = PermissionDenied.class)
	public void permissionsExceptionModTest() throws Exception{
		Permissions check = new Permissions();	
		
		for(int i = 0; i<userList.size(); i++)
			check.Permission(userList.get(i), Credential.MODERATOR);

	}
	
	@Test(expected = PermissionDenied.class)
	public void permissionsExceptionAdmTest() throws Exception{
		Permissions check = new Permissions();	
		
		for(int i = 0; i<userList.size(); i++)
			check.Permission(userList.get(i), Credential.ADMIN);
		
	}
	
	

}
