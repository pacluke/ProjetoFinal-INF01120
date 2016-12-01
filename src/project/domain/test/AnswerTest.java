package project.domain.test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.domain.Credential;
import project.domain.User;
import project.domain.Comment;
import project.domain.Answer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class AnswerTest {
	
	Answer answer1, answer2;
	User user1, user2, user3, user4, user5, user6;
	Comment comment1, comment2, comment3, comment4;
	List<Comment> commentList = new ArrayList<Comment>();
	
	/**
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp()throws Exception{		
		user1 = new User("Marisa", "marisa@bol.com", 002, "qwerqwe", false, Credential.REGISTERED_USER);
		user2 = new User("Joana", "joana@yahoo.com", 003, "qwerqwe", false, Credential.ADMIN);
		user3 = new User("Alberto", "alberto@bol.com", 007, "qwerqwe", false, Credential.REGISTERED_USER);
		user4 = new User("Afrodite", "afrodite@hotmail.com", 309, "qwerqwe", false, Credential.MODERATOR);
		user5 = new User("Dennis", "dennis@yahoo.com", 254, "qwerqwe", false, Credential.REGISTERED_USER);
		user6 = new User("Florentina", "florentina@icloud.com", 192, "qwerqwe", false, Credential.REGISTERED_USER);
		
		comment1 = new Comment("Isso é um comentário!", user1);
		comment2 = new Comment("Isso também é um comentário!", user2);
		comment3 = new Comment("Outro comentario genial de um usuario", user3);
		comment4 = new Comment("Mais um comentario super legal", user4);
		
		commentList.add(comment1);
		commentList.add(comment2);
		commentList.add(comment3);
		commentList.add(comment4);
		
		answer1 = new Answer(user5, "A resposta disso esta aqui: google.com");
		answer1.setComments(commentList);
		answer2 = new Answer(user6, "Acho que esta certo porém errado mas ao mesmo tempo nao tenho certeza");	
	}
	
	@Test
	public void getAuthorTest(){
		assertTrue(answer1.getAuthor() == user5);
		assertTrue(answer2.getAuthor() == user6);		
	}
	
	@Test
	public void setAuthorTest(){
		answer1.setAuthor(user1);
		answer2.setAuthor(user2);
		
		assertTrue(answer1.getAuthor() == user1);
		assertTrue(answer2.getAuthor() == user2);		
	}
	
	@Test
	public void getCommentsTest(){
		assertTrue(answer1.getComments() == commentList);
		assertTrue(answer2.getComments() == null);
		
	}
	
	@Test
	public void setCommentsTest(){
		
		answer1.setComments(null);
		answer2.setComments(commentList);
		
		assertTrue(answer1.getComments() == null);
		assertTrue(answer2.getComments() == commentList);	
	}
	
	@Test
	public void getTextTest(){
		assertTrue(answer1.getText() == "A resposta disso esta aqui: google.com");
		assertTrue(answer2.getText() == "Acho que esta certo porém errado mas ao mesmo tempo nao tenho certeza");
	}
	
	@Test
	public void setTextTest(){
		answer1.setText("Acho que esta certo porém errado mas ao mesmo tempo nao tenho certeza");
		answer2.setText("A resposta disso esta aqui: google.com");
		
		assertTrue(answer2.getText() == "A resposta disso esta aqui: google.com");
		assertTrue(answer1.getText() == "Acho que esta certo porém errado mas ao mesmo tempo nao tenho certeza");
	}
	

}
