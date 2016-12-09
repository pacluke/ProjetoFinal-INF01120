package project.services;

import project.domain.User;

public interface UserServices extends Services {
		
	public User register(String name, String email, long studentID, String password);//
	public User login(String email, String password);//
	public User logOut(User user);//
	public void blockUser(User user) throws Exception;//
	public void removeModeration(User user) throws Exception;
	public void removeAdmin(User user) throws Exception;//
	public void concedeModeration(User user) throws Exception;//
	public void concedeAdmin(User user) throws Exception;
	public boolean isLoggedIn(User user);
}



