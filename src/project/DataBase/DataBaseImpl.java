package project.DataBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import project.domain.*;

public class DataBaseImpl implements DataBase<Object>{
	
	private List<User> users = new ArrayList<User>();
	private List<Question> questions = new ArrayList<Question>();

	@Override
	public void initData() {
		
		User registeredUser = new User("Arthur", "arthurL@gmail.com", 007, "jesus9", false, Credential.REGISTERED_USER);
		User registeredUser2 = new User("Tais", "tais@gmail.com", 007, "jesus9", false, Credential.REGISTERED_USER);
		User registeredUser3 = new User("Joao", "joao@gmail.com", 001, "qwerqwe", false, Credential.REGISTERED_USER);
		
		User moderatorUser = new User("Kath", "Kath@bol.com", 006, "helloe56", false, Credential.MODERATOR);
		User moderatorUser2 = new User("Marisa", "marisa@bol.com", 002, "ewrwer", false, Credential.MODERATOR);
		
		User userRegC = new User("Arthur", "arthurL@gmail.com", 007, "jesus9", false, Credential.REGISTERED_USER);
		User userAdmC = new User("Caroline", "Caroline@yahoo.com", 2314, "top555", false, Credential.ADMIN);
		User userRegB = new User("James", "james@gmail.com", 004, "ghu85", false, Credential.REGISTERED_USER);
		User userModB = new User("Bruna", "bruna@bol.com", 005, "uk4545r", true, Credential.MODERATOR);
		User userAdmB = new User("Lisa", "lisa@yahoo.com", 006, "wert5", false, Credential.ADMIN);
		User userAdmA = new User("Joana", "joana@yahoo.com", 003, "qweiuqweyrrqwe", false, Credential.ADMIN);

	/*	Answer answerA = new Answer(moderatorUser, "Creio que sim!");
		Answer answerB = new Answer(moderatorUser2, "36 créditos provavelmente.");
		Answer answerC = new Answer(registeredUser3, "Não sei, veja na página do curso.");
		Answer answerD = new Answer(userRegB, "Sim, pois o céu está nublado.");
		Answer answerE = new Answer(userRegC, "Começam antes do natal.");
		Answer answerF = new Answer(userRegC, "Não pode.");
		Answer answerG = new Answer(userAdmA, "Moderadores podem");
		Answer answerH = new Answer(userAdmB, "Creio que não.");

		Comment commentA = new Comment("Não entendi sua pergunta.", moderatorUser2);
		Comment commentB = new Comment("Essa pergunta já foi feita", moderatorUser2);
		Comment commentC = new Comment("Você devia ter colocado mais tags", moderatorUser2);
		Comment commentD = new Comment("A pergunta não se encaixa nessa categoria.", registeredUser3);
		Comment commentE = new Comment("Acho que essa pergunta já existe.", userRegB);
		Comment commentF = new Comment("Poderia reformular a pergunta?.", userRegB);
		Comment commentG = new Comment("Creio que a primeira resposta seja a certa.", userRegC);
		Comment commentH = new Comment("Creio que a segunda resposta seja a certa.", userAdmA);
		Comment commentI = new Comment("Creio que a segunda resposta seja a certa.", userAdmB);
		
	*/

		Tag tag1 = new Tag("Computador");
		Tag tag2 = new Tag("Java");
		Tag tag3 = new Tag("TCP");
		Tag tag4 = new Tag("Complexidade");
		Tag tag5 = new Tag("OrgB");
				
		List<Tag> listaTag = Arrays.asList(tag1, tag2, tag3, tag4, tag5);

		Question questionA = new Question("Como tratar exceções em Java?", "Alguém pode me explicar como implementar isso?",   listaTag, userRegC);

		Question questionB = new Question("Minha Pergunta", "Ola, sou usuario registrado, posso fazer pergunta?", listaTag, userRegB);

		Question questionC = new Question("Posso bloquear alguém?", "Usuário moderador pode bloquear?",   listaTag, moderatorUser2);

		Question questionD = new Question("Dúvida usuário registrado", "Usuário registrado pode excluir outras perguntas?", listaTag, registeredUser3);

		Question questionE = new Question("Minha Pergunta", "Ola, sou usuario moderador, posso excluir comentários?", listaTag, moderatorUser2);
		
		Question questionF = new Question("Dúvida créditos complementares", "Qual o número de créditos complementares da CIC?", listaTag, moderatorUser2);

		Question questionG = new Question("Dúvida créditos complementares", "Qual o número de créditos complementares da ECP?", listaTag, userAdmA);

		Question questionH = new Question("Minha Pergunta", "Ola, será que vai chover?", listaTag, userAdmB);

		Question questionI = new Question("Minha Pergunta", "Ola, que dia começam as férias?", listaTag, userAdmC);
		
		Question questionJ = new Question("Tem como ancelar 2016?", "Alguém pode me explicar como fazer isso?",   listaTag, userRegC);
		
		users.add(registeredUser);
		users.add(registeredUser2);
		users.add(registeredUser3);
		users.add(moderatorUser);
		users.add(moderatorUser2);	
		users.add(userAdmA);
		users.add(userAdmB);
		users.add(userAdmC);
		users.add(userRegB);
		users.add(userRegC);
		users.add(userModB);		
		
		questions.add(questionJ);
		questions.add(questionI);
		questions.add(questionH);
		questions.add(questionG);
		questions.add(questionF);
		questions.add(questionE);
		questions.add(questionD);
		questions.add(questionC);
		questions.add(questionB);
		questions.add(questionA);	
		
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