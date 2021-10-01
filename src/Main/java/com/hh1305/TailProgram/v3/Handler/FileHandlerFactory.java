package main.java.com.hh1305.TailProgram.v3.Handler;

import main.java.com.hh1305.TailProgram.v3.Enum.VersionType;

public class FileHandlerFactory {

	private FileHandlerFactory() {

	}

	public static FileHandler getFileHandlerVersion(VersionType version) {

		switch (version) {
			case VERSION_1:
				return new FileHandlerVersion1();
			case VERSION_2:
				return new FileHandlerVersion2();
			case VERSION_3:
				return new FileHandlerVersion3();
			case MONITOR:
				return new FileHandlerMonitor();
			default:
				return null;
		}

	}

}
