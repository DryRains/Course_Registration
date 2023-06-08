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
				
				//��ĳ�ʰ� VO������ ���� �˾Ƽ� �е��� - key�� lecture�� �̸���� ���̺��� ��� readLecture ����
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
