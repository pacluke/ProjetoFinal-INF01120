package project.services;

import project.domain.User;

public interface UserServices {
		
	public User Register(String name, String email, long studentID, String password);
	public User Login(String email, String password);
	public User LogOut(User user);
	public void BlockUser(User user) throws Exception;
	public void removeModeration(User user) throws Exception;
	public void removeAdmin(User user) throws Exception;
	public void concedeModeration(User user) throws Exception;
	public void concedeAdmin(User user) throws Exception;
	public boolean isLoggedIn(User user);
}



