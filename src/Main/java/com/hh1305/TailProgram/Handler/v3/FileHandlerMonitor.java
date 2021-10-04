/**
 * 
 */
package main.java.com.hh1305.TailProgram.Handler.v3;

import java.io.IOException;

import main.java.com.hh1305.TailProgram.Input.Input;
import main.java.com.hh1305.TailProgram.Output.Output;
import main.java.com.hh1305.TailProgram.Handler.FileHandler;

public class FileHandlerMonitor extends FileHandler {

	private final static int TIMEWAIT = 1;
	private boolean isRunning = false;

	public FileHandlerMonitor() {
		super();
		isRunning = true;
	}

	public FileHandlerMonitor(Input input) {
		super(input);
		isRunning = true;
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
			System.out.println(e.toString());
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}

		closeFile();
	}

	@Override
	public Output outputFile() {
		return null;
	}

	@Override
	public void outputLineByLine() {

	}

}
