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
		
		/*pw는 암호화하여 저장*/
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
				//만약 해당 아이디가 이미 데이터에 존재한다면
				if(sc.nextLine().contains(this.id)){
					sc.close();
					return false;
				}
			}
			sc.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		//존재하지 않으면 새로운 데이터로 추가
		saveAccount();
		return true;
	}
	
	public void saveAccount() {
		String addedRow = this.id+" "+this.pw+" "+this.name+" "+this.dept+" "+this.address+" "+this.email+" "+this.phone+" "+this.QA;

        try{
            // 파일 객체 생성
            File file = new File("account/account");

            // true 지정시 파일의 기존 내용에 이어서 작성
            FileWriter fw = new FileWriter(file, true);

            // 파일안에 문자열 쓰기
            fw.write(addedRow+"\r\n");
            fw.flush();

            // 객체 닫기
            fw.close();

        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
}
