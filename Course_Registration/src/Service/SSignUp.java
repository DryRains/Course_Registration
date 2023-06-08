package Service;

import Entity.ESignUp;

public class SSignUp {

	public SSignUp() {
		
	}

	public boolean createAccount(String id, String pw, String name, String dept, String si, String gu,
			String dong, String ho, String email, String phone,String QA) {
			ESignUp eSignUp = new ESignUp(id,pw,name,dept,si,gu,dong,ho,email,phone,QA);
			return eSignUp.isIdExisted();
		
	}

}
