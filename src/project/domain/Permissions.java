package project.domain;


public class Permissions {
	
	public Boolean checkPermission(User user1, User user2){
		if (user1.getCredential() == user2.getCredential())
				return true;	
		else
			return false;
	}
	
	public User changePermission(User user, Credential credential){		
		user.setCredential(credential);	
		return user;		
	}

}
