package Service;

import java.util.Vector;

import Entity.EMyLecture;
import ValueObject.VAccount;
import ValueObject.VDirectory;

public class SMyLecture {
	private EMyLecture eMyLecture;
	public SMyLecture() {
		eMyLecture = new EMyLecture();
	}
	
	public void saveCurrentLecture(VAccount vAccount, Vector<String> Lectures, String type) {
		eMyLecture.saveCurrentLecture(vAccount,Lectures,type);
	}

	public Vector<VDirectory> getSavedLecture(VAccount vAccount, String type) {
		return eMyLecture.getSavedLecture(vAccount, type);
		
	}
	
	public int[] getMyCredit(VAccount vAccount) {
		return eMyLecture.getMyCredit(vAccount);
	}
	
	

}
