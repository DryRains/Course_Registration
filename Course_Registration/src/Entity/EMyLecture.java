package Entity;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import ValueObject.VAccount;
import ValueObject.VDirectory;

public class EMyLecture {

	public EMyLecture() {
		// TODO Auto-generated constructor stub
	}

	public void saveCurrentLecture(VAccount vAccount, Vector<String> lectures, String type) {
		String fileLocation;
		if(type.equals("preload")) fileLocation = "preLoadedLecture";
		else fileLocation = "registratedLecture";
		String newFilePath = "account/"+fileLocation+"/"+vAccount.getId();
		
		try (BufferedWriter newFileBuffer = new BufferedWriter(new FileWriter(newFilePath));){
			
			for(int i = 0; i < lectures.size(); i++) {
			newFileBuffer.write(lectures.get(i) + "\r\n");
			}
			newFileBuffer.close();
			} catch (IOException e) {
				
			}		
		}

	public Vector<VDirectory> getSavedLecture(VAccount vAccount, String type) {
		Vector<VDirectory> vDirectories = new Vector<VDirectory>();
		
		String fileLocation;
		if(type.equals("preload")) fileLocation = "preLoadedLecture";
		else fileLocation = "registratedLecture";
		String filePath = "account/"+fileLocation+"/"+vAccount.getId();
		try {
			Scanner scanner = new Scanner (new File(filePath));
			while(scanner.hasNext()) {
				VDirectory vDirectory = new VDirectory();
				vDirectory.readLecture(scanner);
				
				vDirectories.add(vDirectory);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println();
		}
		return vDirectories;	
		
		
	}

	
	public int[] getMyCredit(VAccount vAccount) {
		int currCredit = 0;
		int remainCredit = 0;
		int[] myCredits = new int[2];
		try (Scanner scanner = new Scanner(new File("account/registratedLecture/"+vAccount.getId()));) {
			
			while(scanner.hasNext()) {
				scanner.next();
				scanner.next();
				scanner.next();
				//이 메소드에서 필요한건 학점 필드뿐 .. 4번째 필드의 값만 변수에 담아줌
				currCredit = currCredit + Integer.parseInt(scanner.next());
				scanner.next();
			}
			scanner.close();
			//18학점에서 현재 내가 신청한 학점을 빼면 남은 신청가능학점
			remainCredit = 18 - currCredit;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}
		myCredits[0] = currCredit;
		myCredits[1] = remainCredit;
		return myCredits;
	}
}


