package save;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * This class creates a file where the serialized objects are stored 
 */
public class SaveFile {

	private String fileName;

	/**
	 * @param file
	 *            The name of the file
	 */
	public SaveFile(String file) {
		this.fileName = "src/savedFiles/" + file + ".ser";
	}

	/**
	 * @param object
	 *            The object to serialize
	 * @return True if the object is saved successfully
	 */
	public boolean save(Object object) {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(fileName, false))) {

			out.writeObject(object);
			return true;

		} catch (IOException e) {
			System.out.println("There was a problem saving "
					+ object.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return True if the object is loaded successfully
	 */
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
