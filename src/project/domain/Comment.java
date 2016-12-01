package project.domain;

public class Comment {
	
	private User author;
	private String text;
	
	public Comment(String text, User author){
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
	
}
