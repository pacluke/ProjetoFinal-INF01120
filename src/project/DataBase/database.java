package project.DataBase;



import project.domain.*;

public interface database {
	
	
	public void initData();
	public void FindUser();
	public User saveUser(User user);
	public Question insertQuestion(Question question);
	public Answer insertAnswer(Answer answer, Question question);
	public void upDateQuestion(Question question);
	public Question addComment(Comment comment, Question question);
	public Question editQuestion(Question question);
	public void removeComment(Comment comment, User user);
	public void removeQuestion(Question question);
	public Question insertBestAnswer(Question question);
	

}
