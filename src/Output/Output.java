package Output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Output {

	private BufferedWriter writer;
	private String message;
	public Output() {
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void print() throws IOException {
		writer.write(message);
		writer.flush();
	}

}