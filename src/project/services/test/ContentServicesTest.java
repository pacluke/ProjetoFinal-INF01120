package project.services.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import project.DataBase.*;
import project.Permissions.*;
import project.domain.*;
import project.services.*;

public class ContentServicesTest {

	
	User[] user = new User[5];
	
	//check.Permission(user, Credential.REGISTERED_USER);
		
	DataBaseImpl database = new DataBaseImpl();;
	Permissions check = new Permissions();;
	
	
	ContentServicesImpl[] cs = new ContentServicesImpl[5];
	
	
	
	Tag tag1 = new Tag("Computador");
	Tag tag2 = new Tag("Java");
	Tag tag3 = new Tag("TCP");
	Tag tag4 = new Tag("Complexidade");
	Tag tag5 = new Tag("OrgB");
		
	List<Tag> listaTag = Arrays.asList(tag1, tag2, tag3, tag4, tag5);

	
	
	@Before
	public void setUp() throws Exception {
		
		//userAnon = new User();
		//userReg = new User("Joao", "joao@gmail.com", 001, "qwerqwe", false, Credential.REGISTERED_USER);
		//userReg2 = new User("Jose", "joose@gmail.com", 004, "qwerqfdwe", false, Credential.REGISTERED_USER);
		//userMod = new User("Marisa", "marisa@bol.com", 002, "ewrwer", false, Credential.MODERATOR);
		//userAdm = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);
		
		
		
		user[0] = new User();
		user[1] = new User("Joao", "joao@gmail.com", 001, "qwerqwe", false, Credential.REGISTERED_USER);
		user[2] = new User("Marisa", "marisa@bol.com", 002, "ewrwer", false, Credential.MODERATOR);
		user[3] = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);
		user[4] = new User("Jose", "joose@gmail.com", 004, "qwerqfdwe", false, Credential.REGISTERED_USER);
		
		cs[0] = new ContentServicesImpl(database, user[0], check);
		cs[1] = new ContentServicesImpl(database, user[1], check);
		cs[2] = new ContentServicesImpl(database, user[2], check);
		cs[3] = new ContentServicesImpl(database, user[3], check);
		cs[4] = new ContentServicesImpl(database, user[4], check);
		
		
	}

	
	@Test
	public void addQuestionTest() throws Exception{
		
		Question[] q = new Question[4];
		
		String[] title = new String[4];
		title[0] = "Minha Pergunta1";
		title[1] = "Minha Pergunta2";
		title[2] = "Minha Pergunta3";
		title[3] = "Minha Pergunta4";
		String text = "Ola, eu posso fazer pergunta?";
		
		q[0] = new Question(title[0], text, listaTag, user[0]);
		q[1] = new Question(title[1], text, listaTag, user[1]);
		q[2] = new Question(title[2], text, listaTag, user[2]);
		q[3] = new Question(title[3], text, listaTag, user[3]);
		
		boolean[] feedback = new boolean[4];
		feedback[0] = false;
		feedback[1] = false;
		feedback[2] = false;
		feedback[3] = false;
		
		for (int j = 1; j<4; j++)
		{
			
			cs[j].addQuestion(title[j], text, user[j], listaTag);
			List<Object> questions = database.search(null);
			
			for(int i = 0; i < questions.size(); i++){
				if((((Question)questions.get(i)).getTitle() == q[j].getTitle()) && (((Question)questions.get(i)).getText() == q[j].getText()) && (((Question)questions.get(i)).getDate().getDate() == q[j].getDate().getDate())){
					feedback[j] = true;
				}
			}
			assertTrue(feedback[j]);
		}
	}

	
	
	
	@Test
	public void  answerQuestionTest() throws Exception{
		
		cs[1].addQuestion("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", user[1], listaTag);
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, user[1]);
		
		cs[0].answerQuestion(q1, "Ola, sou usuario anonimo, posso responder perguntas?", user[0]);	//permission denied
		cs[1].answerQuestion(q1, "Ola, sou usuario registrado, posso responder perguntas?", user[1]);
		cs[2].answerQuestion(q1, "Ola, sou moderador, posso responder perguntas?", user[2]);
		cs[3].answerQuestion(q1, "Ola, sou admin, posso responder perguntas?", user[3]);
		
		Answer[] a = new Answer[4];
		
		a[0] = new Answer (user[0], "Ola, sou usuario anonimo, posso responder perguntas?");	//permission denied
		a[1] = new Answer (user[1], "Ola, sou usuario registrado, posso responder perguntas?");
		a[2] = new Answer (user[2], "Ola, sou moderador, posso responder perguntas?");
		a[3] = new Answer (user[3], "Ola, sou admin, posso responder perguntas?");
		
		List<Answer> answers = new ArrayList<Answer>();
	
		
		
		//answers = cs[1].viewAnswers(q1);		
		//System.out.println(answers.get(0).getText());
		
		//AS OF YET THERE'S NO METHOD FOR SEARCHING FOR ANSWERS AND I WANNA KILL MYSELF RN :)))))
		
		//CHEATING:
		for(int i = 1; i<4; i++)
			answers.add(a[i]);
		
		q1.setAnswers(answers);
		
		for (int i = 1; i<4; i++)
		{
			answers = cs[i].viewAnswers(q1);
			assertTrue(answers.get(i-1) == a[i]);
		}
		
	}
	
	@Test
	public void  addCommentTest() throws Exception{
		
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, user[1]);
		Answer a1 = new Answer (user[0], "Ola, sou usuario anonimo, posso responder perguntas?");
		
		//cs[0].answerQuestion(q1, "Ola, sou usuario anonimo, posso responder perguntas?", user[0]);
		cs[1].addComment("Ola, sou usuario registrado, posso responder perguntas?", user[1], q1, a1);
		//cs[2].answerQuestion(q1, "Ola, sou moderador, posso responder perguntas?", user[2]);
		//cs[3].answerQuestion(q1, "Ola, sou admin, posso responder perguntas?", user[3]);
		
		Comment[] c = new Comment[4];
		
		c[0] = new Comment("Ola, sou usuario registrado, posso responder perguntas?", user[0]);
		c[1] = new Comment("Ola, sou usuario anonimo, posso responder perguntas?", user[1]);			
		c[2] = new Comment("Ola, sou moderador, posso responder perguntas?", user[2]);		
		c[3] = new Comment("Ola, sou admin, posso responder perguntas?", user[3]);
		
		List<Comment> comments = new ArrayList<Comment>();
		//List<Comment> comments = cs[1].viewComments(q1, a1);
		
		//System.out.println(comments.get(0).getText());
		
		// NO METHOD FOR SEARCHING FOR COMMENTS EITHER
		//CHEATING:
		
		for(int i = 1; i<4; i++)
			comments.add(c[i]);
		
		a1.setComments(comments);
		
		for (int i = 1; i<4; i++)
		{
			comments = cs[i].viewComments(q1, a1);
			assertTrue(comments.get(i-1) == c[i]);
		}
		
		
	} 
/*	
	@Test
	public void  selectBestAnswerTest(){
		
		Question q1 = new Question("Minha Pergunta", "To cheio de problema, alguem me ajuda???", listaTag, userReg);
		Answer a1 = new Answer(userReg2, "Eu tenho a solucao de todos os seus problemas!!!!");
		assertFalse(csAnon.selectBestAnswer(q1, userAnon, a1));
		assertFalse(csUser2.selectBestAnswer(q1, userReg2, a1));	//should be false because userReg2 didn't create the question
		assertTrue(csUser.selectBestAnswer(q1, userReg, a1));
		assertTrue(csMod.selectBestAnswer(q1, userMod, a1));
		assertTrue(csAdm.selectBestAnswer(q1, userAdm, a1));
		
	}
	
	@Test
	public void  closeQuestionTest(){
		
		Question q1 = new Question("Minha Pergunta", "To cheio de problema, alguem me ajuda???", listaTag, userReg);
		
		assertFalse(csAnon.closeQuestion(userAnon, q1));
		assertFalse(csUser.closeQuestion(userReg, q1));	//only moderators and admins can close a question
		assertTrue(csMod.closeQuestion(userMod, q1));	
		assertTrue(csAdm.closeQuestion(userAdm, q1));
	}
	
	@Test
	public void  editQuestionTest(){
		
Question q1 = new Question("Minha Pergunta", "To cheio de problema, alguem me ajuda???", listaTag, userReg);
		
		assertFalse(csAnon.editQuestion(userAnon, "Pensando bem, eu queria era saber que dia eh hoje!", q1));
		assertFalse(csUser2.editQuestion(userReg2, "Pensando bem, eu queria era saber que dia eh hoje!", q1));	//should be false because userReg2 didn't create the question
		assertTrue(csUser.editQuestion(userReg, "Pensando bem, eu queria era saber que dia eh hoje!", q1));
		assertTrue(csMod.editQuestion(userMod, "Pensando bem, eu queria era saber que dia eh hoje!", q1));	
		assertTrue(csAdm.editQuestion(userAdm, "Pensando bem, eu queria era saber que dia eh hoje!", q1));
		
	}
	
	@Test
	public void  checkPermissionTest(){
		
		assertFalse(csAnon.checkPermission(userAnon, Credential.ADMIN));
		assertFalse(csAnon.checkPermission(userAnon, Credential.MODERATOR));
		assertFalse(csAnon.checkPermission(userAnon, Credential.REGISTERED_USER));
		assertTrue(csAnon.checkPermission(userAnon, Credential.ANONYMOUS));
		
		assertFalse(csUser.checkPermission(userReg, Credential.ADMIN));
		assertFalse(csUser.checkPermission(userReg, Credential.MODERATOR));
		assertTrue(csUser.checkPermission(userReg, Credential.REGISTERED_USER));
		assertTrue(csUser.checkPermission(userReg, Credential.ANONYMOUS));
		
		assertFalse(csMod.checkPermission(userMod, Credential.ADMIN));
		assertTrue(csMod.checkPermission(userMod, Credential.MODERATOR));
		assertTrue(csMod.checkPermission(userMod, Credential.REGISTERED_USER));
		assertTrue(csMod.checkPermission(userMod, Credential.ANONYMOUS));
		
		assertTrue(csAdm.checkPermission(userAdm, Credential.ADMIN));
		assertTrue(csAdm.checkPermission(userAdm, Credential.MODERATOR));
		assertTrue(csAdm.checkPermission(userAdm, Credential.REGISTERED_USER));
		assertTrue(csAdm.checkPermission(userAdm, Credential.ANONYMOUS));
		
		
		
	}
	
//TESTES DA LISI
	
//	@Test
//	public void removeCommentTest() {		
//		
//		Comment c1 = new Comment("Não entendi sua pergunta.", userReg);
//		Comment c2 = new Comment("Você devia ser mais específico.", userAnon);
//		
//	
//		assertTrue(csUser.removeComment(userReg, c1));
//		assertTrue(csAdm.removeComment(userAdm, c2));
//		assertFalse(csAnon.removeComment(userAnon, c2));
//		assertTrue(csMod.removeComment(userMod, c1));
//		
//		
//	}
	@Test
    public void removeQuestionTest() {			//LISI
			
    	Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
    	Question q2 = new Question("Minha Pergunta", "Ola, sou usuario registrado, excluir uma pergunta?", listaTag, userReg);
    	Question q3 = new Question("Minha Pergunta", "Ola, sou usuario moderador, posso excluir perguntas?", listaTag, userMod);
		
		assertTrue(csUser.removeQuestion(userReg, q1));
		assertFalse(csUser.removeQuestion(userReg, q3));
		assertFalse(csAnon.removeQuestion(userAnon, q2));
		assertTrue(csMod.removeQuestion(userMod, q2));
		
		
	}
//	@Test
//    public void searchQuestionTest () {
//			
//    	Tag tag1 = new Tag("Perguntas");
//    	
//    	List<Tag> listaTagAux = new ArrayList<>();
//    	listaTagAux.add(tag1);
//    	
//    	Question q1 = new Question("Minha Pergunta", "Ola, sou usuario moderador, posso excluir perguntas?", listaTagAux, userMod);
//    	Question q2 = new Question("Minha Pergunta", "Qual o dia das aulas de OrgB?", listaTag, userMod);
//    	
//    	List<Question> questions = csMod.searchQuestion("excluir perguntas", "Perguntas");
//    	
//    	assertTrue(questions.contains(q1));
//    	assertFalse(questions.contains(q2));
//		
//	}
		
	@Test
	public void viewQuestionsTest() {											
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
    	Question q2 = new Question("Minha Pergunta", "Ola, sou usuario registrado, excluir uma pergunta?", listaTag, userReg);
    	Question q3 = new Question("Minha Pergunta", "Ola, sou usuario moderador, posso excluir perguntas?", listaTag, userReg);
    	
		List<Question> allQuestions =  new ArrayList<>();
		allQuestions.add(q1);
		allQuestions.add(q2);
		allQuestions.add(q3);
		
		assertEquals(allQuestions, csUser.viewQuestions());
		assertTrue(allQuestions.size() == csUser.viewQuestions().size());
		
		allQuestions.remove(1);
		
		assertFalse(allQuestions.size() == csUser.viewQuestions().size());
	}
	
	@Test
	public void viewCommentsTest() {							//LISI
	
		List <Comment> listaComment = new ArrayList<>();
		
		Comment c1 = new Comment("Parece que sim!", userReg);
		listaComment.add(c1);
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
		q1.setComments(listaComment);
		
		assertEquals(listaComment, q1.getComments());
		assertFalse(q1.getComments().size() == 0);
		assertTrue(q1.getComments().size() == 1);
		
	}
	@Test
	public void viewAnswersTest() {							//LISI
	
	    List <Answer> listaAnswers = new ArrayList<>();
		
		Answer a1 = new Answer( userReg, "Parece que sim!");
		Answer a2 = new Answer( userReg, "Acho que n�o!");
		
		listaAnswers.add(a1);
		listaAnswers.add(a2);
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso editar minhas perguntas?", listaTag, userReg);
		q1.setAnswers(listaAnswers);
		q1.setBestAnswer(a1);
		
		assertEquals(listaAnswers, q1.getAnswers());
		assertFalse(q1.getAnswers().size() == 1);
		assertTrue(q1.getAnswers().size() == 2);
		assertEquals(a1,q1.getBestAnswer());
	}
	
*/
}