package Service;
import Entity.ELogin;
import ValueObject.VAccount;

public class SLogin {
	private ELogin eLogin;
	public SLogin() {
		this.eLogin = new ELogin();
	}

	public VAccount login(String id, String pw) {
		VAccount vAccount = this.eLogin.getAccount(id, pw);
		return vAccount;
	}

}
