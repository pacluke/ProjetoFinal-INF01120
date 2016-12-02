package project.services;

import project.domain.User;

public interface UserServices {
	
	
	public User Register(String name, String email, long studentID, String password);
	public void Login(String user, String password, User currentUser);
	public void LogOut(int user);
	public User BlockUser(User user);
	public User removeModeration(User user);
	public User removeAdmin(User user);
	public User concedeModeration(User user);
	public User concedeAdmin(User user);
	
	
	}



