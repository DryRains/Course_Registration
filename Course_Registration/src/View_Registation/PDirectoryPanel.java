package View_Registation;

import java.awt.LayoutManager;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import ValueObject.VDirectory;


public class PDirectoryPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private PTable campusTable;
	private PTable collegeTable;
	private PTable departmentTable;
	public PTable lectureTable;
	
	private Vector<VDirectory> selectedLectures;
	
	int[] selectedIndexes;
	
	public PDirectoryPanel() {
		
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
	

		JPanel subPanel1 = new JPanel();
			layoutManager = new BoxLayout(subPanel1, BoxLayout.X_AXIS);
			subPanel1.setLayout(layoutManager);
			
			JScrollPane scrollPane = new JScrollPane();
			this.campusTable = new PTable("campus");
			scrollPane.setViewportView(this.campusTable);
			this.campusTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
			subPanel1.add(scrollPane);
	
			scrollPane = new JScrollPane();
			this.collegeTable = new PTable("college");
			scrollPane.setViewportView(this.collegeTable);
			this.collegeTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
			subPanel1.add(scrollPane);
			
			scrollPane = new JScrollPane();
			this.departmentTable = new PTable("department");
			scrollPane.setViewportView(this.departmentTable);
			this.departmentTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());
			
			subPanel1.add(scrollPane);
		this.add(subPanel1);
			
		JPanel subPanel2 = new JPanel();
			layoutManager = new BoxLayout(subPanel2, BoxLayout.Y_AXIS);
			subPanel2.setLayout(layoutManager);
			
			scrollPane = new JScrollPane();
			this.lectureTable = new PTable("lecture");
			scrollPane.setViewportView(this.lectureTable);
			this.lectureTable.getSelectionModel().addListSelectionListener(new ListSelectionHandler());

			
			subPanel2.add(scrollPane);			
		this.add(subPanel2);
		this.updateTable(null,0);	
		
	}
	
	private void updateTable(Object object, int selectedRow) {
		String fileName=null;
		
		
		
		if(object==null) {
			
			fileName = "campus/root";
			
			this.campusTable.setData(fileName);	
		}
		
		 if(object==this.campusTable.getSelectionModel()){
			selectedIndexes = this.campusTable.getSelectedRows();
			if(selectedIndexes.length>0) {
				fileName = "college/"+this.campusTable.getFileName(selectedIndexes[0]);
				this.collegeTable.setData(fileName);
			}
			
		}
		else if(object==this.collegeTable.getSelectionModel()) {
			selectedIndexes = this.collegeTable.getSelectedRows();
			if(selectedIndexes.length>0) {
				fileName = "department/"+this.collegeTable.getFileName(selectedIndexes[0]);
				this.departmentTable.setData(fileName);
			}
		}
		else if(object==this.departmentTable.getSelectionModel()) {
			selectedIndexes = this.departmentTable.getSelectedRows();
			if(selectedIndexes.length>0) {
				fileName = "lecture/"+this.departmentTable.getFileName(selectedIndexes[0]);
				this.lectureTable.setData(fileName);
			}
		}	
	}
	
	public int getIndex(Object object) {
		if(object==campusTable.getSelectionModel()) {
			return campusTable.getSelectedRow();
		}
		else if(object==collegeTable.getSelectionModel()) {
			return collegeTable.getSelectedRow();
		}
		else if(object==departmentTable.getSelectionModel()) {
			return departmentTable.getSelectedRow();
		}
		else if(object==lectureTable.getSelectionModel()) {
			return lectureTable.getSelectedRow();
		}
		
		else return 0;
		
	}

	private class ListSelectionHandler implements ListSelectionListener{
		int selectedRow;
		Object object;
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {//This line prevents double events
				object = e.getSource();
				
				selectedRow = getIndex(object);
				
				updateTable(object,selectedRow);	
				
		    }		
		}	
	}			
}
	
