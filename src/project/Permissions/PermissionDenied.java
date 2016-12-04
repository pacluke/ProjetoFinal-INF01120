package project.Permissions;

public class PermissionDenied extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PermissionDenied(){
		
	}
	
	public PermissionDenied(String message){
		super(message);
	}
	

}
