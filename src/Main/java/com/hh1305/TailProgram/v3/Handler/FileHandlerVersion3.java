package main.java.com.hh1305.TailProgram.v3.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;

import main.java.com.hh1305.TailProgram.v3.Input.Input;
import main.java.com.hh1305.TailProgram.v3.Output.Output;

public class FileHandlerVersion3 extends FileHandler {

	// assume: a line of line has 700 characters
	final static int LINESIZE = 700;

	private Queue<String> lines = new LinkedList<String>();
	private RandomAccessFile randomAccessFile;

	public FileHandlerVersion3() {
		super();
	}

	public FileHandlerVersion3(Input input) {
		super(input);

	}

	@Override
	public void openFile() {
		try {
			randomAccessFile = new RandomAccessFile(new File(path), "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleFile() {
		openFile();
		lines.clear();

		if (n > 0) {
			try {
				long fileLength = randomAccessFile.length();
				long seekPosition = fileLength - LINESIZE * n;
				int countLine;
				int increase = 50;
				if (seekPosition < 0) {
					seekPosition = 0;
				}
				if ((countLine = randomSeekCountLines(randomAccessFile, seekPosition)) < n) {
					seekPosition = fileLength - (LINESIZE + increase) * n;
					if (seekPosition < 0) {
						seekPosition = 0;
					}
					lines.clear();
					countLine = randomSeekCountLines(randomAccessFile, seekPosition);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		closeFile();
	}

	@Override
	public void closeFile() {

		try {
			randomAccessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Output outputFile() {

		Output output = new Output();
		StringBuilder builder = new StringBuilder();

		for (String line : lines) {
			builder.append(line).append('\n');
		}
		output.setMessage(builder.toString());

		return output;
	}

	@Override
	public void outputLineByLine() {
		Output output = new Output();

		for (String line : lines) {
			output.printLineByLine(line + "\n");
		}

	}

	public void pushToQueue(String line) {
		if (lines.size() == n) {
			lines.remove();
		}
		lines.add(line);
	}

	public int randomSeekCountLines(RandomAccessFile randomAccessFile, long seekPosition) {
		int countLines = 0;

		try {
			randomAccessFile.seek(seekPosition);
			StringBuilder builder = new StringBuilder();
			byte characters[] = new byte[1024];
			int readChars;

			while ((readChars = randomAccessFile.read(characters)) != -1) {

				for (int i = 0; i < readChars; ++i) {

					if ((char) characters[i] == '\n') {
						countLines++;
						pushToQueue(builder.toString());
						builder.setLength(0);
					} else {
						builder.append((char) characters[i]);
					}

				}

			}
			if (builder.length() > 0) {
				pushToQueue(builder.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return countLines;
	}

}
