package project.services;

import project.domain.*;

interface ContentServices extends Services {
	
	public Question addQuestion(String title, String Text, User user, Tag[] tags);
	public Answer answerQuestion(Question question, String Text, User user);
	public Comment addComment (String text, User user, Question question, Answer answer); 
	public Question selectBestAnswer(Question question, Answer answer);
	public Question closeQuestion(User user, Question question);
	public Question editQuestion(User user, Question question);
	public boolean removeComment(User user, Comment comment);	//returns false in case the user doesnt have the permission to remove it
	public boolean removeQuestion(User user, Question question);	//returns false in case the user doesnt have the permission to remove it
	public Question[] searchQuestion(String text, String Category);
	public Question[] viewQuestions(); //this is basically searchQuestion without any parameters, it should return ALL questions
	public Comment[] viewComments(Question question);
	public Answer[] viewAnswers(Question question);
}
