package project.services;

import java.util.List;

import project.DataBase.dataBaseImpl;
import project.domain.Answer;
import project.domain.Comment;
import project.domain.Credential;
import project.domain.Question;
import project.domain.Tag;
import project.domain.User;

public class ContentServicesImpl implements ContentServices {
	
	
	public User actualUser;
	private final dataBaseImpl database;

	//Credential c1 = new Credential(1);
	

	public ContentServicesImpl(dataBaseImpl database, User actualUser) {
		this.database = database;
		this.actualUser = actualUser;
	}

	@Override
	public boolean addQuestion(String title, String text, User user, List<Tag> tags) {			//RENAN
		

		Question q1 = new Question(title, text, tags, user);
		
		if(checkPermission(user, Credential.REGISTERED_USER))
		{
			database.insertQuestion(q1);
			return true;
		}
		
		else
		return false;		
		
	}

	@Override
	public boolean answerQuestion(Question question, String text, User user) {			//RENAN

		Answer a1 = new Answer(user, text);
		
		if((checkPermission(user, Credential.REGISTERED_USER)) && (question.getIsOpen()))	//user needs to be registered AND question needs to be open
		{
			database.insertAnswer(a1, question);
			return true;
		}
		
		else
		return false;
	}

	@Override
	public boolean addComment(String text, User user, Question question, Answer answer) {			//RENAN
		
		Comment c1 = new Comment(text, user);
		
		if((checkPermission(user, Credential.REGISTERED_USER)) && (question.getIsOpen()))	//user needs to be registered AND question needs to be open
		{
			database.addComment(c1, question);
			return true;
		}
		
		else
		return false;
	}

	@Override
	public boolean selectBestAnswer(Question question, User user, Answer answer) {			//RENAN
		
		
		question.setBestAnswer(answer);
		
		if((user == question.getAuthor()) ||  (checkPermission(user, Credential.MODERATOR)) )	//question gets updated either if the person
		{															//doing it is the author or if it's a moderator or higher.
			database.upDateQuestion(question);
			return true;
		}
		
		else
		return false;		
		
	}

	@Override
	public boolean closeQuestion(User user, Question question) {			//RENAN
		
		question.setIsOpen(false);
		
		if(checkPermission(user, Credential.MODERATOR))	//question gets updated only if a moderator or higher is doing it
		{															
			database.upDateQuestion(question);
			return true;
		}
		
		else
		return false;	
	}

	@Override
	public boolean editQuestion(User user, String text, Question question) {			//RENAN
		question.setText(text);
		
		if((user == question.getAuthor()) ||  (checkPermission(user, Credential.MODERATOR)) )	//question gets updated either if the person
		{															//doing it is the author or if it's a moderator or higher.
			database.upDateQuestion(question);
			return true;
		}
		
		else
		return false;
	}
	
	@Override
	public boolean checkPermission(User user, Credential serviceCredential) {		//RENAN
		
		if (user.getCredential().getValue() >= serviceCredential.getValue())	//gets permission (true) if its credentials are equal
			return true;										//or better than the ones needed.
		else
			return false;
	}

	@Override
	public boolean removeComment(User user, Comment comment) {			//LISI
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeQuestion(User user, Question question) {			//LISI
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Question> searchQuestion(String text, String Category) {			//LISI
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> viewQuestions() {											//LISI
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> viewComments(Question question) {							//LISI
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> viewAnswers(Question question) {							//LISI
		// TODO Auto-generated method stub
		return null;
	}

}
