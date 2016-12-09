package project.services;		//as

import java.util.Date;
import java.util.List;

import project.domain.*;

interface ContentServices extends Services {
	
	public void addQuestion(String title, String text, List<Tag> tags) throws Exception;//
	public void answerQuestion(Question question, String text) throws Exception;
	public void addComment (String text, Question question, Answer answer) throws Exception; 
	public void selectBestAnswer(Question question, Answer answer) throws Exception;
	public void closeQuestion(Question question) throws Exception;
	public void editQuestion(String text, Question question) throws Exception;
	public void removeComment(Comment comment, Question question, Answer answer) throws Exception;
	public void removeQuestion(Question question) throws Exception;
	public List<Object> searchQuestion(String title, Tag tag, User author, Date date);
	public List<Object> viewQuestions(); 
	public List<Comment> viewComments(Question question, Answer answer);
	public List<Answer> viewAnswers(Question question);
}
