package ca.javajesus.saves;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveFile {

	private String fileName;

	public SaveFile(String file) {
		this.fileName = "src/Saves/" + file + ".ser";
	}

	public boolean save(Object object) {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(fileName, false))) {

			out.writeObject(object);
			return true;

		} catch (IOException e) {
			System.out.println("There was a problem saving " + object.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public Object load() {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				fileName))) {
			return in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("There was a problem loading" + fileName);
			System.out.println(e.getMessage());
		}
		return null;
	}

}
