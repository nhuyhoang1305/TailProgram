package main.java.com.hh1305.TailProgram.Handler.v1;

import java.io.IOException;

import main.java.com.hh1305.TailProgram.Handler.FileHandler;
import main.java.com.hh1305.TailProgram.Input.Input;

public class FileHandlerVersion1 extends FileHandler {

	public FileHandlerVersion1() {
		super();
	}

	public FileHandlerVersion1(Input input) {
		super(input);
	}

	@Override
	public void handleFile() {
		openFile();

		try {
			String line = "";
			while ((line = reader.readLine()) != null) {
				
				pushToQueue(line);
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeFile();
	}

}
