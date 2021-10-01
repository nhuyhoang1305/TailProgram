/**
 * 
 */
package main.java.com.hh1305.TailProgram.v3.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import main.java.com.hh1305.TailProgram.v3.Input.Input;
import main.java.com.hh1305.TailProgram.v3.Output.Output;

public class FileHandlerMonitor extends FileHandler {

	private final static int TIMEWAIT = 1;
	private RandomAccessFile reader;
	private boolean isRunning = false;;

	public FileHandlerMonitor() {
		super();
		isRunning = true;
	}

	public FileHandlerMonitor(Input input) {
		super(input);
		isRunning = true;
	}

	@Override
	public void openFile() {
		try {
			reader = new RandomAccessFile(new File(path), "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleFile() {
		openFile();
		try {
			long seekPosition = reader.length();
			Output output = new Output();
			String line;
			while (isRunning) {
				Thread.sleep(TIMEWAIT * 1000);

				if (reader.length() > seekPosition) {
					reader.seek(seekPosition);
					while ((line = reader.readLine()) != null) {
						output.printLineByLine(line + "\n");
					}
					seekPosition = reader.length();
				} else if (reader.length() < seekPosition)
					seekPosition = reader.length();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
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
		return null;
	}

	@Override
	public void outputLineByLine() {

	}

}
