package Entity;

import java.io.File;
import java.util.Scanner;

public class EFindAccount {
	private String email;
	private String name;
	
	private String id;
	private String question;

	public EFindAccount() {
		// TODO Auto-generated constructor stub
	}
	
	public String FindID(String email, String name) {
		try {
			Scanner sc = new Scanner(new File("account/account"));
			while(sc.hasNext()) {
				//해당 이메일과 이름이 존재하면
				String row = sc.nextLine();
				if(row.contains(email)&&row.contains(name)){
					//그 행의 첫번째 컬럼이 id, 다섯번째 칼럼이 이메일이니 이 두개를 뽑아서 입력한 이름과 이메일과 일치하는지 검사
					String[] rowArray = row.split(" ");
					if(name.equals(rowArray[2])&&email.equals(rowArray[5])) {
						//일치한다면 아이디를 알려줌(반환)
						sc.close();
						return rowArray[0];
					}
					
					
				}
			}
			sc.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		
		return null;
	}


	public String findPW(String id, String QA) {
		try {
			Scanner sc = new Scanner(new File("account/account"));
			while(sc.hasNext()) {
				//한줄씩 읽어서 일치하는 아이디와 본인확인질문 발견되면
				String row = sc.nextLine();
				if(row.contains(id)&&row.contains(QA)){
					String[] rowArray = row.split(" ");
					if(id.equals(rowArray[0])&&QA.equals(rowArray[7])) {
					sc.close();
					return rowArray[1];
					}
				}
			}
			sc.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}

}
