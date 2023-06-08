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
		super(loginDialog,"ID & PASSWORD 찾기",true);
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
		
		JLabel email_Lbl = new JLabel("이메일 주소를 입력하세요 : ");
		panelA.add(email_Lbl);
		
		email_Tf = new JTextField();
		panelA.add(email_Tf);
		
		JLabel name_Lbl = new JLabel("이름을 입력하세요 : ");
		panelA.add(name_Lbl);
		
		name_Tf = new JTextField();
		panelA.add(name_Tf);
		
		idPanel.add(panelA,BorderLayout.CENTER);
		
		JButton findID_Btn = new JButton("ID 찾기");
		findID_Btn.addActionListener(new FindID());
		idPanel.add(findID_Btn,BorderLayout.SOUTH);
		
		
		
		JPanel pwPanel = new JPanel();
		pwPanel.setLayout(new BorderLayout());
		
		JPanel panelB = new JPanel();
		panelB.setLayout(new GridLayout(3,2));
		
		JLabel id_Lbl = new JLabel("id을 입력하세요 : ");
		panelB.add(id_Lbl);
		
		id_Tf = new JTextField();
		panelB.add(id_Tf);
		
		JLabel question_Lbl = new JLabel("본인 확인 질문 선택 : ");
		panelB.add(question_Lbl);
		
		comboBox = new JComboBox();
		comboBox.addItem("나의 좌우명은?");
		comboBox.addItem("가장 좋아하는 축구선수는?");
		comboBox.addItem("가장 좋아하는 영화는?");
		panelB.add(comboBox);
		
		
		JLabel answer_Lbl = new JLabel("본인 확인 질문 대답 : ");
		panelB.add(answer_Lbl);
		
		answer_Tf = new JTextField();
		panelB.add(answer_Tf);
		
	
		
		pwPanel.add(panelB,BorderLayout.CENTER);
		
		JButton findPW_Btn = new JButton("PW 찾기");
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
				JOptionPane.showMessageDialog(thisDialog,name+"님의 ID는 "+id+" 입니다.","알림",JOptionPane.PLAIN_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(thisDialog,"잘못된 정보를 입력하셨습니다. 다시 입력하세요","알림",JOptionPane.PLAIN_MESSAGE);
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
				String newPW = pane.showInputDialog(thisDialog,"새로운 비밀번호를 입력하세요","비밀번호 갱신",JOptionPane.PLAIN_MESSAGE);
				
				if(!newPW.equals("")&&newPW!=null) {
					if(newPW.contains(" ")) {
						JOptionPane.showMessageDialog(thisDialog,"비밀번호에는 공백이 포함될 수 없습니다. 다시 입력하세요","알림",JOptionPane.PLAIN_MESSAGE);
					}
					else {
						SRevise sRevise = new SRevise();
						sRevise.updatePW(oldPW,newPW);
						JOptionPane.showMessageDialog(thisDialog,"비밀번호가 변경되었습니다. 새로운 비밀번호로 로그인 해주세요.","알림",JOptionPane.PLAIN_MESSAGE);
						thisDialog.dispose();
						break;
					}
				}
				else {
					JOptionPane.showMessageDialog(thisDialog,"비밀번호를 입력해주세요","알림",JOptionPane.PLAIN_MESSAGE);
					continue;
				}
			  }
			}
			else {
				JOptionPane.showMessageDialog(thisDialog,"잘못된 정보를 입력하셨습니다. 다시 입력하세요","알림",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
