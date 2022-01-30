package sports;

public class Rating {
	private String productName;
	private String userName;
	private int numStars;
	private String comment;
	public Rating(String productName, String userName, int numStars, String comment) {
		this.productName=productName;
		this.numStars=numStars;
		this.comment=comment;
		this.userName=userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getNumStars() {
		return numStars;
	}
	public void setNumStars(int numStars) {
		this.numStars = numStars;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return this.getNumStars() + " : " + this.getComment();
	}

	
	
	
	
}
