package main.java.com.hh1305.TailProgram.v1.Handler;

import main.java.com.hh1305.TailProgram.v1.Input.Input;
import main.java.com.hh1305.TailProgram.v1.Output.Output;

public abstract class FileHandler {
	protected String path;
	protected int n;

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
	public abstract void openFile();

	// handle file
	public abstract void handleFile();

	// close file
	public abstract void closeFile();

	// output
	public abstract Output outputFile();

}