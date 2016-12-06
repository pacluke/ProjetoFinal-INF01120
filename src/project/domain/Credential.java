package project.domain;

public enum Credential {
	
	ANONYMOUS(0),
	REGISTERED_USER(1),
	MODERATOR(2),
	ADMIN(3);
	
	private int value;
	
	Credential(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}


}
