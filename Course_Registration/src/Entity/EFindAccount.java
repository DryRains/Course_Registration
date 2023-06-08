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
				//�ش� �̸��ϰ� �̸��� �����ϸ�
				String row = sc.nextLine();
				if(row.contains(email)&&row.contains(name)){
					//�� ���� ù��° �÷��� id, �ټ���° Į���� �̸����̴� �� �ΰ��� �̾Ƽ� �Է��� �̸��� �̸��ϰ� ��ġ�ϴ��� �˻�
					String[] rowArray = row.split(" ");
					if(name.equals(rowArray[2])&&email.equals(rowArray[5])) {
						//��ġ�Ѵٸ� ���̵� �˷���(��ȯ)
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
				//���پ� �о ��ġ�ϴ� ���̵�� ����Ȯ������ �߰ߵǸ�
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
