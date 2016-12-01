package project.services;

import java.util.List;

import project.domain.*;

interface ContentServices extends Services {
	
	public Question addQuestion(String title, String text, User user, List<Tag> tags);
	public Answer answerQuestion(Question question, String text, User user);
	public Comment addComment (String text, User user, Question question, Answer answer); 
	public Question selectBestAnswer(Question question, User user,  Answer answer);
	public Question closeQuestion(User user, Question question);
	public Question editQuestion(User user, String text, Question question);
	public boolean checkPermission(User user, Credential serviceCredential);
	public boolean removeComment(User user, Comment comment);	//returns false in case the user doesnt have the permission to remove it
	public boolean removeQuestion(User user, Question question);	//returns false in case the user doesnt have the permission to remove it
	public List<Question> searchQuestion(String text, String Category);
	public List<Question> viewQuestions(); //this is basically searchQuestion without any parameters, it should return ALL questions
	public List<Comment> viewComments(Question question);
	public List<Answer> viewAnswers(Question question);
}
