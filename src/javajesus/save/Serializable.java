package javajesus.save;

/*
 * Java Jesus specific serializable interface
 * to allow proper saving and loading of entity data
 */
public interface Serializable {
	
	/**
	 * @return the ID byte
	 */
	public byte getId();
	
	/** First Byte: TYPE
	 *  Then Short: getX
	 *  Then Short: getY
	 *  Then Short: getMaxHealth
	 *  Then Byte: Extra
	 * @return the compressed data in a long
	 */
	public long getData();
	
}
