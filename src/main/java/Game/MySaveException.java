package Game;

import java.io.IOException;

public class MySaveException extends IOException {
	
	public MySaveException() {
		super("Save failed.");
	}

}
