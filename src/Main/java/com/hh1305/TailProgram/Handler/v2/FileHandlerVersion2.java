package main.java.com.hh1305.TailProgram.Handler.v2;

import java.io.IOException;
import java.util.Stack;

import main.java.com.hh1305.TailProgram.Input.Input;
import main.java.com.hh1305.TailProgram.Output.Output;
import main.java.com.hh1305.TailProgram.Handler.FileHandler;

public class FileHandlerVersion2 extends FileHandler {

	private Stack<String> lines = new Stack<String>();

	public FileHandlerVersion2() {
		super();
	}

	public FileHandlerVersion2(Input input) {
		super(input);
	}

	@Override
	public void handleFile() {
		openFile();

		StringBuilder builder = new StringBuilder();

		byte[] ch = new byte[1];
		long fileLength;
		try {
			fileLength = reader.length() - 1;
			int nLines = n;
			for (long pointer = fileLength; pointer >= 0 && nLines > 0; --pointer) {
				reader.seek(pointer);
				reader.read(ch);

				if ((char) ch[0] == '\n') {
					builder.reverse();
					lines.push(builder.toString());
					builder.setLength(0);
					--nLines;
				} else {
					builder.append((char) ch[0]);
				}
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		closeFile();
	}

	@Override
	public Output outputFile() {
		Output output = new Output();
		StringBuilder sb = new StringBuilder();

		while (!lines.empty()) {
			sb.append(lines.peek()).append('\n');
			lines.pop();
		}

		output.setMessage(sb.toString());

		return output;
	}

	public void outputLineByLine() {
		Output output = new Output();
		while (!lines.empty()) {
			output.printLineByLine(lines.peek() + "\n");
			lines.pop();
		}
	}

}
