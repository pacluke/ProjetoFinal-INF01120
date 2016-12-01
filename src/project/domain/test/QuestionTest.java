package project.domain.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import project.domain.*;

import org.junit.Before;


public class QuestionTest {
	
	Answer answer1, answer2;
	List<Answer> answerList = new ArrayList<Answer>();
	User user1, user2, user3, user4, user5, user6;
	Comment comment1, comment2, comment3, comment4;
	List<Comment> commentList = new ArrayList<Comment>();
	Tag tag1, tag2, tag3, tag4, tag5;
	List<Tag> tagList = new ArrayList<Tag>();
	List<Tag> tagList2 = new ArrayList<Tag>();
	Question question1, question2;
	
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp()throws Exception{
		user1 = new User("Marisa", "marisa@bol.com", 002, "987987asd", false, Credential.REGISTERED_USER);
		user2 = new User("Joana", "joana@yahoo.com", 003, "987987asd", false, Credential.ADMIN);
		user3 = new User("Alberto", "alberto@bol.com", 007, "987987asd", false, Credential.REGISTERED_USER);
		user4 = new User("Afrodite", "afrodite@hotmail.com", 309, "987987asd", false, Credential.MODERATOR);
		user5 = new User("Dennis", "dennis@yahoo.com", 254, "987987asd", false, Credential.REGISTERED_USER);
		user6 = new User("Florentina", "florentina@icloud.com", 192, "987987asd", false, Credential.REGISTERED_USER);
		
		comment1 = new Comment("Isso é um comentário!", user1);
		comment2 = new Comment("Isso também é um comentário!", user2);
		comment3 = new Comment("Outro comentario genial de um usuario", user3);
		comment4 = new Comment("Mais um comentario super legal", user4);
		
		commentList.add(comment1);
		commentList.add(comment2);
		commentList.add(comment3);
		commentList.add(comment4);
		
		answer1 = new Answer(user5, "A resposta disso esta aqui: google.com");
		answer2 = new Answer(user6, "Acho que esta certo porém errado mas ao mesmo tempo nao tenho certeza");
		
		answerList.add(answer1);
		answerList.add(answer2);
		
		tag1 = new Tag("Java");
		tag2 = new Tag("Swift");
		tag3 = new Tag("C++");
		tag4 = new Tag("Python");
		tag5 = new Tag("Objective-C");
		
		tagList.add(tag1);
		tagList.add(tag2);
		tagList.add(tag3);
		tagList.add(tag4);
		tagList.add(tag5);
		
		tagList.add(tag1);
		
		question1 = new Question("Qual a liguagem mais divertida?", "Nao sei, to querendo descobrir", tagList, user6);
		question1.setComments(commentList);
		question1.setBestAnswer(answer1);
		question1.setAnswers(answerList);
		
		question2 = new Question("Alguém tem o trabalho final de tcp em java pronto?", "TO quase rodando", tagList2, user6);
	}
	
	@Test
	public void getTitleTest(){
		assertTrue(question1.getTitle() == "Qual a liguagem mais divertida?");
		assertTrue(question2.getTitle() == "Alguém tem o trabalho final de tcp em java pronto?");
		
	}
	
	@Test
	public void setTitleTest(){
		question1.setTitle("Woooow");
		question2.setTitle("Yaaaaaaaaaay");
			
		assertTrue(question1.getTitle() == "Woooow");
		assertTrue(question2.getTitle() == "Yaaaaaaaaaay");
	}
	
	@Test
	public void getTextTest(){
		assertTrue(question1.getText() == "Nao sei, to querendo descobrir");
		assertTrue(question2.getText() == "TO quase rodando");
	}
	
	@Test
	public void setTextTest(){	
		question1.setText("Eitcha");
		question2.setText("OMG");
			
		assertTrue(question1.getText() == "Eitcha");
		assertTrue(question2.getText() == "OMG");
		
	}
	
	@Test
	public void getAuthorTest(){
		assertTrue(question1.getAuthor() == user6);
		assertTrue(question2.getAuthor() == user6);
		
	}
	
	@Test
	public void setAuthorTest(){
		question1.setAuthor(user1);
		question2.setAuthor(user2);
		
		assertTrue(question1.getAuthor() == user1);
		assertTrue(question2.getAuthor() == user2);
	}
	
	@Test
	public void getCommentsTest(){
		assertTrue(question1.getComments() == commentList);
		assertTrue(question2.getComments() == null);
		
	}
	
	@Test
	public void setCommentsTest(){
		question1.setComments(null);
		question2.setComments(commentList);
		
		assertTrue(question1.getComments() == null);
		assertTrue(question2.getComments() == commentList);		
	}
	
	@Test
	public void getIsOpenTest(){
		assertTrue(question1.getIsOpen() == true);
		assertTrue(question2.getIsOpen() == true);
	}
	
	@Test
	public void setIsOpenTest(){
		question1.setIsOpen(false);
		question2.setIsOpen(false);

		assertTrue(question1.getIsOpen() == false);
		assertTrue(question2.getIsOpen() == false);
	}
	
	@Test
	public void getBestAnswerTest(){
		assertTrue(question1.getBestAnswer() == answer1);
		assertTrue(question2.getBestAnswer() == null);
	}
	
	@Test
	public void setBestAnswerTest(){
		question1.setBestAnswer(null);
		question2.setBestAnswer(answer2);		
		
		assertTrue(question1.getBestAnswer() == null);
		assertTrue(question2.getBestAnswer() == answer2);
	}
	
	@Test
	public void getTagsTest(){
		assertTrue(question1.getTags() == tagList);
		assertTrue(question2.getTags() == tagList2);	
	}
	
	@Test
	public void setTagsTest(){
		question1.setTags(tagList2);
		question2.setTags(tagList);		
		
		assertTrue(question1.getTags() == tagList2);
		assertTrue(question2.getTags() == tagList);	
	}
	
	@Test
	public void getAnswersTest(){
		assertTrue(question1.getAnswers() == answerList);
		assertTrue(question2.getAnswers() == null);			
	}
	
	@Test
	public void setAnswersTest(){
		question1.setAnswers(null);
		question2.setAnswers(answerList);		
		
		assertTrue(question1.getAnswers() == null);
		assertTrue(question2.getAnswers() == answerList);	
	}
	
	
	

}
