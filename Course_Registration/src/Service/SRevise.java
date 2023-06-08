package Service;

import Entity.ERevise;
import ValueObject.VAccount;

public class SRevise {
	ERevise eRevise;

	public SRevise() {
		eRevise = new ERevise();
	}
	
	public void updateUserInfo(VAccount vAccount, String address, String email, String phone) {
		eRevise.updateUserInfo(vAccount, address, email, phone);
	}

	public void updatePW(String oldPW, String newPW) {
		eRevise.updatePW(oldPW, newPW);
		
	}

}
