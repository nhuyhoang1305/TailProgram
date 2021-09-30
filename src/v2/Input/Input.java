package v2.Input;

public class Input {

	private String fileName;
	private int n;

	public Input() {

	}

	public Input(String fileName, int n) {
		super();
		this.fileName = fileName;
		this.n = n;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}
