package main.java.com.hh1305.TailProgram.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;

import main.java.com.hh1305.TailProgram.Input.Input;
import main.java.com.hh1305.TailProgram.Output.Output;

public abstract class FileHandler {
	
	protected String path;
	protected int n;
	protected RandomAccessFile reader;
	protected Queue<String> lines = new LinkedList<>();
	
	public FileHandler() {

	}

	public FileHandler(Input input) {
		this.path = input.getFileName();
		this.n = input.getN();
	}

	public void setInput(Input input) {
		this.path = input.getFileName();
		this.n = input.getN();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	// open file
	protected void openFile() {
		try {
			reader = new RandomAccessFile(new File(path), "r");
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	// handle file
	public abstract void handleFile();

	// close file
	protected void closeFile() {
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	// Nếu độ dài trong queue = n, xoá phần tử đầu rồi mới thêm text vào hàng đợi
	protected void pushToQueue(String line) {
		if (lines.size() >= n) {
			lines.remove();
			lines.add(line);
		} else {
			lines.add(line);
		}
	}
	
	// output
	public Output outputFile() {

		Output output = new Output();
		StringBuilder builder = new StringBuilder();

		for (String line : lines) {
			builder.append(line).append('\n');
		}
		output.setMessage(builder.toString());

		return output;
	}

	// output line by line
	public void outputLineByLine() {
		Output output = new Output();

		for (String line : lines) {
			output.printLineByLine(line + "\n");
		}

	}

}

