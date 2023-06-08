package Service;
import java.util.Vector;

import Entity.EDirectory;
import ValueObject.VDirectory;
import ValueObject.VHeader;

public class SDirectory {
	private EDirectory eDirectory;
	
	public SDirectory() {
		eDirectory = new EDirectory();
	}
	
	public Vector<VDirectory> getDirectories(String fileName, String type){
		return eDirectory.getDirectories(fileName,type);	
	}

	public Vector<VHeader> getHeaders(String type) {
		return eDirectory.getHeaders(type);
	}
}
