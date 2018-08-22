package view;


public class ResourceHandler {
	
	private static ResourceHandler instance;
	
	public static ResourceHandler getInstance() {
		
		if (instance == null) 
			instance = new ResourceHandler();
		
		
		return instance;
		
		
	}

}
