package main.java.com.hh1305.TailProgram.Handler.v1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import main.java.com.hh1305.TailProgram.Handler.FileHandler;
import main.java.com.hh1305.TailProgram.Input.Input;
import main.java.com.hh1305.TailProgram.Output.Output;

public class FileHandlerVersion1 extends FileHandler {

	protected Queue<String> lineNodes = new LinkedList<>();

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

				/*
				 * Nếu độ dài hàng đợi lớn hơn n thì xoá phần tử đầu và
				 * thêm phần tử sau vào.
				 */
				if (lineNodes.size() >= n) {
					lineNodes.remove();
					lineNodes.add(line);
				} else {
					lineNodes.add(line);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeFile();
	}

	@Override
	public Output outputFile() {
		Output output = new Output();

		StringBuilder sb = new StringBuilder();

		for (String lineNode : lineNodes) {
			sb.append(lineNode).append('\n');
		}

		output.setMessage(sb.toString());

		return output;
	}

	@Override
	public void outputLineByLine() {
		// TODO Auto-generated method stub
		
	}

}
