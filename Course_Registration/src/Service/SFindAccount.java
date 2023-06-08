package Service;

import Entity.EFindAccount;

public class SFindAccount {
		EFindAccount eFindAccount;

	public SFindAccount() {
		eFindAccount = new EFindAccount();
	}

	public String findPW(String id, String QA) {
		return eFindAccount.findPW(id,QA);
		
	}

	public String findID(String email, String name) {
		return eFindAccount.FindID(email, name);
		
	}

}
