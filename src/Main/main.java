package Main;

import java.io.IOException;

import Enum.VersionType;
import Handler.FileHandler;
import Handler.FileHandlerFactory;
import Input.Input;
import Output.Output;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		if (args.length == 2) {
			//String path = "C:\\Users\\hh1305\\eclipse-workspace\\TailProgram\\text\\";
			String fileName =  args[0];
			int n = Integer.valueOf(args[1]);
			
			Input input = new Input(fileName, n);
			//System.out.println("Hello World");
			FileHandler fileHandler = FileHandlerFactory.getFileHandlerVersion(VersionType.VERSION_2);
			fileHandler.setInput(input);
			fileHandler.handleFile();
			
			Output output = fileHandler.outputFile();
			output.print();
		}
		else {
			System.out.println("Usage: %main <filename> <n>");
		}
		System.out.println("Execution Time : " + (double)(System.currentTimeMillis() - startTime)/1000);
	}

}
