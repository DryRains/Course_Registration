package Entity;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ESignUp {
	private String id;
	private String pw;
	private String name;
	private String dept;
	private String address;
	private String email;
	private String phone;
	private String QA;
	

	public ESignUp(String id, String pw, String name, String dept, String si, String gu, String dong, String ho, String email, String phone,String QA) {
		this.id = id;
		
		/*pw�� ��ȣȭ�Ͽ� ����*/
		EEncryption encryption = new EEncryption();
		try {
			this.pw = encryption.encrypt(pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.name = name;
		this.dept = dept;
		this.address = si+"/"+gu+"/"+dong+"/"+ho;
		this.email = email;
		this.phone = phone;
		this.QA = QA;
	}
	
	public boolean isIdExisted() {
		try {
			Scanner sc = new Scanner(new File("account/account"));
			while(sc.hasNext()) {
				//���� �ش� ���̵� �̹� �����Ϳ� �����Ѵٸ�
				if(sc.nextLine().contains(this.id)){
					sc.close();
					return false;
				}
			}
			sc.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		//�������� ������ ���ο� �����ͷ� �߰�
		saveAccount();
		return true;
	}
	
	public void saveAccount() {
		String addedRow = this.id+" "+this.pw+" "+this.name+" "+this.dept+" "+this.address+" "+this.email+" "+this.phone+" "+this.QA;

        try{
            // ���� ��ü ����
            File file = new File("account/account");

            // true ������ ������ ���� ���뿡 �̾ �ۼ�
            FileWriter fw = new FileWriter(file, true);

            // ���Ͼȿ� ���ڿ� ����
            fw.write(addedRow+"\r\n");
            fw.flush();

            // ��ü �ݱ�
            fw.close();

        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
}
