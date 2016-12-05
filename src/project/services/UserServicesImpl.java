/*
 * Author: Diogo 
 */

package project.services;


import project.DataBase.DataBaseImpl;
import project.domain.Credential;
import project.domain.User;
import project.Permissions.*;

public class UserServicesImpl implements UserServices {
		
	public User actualUser;
	private final DataBaseImpl database;
	private final Permissions check;
	
	public UserServicesImpl(DataBaseImpl database, User actualUser, Permissions check) {
		this.database = database;
		this.actualUser = actualUser;
		this.check = check;
	}	

	@Override
	public User Register(String name, String email, long studentID, String password) {	
		User userToRegister = new User(name, email, studentID, password, false, Credential.REGISTERED_USER);
		database.save(userToRegister, null, null);		
		return userToRegister;
	}
	
	@Override
	public boolean isLoggedIn(User user){
		
		if(user.getCredential() == Credential.ANONYMOUS)
			return false;
		else
			return true;
	}

	@Override
	public User Login(String email, String password) {
		User search = new User();
		
		if (!(isLoggedIn(this.actualUser))){
			User userFound = (User) database.find(search, email);
			if(userFound.getPassword() == password){
				this.actualUser = userFound;
				return this.actualUser;
			}
			else
				System.out.println("Usuario nao encontrado");			
		}
		else{
			System.out.println("Usuario ja esta logado");
		}
		return this.actualUser;	
	}
	

	@Override
	public User LogOut(User user) {
		this.actualUser = new User(); 
		return this.actualUser;
	}

	@Override
	//permission moderator or more
	public void BlockUser(User user) throws Exception{
		
		boolean userIsNotAdmin = (user.getCredential() != Credential.ADMIN);
		boolean userIsNotAnonymous = (user.getCredential() != Credential.ANONYMOUS);
		
		check.Permission(this.actualUser, Credential.MODERATOR);
		System.out.println("churros");
		if(userIsNotAdmin && userIsNotAnonymous){															
			user.setIsBlocked(true);
		}
	}
	
	
	@Override
	public void removeModeration(User user) throws Exception{
		//just admin can removeModeration
		
		boolean userIsNotAnonymous = (user.getCredential() != Credential.ANONYMOUS);
		boolean userIsNotRegistred = (user.getCredential() != Credential.REGISTERED_USER);
		
		check.Permission(this.actualUser, Credential.ADMIN);
		
		if(userIsNotRegistred &&userIsNotAnonymous){															
			user.setCredential(Credential.REGISTERED_USER);
		}
	}
	

	@Override
	public void removeAdmin(User user) throws Exception{	
		boolean userIsAdmin = (user.getCredential() == Credential.ADMIN);
		
		check.Permission(this.actualUser, Credential.ADMIN);
		
		if(userIsAdmin){															
			user.setCredential(Credential.REGISTERED_USER);
		}
	}

	
	@Override
	public void concedeModeration(User user) throws Exception{
		//just admin can removeAdmin

		boolean userIsNotAnonymous = (user.getCredential() != Credential.ANONYMOUS);
		
		check.Permission(this.actualUser, Credential.ADMIN);
		
		if(userIsNotAnonymous){															
			user.setCredential(Credential.MODERATOR);
		}
	}

	@Override
	public void concedeAdmin(User user) throws Exception{
		boolean userIsNotAnonymous = (user.getCredential() != Credential.ANONYMOUS);
		
		check.Permission(this.actualUser, Credential.ADMIN);
		
		if(userIsNotAnonymous){															
			user.setCredential(Credential.ADMIN);
		}
	}
}

