package project.services.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import project.DataBase.DataBaseImpl;
import project.domain.*;
import project.services.*;

public class ContentServicesTest {

	
	User userAnon;
	User userReg;
	User userReg2;
	User userMod;
	User userAdm;	
		
	DataBaseImpl database;
	
	ContentServicesImpl csAnon = new ContentServicesImpl(database, userAnon);
	ContentServicesImpl csUser = new ContentServicesImpl(database, userReg);
	ContentServicesImpl csUser2 = new ContentServicesImpl(database, userReg2);
	ContentServicesImpl csMod = new ContentServicesImpl(database, userMod);
	ContentServicesImpl csAdm = new ContentServicesImpl(database, userAdm);
	
	Tag tag1 = new Tag("Computador");
	Tag tag2 = new Tag("Java");
	Tag tag3 = new Tag("TCP");
	Tag tag4 = new Tag("Complexidade");
	Tag tag5 = new Tag("OrgB");
		
	List<Tag> listaTag = Arrays.asList(tag1, tag2, tag3, tag4, tag5);

	
	
	@Before
	public void setUp() throws Exception {
		
		userAnon = new User();
		userReg = new User("Joao", "joao@gmail.com", 001, "qwerqwe", false, Credential.REGISTERED_USER);
		userReg2 = new User("Jose", "joose@gmail.com", 004, "qwerqfdwe", false, Credential.REGISTERED_USER);
		userMod = new User("Marisa", "marisa@bol.com", 002, "ewrwer", false, Credential.MODERATOR);
		userAdm = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);
		
		
	}

	@Test
	public void addQuestionTest(){
	
		assertFalse(csAnon.addQuestion("Minha Pergunta", "Ola, sou usuario anonimo, posso fazer pergunta?", userAnon, listaTag));
		assertTrue(csUser.addQuestion("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", userReg, listaTag));
		assertTrue(csMod.addQuestion("Minha Pergunta", "Ola, sou  moderador, posso fazer pergunta?", userMod, listaTag));
		assertTrue(csAdm.addQuestion("Minha Pergunta", "Ola, sou admin, posso fazer pergunta?", userAdm, listaTag));
	}
	
	
	@Test
	public void  answerQuestionTest(){

		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
		
		assertFalse(csAnon.answerQuestion(q1, "Ola, sou usuario anonimo, posso responder perguntas?", userAnon));
		assertTrue(csUser.answerQuestion(q1, "Ola, sou usuario registrado, posso responder perguntas?", userReg));
		assertTrue(csMod.answerQuestion(q1, "Ola, sou moderador, posso responder perguntas?", userMod));
		assertTrue(csAdm.answerQuestion(q1, "Ola, sou admin, posso responder perguntas?", userAdm));
		
	}
	
	@Test
	public void  addCommentTest(){
		
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
		Answer a1 = new Answer(userReg, "Parece que sim! Sera que posso responder tambem?");
		
		assertFalse(csAnon.addComment("Ola, sou usuario anonimo, posso comentar aqui?", userAnon, q1, a1));
		assertTrue(csUser.addComment("Ola, sou usuario registrado, posso comentar aqui?", userReg, q1, a1));
		assertTrue(csMod.addComment("Ola, sou moderador, posso responder perguntas?", userMod, q1, a1));
		assertTrue(csAdm.addComment("Ola, sou admin, posso responder perguntas?", userAdm, q1, a1));
	} 
	
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
	

}
