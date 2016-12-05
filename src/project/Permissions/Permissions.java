package project.Permissions;

import project.domain.*;

public class Permissions{

	public  void Permission (User user, Credential credential) throws Exception{
		if (!(user.getCredential().getValue() >= credential.getValue())){
			throw new PermissionDenied("SEM PERMISSAO PARA EXECUTAR O SERVICO");
		}		
	}
}
