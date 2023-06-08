package View_Registation;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Global.Constants;
import View_Registation.PRegistrationPanel.ActionHandler;


public class PControlPanel extends JPanel {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private JButton btnRight;
	private JButton btnLeft;
	
	
	public PControlPanel(String PanelID, ActionHandler actionHandler){
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		btnRight = new JButton(">>");
		btnRight.addActionListener(actionHandler);
		btnRight.setActionCommand(PanelID+btnRight.getText());//btnRight의 이름을 세팅 e.g., 1>>
		this.add(btnRight);
		 
		
		btnLeft = new JButton("<<");
		btnLeft.addActionListener(actionHandler);
		btnLeft.setActionCommand(PanelID+btnLeft.getText());
		this.add(btnLeft);
		
	
	}
	
	

}
