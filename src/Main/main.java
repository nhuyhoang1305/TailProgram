package main;

import main.java.com.hh1305.TailProgram.Enum.VersionType;
import main.java.com.hh1305.TailProgram.Handler.FileHandlerFactory;
import main.java.com.hh1305.TailProgram.Handler.FileHandler;
import main.java.com.hh1305.TailProgram.Input.Input;

public class main {

	public final static int LINES = 5000;

	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();
		if (args.length == 2) {

			VersionType version;
			Input input = null;
			int nLines = -1;
			if (args[0].equals("-f")) {
				input = new Input(args[1]);
				version = VersionType.MONITOR;
			} else {
				nLines = Integer.valueOf(args[1]);
				input = new Input(args[0], nLines);
				version = VersionType.VERSION_3;
			}

			FileHandler fileHandler = FileHandlerFactory.getFileHandlerVersion(version);
			fileHandler.setInput(input);
			fileHandler.handleFile();

			if (nLines > -1) {
				if (nLines > LINES) {
					fileHandler.outputLineByLine();
				} else {
					fileHandler.outputFile().print();
				}
			}

		} else {
			System.out.println("Usage: java -jar tail.jar <fileName> <nLines>");
			System.out.println("Or: java -jar tail.jar -f <fileName>");
		}
		System.out.println("Execution Time : " + (double) (System.currentTimeMillis() - startTime) / 1000);
	}

}
