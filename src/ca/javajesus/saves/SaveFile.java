package ca.javajesus.saves;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveFile {

	private String fileName;
	private Object object;
	
	public SaveFile(String file, Object object) {
		this.fileName = "src/Saves/" + file +".ser";
		this.object = object;
	}

	public void save() {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(fileName, false))) {
			
			out.writeObject(object);

		} catch (IOException e) {
			System.out.println("There was a problem saving " + object);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Object load() {

		try (ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(fileName))) {
			return in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("There was a problem loading " + object);
			System.out.println(e.getMessage());
		}
		return null;
	}

}
