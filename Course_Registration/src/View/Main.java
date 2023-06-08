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
			System.exit(Constants.ZERO);; //�ý��� ����
		}
		
	}
	 
	 class ActionLogout implements ActionListener{//�α׾ƿ� ��ư�׼� -> �α׾ƿ��� �ϸ� MainFrame�� �ݰ� dialog�� �����־�� �ϴ� ���� ��� �����Ҽ� �ִ� Main Ŭ�������� ����
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