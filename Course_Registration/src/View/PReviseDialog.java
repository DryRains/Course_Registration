package View;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Global.Constants;
import Global.Locale;
import Service.SRevise;
import ValueObject.VAccount;

public class PReviseDialog extends JDialog {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	private VAccount vAccount;
	private String address;
	private String email;
	private String phone;
	
	private JTextField si_Tf;
	private JTextField gu_Tf;
	private JTextField dong_Tf;
	private JTextField ho_Tf;
	private JTextField email_Tf;
	private JTextField phone_Tf;
	

	public PReviseDialog(VAccount vAccount, String Si, String Gu, String Dong, String Ho, String Email, String Phone) {
		this.vAccount = vAccount;
		
		LayoutManager layoutManager = new GridLayout(7,2);
		this.setLayout(layoutManager);
		
		this.setLayout(layoutManager);
		this.setSize(Constants.CMyInfoDialog.WIDTH,Constants.CMyInfoDialog.HEIGHT);
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle(Locale.LReviseDialog.TITLE);
		this.addWindowListener(new WindowAction());
		
		JLabel	si_Lbl = new JLabel(Locale.LReviseDialog.CHANGE_SI);
		si_Tf = new JTextField(Si);
		this.add(si_Lbl);
		this.add(si_Tf);
		JLabel gu_Lbl = new JLabel(Locale.LReviseDialog.CHANGE_GU);
		gu_Tf = new JTextField(Gu);
		this.add(gu_Lbl);
		this.add(gu_Tf);
		JLabel dong_Lbl = new JLabel(Locale.LReviseDialog.CHANGE_DONG);
		dong_Tf = new JTextField(Dong);
		this.add(dong_Lbl);
		this.add(dong_Tf);
		JLabel ho_Lbl = new JLabel(Locale.LReviseDialog.CHANGE_HO);
		ho_Tf = new JTextField(Ho);
		this.add(ho_Lbl);
		this.add(ho_Tf);
		JLabel email_Lbl = new JLabel(Locale.LReviseDialog.CHANGE_EMAIL);
		email_Tf = new JTextField(Email);
		this.add(email_Lbl);
		this.add(email_Tf);
		JLabel phone_Lbl = new JLabel(Locale.LReviseDialog.CHANGE_PHONE);
		phone_Tf = new JTextField(Phone);
		this.add(phone_Lbl);
		this.add(phone_Tf);
		
		
	}
	
	public void updateUserInfo() {
		this.address = si_Tf.getText()+"/"+gu_Tf.getText()+"/"+dong_Tf.getText()+"/"+ho_Tf.getText();
		this.email = email_Tf.getText();
		this.phone = phone_Tf.getText();
		
		SRevise sRevise = new SRevise();
		sRevise.updateUserInfo(this.vAccount,this.address,this.email,this.phone);
	}
	
	private class WindowAction implements WindowListener {
		@Override
		public void windowClosing(WindowEvent e) {
			int result = JOptionPane.showConfirmDialog(e.getWindow(), Locale.LReviseDialog.CONFIRM_SAVE, Locale.LReviseDialog.TITLE_CONFIRM_SAVE, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.CLOSED_OPTION) {
			}
			else if(result == JOptionPane.YES_OPTION) {
				updateUserInfo();
			}	
			else {		
			}
		}
		@Override
		public void windowClosed(WindowEvent e) {
		}
		@Override
		public void windowOpened(WindowEvent e) {
		}
		@Override
		public void windowIconified(WindowEvent e) {	
		}
		@Override
		public void windowDeiconified(WindowEvent e) {
		}
		@Override
		public void windowActivated(WindowEvent e) {
		}
	
		@Override
		public void windowDeactivated(WindowEvent e) {
		}
	
	}

	

	

}
