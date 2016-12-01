package project.services;


import project.DataBase.dataBaseImpl;
import project.domain.Credential;
import project.domain.User;

public class UserServicesImpl implements UserServices {
	
	
	public User actualUser;
	private final dataBaseImpl database;
	
	

	public UserServicesImpl(dataBaseImpl database, User actualUser) {
		this.database = database;
		this.actualUser = actualUser;
	}
	
	

	@Override
	public User Register(String name, String email, long studentID, String password) {
		
		User userToRegister = new User(name, email, studentID, password, false, Credential.REGISTERED_USER);
		database.saveUser(userToRegister);		
		return userToRegister;
	}

	@Override
	public void Login(String user, String password, User currentUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LogOut(int user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User BlockUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User removeModeration(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User removeAdmin(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User concedeModeration(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User concedeAdmin(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}

