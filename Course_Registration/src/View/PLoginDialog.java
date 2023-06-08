package View;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Global.Constants;
import Global.Locale;
import Service.SLogin;
import ValueObject.VAccount;
import View.Main.ActionExit;
import View.Main.ActionLogin;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private JPanel panelA;
	private JPanel panelB;
	
	private JTextField textField_ID;
	private JPasswordField textField_PW;
	
	private SLogin sLogin;
	private VAccount vAccount;
	
	private JDialog thisDialog;
	
	private int loginAttempt = 0;
	
	public PLoginDialog(ActionLogin actionExit, ActionExit actionLogin) {
		this.sLogin = new SLogin();
		
		this.thisDialog= this;
		
		this.setSize(Constants.CLoginDialog.WIDTH,Constants.CLoginDialog.HEIGHT); //화면 사이즈
		this.setLocationRelativeTo(null);
		this.setTitle(Locale.CMainFrame.TITLE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
														
		LayoutManager layoutManager = new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS);//Boxlayout(부모 컨테이너,나눌방향) , getContentPane은 최종 컨테이너 가져옴 최종 컨테이너는 가장 최상단의 부모 윈도우 즉 여기선 MainFrame의 JFrame 윈도우
		this.setLayout(layoutManager);
		
		//panel A
		panelA = new JPanel();

		LayoutManager layoutLoginInputPanel = new GridLayout(2,2,4,10); //행,열,좌우간격,상하간격 의 테이블형태의 GridLayout
		panelA.setLayout(layoutLoginInputPanel);
		
		JLabel label_ID = new JLabel(Locale.LLoginPanel.ID_LABEL);
		panelA.add(label_ID);//첫번째 패널에 추가
		
		this.textField_ID = new JTextField();
		this.textField_ID.setColumns(10); //10자내로 입력할수 있도록 텍스트필드 길이 조절
		panelA.add(this.textField_ID);
		
		JLabel label_PW = new JLabel(Locale.LLoginPanel.PASSWORD_LABEL);
		panelA.add(label_PW);
		
		this.textField_PW = new JPasswordField();
		this.textField_PW.setColumns(10);
		panelA.add(this.textField_PW);
		
		this.add(panelA);
		
		//panel B
		panelB = new JPanel();
		
		LayoutManager layoutPanelB = new BoxLayout(panelB,BoxLayout.X_AXIS);
		panelB.setLayout(layoutPanelB);
		
		
		JButton btn_SignUp = new JButton("SIGN UP");
		btn_SignUp.addActionListener(new ActionSignUp());
		panelB.add(btn_SignUp);
		
		panelB.add(Box.createHorizontalStrut(15));
		
		
		JButton btn_Find = new JButton("FIND");
		btn_Find.addActionListener(new ActionFind());
		panelB.add(btn_Find);
		
		panelB.add(Box.createHorizontalStrut(10));
		
		JButton btn_Exit = new JButton(Locale.LLoginPanel.EXIT_BUTTON);
		btn_Exit.addActionListener(actionLogin);
		panelB.add(btn_Exit);
		
		panelB.add(Box.createHorizontalStrut(10));
		
		JButton btn_Login = new JButton(Locale.LLoginPanel.LOGIN_BUTTON);
		this.getRootPane().setDefaultButton(btn_Login); //기본으로 선택되는 버튼으로 만들어 놓음
		btn_Login.addActionListener(actionExit);
		panelB.add(btn_Login);
		
		
		
		
		
		this.add(panelB);
		
		
		
		

		}
	
		public void loginCountLimit() {
			this.loginAttempt ++;
			if(this.loginAttempt>=5) {
				JOptionPane.showMessageDialog(this,Locale.LLoginPanel.LOGIN_LIMITED,Locale.LLoginPanel.DIALOG_ERROR,JOptionPane.ERROR_MESSAGE);
				System.exit(Constants.ZERO);
			}
		}
	
		

		VAccount login() {
			String id = this.textField_ID.getText();
			String pw = this.textField_PW.getText();
			
			vAccount = this.sLogin.login(id,pw);
			
			if(vAccount==null) {
				loginCountLimit();
				//JOptionPane.showMessageDialog(null,"메세지","타이틀","JOptionPane.아이콘 타입")
				JOptionPane.showMessageDialog(this,Locale.LLoginPanel.LOGIN_ERROR,Locale.LLoginPanel.DIALOG_ERROR,JOptionPane.ERROR_MESSAGE);	
				return null;
			}
			else { //vAccount!=null
				JOptionPane.showMessageDialog(this,Locale.LLoginPanel.LOGIN_SUCCESS_PREFIX+vAccount.getName()+Locale.LLoginPanel.LOGIN_SUCCESS_POSTFIX);
				loginAttempt = 0;
				return vAccount;
			}
		}
		
		public VAccount getVAccount() {
			return this.vAccount;
		}
		
		private class ActionSignUp implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				 
				 PSignUpDialog pSignUpDialog = new PSignUpDialog(thisDialog);
				 pSignUpDialog.setVisible(true);
		
			}
			
		}
		
		public class ActionFind implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
			
				PFindAccountDialog pFindAccountDialog = new PFindAccountDialog(thisDialog);
				pFindAccountDialog.setVisible(true);
			
			}
			
		}
		
		
}
