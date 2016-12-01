package project.domain.test;

import static org.junit.Assert.*;

import org.junit.Test;

import project.domain.Tag;

import org.junit.Before;


public class TagTest {
	
	Tag tag1, tag2, tag3, tag4, tag5;
	
	/**
	 * @throws java.lang.Exception
	 */
	

	@Before
	public void setUp()throws Exception{
		tag1 = new Tag("Java");
		tag2 = new Tag("Swift");
		tag3 = new Tag("C++");
		tag4 = new Tag("Python");
		tag5 = new Tag("Objective-C");
	}
	
	@Test
	public void getTitleTest(){
		assertTrue(tag1.getTitle() == "Java");
		assertTrue(tag2.getTitle() == "Swift");
		assertTrue(tag3.getTitle() == "C++");
		assertTrue(tag4.getTitle() == "Python");
		assertTrue(tag5.getTitle() == "Objective-C");
	}
	
	@Test
	public void setTitleTest(){
		tag1.setTitle("C");
		tag2.setTitle("Scheme");
		tag3.setTitle("Ruby");
		tag4.setTitle("Haskell");
		tag5.setTitle("C#");
		
		assertTrue(tag1.getTitle() == "C");
		assertTrue(tag2.getTitle() == "Scheme");
		assertTrue(tag3.getTitle() == "Ruby");
		assertTrue(tag4.getTitle() == "Haskell");
		assertTrue(tag5.getTitle() == "C#");
	}

}
