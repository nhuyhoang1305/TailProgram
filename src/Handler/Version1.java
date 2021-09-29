package Handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import Input.Input;
import Node.LineNode;
import Output.Output;

public class Version1 extends FileHandler {

	private BufferedReader reader;
	protected Queue<LineNode> lineNodes = new LinkedList<>();

	public Version1() {
		super();
	}

	public Version1(Input input) {
		super(input);
	}

	@Override
	public void openFile() {
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleFile() {
		openFile();

		try {
			String line = "";
			while ((line = reader.readLine()) != null) {
				LineNode lineNode = new LineNode();
				lineNode.data = line;

				/*
				 * Nếu độ dài hàng đợi lớn hơn n thì xoá phần tử đầu và thêm phần tử sau vào.
				 */
				if (lineNodes.size() >= n) {
					lineNodes.remove();
					lineNodes.add(lineNode);
				} else {
					lineNodes.add(lineNode);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		Output output = new Output();

		StringBuilder sb = new StringBuilder();

		for (LineNode lineNode : lineNodes) {
			sb.append(lineNode.data).append('\n');
		}

		output.setMessage(sb.toString());

		return output;
	}

}
