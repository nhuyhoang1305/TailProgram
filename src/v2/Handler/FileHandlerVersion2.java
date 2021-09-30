package v2.Handler;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

import v2.Input.Input;
import v2.Output.Output;

public class FileHandlerVersion2 extends FileHandler {

	private BufferedInputStream reader;
	private Stack<String> lines = new Stack<String>();

	public FileHandlerVersion2() {
		super();
	}

	public FileHandlerVersion2(Input input) {
		super(input);
	}

	@Override
	public void openFile() {
		try {
			reader = new BufferedInputStream(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleFile() {
		openFile();

		StringBuilder builder = new StringBuilder();
		try {
			SeekableByteChannel seek = Files.newByteChannel(Paths.get(path));
			ByteBuffer byteBuffer = ByteBuffer.allocate(1);
			long fileLength = seek.size() - 1;
			seek.position(fileLength);

			int nLines = n;
			for (long pointer = fileLength; pointer >= 0 && nLines > 0; --pointer) {
				seek.position(pointer);
				seek.read(byteBuffer);
				byteBuffer.flip();

				if ((char) byteBuffer.array()[0] == '\n') {
					builder.reverse();
					lines.push(builder.toString());
					builder.setLength(0);
					--nLines;
				} else {
					builder.append((char) byteBuffer.array()[0]);
				}
				byteBuffer.clear();
			}
			seek.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		closeFile();
	}

	@Override
	public void closeFile() {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
