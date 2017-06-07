package model;

import expr.Environment;

public class CommentSlot implements Slot {
	String comment;
	
	public CommentSlot(String comment) {
		this.comment = comment;
	}
	@Override
	public double value(Environment env) {
		return 0;
	}
	
	@Override
	public String toString() {
		return comment.substring(0);
	}
	
	@Override
	public String valueString(Environment env) {
		return comment.substring(1);
	}
	
	
}
