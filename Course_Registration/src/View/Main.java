package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Global.Constants;
import Global.Locale;
import ValueObject.VAccount;

public class Main {
	private PLoginDialog loginDialog;
	private PMainFrame mainFrame;
	
	private ActionLogin actionLogin;
	private ActionExit actionExit;
	private ActionLogout actionLogout;
	public Main() {
		
	}
	
	public void initilaize() {
		actionLogin = new ActionLogin();
		actionExit = new ActionExit();
		actionLogout = new ActionLogout();
		
		loginDialog = new PLoginDialog(actionLogin,actionExit);
		loginDialog.setVisible(true);
	}
	
	public void run() {
		VAccount vAccount = this.loginDialog.login();
		if(vAccount!=null) {
			loginDialog.dispose();
			mainFrame = new PMainFrame(vAccount, actionLogout);
			mainFrame.initialize();
		}
	}
	
	public void finish() {
		
	}
	
	class ActionLogin implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			run();
		}
		
	}
	
	 class ActionExit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(Constants.ZERO);; //시스템 종료
		}
		
	}
	 
	 class ActionLogout implements ActionListener{//로그아웃 버튼액션 -> 로그아웃을 하면 MainFrame을 닫고 dialog를 보여주어야 하니 둘을 모두 관리할수 있는 Main 클래스에서 관리
			public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, Locale.CONFIRM_LOGOUT, Locale.TITlE_CONFIRM_LOGOUT, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {
				}
				else if(result == JOptionPane.YES_OPTION) {
					mainFrame.saveUserLectures();
					mainFrame.dispose();
					Main main = new Main();
					main.initilaize();
				}	
				else {		
				}
						
			
			}
			
		}
	
	
	public static void main(String[] args) {	
	Main main = new Main();
	main.initilaize();
	//main.finish();
	
	
	}
}