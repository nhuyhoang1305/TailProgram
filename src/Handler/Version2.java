package Handler;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.RandomAccessFile;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import java.util.LinkedList;
import java.util.Queue;
import Input.Input;
import Node.LineNode;
import Output.Output;

public class Version2 extends FileHandler {

	private BufferedInputStream reader;
	protected Queue<LineNode> lineNodes = new LinkedList<>();

	public Version2() {
		super();
	}

	public Version2(Input input) {
		super(input);
	}

	@Override
	public void openFile() {
		try {
			reader = new BufferedInputStream(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleFile() {
		openFile();

		StringBuilder builder = new StringBuilder();
		try {
			File file = new File(path);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			FileChannel fileChannel = randomAccessFile.getChannel();
			MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

			for (int i = 0; i < buffer.limit(); i++) {
				byte read = buffer.get();
				if ((char) read == '\n') {
					LineNode lineNode = new LineNode();
					lineNode.data = builder.toString();
					builder.setLength(0);

					push(lineNode);
				} else {
					builder.append((char) read);
				}
			}

			if (builder.length() > 0) {
				LineNode lineNode = new LineNode();
				lineNode.data = builder.toString();
				builder.setLength(0);

				push(lineNode);
			}

			randomAccessFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// System.out.println(lineNodes.size());
		for (LineNode lineNode : lineNodes) {
			sb.append(lineNode.data).append('\n');
		}

		output.setMessage(sb.toString());

		return output;
	}

	private void push(LineNode lineNode) {
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

	@Override
	public void readFile() {
		RandomAccessFile aFile;
		try {
			aFile = new RandomAccessFile(path, "r");
			FileChannel inChannel = aFile.getChannel();
			MappedByteBuffer buffer = null;
			try {
				buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < buffer.limit(); i++) {
				byte read = buffer.get();
				System.out.print((char) read);

			}
			try {
				aFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
