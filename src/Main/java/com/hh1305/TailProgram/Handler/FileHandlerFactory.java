package main.java.com.hh1305.TailProgram.Handler;

import main.java.com.hh1305.TailProgram.Enum.VersionType;
import main.java.com.hh1305.TailProgram.Handler.FileHandler;
import main.java.com.hh1305.TailProgram.Handler.v3.FileHandlerMonitor;
import main.java.com.hh1305.TailProgram.Handler.v1.FileHandlerVersion1;
import main.java.com.hh1305.TailProgram.Handler.v2.FileHandlerVersion2;
import main.java.com.hh1305.TailProgram.Handler.v3.FileHandlerVersion3;

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
