package javajesus.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

import javax.imageio.ImageIO;

import utility.BufferUtils;

/*
 * Loads an image, extracts the pixels,
 * then creates an open GL Texture
 */
public class Texture {
	
	// width and height of the image in pixels
	private int width, height;
	
	// pointer to the texture object
	private int texture;
	
	/**
	 * Texture ctor()
	 * 
	 * @param path image filepath
	 */
	public Texture(String path) {
		texture = load(path);
	}
	
	/**
	 * load()
	 * Creates a texture object from a filepath
	 * 
	 * @param path image filepath
	 * @return a texture pointer
	 */
	private int load(String path) {
		
		// pixels of the image
		int[] pixels = null;
		
		try {
			
			// load the image into memory
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			
			// set the dimensions of the texture
			width = image.getWidth();
			height = image.getHeight();
			
			// set the number of pixels
			pixels = new int[width * height];
			
			// load the pixels of the image into memory
			// in ARGB format
			image.getRGB(0,  0, width, height, pixels, 0, width);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// temporary storage of pixels needed
		// to convert to ABGR format
		int[] data = new int[width * height];
		
		// extract the argb data in each pixel
		for(int i = 0; i < width * height; i++) {
			int a = (pixels[i] & 0xFF000000) >> 24;
			int r = (pixels[i] & 0xFF0000) >> 16;
			int g = (pixels[i] & 0xFF00) >> 8;
			int b = (pixels[i] & 0xFF);
			
			// store each pixel in abgr format
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		// create a texture pointer
		int result = glGenTextures();
		
		// activate the texture for use
		// (Selecting a layer in openGL)
		glBindTexture(GL_TEXTURE_2D, result);
		
		// disable antialiasing
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		// allows texture to be read by shaders
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));
		
		// deselect the texture
		glBindTexture(GL_TEXTURE_2D, 0);
		
		// return the result
		return result;
		
	}
	
	/**
	 * Bind the Texture for use
	 */
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	/**
	 * UnBind the Texture
	 */
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	/**
	 * @return pointer to the texture
	 */
	public int getID() {
		return texture;
	}

}
