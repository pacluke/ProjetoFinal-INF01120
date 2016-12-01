package project.services;

import project.domain.Answer;
import project.domain.Comment;
import project.domain.Question;
import project.domain.Tag;
import project.domain.User;

public class ContentServicesImpl implements ContentServices {

	@Override
	public Question addQuestion(String title, String Text, User user, Tag[] tags) {			//RENAN
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer answerQuestion(Question question, String Text, User user) {			//RENAN
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment addComment(String text, User user, Question question, Answer answer) {			//RENAN
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question selectBestAnswer(Question question, Answer answer) {			//RENAN
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question closeQuestion(User user, Question question) {			//RENAN
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question editQuestion(User user, Question question) {			//RENAN
		// TODO Auto-generated method stub
		return null;
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
	public Question[] searchQuestion(String text, String Category) {			//LISI
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question[] viewQuestions() {											//LISI
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment[] viewComments(Question question) {							//LISI
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer[] viewAnswers(Question question) {							//LISI
		// TODO Auto-generated method stub
		return null;
	}

}
