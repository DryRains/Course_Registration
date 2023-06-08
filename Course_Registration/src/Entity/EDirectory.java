package Entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import ValueObject.VDirectory;
import ValueObject.VHeader;

public class EDirectory {

	public Vector<VDirectory> getDirectories(String fileName, String type) {
		
		Vector<VDirectory> vDirectories = new Vector<VDirectory>();
		
		try {
			Scanner scanner = new Scanner (new File("directory/"+fileName));
			while(scanner.hasNext()) {
				VDirectory vDirectory = new VDirectory();
				
				//스캐너가 VO속으로 들어가서 알아서 읽도록 - key가 lecture나 미리담기 테이블일 경우 readLecture 실행
				if(type.equals("lecture")||type.equals("preload")||type.equals("regist")) {
					vDirectory.readLecture(scanner);
					}
				else{
					vDirectory.read(scanner);
					}
				
				vDirectories.add(vDirectory);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vDirectories;
	}

	public Vector<VHeader> getHeaders(String type) {
		Vector<VHeader> vHeaders = new Vector<VHeader>();
		String fileLocation;
		if(type.equals("lecture")||type.equals("preload")||type.equals("regist")) fileLocation = "lecture";
		else fileLocation = type;
		try {
			Scanner scanner = new Scanner (new File("directory/"+fileLocation+"/header"));
			while(scanner.hasNext()) {
				VHeader vHeader = new VHeader();
				vHeader.setHeader(scanner.next());
				vHeaders.add(vHeader);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return vHeaders;
	}

}
