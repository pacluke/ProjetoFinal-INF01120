package project.MainTest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
 
import project.DataBase.*;
import project.domain.*;
import project.Permissions.*;
import project.services.*;

import org.junit.Test;

public class MainTest {
	
	DataBaseImpl db = new DataBaseImpl();
	Permissions check = new Permissions();
	User moderator = new User("Adelaide", "adelaide@inf.ufrgs.br", 243324, "adelaide_v1d4l0k4", false, Credential.MODERATOR);
	User admin = new User("Cleverson", "cleverson@inf.ufrgs.br", 251234, "cleversindosbrother", false, Credential.ADMIN);
	
	
	
	@Before
	public void setUp() {
		db.initData();
		db.save(moderator, null, null);
		db.save(admin, null, null);
	}
	
	@Test
	public void mainTest() throws Exception{
		
		/** USUARIOS NOVOS **/
		
		/* usuario anonimo por default */
		User newUser = new User();
		UserServicesImpl userServicesExample = new UserServicesImpl(db, newUser, check);
		
		/* registrando */
		userServicesExample.register("Renan", "renan@inf.ufrgs.br", 243534, "renazinho100%guaiba");
		
		/* login */
		userServicesExample.login("renan@inf.ufrgs.br", "renazinho100%guaiba");
		assertTrue(db.find(newUser, "renan@inf.ufrgs.br") == userServicesExample.actualUser);
		
		/* logout */
		userServicesExample.logOut(newUser);
		assertTrue(newUser.getCredential() == Credential.ANONYMOUS);
		
		/** CRIANDO CONTEUDO **/
		
		/* login */
		User registeredUser = userServicesExample.login("renan@inf.ufrgs.br", "renazinho100%guaiba");
		ContentServicesImpl contentServicesExample = new ContentServicesImpl(db, registeredUser, check);
		
		Tag tag1 = new Tag("Formais");
		Tag tag2 = new Tag("Trabalho Final"); 
		
		/* criando pergunta */
		contentServicesExample.addQuestion("Alguém tem o trabalho de formais pronto?", "Não sei nada da cadeira, mas aceito ajuda.", Arrays.asList(tag1, tag2));
		
		List<Object> questionsList = db.search(null);
		Question question = null;
		boolean feedback = false;
		
		for(int i = 0; i < questionsList.size(); i++){	
			
			boolean equalTitle = ((Question)questionsList.get(i)).getTitle() == "Alguém tem o trabalho de formais pronto?";
			boolean equalText = ((Question)questionsList.get(i)).getText() == "Não sei nada da cadeira, mas aceito ajuda.";

			if(equalTitle && equalText){
				question = (Question)questionsList.get(i);
				feedback = true;
			}
		}
		
		assertTrue(feedback);
		
		/** BLOQUEANDO USUARIO **/
		
		userServicesExample = new UserServicesImpl(db, moderator, check);	
		userServicesExample.blockUser(registeredUser);
		assertTrue(((User)db.find(registeredUser, "renan@inf.ufrgs.br")).getIsBlocked());
		
		
		/** ALTERANDO CREDENCIAIS **/
		
		userServicesExample = new UserServicesImpl(db, admin, check);
		
		/* moderador vira adm */
		userServicesExample.concedeAdmin(moderator);
		assertTrue(moderator.getCredential().equals(Credential.ADMIN));
		
		/* adm vira registrado apenas*/
		User newAdmin = moderator;
		
		userServicesExample = new UserServicesImpl(db, newAdmin, check);
		userServicesExample.removeAdmin(admin);
		
		User exAdmin = admin;
		
		assertTrue(admin.getCredential().equals(Credential.REGISTERED_USER));
		
		
		/** REMOVENDO CONTEUDO **/	
		contentServicesExample = new ContentServicesImpl(db, newAdmin, check);
		
		/* removendo questao */
		contentServicesExample.removeQuestion(question);	
		questionsList = db.search(null);	
		assertFalse(questionsList.contains(question));
		
		/** ALTERANDO CONTEUDO **/
		
		Tag newTag = new Tag("TCP");
		contentServicesExample.addQuestion("Alguem sabe quantas faltas pode ter em TCP?", "Faltei nos labs mas entreguei eles, nao sei se isso conta.", Arrays.asList(newTag));
		questionsList = db.search(null);
		
		for(int i = 0; i < questionsList.size(); i++){	
			
			boolean equalTitle = ((Question)questionsList.get(i)).getTitle() == "Alguem sabe quantas faltas pode ter em TCP?";
			boolean equalText = ((Question)questionsList.get(i)).getText() == "Faltei nos labs mas entreguei eles, nao sei se isso conta.";
			if(equalTitle && equalText){
				question = (Question)questionsList.get(i);
			}
		}
		
		contentServicesExample.editQuestion("Entreguei todos os labs, isso vale a presenca ne?", question);
		
		feedback = false;
		
		questionsList = db.search(null);
		for(int i = 0; i < questionsList.size(); i++){	
			boolean equalText = ((Question)questionsList.get(i)).getText() == "Entreguei todos os labs, isso vale a presenca ne?";
			
			if(equalText){
				question = (Question)questionsList.get(i);
				feedback = true;
			}
		}
		
		assertTrue(feedback);
		
		
		/** INTERAGINDO COM O CONTEUDO **/
		
		contentServicesExample = new ContentServicesImpl(db, exAdmin, check);
		contentServicesExample.answerQuestion(question, "Cadeiras de 4 creditos podem ter 7 faltas, na 8a o professor que decide.");
		feedback = false;
		
		if (contentServicesExample.viewAnswers(question).size() > 0)
			feedback = true;
		
		assertTrue(feedback);
		
	}

}
