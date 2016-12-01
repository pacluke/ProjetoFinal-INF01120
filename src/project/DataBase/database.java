package project.DataBase;

import javax.xml.stream.events.Comment;

import project.domain.Answer;
import project.domain.Question;
import project.domain.User;

public interface database {
	
	
	public void initData();
	public void FindUser();
	public User saveUser(User user);
	public Question insertQuestion(Question question);
	public Answer insertAnswer(Answer answer);
	public void upDateQuestion(Question question);
	public Question addComment(String text, User user, Question question);
	public Question editQuestion(Question question);
	public void removeComment(Comment comment, User user);
	public void removeQuestion(Question question);
	public Question insertBestAnswer(Question question);
	

}
