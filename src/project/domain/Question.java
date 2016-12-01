package project.domain;

import java.util.List;

public class Question {
	
	private String title;
	private Tag[] tags = new Tag[5];
	private User author;
	private List<Comment> comments;
	private Boolean isOpen;
	private String text;
	private Answer bestAnswer;
	
	public Question(String title, String text, List<Tag> tags, User author){
		this.title = title;
		//this.tags = tags;
		this.author = author;
		this.text = text;
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
//	public List<Tag> getTags() {
//		return tags;
//	}
//	
//	public void setTags(List<Tag> tags) {
//		this.tags = tags;
//	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Boolean getIsOpen() {
		return isOpen;
	}
	
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Answer getBestAnswer() {
		return bestAnswer;
	}
	
	public void setBestAnswer(Answer bestAnswer) {
		this.bestAnswer = bestAnswer;
	}

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		
		if(tags.length < 5)
			this.tags = tags;
		
		else
			for(int i = 0; i < 5; i++)
				this.tags[i] = tags[i];
		
	}
	
}
