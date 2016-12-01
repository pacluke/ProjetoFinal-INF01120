package project.domain.test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.domain.Credential;
import project.domain.User;
import project.domain.Comment;

import org.junit.Before;


public class CommentTest {
	
	User user1;
	User user2;
	Comment comment1;
	Comment comment2;
	
	/**
	 * @throws java.lang.Exception
	 */
	

	@Before
	public void setUp()throws Exception{
		user1 = new User("Marisa", "marisa@bol.com", 002, "qwerqwe", false, Credential.REGISTERED_USER);
		user2 = new User("Joana", "joana@yahoo.com", 003, "qwerqwe", false, Credential.ADMIN);
		comment1 = new Comment("Isso é um comentário!", user1);
		comment2 = new Comment("Isso também é um comentário!", user2);
	}
	
	@Test	
	public void getAuthorTest(){
		assertTrue(comment1.getAuthor() == user1);
		assertTrue(comment2.getAuthor() == user2);
	}
	
	@Test
	public void setAuthorTest(){
		comment1.setAuthor(user2);
		comment2.setAuthor(user1);
		
		assertTrue(comment1.getAuthor() == user2);
		assertTrue(comment2.getAuthor() == user1);
	}
	
	@Test
	public void getTextTest(){
		assertTrue(comment1.getText() == "Isso é um comentário!");
		assertTrue(comment2.getText() == "Isso também é um comentário!");
	}
	
	@Test
	public void setTextTest(){
		comment1.setText("Isso também é um comentário!");
		comment2.setText("Isso é um comentário!");
		
		assertTrue(comment1.getText() == "Isso também é um comentário!");
		assertTrue(comment2.getText() == "Isso é um comentário!");
	}
	

}
