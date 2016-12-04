package project.services;		//as

import java.util.Date;
import java.util.List;

import project.domain.*;

interface ContentServices extends Services {
	
	public void addQuestion(String title, String text, User user, List<Tag> tags) throws Exception;
	public void answerQuestion(Question question, String text, User user) throws Exception;
	public void addComment (String text, User user, Question question, Answer answer) throws Exception; 
	public void selectBestAnswer(Question question, User user,  Answer answer) throws Exception;
	public void closeQuestion(User user, Question question) throws Exception;
	public void editQuestion(User user, String text, Question question) throws Exception;
	public void removeComment(User user, Comment comment, Question question, Answer answer) throws Exception;
	public void removeQuestion(User user, Question question) throws Exception;
	public List<Object> searchQuestion(String title, Tag tag, User author, Date date);
	public List<Object> viewQuestions(); 
	public List<Comment> viewComments(Question question, Answer answer);
	public List<Answer> viewAnswers(Question question);
}
