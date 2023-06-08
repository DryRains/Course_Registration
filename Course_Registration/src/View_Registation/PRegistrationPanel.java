package View_Registation;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Service.SMyLecture;
import ValueObject.VAccount;
import ValueObject.VDirectory;

public class PRegistrationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	VDirectory vLecture;
	
	private VAccount vAccount;
	
	private PDirectoryPanel directoryPanel;
	
	private PControlPanel controlPanel1;
	
	private PTable miridamgiTable;
	
	private PControlPanel controlPanel2;
	
	private PTable sincheongTable;
	
	private JLabel myCreditLbl;
	
	private JLabel selectedCreditLbl;
	
	private int[] myCredit;
	
	public PRegistrationPanel(VAccount vAccount) {
		this.vAccount = vAccount;
		
		ActionHandler actionHandler = new ActionHandler();
		
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layoutManager);
		
		JPanel directPanel = new JPanel();
		directPanel.setLayout(new BorderLayout());
		this.directoryPanel = new PDirectoryPanel();
		directPanel.add(this.directoryPanel);
		JLabel tempLabel1 = new JLabel(" ");
		directPanel.add(tempLabel1,BorderLayout.SOUTH);
		this.add(directPanel);
		
		this.controlPanel1 = new PControlPanel("1",actionHandler);
		this.add(this.controlPanel1);
		
		JPanel preloadPanel = new JPanel();
		preloadPanel.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		this.miridamgiTable = new PTable("preload");
		this.miridamgiTable.init(vAccount);
		this.miridamgiTable.getSelectionModel().addListSelectionListener(new SelectionCredit());
		scrollPane.setViewportView(miridamgiTable);
		preloadPanel.add(scrollPane);
		selectedCreditLbl = new JLabel("현재 선택된 학점 : 0점");
		selectedCreditLbl.setHorizontalAlignment(JLabel.CENTER);
		preloadPanel.add(selectedCreditLbl,BorderLayout.SOUTH);
		this.add(preloadPanel);
		
		
		
		this.controlPanel2 = new PControlPanel("2",actionHandler);
		this.add(controlPanel2);
		
		JPanel registPanel = new JPanel();
		registPanel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		this.sincheongTable = new PTable("regist");
		this.sincheongTable.init(vAccount);
		
		scrollPane.setViewportView(sincheongTable);
		registPanel.add(scrollPane);
		
		myCreditLbl = new JLabel();
		myCreditLbl.setHorizontalAlignment(JLabel.CENTER);
		updateMyCredit(vAccount);
		registPanel.add(myCreditLbl,BorderLayout.SOUTH);
		
		this.add(registPanel);
	}
	
	private int[] getMyCredit(VAccount vAccount) {
		SMyLecture sMyLecture = new SMyLecture();
		myCredit = sMyLecture.getMyCredit(vAccount);	
		return myCredit;
	}
	
	private void updateMyCredit(VAccount vAccount) {
		int[] myCredits = getMyCredit(vAccount);
		int currCredit = myCredits[0];
		int remainCredit = myCredits[1];
		this.myCreditLbl.setText("현재 학점 : "+currCredit+"점    남은 학점 : "+remainCredit+"점");
	}
	
	

	public void moveFromLectureToMiridamgi() {
		Vector <VDirectory> vLectures = this.directoryPanel.lectureTable.getSelectedLectures();
		this.miridamgiTable.addLectures(vLectures);
		//this.directoryPanel.deleteSelectedLectures();
	}
	
	public void moveFromMiridamgiToLecture() {
		Vector <VDirectory> vLectures = this.miridamgiTable.getSelectedLectures();
		//this.directoryPanel.addLectures(vLectures);
		this.miridamgiTable.deleteSelectedLectures();
		
	}


	public void moveFromMiridamgiToSincheong() {
		int currCredit = myCredit[0];
		//미리담기에서 선택해서 옮길려는 학점과 현재 내가 신청한 학점을 더했을 때 18학점 넘어가면 신청 못하게 막음
		if(currCredit+this.miridamgiTable.getSelectedCredit()>18) {
			JOptionPane.showMessageDialog(null,"최대 신청 가능 학점은 18점 입니다.","학점 초과",JOptionPane.ERROR_MESSAGE);
		}
		else {
			//18학점 안넘으면 
			Vector <VDirectory> vLectures = this.miridamgiTable.getSelectedLectures();
			this.sincheongTable.addLectures(vLectures);
			this.miridamgiTable.deleteSelectedLectures();
			//변경된 현재 과목상태 파일에 저장해주고
			this.sincheongTable.saveCurrentLecture();
			//그 업데이트된 파일에서 학점을 읽어옴
			updateMyCredit(this.vAccount);
		}
	}

	
	
	public void moveFromSincheongToMiridamgi() {
		Vector <VDirectory> vLectures = this.sincheongTable.getSelectedLectures();
		this.miridamgiTable.addLectures(vLectures);
		this.sincheongTable.deleteSelectedLectures();
		this.sincheongTable.saveCurrentLecture();
		updateMyCredit(this.vAccount);
	}
	
	public void saveUserLectures() {
		//프로그램 종료시 현재 과목상태들 저장
		this.miridamgiTable.saveCurrentLecture();
		this.sincheongTable.saveCurrentLecture();
	}
	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "1>>":
				moveFromLectureToMiridamgi();
				break;
			case "1<<":
				moveFromMiridamgiToLecture();
				break;
			case "2>>":
				moveFromMiridamgiToSincheong();
				break;
			case "2<<":
				moveFromSincheongToMiridamgi();
				break;
			}
				
		}
			
	}
	
	//미리담기 테이블에 과목(들)이 클릭되면 몇학점이 클릭된 상태인지 표시
	private class SelectionCredit implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {//This line prevents double events
				selectedCreditLbl.setText("현재 선택된 학점 : " + miridamgiTable.getSelectedCredit()+"점");    
		    }		
		}	
	}


	
		
}
