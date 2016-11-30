package project.domain;

public class User {
	
	private String name;
	private String email;
	private long studentID;
	private Boolean isBlocked;
	private Credential credential;
	
	public User(){	
		this.credential = Credential.ANONYMOUS;
		this.email = "Anonymous";
		this.studentID = 0000;
		this.isBlocked = null;
		this.name = "Anonymous";
	}
	
	public User(String name, String email, long studentID, Boolean isBlocked, Credential credential){
		this.credential = credential;
		this.email = email;
		this.studentID = studentID;
		this.isBlocked = isBlocked;
		this.name = name;		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getStudentID() {
		return studentID;
	}
	
	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}
	
	public Boolean getIsBlocked() {
		return isBlocked;
	}
	
	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	
	public Credential getCredential() {
		return credential;
	}
	
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	

}
