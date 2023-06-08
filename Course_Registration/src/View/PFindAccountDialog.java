package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Service.SFindAccount;
import Service.SRevise;

public class PFindAccountDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JDialog thisDialog;
	
	private JTextField email_Tf;
	private JTextField name_Tf;
	private JComboBox comboBox;
	private JTextField answer_Tf;
	private JTextField id_Tf;
	
	
	

	public PFindAccountDialog(JDialog loginDialog) {
		super(loginDialog,"ID & PASSWORD ã��",true);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.thisDialog = this;
		LayoutManager layoutManager = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
	
		
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new BorderLayout());
		
		JPanel panelA = new JPanel();
		panelA.setLayout(new GridLayout(2,2));
		
		JLabel email_Lbl = new JLabel("�̸��� �ּҸ� �Է��ϼ��� : ");
		panelA.add(email_Lbl);
		
		email_Tf = new JTextField();
		panelA.add(email_Tf);
		
		JLabel name_Lbl = new JLabel("�̸��� �Է��ϼ��� : ");
		panelA.add(name_Lbl);
		
		name_Tf = new JTextField();
		panelA.add(name_Tf);
		
		idPanel.add(panelA,BorderLayout.CENTER);
		
		JButton findID_Btn = new JButton("ID ã��");
		findID_Btn.addActionListener(new FindID());
		idPanel.add(findID_Btn,BorderLayout.SOUTH);
		
		
		
		JPanel pwPanel = new JPanel();
		pwPanel.setLayout(new BorderLayout());
		
		JPanel panelB = new JPanel();
		panelB.setLayout(new GridLayout(3,2));
		
		JLabel id_Lbl = new JLabel("id�� �Է��ϼ��� : ");
		panelB.add(id_Lbl);
		
		id_Tf = new JTextField();
		panelB.add(id_Tf);
		
		JLabel question_Lbl = new JLabel("���� Ȯ�� ���� ���� : ");
		panelB.add(question_Lbl);
		
		comboBox = new JComboBox();
		comboBox.addItem("���� �¿����?");
		comboBox.addItem("���� �����ϴ� �౸������?");
		comboBox.addItem("���� �����ϴ� ��ȭ��?");
		panelB.add(comboBox);
		
		
		JLabel answer_Lbl = new JLabel("���� Ȯ�� ���� ��� : ");
		panelB.add(answer_Lbl);
		
		answer_Tf = new JTextField();
		panelB.add(answer_Tf);
		
	
		
		pwPanel.add(panelB,BorderLayout.CENTER);
		
		JButton findPW_Btn = new JButton("PW ã��");
		findPW_Btn.addActionListener(new FindPW());
		pwPanel.add(findPW_Btn,BorderLayout.SOUTH);
		
		this.add(idPanel);
		this.add(pwPanel);
		
		
	}
	
	private class FindID implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String email = email_Tf.getText();
			String name = name_Tf.getText();
			SFindAccount sFindAccount = new SFindAccount();
			String id = sFindAccount.findID(email, name);
			if(id!=null) {
				JOptionPane.showMessageDialog(thisDialog,name+"���� ID�� "+id+" �Դϴ�.","�˸�",JOptionPane.PLAIN_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(thisDialog,"�߸��� ������ �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���","�˸�",JOptionPane.PLAIN_MESSAGE);
			}
			
			
		}
		
	}
	
	private class FindPW implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String id = id_Tf.getText();
			String question = comboBox.getSelectedItem().toString().replace(" ", "");
			String answer = answer_Tf.getText().replace(" ", "");
			String QA = question+":"+answer;
			
			SFindAccount sFindAccount = new SFindAccount();
			String oldPW = sFindAccount.findPW(id,QA);
			if(oldPW!=null) {
			while(true) {
				JOptionPane pane = new JOptionPane();
				String newPW = pane.showInputDialog(thisDialog,"���ο� ��й�ȣ�� �Է��ϼ���","��й�ȣ ����",JOptionPane.PLAIN_MESSAGE);
				
				if(!newPW.equals("")&&newPW!=null) {
					if(newPW.contains(" ")) {
						JOptionPane.showMessageDialog(thisDialog,"��й�ȣ���� ������ ���Ե� �� �����ϴ�. �ٽ� �Է��ϼ���","�˸�",JOptionPane.PLAIN_MESSAGE);
					}
					else {
						SRevise sRevise = new SRevise();
						sRevise.updatePW(oldPW,newPW);
						JOptionPane.showMessageDialog(thisDialog,"��й�ȣ�� ����Ǿ����ϴ�. ���ο� ��й�ȣ�� �α��� ���ּ���.","�˸�",JOptionPane.PLAIN_MESSAGE);
						thisDialog.dispose();
						break;
					}
				}
				else {
					JOptionPane.showMessageDialog(thisDialog,"��й�ȣ�� �Է����ּ���","�˸�",JOptionPane.PLAIN_MESSAGE);
					continue;
				}
			  }
			}
			else {
				JOptionPane.showMessageDialog(thisDialog,"�߸��� ������ �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���","�˸�",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
