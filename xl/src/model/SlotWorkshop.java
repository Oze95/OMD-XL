package model;

import java.io.IOException;

public class SlotWorkshop {
	
	static Slot createSlot(String input) throws IOException {
		if (input.charAt(0) == '#') {
			return new CommentSlot(input);
		} else {
			try {
				return new ExprSlot(input);
			} catch (IOException e) {
				throw e;
			}
		}
	}
}
