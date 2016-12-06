package project.DataBase;

public enum CombinationType {
	
	USER_NULL(1),
	ANSWER_QUESTION(2),
	QUESTION_NULL(3),
	COMMENT_QUESTION(4),
	COMMENT_ANSWER_QUESTION(5),
	USER_STRING(6),
	NOT_VALID_SEARCH(0);
	
	private int value;
	
	CombinationType(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}

}
