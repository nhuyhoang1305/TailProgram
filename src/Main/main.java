package Main;

import java.io.IOException;

import v2.Enum.VersionType;
import v2.Handler.FileHandler;
import v2.Handler.FileHandlerFactory;
import v2.Input.Input;
import v2.Output.Output;

public class main {

	public final static int LINES = 5000;
	
	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis();
		if (args.length == 3) {
			VersionType version;
			if (args[0].equals("v1")) {
				version = VersionType.VERSION_1;
			}
			else if (args[0].equals("v2")) {
				version = VersionType.VERSION_2;
			}
			else {
				throw new RuntimeException("Invalid value of number (v1 or v2)." + args[0]);
			}

			String fileName =  args[1];
			int nLines = Integer.valueOf(args[2]);
			
			Input input = new Input(fileName, nLines);

			FileHandler fileHandler = FileHandlerFactory.getFileHandlerVersion(version);
			fileHandler.setInput(input);
			fileHandler.handleFile();
			
			// if line need read > LINES, print data line by line.
			if (nLines > LINES) {
				fileHandler.outputLineByLine();
			}
			else {
				Output output = fileHandler.outputFile();
				output.print();
			}
			
		}
		else {
			System.out.println("Usage: java -jar tail.jar <versionName> <fileName> <nLines>");
		}
		System.out.println("Execution Time : " + (double)(System.currentTimeMillis() - startTime)/1000);
	}

}
