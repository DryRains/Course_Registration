package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Global.Constants;
import Global.Locale;
import ValueObject.VAccount;
import View.Main.ActionLogout;
import View_Registation.PRegistrationPanel;

public class PMainFrame extends JFrame {//JFrame�� ��ӹޱ⿡ JFrame�� ��� �ڵ带 ����
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID; //���߿� ����
	

//	private VAccount vAccount;
	
	private PAccountPanel accountPanel;
	private PRegistrationPanel sugangsincheongPanel;
	
	public PMainFrame(VAccount vAccount, ActionLogout actionLogout) {
	//attributes(�Ӽ�)
		this.setSize(Constants.CMainFrame.WIDTH,Constants.CMainFrame.HEIGHT); //ȭ�� ������
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width/2 - this.getWidth()/2, Constants.CMainFrame.Y);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//â ������ ���α׷� ����
		this.setTitle(Locale.CMainFrame.TITLE);
		this.addWindowListener(new CloseAction());
		
	//components(��ǰ,���,�ڽĵ�..)
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		
		this.accountPanel = new PAccountPanel(vAccount, actionLogout);
		this.add(this.accountPanel, BorderLayout.NORTH);
		
		this.sugangsincheongPanel = new PRegistrationPanel(vAccount);
		this.add(sugangsincheongPanel, BorderLayout.CENTER);
		
	}
	
	public void initialize() {
		this.setVisible(true);
	}
	
	public void saveUserLectures() {
		this.sugangsincheongPanel.saveUserLectures();
	}
	
	private class CloseAction implements WindowListener {
		@Override
		public void windowClosing(WindowEvent e) {
			saveUserLectures();
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
