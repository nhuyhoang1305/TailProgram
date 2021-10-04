package main.java.com.hh1305.TailProgram.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import main.java.com.hh1305.TailProgram.Input.Input;
import main.java.com.hh1305.TailProgram.Output.Output;

public abstract class FileHandler {
	
	protected String path;
	protected int n;
	protected RandomAccessFile reader;
	
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

	// output
	public abstract Output outputFile();

	// output line by line
	public abstract void outputLineByLine();
}

