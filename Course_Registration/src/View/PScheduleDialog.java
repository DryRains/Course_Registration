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
		this.setTitle("�л� ����");
		
		
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
		//��ο��� �̹����� ã�Ƽ�
		ImageIcon logoImage = new ImageIcon("imageSources/logo.gif");
		//�� �̹����� ��������
		Image image = logoImage.getImage();
		//����� ����
		Image finalImage = image.getScaledInstance(140, 140, DO_NOTHING_ON_CLOSE);
		//������ �Ϸ�� �̹���(userImage)�� ��ü�� ����
		ImageIcon userImage = new ImageIcon(finalImage);
		return userImage;
	}

}
