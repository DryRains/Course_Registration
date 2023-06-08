package View_Registation;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Global.Locale;
import Service.SDirectory;
import Service.SMyLecture;
import ValueObject.VAccount;
import ValueObject.VDirectory;
import ValueObject.VHeader;


public class PTable extends JTable {
		private static final long serialVersionUID = 1L;
		
		private VAccount vAccount;
		
		private String type;
		
		private int[] selectedLectureIndexes;
		
		private Vector<VDirectory> selectedLectures;
		
		private Vector<VDirectory> vDirectories;
		
		protected Vector<String> header;
		
		protected DefaultTableModel tableModel;
				
		public PTable() {
		}
		
		public PTable(String type) {
			this.type = type;
			//header ����
			header = new Vector<String>();
			Vector<VHeader> vHeaders = new Vector<VHeader>();
			SDirectory sHeader = new SDirectory();
			vHeaders = sHeader.getHeaders(this.type);
			
			for(int i = 0 ; i < vHeaders.size() ; i++) {
				header.addElement(vHeaders.get(i).getHeader());
			}
	
			
			this.setRowSelectionAllowed(true);
			this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			this.tableModel = new DefaultTableModel(header,0); //header�� row �� �־� �� ���� -> �ʱ� row�� 0���� �ΰ�, ���������� �÷��� ��
			this.setModel(this.tableModel);
			
			if(type.equals("lecture")||type.equals("preload")||type.equals("regist")) {
				this.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
			}
		}
		
		public void init(VAccount vAccount) {
			this.vAccount = vAccount;
			addLectures(this.getSavedLecture());
		}


		//���� �̸��� �Ű������� �ָ�, �ش� ������ ��� �����͸� �������� �޼ҵ�
		public void setData(String fileName) {
			//�����ϱ��� �ѹ� ����
			this.tableModel.setNumRows(0);
			
			//send & get to Controller
			SDirectory sDirectory = new SDirectory();
			vDirectories = sDirectory.getDirectories(fileName,this.type);
			
			//Convert VDirectory to String - Value Object�� ��� �����͸� ���̺� ��� ����
			for (VDirectory vDirectory : vDirectories) {//vDrirectory�� ��� �������� ������ŭ �ݺ�
				
				Vector<String> row = new Vector<String>();
				
				//Lecture�� ��� 5���� �����͸� �� ������ ������ ��
				if(this.type.equals("lecture")) {
					row.add(vDirectory.getId());
					row.add(vDirectory.getName());
					row.add(vDirectory.getProfessor());
					row.add(vDirectory.getCredit());
					row.add(vDirectory.getTime());	
				}
				//������ ���̺��� ��� Name�� row�� ����� ��
				else {
					row.add(vDirectory.getName());
				}
				
				this.tableModel.addRow(row); //�۾��� ���� �𵨿� �߰�
			}
			this.setRowSelectionInterval(0, 0);;
			}

		public Vector<VDirectory> getVDirectories(){
			return this.vDirectories;
		}
		public String getFileName(int row) {
			return vDirectories.get(row).getFileName();
		}
		
		public void addLectures(Vector<VDirectory> vLectures) {
			/*�ƹ��͵� �������� �ʾ������ �Լ� ����*/
			if(vLectures==null) return;
			else {
				/*block duplicated row addtion*/
				for(int i=0;i<this.tableModel.getRowCount();i++) {
					for(int j=0; j<vLectures.size(); j++)	{
						//�ݺ��� ���鼭 ���¹�ȣ�� �������� �߰ߵǸ� �ش� �����͸� ����
						if(this.tableModel.getValueAt(i,0).equals(vLectures.get(j).getId())){
							vLectures.removeElementAt(j);
						}	
					}
				}
				for(int i=0; i<vLectures.size(); i++) {
					Vector<String> row = new Vector<String>();
					row.add(vLectures.get(i).getId());
					row.add(vLectures.get(i).getName());
					row.add(vLectures.get(i).getProfessor());
					row.add(vLectures.get(i).getCredit());
					row.add(vLectures.get(i).getTime());
					this.tableModel.addRow(row);
				}
			}
		}
	
		public Vector<VDirectory> getSelectedLectures() {
			// TODO Auto-generated method stub
			return this.selectedLectures;
		}
		
		public void setSelectedLecture() {
			DefaultTableModel model = (DefaultTableModel) this.getModel();
			selectedLectures = new Vector<VDirectory>();
			selectedLectureIndexes = this.getSelectedRows();
			for (int i = 0; i < selectedLectureIndexes.length; i++) {
				VDirectory vDirectory = new VDirectory();
				
				vDirectory.setId(model.getValueAt(selectedLectureIndexes[i], 0).toString());
				vDirectory.setName(model.getValueAt(selectedLectureIndexes[i], 1).toString());
				vDirectory.setProfessor(model.getValueAt(selectedLectureIndexes[i], 2).toString());
				vDirectory.setCredit(model.getValueAt(selectedLectureIndexes[i], 3).toString());
				vDirectory.setTime(model.getValueAt(selectedLectureIndexes[i], 4).toString());
				
				selectedLectures.addElement(vDirectory);
			}
		}
		
		public void deleteSelectedLectures() {
			/*�ƹ� ���� �������� �ʾ��� ��� �Լ� �ٷ� ����*/
			if(selectedLectureIndexes==null) return;
		
			DefaultTableModel model = (DefaultTableModel) this.getModel();
			/*point is you want to remove the highest index first and work your way down to the lower indices.*/
			for (int i = selectedLectureIndexes.length - 1; i >= 0; i--) {
			    model.removeRow(selectedLectureIndexes[i]);
			}
		}
		
		
		public Vector<String> getCurrentLectures(){ //���� ���̺��� �����͸� ���ٷ� ����� -> â������ ���� �۾��س��� �̸���⳪ ������û�� �����ϱ� ����
			Vector<String> entireLectures = new Vector<String>();
			for(int i=0; i < this.tableModel.getRowCount(); i++) {
				String id = tableModel.getValueAt(i, 0).toString();
				String name = tableModel.getValueAt(i, 1).toString();
				String professor = tableModel.getValueAt(i, 2).toString();
				String credit = tableModel.getValueAt(i, 3).toString();
				String time = tableModel.getValueAt(i, 4).toString();
				entireLectures.add(id+" "+name+" "+professor+" "+credit+" "+time);
			}
			
			return entireLectures;
		}
		
		//���� ����Ǿ��ִ� �����͸� ����
		public void saveCurrentLecture() {
			SMyLecture sSave = new SMyLecture();
			sSave.saveCurrentLecture(this.vAccount,getCurrentLectures(),this.type);
		}
		
		//����� ����� ������
		public Vector<VDirectory> getSavedLecture() {
			SMyLecture sSave = new SMyLecture();
			return sSave.getSavedLecture(this.vAccount,this.type);
		}
		
		
		public int getSelectedCredit() {
			selectedLectureIndexes = this.getSelectedRows();
			int credit = 0;
			for(int i=0; i < selectedLectureIndexes.length; i++) {
				credit = credit + Integer.parseInt(tableModel.getValueAt(selectedLectureIndexes[i], 3).toString());
			}
			return credit;
		}
		
		
		private class ListSelectionHandler implements ListSelectionListener{
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {//This line prevents double events
					setSelectedLecture();
			    }		
			}	
		}
	}


		
		
			