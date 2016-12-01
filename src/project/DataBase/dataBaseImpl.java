package project.DataBase;

import javax.xml.stream.events.Comment;

import project.domain.Answer;
import project.domain.Question;
import project.domain.User;

public class dataBaseImpl implements database{

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void FindUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question insertQuestion(Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer insertAnswer(Answer answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upDateQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question addComment(String text, User user, Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question editQuestion(Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeComment(Comment comment, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question insertBestAnswer(Question question) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}