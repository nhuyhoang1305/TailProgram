package v2.Output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Output {

	private BufferedWriter writer;
	private String message;
	public Output() {
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	public Output(String fileName) throws IOException {
		writer = new BufferedWriter(new FileWriter(fileName));
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void printLineByLine(String lineCharacter) {
		try {
			writer.write(lineCharacter);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void print(String lines) {
		try {
			writer.write(lines);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void print() throws IOException {
		writer.write(message);
		writer.flush();
	}

}