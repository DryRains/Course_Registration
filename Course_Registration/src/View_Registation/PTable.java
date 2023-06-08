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
			//header 정의
			header = new Vector<String>();
			Vector<VHeader> vHeaders = new Vector<VHeader>();
			SDirectory sHeader = new SDirectory();
			vHeaders = sHeader.getHeaders(this.type);
			
			for(int i = 0 ; i < vHeaders.size() ; i++) {
				header.addElement(vHeaders.get(i).getHeader());
			}
	
			
			this.setRowSelectionAllowed(true);
			this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			this.tableModel = new DefaultTableModel(header,0); //header와 row 수 주어 모델 생성 -> 초기 row는 0개로 두고, 가변적으로 늘려갈 것
			this.setModel(this.tableModel);
			
			if(type.equals("lecture")||type.equals("preload")||type.equals("regist")) {
				this.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
			}
		}
		
		public void init(VAccount vAccount) {
			this.vAccount = vAccount;
			addLectures(this.getSavedLecture());
		}


		//파일 이름을 매개변수로 주면, 해당 파일을 열어서 데이터를 가져오는 메소드
		public void setData(String fileName) {
			//시작하기전 한번 비우고
			this.tableModel.setNumRows(0);
			
			//send & get to Controller
			SDirectory sDirectory = new SDirectory();
			vDirectories = sDirectory.getDirectories(fileName,this.type);
			
			//Convert VDirectory to String - Value Object에 담긴 데이터를 테이블에 담기 위해
			for (VDirectory vDirectory : vDirectories) {//vDrirectory에 담긴 데이터의 개수만큼 반복
				
				Vector<String> row = new Vector<String>();
				
				//Lecture의 경우 5개의 데이터를 한 행으로 만들어야 함
				if(this.type.equals("lecture")) {
					row.add(vDirectory.getId());
					row.add(vDirectory.getName());
					row.add(vDirectory.getProfessor());
					row.add(vDirectory.getCredit());
					row.add(vDirectory.getTime());	
				}
				//나머지 테이블의 경우 Name만 row로 만들면 됨
				else {
					row.add(vDirectory.getName());
				}
				
				this.tableModel.addRow(row); //작업한 행을 모델에 추가
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
			/*아무것도 선택하지 않았을경우 함수 종료*/
			if(vLectures==null) return;
			else {
				/*block duplicated row addtion*/
				for(int i=0;i<this.tableModel.getRowCount();i++) {
					for(int j=0; j<vLectures.size(); j++)	{
						//반복을 돌면서 강좌번호가 같은것이 발견되면 해당 데이터를 삭제
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
			/*아무 과목도 선택하지 않았을 경우 함수 바로 종료*/
			if(selectedLectureIndexes==null) return;
		
			DefaultTableModel model = (DefaultTableModel) this.getModel();
			/*point is you want to remove the highest index first and work your way down to the lower indices.*/
			for (int i = selectedLectureIndexes.length - 1; i >= 0; i--) {
			    model.removeRow(selectedLectureIndexes[i]);
			}
		}
		
		
		public Vector<String> getCurrentLectures(){ //현재 테이블의 데이터를 한줄로 만든다 -> 창닫을때 현재 작업해놓은 미리담기나 수강신청을 저장하기 위함
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
		
		//현재 저장되어있는 데이터를 저장
		public void saveCurrentLecture() {
			SMyLecture sSave = new SMyLecture();
			sSave.saveCurrentLecture(this.vAccount,getCurrentLectures(),this.type);
		}
		
		//저장된 목록을 가져옴
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


		
		
			