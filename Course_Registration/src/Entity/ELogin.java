package Entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ValueObject.VAccount;

public class ELogin {
	private String id;
	private String password;
	private String name;
	private String department;
	private String address;
	private String email;
	private String phone;
	
	
	public ELogin() {
	}

	private boolean read(String id,String pw) {
		boolean found = false;
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext() && !found) {
				this.id = scanner.next();
				
				/*읽어올 때 복호화하여 읽어옴*/
				EEncryption encryption = new EEncryption();
				this.password = encryption.decrypt(scanner.next());
				
				this.name = scanner.next();
				this.department = scanner.next();
				this.address = scanner.next();
				this.email = scanner.next();
				this.phone = scanner.next();
				scanner.next(); //본인확인 질문 - 딱히 필요없으니까 변수에 안담고 스캐너만 소모해줌
				
				if (this.id.compareTo(id) == 0 && this.password.compareTo(pw) == 0) {
					found = true;
				}
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return found;
		
	}
	public VAccount getAccount(String id, String pw) {
		VAccount vAccount = null;
		// file read
		boolean found = this.read(id, pw);
		
		// vAccount에 데이터 적재
		if (found) {
			vAccount = new VAccount();
			vAccount.setId(this.id);
			vAccount.setPassword(this.password);
			vAccount.setName(this.name);
			vAccount.setDepartment(this.department);
			vAccount.setAddress(this.address);
			vAccount.setEmail(this.email);
			vAccount.setPhone(this.phone);
		}
		
		return vAccount;
	}

}
