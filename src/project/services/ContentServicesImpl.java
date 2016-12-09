package project.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import project.DataBase.DataBaseImpl;
import project.domain.*;
import project.Permissions.*;

public class ContentServicesImpl implements ContentServices {
	
	public User actualUser;
	private final DataBaseImpl database;
	private final Permissions check;
	
	public ContentServicesImpl(DataBaseImpl database, User actualUser, Permissions check) {
		this.database = database;
		this.actualUser = actualUser;
		this.check = check;
	}

	@Override
	public void addQuestion(String title, String text, List<Tag> tags) throws Exception {			//RENAN
		Question q1 = new Question(title, text, tags, this.actualUser);
		
		check.Permission(this.actualUser, Credential.REGISTERED_USER);
		
		if(!this.actualUser.getIsBlocked())
			database.save(q1, null, null);
	}

	@Override
	public void answerQuestion(Question question, String text) throws Exception {			//RENAN

		Answer a1 = new Answer(this.actualUser, text);
		
		check.Permission(this.actualUser, Credential.REGISTERED_USER);
		
		if(question.getIsOpen()){	//user needs to be registered AND question needs to be open
			database.save(a1, question, null);
		}
	}

	@Override
	public void addComment(String text, Question question, Answer answer) throws Exception{			//RENAN
		
		Comment c1 = new Comment(text, this.actualUser);
		check.Permission(this.actualUser, Credential.REGISTERED_USER);
		
		if(question.getIsOpen()){	//user needs to be registered AND question needs to be open
			if(answer == null){
				database.save(c1, question, null);
			}
			else{
				database.save(c1, answer, question);
			}
		}
	}

	@Override
	public void selectBestAnswer(Question question, Answer answer) throws Exception {			//RENAN	
		check.Permission(this.actualUser, Credential.REGISTERED_USER);
		
		if(this.actualUser == question.getAuthor())	//question gets updated either if the person
		{															//doing it is the author or if it's a moderator or higher.
			database.remove(question, null, null);
			question.setBestAnswer(answer);
			database.save(question, null, null);
		}	
		
		else{
			check.Permission(this.actualUser, Credential.MODERATOR);
			database.remove(question, null, null);
			question.setBestAnswer(answer);
			database.save(question, null, null);
		}
		
	}

	@Override
	public void closeQuestion(Question question) throws Exception{			//RENAN
		check.Permission(this.actualUser, Credential.MODERATOR);
		
		database.remove(question, null, null);
		question.setIsOpen(false);
		database.save(question, null, null);
	}

	@Override
	public void editQuestion(String text, Question question) throws Exception{			//RENAN
		
		check.Permission(this.actualUser, Credential.REGISTERED_USER);
		
		if(this.actualUser == question.getAuthor()){	//question gets updated either if the person															//doing it is the author or if it's a moderator or higher.
			database.remove(question, null, null);
			question.setText(text);
			database.save(question, null, null);
		}
		
		else{
			check.Permission(this.actualUser, Credential.MODERATOR);
			database.remove(question, null, null);
			question.setText(text);
			database.save(question, null, null);
		}
	}
	@Override
	public void removeComment(Comment comment, Question question, Answer answer) throws Exception{			//LISI
		check.Permission(this.actualUser, Credential.REGISTERED_USER);
		
		if(this.actualUser == comment.getAuthor()){															
			if (answer == null){
				database.remove(comment, question, null);
			}
			
			else {
				database.remove(comment, answer, question);
			}
		}
		
		else {
			
			check.Permission(this.actualUser, Credential.MODERATOR);
			if (answer == null){
				database.remove(comment, question, null);
			}
		
			else {
				database.remove(comment, answer, question);
			}
		}

	}

	@Override
	public void removeQuestion(Question question) throws Exception{	
		//LISI
		if(this.actualUser == question.getAuthor()){	
			check.Permission(this.actualUser, Credential.REGISTERED_USER);
			database.remove(question, null, null);
		}
		
		
		else{
			check.Permission(this.actualUser, Credential.MODERATOR);
			database.remove(question, null, null);		
		}

	}

	@Override
	public List<Object> searchQuestion(String title, Tag tag, User author, Date date) {			//LISI
		
		List<Object> questionsReturn = new ArrayList<Object>();
		
		if (title != null){		
			questionsReturn.addAll(database.search(title));			
		}
		if (tag != null){		
			questionsReturn.addAll(database.search(tag));			
		}
		if (author != null){		
			questionsReturn.addAll(database.search(author));			
		}
		if (date != null){		
			questionsReturn.addAll(database.search(date));			
		}
		
		return questionsReturn;
	}
	
	@Override
	public List<Object> viewQuestions() {											//LISI
	
		List<Object> questions = database.search(null);
		return questions;
	}

	@Override
	public List<Comment> viewComments(Question question, Answer answer) {							//LISI
		List<Comment> comments;
		
		if(answer != null){
			comments = answer.getComments();
		}
		
		else{
			comments = question.getComments();
		}
		
		return comments;
	}

	@Override
	public List<Answer> viewAnswers(Question question) {							//LISI
		List<Answer> answers = question.getAnswers();
		return answers;
	}

}