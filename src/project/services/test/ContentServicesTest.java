package project.services.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import project.DataBase.*;
import project.domain.*;
import project.Permissions.*;
import project.services.*;

public class ContentServicesTest {
	
	User userAnon = new User();
	User userReg = new User("Arthur", "arthurL@gmail.com", 007, "jesus9", false, Credential.REGISTERED_USER);
	User userReg2 = new User("Tais", "tais@gmail.com", 007, "jesus9", false, Credential.REGISTERED_USER);
	User userMod = new User("Kath", "Kath@bol.com", 006, "helloe56", false, Credential.MODERATOR);
	User userAdm = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);	

	User[] user = new User[5];
	
	//check.Permission(user, Credential.REGISTERED_USER);
		
	DataBaseImpl database = new DataBaseImpl();;
	Permissions check = new Permissions();;
	
	
	ContentServicesImpl csAnon = new ContentServicesImpl(database, userAnon, check);
	
	ContentServicesImpl csUser = new ContentServicesImpl(database, userReg,  check);
	ContentServicesImpl csUser2 = new ContentServicesImpl(database, userReg2, check);
	ContentServicesImpl csMod = new ContentServicesImpl(database, userMod, check);
	ContentServicesImpl csAdm = new ContentServicesImpl(database, userAdm, check);
	
	
	
	//check.Permission(user, Credential.REGISTERED_USER);
	
	
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

	@SuppressWarnings("deprecation")
	@Test
	public void addQuestionTest() throws Exception{
		
		Question[] q = new Question[4];
		
		String[] title = new String[4];
		title[0] = "Minha Pergunta1";
		title[1] = "Minha Pergunta2";
		title[2] = "Minha Pergunta3";
		title[3] = "Minha Pergunta4";
		String text = "Ola, eu posso fazer pergunta?";
		
		q[0] = new Question(title[0], text, listaTag, user[0]);		// question can't be added since it's an anonymous user
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
			
			cs[j].addQuestion(title[j], text, listaTag);
			List<Object> questions = database.search(null);
			
			for(int i = 0; i < questions.size(); i++){
				if((((Question)questions.get(i)).getTitle() == q[j].getTitle()) && (((Question)questions.get(i)).getText() == q[j].getText()) && ((Question)questions.get(i)).getTags() == q[j].getTags() && (((Question)questions.get(i)).getDate().getDate() == q[j].getDate().getDate())){
					feedback[j] = true;
				}
			}
			assertTrue(feedback[j]);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void  answerQuestionTest() throws Exception{
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, user[1]);
		
		database.save(q1,  null, null);
		
		//cs[0].answerQuestion(q1, "Ola, sou usuario anonimo, posso responder perguntas?", user[0]);	//permission denied
		cs[1].answerQuestion(q1, "Ola, sou usuario registrado, posso responder perguntas?");
		cs[2].answerQuestion(q1, "Ola, sou moderador, posso responder perguntas?");
		cs[3].answerQuestion(q1, "Ola, sou admin, posso responder perguntas?");
		
		Answer[] a = new Answer[4];
		
		a[0] = new Answer (user[0], "Ola, sou usuario anonimo, posso responder perguntas?");	//permission denied
		a[1] = new Answer (user[1], "Ola, sou usuario registrado, posso responder perguntas?");
		a[2] = new Answer (user[2], "Ola, sou moderador, posso responder perguntas?");
		a[3] = new Answer (user[3], "Ola, sou admin, posso responder perguntas?");
		
		List<Answer> answers = new ArrayList<Answer>();
		boolean[] feedback = new boolean[4];
		feedback[1] = false;
		feedback[2] = false;
		feedback[3] = false;

		List<Object> questions = database.search(null);
			
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q1.getTitle()) && (((Question)questions.get(i)).getText() == q1.getText()) && (((Question)questions.get(i)).getDate().getDate() == q1.getDate().getDate())){
				answers = cs[1].viewAnswers((Question)questions.get(i));				
				
				
				for (int k = 1; k<4; k++){	
					for(int j = 0; j < answers.size(); j++){
						if (a[k].getText()==((Answer)answers.get(j)).getText())
						{
							feedback[k] = true;
						}
					}
					assertTrue(feedback[k]);
					
				}
				
				
				
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void  addCommentTest() throws Exception{
		
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, user[1]);
		database.save(q1,  null, null);
		
		Question q2 = new Question("Minha Pergunta2", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q2,  null, null);
		
		Answer a1 = new Answer (user[1], "Ola, sou usuario registrado, posso responder perguntas?");
		database.save(a1, q2, null);
		
			
		//cs[0].answerQuestion(q1, "Ola, sou usuario anonimo, posso responder perguntas?", user[0]);
		cs[1].addComment("Ola, sou usuario registrado, posso comentar em perguntas?", q1, null);
		cs[2].addComment("Ola, sou moderador, posso comentar em perguntas?", q1, null);
		cs[3].addComment("Ola, sou admin, posso comentar em perguntas?", q1, null);
		
		cs[1].addComment("Ola, sou usuario registrado, posso comentar em repostas tambem?", q2, a1);
		cs[2].addComment("Ola, sou moderador, posso comentar em repostas tambem", q2, a1);
		cs[3].addComment("Ola, sou admin, posso comentar em repostas tambem", q2, a1);
		
		Comment[] c = new Comment[8];
		
		//c[0] = new Comment("Ola, sou usuario registrado, posso responder perguntas?", user[0]);
		c[1] = new Comment("Ola, sou usuario registrado, posso comentar em perguntas?", user[1]);			
		c[2] = new Comment("Ola, sou moderador, posso comentar em perguntas?", user[2]);		
		c[3] = new Comment("Ola, sou admin, posso comentar em perguntas?", user[3]);
		
		c[5] = new Comment("Ola, sou usuario registrado, posso comentar em repostas tambem?", user[1]);			
		c[6] = new Comment("Ola, sou moderador, posso comentar em repostas tambem", user[2]);		
		c[7] = new Comment("Ola, sou admin, posso comentar em repostas tambem", user[3]);
		
		List<Comment> comments = new ArrayList<Comment>();
		List<Comment> commentsAnswers = new ArrayList<Comment>();
		
		boolean[] feedback = new boolean[8];
		feedback[1] = false;
		feedback[2] = false;
		feedback[3] = false;
		feedback[4] = false;
		feedback[5] = false;
		feedback[6] = false;
		feedback[7] = false;
		

		List<Object> questions = database.search(null);
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q1.getTitle()) && (((Question)questions.get(i)).getText() == q1.getText()) && (((Question)questions.get(i)).getDate().getDate() == q1.getDate().getDate())){
				comments = cs[1].viewComments((Question)questions.get(i), null);	
				
				for (int k = 1; k<4; k++){	
					for(int j = 0; j < comments.size(); j++){
						if (c[k].getText()==((Comment)comments.get(j)).getText())
						{
							feedback[k] = true;
						}
					}
					assertTrue(feedback[k]);
					
				}
			}
		}
				
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q2.getTitle()) && (((Question)questions.get(i)).getText() == q2.getText()) && (((Question)questions.get(i)).getDate().getDate() == q2.getDate().getDate())){
				commentsAnswers = cs[1].viewComments((Question)questions.get(i), a1);
				
				for (int k = 5; k<8; k++){	
					for(int j = 0; j < commentsAnswers.size(); j++){
						if (c[k].getText()==((Comment)commentsAnswers.get(j)).getText())
						{
							feedback[k] = true;
						}
					}
					assertTrue(feedback[k]);
					
				}
				
				
				
			}
		}
		
		
	} 


	
	
	@SuppressWarnings("deprecation")
	@Test
	public void  selectBestAnswerTest() throws Exception{
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);
		
		Question q2 = new Question("Minha Pergunta2", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);
		
		Question q3 = new Question("Minha Pergunta3", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);
		
		Answer a1 = new Answer (user[1], "Ola, sou usuario registrado, posso responder perguntas?");
		database.save(a1, q1, null);
		database.save(a1, q2, null);
		database.save(a1, q3, null);
		
		cs[1].selectBestAnswer(q1, a1);	//same user selecting best answer
		cs[2].selectBestAnswer(q2, a1);	//mod selecting best answer
		cs[3].selectBestAnswer(q3, a1);	//adm selecting best answer
		
		List<Object> questions = database.search(null);
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q1.getTitle()) && (((Question)questions.get(i)).getText() == q1.getText()) && (((Question)questions.get(i)).getDate().getDate() == q1.getDate().getDate())){
				
				assertTrue(a1.getText() == (((Question)questions.get(i)).getBestAnswer().getText()));
		
			}
		}
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q2.getTitle()) && (((Question)questions.get(i)).getText() == q2.getText()) && (((Question)questions.get(i)).getDate().getDate() == q2.getDate().getDate())){
				
				assertTrue(a1.getText() == (((Question)questions.get(i)).getBestAnswer().getText()));
		
			}
		}
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q3.getTitle()) && (((Question)questions.get(i)).getText() == q3.getText()) && (((Question)questions.get(i)).getDate().getDate() == q3.getDate().getDate())){
				
				assertTrue(a1.getText() == (((Question)questions.get(i)).getBestAnswer().getText()));
		
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void  closeQuestionTest() throws Exception{
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);
		
		Question q2 = new Question("Minha Pergunta2", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);

											//only mods and adms can close questions
		cs[2].closeQuestion(q1);	//mod selecting best answer
		cs[3].closeQuestion(q2);	//adm selecting best answer
		
		List<Object> questions = database.search(null);
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q1.getTitle()) && (((Question)questions.get(i)).getText() == q1.getText()) && (((Question)questions.get(i)).getDate().getDate() == q1.getDate().getDate())){
				
				assertFalse(((Question)questions.get(i)).getIsOpen());
		
			}
		}
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q2.getTitle()) && (((Question)questions.get(i)).getText() == q2.getText()) && (((Question)questions.get(i)).getDate().getDate() == q2.getDate().getDate())){
				
				assertFalse(((Question)questions.get(i)).getIsOpen());
		
			}
		}
		
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void editQuestionTest() throws Exception{
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);
		
		Question q2 = new Question("Minha Pergunta2", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);
		
		Question q3 = new Question("Minha Pergunta3", "Ola, sou usuario registrado, posso fazer pergunta?2", listaTag, user[1]);
		database.save(q1,  null, null);
		
		database.save(q1, null, null);
		database.save(q2, null, null);
		database.save(q3, null, null);
		
		cs[1].editQuestion("NOVO TEXTO MUDADISSIMO COM MUITA DIFEREN�A", q1);
		cs[2].editQuestion("NOVO TEXTO MUDADISSIMO COM MUITA DIFEREN�A2222", q2);
		cs[3].editQuestion("NOVO TEXTO MUDADISSIMO COM MUITA DIFEREN�A3333", q3);
		
		
		List<Object> questions = database.search(null);
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q1.getTitle()) && (((Question)questions.get(i)).getText() == q1.getText()) && (((Question)questions.get(i)).getDate().getDate() == q1.getDate().getDate())){
				
				assertTrue("NOVO TEXTO MUDADISSIMO COM MUITA DIFEREN�A" == (((Question)questions.get(i)).getText()));
		
			}
		}
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q2.getTitle()) && (((Question)questions.get(i)).getText() == q2.getText()) && (((Question)questions.get(i)).getDate().getDate() == q2.getDate().getDate())){
				
				assertTrue("NOVO TEXTO MUDADISSIMO COM MUITA DIFEREN�A2222" == (((Question)questions.get(i)).getText()));
		
			}
		}
		
		for(int i = 0; i < questions.size(); i++){
			if((((Question)questions.get(i)).getTitle() == q3.getTitle()) && (((Question)questions.get(i)).getText() == q3.getText()) && (((Question)questions.get(i)).getDate().getDate() == q3.getDate().getDate())){
				
				assertTrue("NOVO TEXTO MUDADISSIMO COM MUITA DIFEREN�A3333" == (((Question)questions.get(i)).getText()));
		
			}
		}
		
		
		
	}
	
	@Test
	
	public void removeCommentTest() throws Exception {		
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
		Answer a1 = new Answer( userReg, "Parece que sim!");
		
		Comment c1 = new Comment("Nao entendi sua pergunta.", userReg);
		Comment c2 = new Comment("Voce devia ser mais especifico.", userReg);
		
		Comment c3 = new Comment("Isso � um comentario!", userAdm);
		Comment c4 = new Comment("Isso tambem � um coment�rio!", userMod);
		Comment c5 = new Comment("Outro comentario genial de um usuario", userReg);
		
		
		List<Comment> comments = new ArrayList<Comment>();
		comments.add(c1);
		comments.add(c2);
		comments.add(c3);
		comments.add(c4);
		comments.add(c5);
		q1.setComments(comments);
		a1.setComments(comments);
		
		database.save(q1, null, null);
		database.save(a1, null, null);
		
		assertTrue(q1.getComments().size() == 5);
		
		csUser.removeComment(c1, q1, null);
		csUser.removeComment(c2, q1, null);
		
		assertTrue(q1.getComments().size() == 3);
	}
	
	@Test
    public void removeQuestionTest() throws Exception {			//LISI
			

    	Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
    	Question q2 = new Question("Minha Pergunta", "Ola, sou usuario registrado, excluir uma pergunta?", listaTag, userReg);
    	Question q3 = new Question("Minha Pergunta", "Ola, sou usuario moderador, posso excluir perguntas?", listaTag, userMod);
    	Question q4 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer comentario??", listaTag, userReg);
		
    	database.save(q1, null, null);
    	database.save(q2, null, null);
    	database.save(q3, null, null);
    	database.save(q4, null, null);
		
		boolean passou = true;
		
		csMod.removeQuestion(q1);
		
		
		List<Object> questions = database.search(null);
		Iterator<Object> questionsIter = (Iterator<Object>) questions.iterator();

		while(questionsIter.hasNext()){
			if(questionsIter.next() == q1)
				passou = false;
		}

		assertTrue(passou);
		
		csMod.removeQuestion(q3);
		passou = true;
		while(questionsIter.hasNext()){
			if(questionsIter.next() == q3)
				passou = false;
		}

		assertTrue(passou);
		
		
		csAdm.removeQuestion(q4);
		passou = true;
		while(questionsIter.hasNext()){
			if(questionsIter.next() == q4)
				passou = false;
		}

		assertTrue(passou);
		
	}

	@Test
    public void searchQuestionTest() throws Exception {
			
    	Tag tag = new Tag("Perguntas");
    	
    	List<Tag> listaTagAux = new ArrayList<>();
    	listaTagAux.add(tag);
    	
    	
    	Question q1 = new Question("Minha Pergunta", "Ola, sou usuario moderador, posso excluir perguntas?", listaTagAux, userMod);
    	Question q2 = new Question("Minha Pergunta", "Qual o dia das aulas de OrgB?", listaTag, userMod);
    	
    	database.save(q1, null, null);
    	database.save(q2, null, null);
    	
    	
    	List<Object> questions = csMod.searchQuestion("Ola, sou usuario moderador, posso excluir perguntas?", tag, null, null);
    	
    	assertTrue(questions.contains(q1));
    	assertFalse(questions.contains(q2));
		
	}
		
	@Test
	public void viewQuestionsTest() {											
		
		Question q1 = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userReg);
    	Question q2 = new Question("Minha Pergunta", "Ola, sou usuario registrado, excluir uma pergunta?", listaTag, userReg);
    	Question q3 = new Question("Minha Pergunta", "Ola, sou usuario moderador, posso excluir perguntas?", listaTag, userReg);
    	
		List<Question> allQuestions =  new ArrayList<>();
		allQuestions.add(q1);
		allQuestions.add(q2);
		allQuestions.add(q3);
		
		database.save(q1, null, null);
		database.save(q2, null, null);
		database.save(q3, null, null);
		
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
		Answer a2 = new Answer( userReg, "Acho que nao!");
		
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