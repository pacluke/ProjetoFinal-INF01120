package project.domain;

import java.util.List;

public class Answer {
	
	private User author;
	private String text;
	private List<Comment> comments;
	
	public Answer(User author, String text){
		this.author = author;
		this.text = text;		
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
