package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Global.Constants;
import Global.Locale;
import Service.SSignUp;

public class PSignUpDialog extends JDialog{
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	
	private JDialog thisDialog;
	
	private JTextField id_Tf;
	private JPasswordField pw_Pf;
	private JTextField name_Tf;
	private JTextField dept_Tf;
	private JTextField si_Tf;
	private JTextField gu_Tf;
	private JTextField dong_Tf;
	private JTextField ho_Tf;
	private JTextField email_Tf;
	private JTextField phone_Tf;
	private JComboBox comboBox;
	private JTextField answer_Tf;

	public PSignUpDialog(JDialog loginDialog) {
		//PLoginDialog�� ��� ���̾�α�
		super(loginDialog,Locale.LSignUpDialog.SIGNUP,true);
		
		this.setSize(400, 550);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.thisDialog = this;
		
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridLayout(12,2));
		JLabel id_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_ID);
		id_Tf = new JTextField();
		innerPanel.add(id_Lbl);
		innerPanel.add(id_Tf);
		JLabel pw_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_PW);
		pw_Pf = new JPasswordField();
		innerPanel.add(pw_Lbl);
		innerPanel.add(pw_Pf);
		JLabel name_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_NAME);
		name_Tf = new JTextField();
		innerPanel.add(name_Lbl);
		innerPanel.add(name_Tf);
		JLabel dept_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_DEPT);
		dept_Tf = new JTextField();
		innerPanel.add(dept_Lbl);
		innerPanel.add(dept_Tf);
		
		JLabel si_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_SI);
		si_Tf = new JTextField();
		innerPanel.add(si_Lbl);
		innerPanel.add(si_Tf);
		JLabel gu_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_GU);
		gu_Tf = new JTextField();
		innerPanel.add(gu_Lbl);
		innerPanel.add(gu_Tf);
		JLabel dong_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_DONG);
		dong_Tf = new JTextField();
		innerPanel.add(dong_Lbl);
		innerPanel.add(dong_Tf);
		JLabel ho_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_HO);
		ho_Tf = new JTextField();
		innerPanel.add(ho_Lbl);
		innerPanel.add(ho_Tf);
		JLabel email_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_EMAIL);
		email_Tf = new JTextField();
		innerPanel.add(email_Lbl);
		innerPanel.add(email_Tf);
		JLabel phone_Lbl = new JLabel(Locale.LSignUpDialog.INPUT_PHONE);
		phone_Tf = new JTextField();
		innerPanel.add(phone_Lbl);
		innerPanel.add(phone_Tf);
		
		JLabel question_Lbl = new JLabel("11. ���� Ȯ�� ������ �����ϼ��� : ");
		innerPanel.add(question_Lbl);
		
		comboBox = new JComboBox();
		comboBox.addItem("���� �¿����?");
		comboBox.addItem("���� �����ϴ� �౸������?");
		comboBox.addItem("���� �����ϴ� ��ȭ��?");
		innerPanel.add(comboBox);
		
		
		JLabel answer_Lbl = new JLabel("12. ����� �Է��ϼ��� : ");
		innerPanel.add(answer_Lbl);
		
		answer_Tf = new JTextField();
		innerPanel.add(answer_Tf);
		
		
		JButton confirm_Btn = new JButton(Locale.LSignUpDialog.SIGNUP);
		confirm_Btn.addActionListener(new ActionConfirm());
		
		this.add(innerPanel,BorderLayout.CENTER);
		this.add(confirm_Btn,BorderLayout.SOUTH);
		
	}
	
	
    public boolean isIdNumeric(String id) {
    	//id�� 8�ڸ��� �������� üũ
    	if(id.length()==8) {
    		try {
    			//id�� �������� �Ǻ��ϴ� �޼ҵ� - �Ǽ��� ��ȯ�� ���� ����� ���� �ƴ�
    			Double.parseDouble(id);
    			return true;
    		} catch (NumberFormatException e) {
    			return false;
    		}
        }
    	else return false;
    }
    
    public boolean isFulled() {
    	//��� �׸��� �����ߴ��� üũ
    	if(!id_Tf.getText().equals("")&&!pw_Pf.getText().equals("")&&!name_Tf.getText().equals("")&&!dept_Tf.getText().equals("")&&
    			!si_Tf.getText().equals("")&&!gu_Tf.getText().equals("")&&!dong_Tf.getText().equals("")&&!ho_Tf.getText().equals("")&&
    			!email_Tf.getText().equals("")&&!phone_Tf.getText().equals("")&&!answer_Tf.getText().equals("")) {
    		return true;
    	}
    	else return false;
    }
    
   public boolean isAccountCreated() {
	   //���̵� �����ϸ� false ��ȯ , �������� ������ �ش� ���� ������ �����ϰ� true ��ȯ
	 	SSignUp sSignUp = new SSignUp();
	 	
	 	//����Ȯ�� ���� �޺����ڿ� ���� ���õ� ��� - ���� �����Ͽ�
	 	String question = comboBox.getSelectedItem().toString().replace(" ", "");
	 	String answer = answer_Tf.getText().replace(" ", "");
		String QA = question+":"+answer;
		
		if(sSignUp.createAccount(id_Tf.getText(),pw_Pf.getText(),name_Tf.getText(),dept_Tf.getText(),
				si_Tf.getText(),gu_Tf.getText(),dong_Tf.getText(),ho_Tf.getText(),email_Tf.getText(),phone_Tf.getText(),QA)){
    		return true;
    	}
		else return false;
   }
    
   private class ActionConfirm implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isFulled()) {
			if(isIdNumeric(id_Tf.getText())){
				if(isAccountCreated()){
					JOptionPane.showMessageDialog(thisDialog, Locale.LSignUpDialog.COMPLETE_SIGNUP);
					thisDialog.dispose();
				}
				else {
					JOptionPane.showMessageDialog(thisDialog, Locale.LSignUpDialog.ID_ALREADY_EXITS, Locale.LLoginPanel.DIALOG_ERROR,JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(thisDialog, Locale.LSignUpDialog.ID_MUST_BE_EIGHT, Locale.LLoginPanel.DIALOG_ERROR,JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(thisDialog, Locale.LSignUpDialog.FILL_EVERY_BLANK,Locale.LLoginPanel.DIALOG_ERROR,JOptionPane.ERROR_MESSAGE);	
		}
		
	}
	   
   }
}
