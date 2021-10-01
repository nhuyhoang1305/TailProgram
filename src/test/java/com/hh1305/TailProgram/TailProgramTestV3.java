package test.java.com.hh1305.TailProgram;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.com.hh1305.TailProgram.v3.Handler.FileHandler;
import main.java.com.hh1305.TailProgram.v3.Input.Input;
import main.java.com.hh1305.TailProgram.v3.Handler.FileHandlerVersion3;

class TailProgramTestV3 {

	String filePath = "C:\\Users\\hh1305\\eclipse-workspace\\";
	String fileName = "version1.txt";
	int nLines = 3;
	Input input = new Input(filePath + fileName, nLines);

	FileHandler v3_fileHandler = new FileHandlerVersion3(input);
	String expected = "Nunc nisl elit, consequat vitae mollis ac, posuere sit amet diam. Morbi metus ante, euismod a consequat nec, lobortis id lectus. Sed ultrices tortor vel dignissim consectetur. Fusce tempus tellus ligula, eu laoreet arcu posuere in. Fusce gravida leo odio, quis auctor ex finibus eu. Cras quis justo iaculis, efficitur ligula sed, scelerisque lectus. Donec justo velit, convallis quis erat eu, fringilla viverra quam. Phasellus euismod aliquet odio ac ultricies. Etiam vel tincidunt ante. Suspendisse odio neque, mattis id tellus vitae, ultrices commodo elit. Mauris facilisis dui erat. Mauris fringilla feugiat nulla at placerat. Maecenas id luctus ante.\r\n"
			+ "Fusce egestas, tortor sit amet viverra tristique, magna ex venenatis est, vel iaculis leo nulla ac sem. Nullam fringilla congue malesuada. Nunc in dignissim odio. Phasellus at sem sit amet ex finibus lobortis et eu dui. In volutpat nulla ex, faucibus vehicula ante consequat a. Cras a turpis eget ex bibendum euismod in sit amet elit. Phasellus sed sapien sed sapien tincidunt consectetur semper eu nunc. Aenean imperdiet et nisi ac ornare. Sed eu nisl dolor. Proin nec nulla feugiat, luctus enim eget, bibendum orci.\r\n"
			+ "Duis nibh dolor, feugiat sed dictum vel, mollis ut nisi. Curabitur eleifend tempus diam, id gravida turpis tempus sed. Cras volutpat odio in nisl tempor egestas. Vivamus consectetur enim vel odio vulputate aliquet. Nunc ut rutrum nibh, a aliquam purus. Praesent et rhoncus enim. Maecenas sagittis enim id odio accumsan, non viverra massa hendrerit. Mauris congue vel nibh eu volutpat. Ut facilisis lorem non vestibulum rhoncus. Fusce ex est, porttitor et dictum sed, semper vitae ante. Mauris tempor imperdiet lacus, vitae ullamcorper dui cursus et.\r\n";

	@Test
	void test() {
		v3_fileHandler.handleFile();
		assertEquals(expected, v3_fileHandler.outputFile().getMessage());
	}

	@Test
	void test2() {
		v3_fileHandler.setN(0);
		v3_fileHandler.handleFile();
		assertEquals("", v3_fileHandler.outputFile().getMessage());
	}

}
