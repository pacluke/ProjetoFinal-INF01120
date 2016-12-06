package project.DataBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import project.domain.*;

public class DataBaseImpl implements DataBase<Object>{
	
	private List<User> users = new ArrayList<User>();
	private List<Question> questions = new ArrayList<Question>();

	@Override
	public void initData() {
		
		
		
	}

	@Override
	public Object find(Object data1, Object data2) {		
		CombinationType search = new DataBaseImpl().getCombination(data1, data2, null);
		Object searchReturn = null;
		
		switch(search) {		
			case USER_STRING:
				for (Iterator<User> it = users.iterator(); it.hasNext(); ) {

				    User user = it.next();
				    if (user.getEmail().equals(data2)) {
				       searchReturn = user;
				    }
				}
				break;			
			default:
				break;		
		}	
		return searchReturn;
	}

	@Override
	public void save(Object data1, Object data2, Object data3) {
		CombinationType search = new DataBaseImpl().getCombination(data1, data2, data3);
		
		switch(search) {		
			case USER_NULL:		
				users.add((User) data1);
				break;
				
			case QUESTION_NULL:	
				questions.add((Question) data1);
				break;
				
			case ANSWER_QUESTION:
				for (Iterator<Question> it = questions.iterator(); it.hasNext(); ) {
				    Question question = it.next();
				    if (question == data2) {
				       List<Answer> answers = question.getAnswers();
				       List<Answer> answersAux = new ArrayList<Answer>();
				       if(answers != null){
							for (Iterator<Answer> ita = answers.iterator(); ita.hasNext(); ) {
								Answer answer = ita.next();
								answersAux.add(answer);
							}
				       }	
				       answersAux.add((Answer) data1);
				       question.setAnswers(answersAux);
				    }
				}
				break;
				
			case COMMENT_QUESTION:	
				for (Iterator<Question> it = questions.iterator(); it.hasNext(); ) {
				    Question question = it.next();
				    if (question == data2) {
				       List<Comment> comments = question.getComments();
				       List<Comment> commentsAux = new ArrayList<Comment>();
				       if(comments != null){
							for (Iterator<Comment> ita = comments.iterator(); ita.hasNext(); ) {
								Comment comment = ita.next();
								commentsAux.add(comment);
							}
				       }	
				       commentsAux.add((Comment) data1);
				       question.setComments(commentsAux);
				    }
				}
				break;
				
			case COMMENT_ANSWER_QUESTION:
				for (Iterator<Question> it = questions.iterator(); it.hasNext(); ) {
				    Question question = it.next();
				    if (question == data3) {
				       List<Answer> answers = question.getAnswers();
						for (Iterator<Answer> ita = answers.iterator(); ita.hasNext(); ) {
						    Answer answer = ita.next();
						    if (answer == data2) {
							       List<Comment> comments = answer.getComments();
							       List<Comment> commentsAux = new ArrayList<Comment>();
							       if(comments != null){
										for (Iterator<Comment> itc = comments.iterator(); itc.hasNext(); ) {
											Comment comment = itc.next();
											commentsAux.add(comment);
										}
							       }	
							       commentsAux.add((Comment) data1);
							       answer.setComments(commentsAux);
							    }
						    }
						}
				    }			
				break;

			default:
				break;		
		}	
	}

	@Override
	public CombinationType getCombination(Object data1, Object data2, Object data3) {
		
		if ((data1 instanceof User) && (data2 == null) && (data3 == null)){
			return CombinationType.USER_NULL;
		}
		
		else if ((data1 instanceof Answer) && (data2 instanceof Question) && (data3 == null)){
			return CombinationType.ANSWER_QUESTION;
		}
		
		else if ((data1 instanceof Question) && (data2 == null) && (data3 == null)){
			return CombinationType.QUESTION_NULL;
		}
		
		else if ((data1 instanceof Comment) && (data2 instanceof Answer) && (data3 instanceof Question)){
			return CombinationType.COMMENT_ANSWER_QUESTION;
		}
		
		else if ((data1 instanceof Comment) && (data2 instanceof Question) && (data3 == null)){
			return CombinationType.COMMENT_QUESTION;
		}
		
		else if ((data1 instanceof User) && (data2 instanceof String) && (data3 == null)){
			return CombinationType.USER_STRING;
		}
		
		return CombinationType.NOT_VALID_SEARCH;
	}

	@Override
	public List<Object> search(Object data1) {
		List<Object> searchReturn = new ArrayList<Object>();
		
		if(data1 == null){			
			searchReturn.addAll(questions);
		}
		
		else {

		
		for(int i = 0; i<questions.size(); i++){
			
			if(data1 instanceof User){				
				if (questions.get(i).getAuthor() == data1){
					searchReturn.add(questions.get(i));
				}
			}
			
			else if(data1 instanceof Tag){	
				
				for(int j = 0; j<questions.get(i).getTags().size(); j++){
					boolean feedback = false;
					if (questions.get(i).getTags().get(j) == data1){
						feedback = true;
					}
					if(feedback)
						searchReturn.add(questions.get(i));
				}
				
			}	
			else if(data1 instanceof String){
				if (questions.get(i).getTitle() == data1){
					searchReturn.add(questions.get(i));
				}				
			}
			
			else if(data1 instanceof Date){
				if (questions.get(i).getDate() == data1){
					searchReturn.add(questions.get(i));
					}				
				}	
			}
		}
		return searchReturn;
	}

	@Override
	public void remove(Object data1, Object data2, Object data3) {
		CombinationType search = new DataBaseImpl().getCombination(data1, data2, data3);
		switch(search) {		
			case USER_NULL:		
				users.remove(data1);
				break;
				
			case QUESTION_NULL:	
				questions.remove(data1);
				break;
				
			case ANSWER_QUESTION:
				for (Iterator<Question> it = questions.iterator(); it.hasNext(); ) {
				    Question question = it.next();
				    if (question == data2) {
				       List<Answer> answers = question.getAnswers();
						for (Iterator<Answer> ita = answers.iterator(); ita.hasNext(); ) {
						    Answer answer = ita.next();
						    if (answer == data1) {
						       ita.remove();
						    }
						}
				    }
				}
				break;
				
			case COMMENT_QUESTION:	
				for (Iterator<Question> it = questions.iterator(); it.hasNext(); ) {
				    Question question = it.next();
				    if (question == data2) {
				       List<Comment> comments = question.getComments();
						for (Iterator<Comment> itc = comments.iterator(); itc.hasNext(); ) {
						    Comment comment = itc.next();
						    if (comment == data1) {
						       itc.remove();
						    }
						}
				    }
				}
				break;
				
			case COMMENT_ANSWER_QUESTION:					
				for (Iterator<Question> it = questions.iterator(); it.hasNext(); ) {
				    Question question = it.next();
				    if (question == data3) {
				       List<Answer> answers = question.getAnswers();
						for (Iterator<Answer> ita = answers.iterator(); ita.hasNext(); ) {
						    Answer answer = ita.next();
						    if (answer == data2) {
							       List<Comment> comments = answer.getComments();
									for (Iterator<Comment> itc = comments.iterator(); itc.hasNext(); ) {
									    Comment comment = itc.next();
									    if (comment == data1) {
									       itc.remove();
									    }
									}
						       
						    }
						}
				    }
				}				
				break;
				
			default:
				break;	
		}
	}
}