package view;


public class ResourceHandler {
	
	/***
	 * Does absolutely nothing. The only reason for this class to exist is to have global access (through this class' instance)
	 * to the directory where the image and sound files folders are located
	 */
	
	private static ResourceHandler instance;
	
	public static ResourceHandler getInstance() {
		
		if (instance == null) 
			instance = new ResourceHandler();
		
		
		return instance;
	}

}
