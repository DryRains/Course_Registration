package View;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Global.Constants;
import Service.SWebData;

public class PScheduleDialog extends JDialog {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	

	public PScheduleDialog() {
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		this.setSize(500,550);
		this.setLocationRelativeTo(null);
		this.setTitle("학사 일정");
		
		
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		ImageIcon logoImage = getLogoImage();
		JLabel imgLabel = new JLabel(logoImage);
		imagePanel.add(imgLabel,BorderLayout.CENTER);
		this.add(imagePanel,BorderLayout.NORTH);
		
		JPanel schedulePanel = new JPanel();
		JLabel scheduleLabel = new JLabel(getSchoolSchedule());
		schedulePanel.add(scheduleLabel);
		this.add(schedulePanel);
		
		}
		
	
	
	public String getSchoolSchedule() {
		SWebData sWebData = new SWebData();
		return sWebData.getSchoolSchedule();
	}

	private ImageIcon getLogoImage() {
		//경로에서 이미지를 찾아서
		ImageIcon logoImage = new ImageIcon("imageSources/logo.gif");
		//그 이미지를 가져오고
		Image image = logoImage.getImage();
		//사이즈를 설정
		Image finalImage = image.getScaledInstance(140, 140, DO_NOTHING_ON_CLOSE);
		//가공이 완료된 이미지(userImage)의 객체를 생성
		ImageIcon userImage = new ImageIcon(finalImage);
		return userImage;
	}

}
