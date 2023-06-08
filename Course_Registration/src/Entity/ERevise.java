package Entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import ValueObject.VAccount;

public class ERevise {

	public ERevise() {
	}
		
	
	public void updateUserInfo(VAccount vAccount, String address, String email, String phone) {
		String lagacyFilePath = "account/account";
		String newFilePath = "account/account_revised";
		
		String rowOfLagacy;
		try {
			//1. 파일 내용 수정하여 새로 만들기
			//이전 파일에서 읽어서
			BufferedReader br = new BufferedReader(new FileReader(lagacyFilePath));
			//파일 없으면 자동으로 생성하고 쓰기(이미 존재할경우 그곳을 밀고 다시 새로 씀)
			BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath));
			 
			while((rowOfLagacy = br.readLine()) != null) {
				rowOfLagacy = rowOfLagacy.replaceAll(vAccount.getAddress(), address);
				rowOfLagacy = rowOfLagacy.replaceAll(vAccount.getEmail(), email);
				rowOfLagacy = rowOfLagacy.replaceAll(vAccount.getPhone(), phone);
				bw.write(rowOfLagacy + "\r\n");
				bw.flush();
			}
			bw.close();
			br.close();
			
			//2.기존 파일은 지우고, 새로운 파일을 기존 파일경로로
			System.gc();
			System.runFinalization(); 
			
			File oldfile = new File(lagacyFilePath);
			oldfile.delete();
			
			File newfile = new File(newFilePath);
			newfile.renameTo(oldfile);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void updatePW(String oldPW, String newPW) {
		String encryptedPW = null;
		//우선 새로 입력한 비밀번호 암호화부터
		EEncryption encryption = new EEncryption();
		try {
			encryptedPW = encryption.encrypt(newPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String lagacyFilePath = "account/account";
		String newFilePath = "account/account_revised";
		
		String rowOfLagacy;
		try {
			BufferedReader br = new BufferedReader(new FileReader(lagacyFilePath));
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath));
			 
			while((rowOfLagacy = br.readLine()) != null) {
				rowOfLagacy = rowOfLagacy.replace(oldPW,encryptedPW);
				bw.write(rowOfLagacy + "\r\n");
				bw.flush();
			}
			bw.close();
			br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//2.기존 파일은 지우고, 새로운 파일을 기존 파일경로로
			/*나의 컴퓨터의 경우 어디선가 파일을 잡고있어서 다음과 같이 가비지컬렉션으로 한번 밀어줘야 아래의 삭제 메소드가 먹힘*/
			System.gc();
			System.runFinalization(); 
			File oldfile = new File(lagacyFilePath);
			oldfile.delete();
			
			File newfile = new File(newFilePath);
			newfile.renameTo(oldfile);
			
		
	}
		
}

