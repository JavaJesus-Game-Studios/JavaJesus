package ca.javajesus.saves;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@SuppressWarnings("unused")
public class SerialWrite {
	private FileOutputStream fileOut;
	private ObjectOutputStream objectOut;
	private String path;
	
	public SerialWrite() throws IOException {
		if (System.getProperty("os.name").indexOf("Windows") >= 0) {
			path = "C:/Users/" + System.getProperty("user.name") + "/Documents/JavaJesus/save.ser";
		} else {
			path = "Your filepath is in another castle";
		}
		fileOut = new FileOutputStream(path);
		objectOut = new ObjectOutputStream(fileOut);
	}
	
	private void objectWrite(Object e) throws IOException {
		objectOut.writeObject(e);
	}
}
