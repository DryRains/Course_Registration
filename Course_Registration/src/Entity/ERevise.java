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
			//1. ���� ���� �����Ͽ� ���� �����
			//���� ���Ͽ��� �о
			BufferedReader br = new BufferedReader(new FileReader(lagacyFilePath));
			//���� ������ �ڵ����� �����ϰ� ����(�̹� �����Ұ�� �װ��� �а� �ٽ� ���� ��)
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
			
			//2.���� ������ �����, ���ο� ������ ���� ���ϰ�η�
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
		//�켱 ���� �Է��� ��й�ȣ ��ȣȭ����
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
			
			//2.���� ������ �����, ���ο� ������ ���� ���ϰ�η�
			/*���� ��ǻ���� ��� ��𼱰� ������ ����־ ������ ���� �������÷������� �ѹ� �о���� �Ʒ��� ���� �޼ҵ尡 ����*/
			System.gc();
			System.runFinalization(); 
			File oldfile = new File(lagacyFilePath);
			oldfile.delete();
			
			File newfile = new File(newFilePath);
			newfile.renameTo(oldfile);
			
		
	}
		
}

