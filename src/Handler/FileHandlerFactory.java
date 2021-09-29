package Handler;

import Enum.VersionType;

public class FileHandlerFactory {

	private FileHandlerFactory() {

	}

	public static FileHandler getFileHandlerVersion(VersionType version) {

		switch (version) {
			case VERSION_1:
				return new Version1();
			case VERSION_2:
				return new Version2();
			default:
				return null;
		}

	}

}
