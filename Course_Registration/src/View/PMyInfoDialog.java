package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Global.Constants;
import Global.Locale;
import ValueObject.VAccount;

public class PMyInfoDialog extends JDialog{
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private VAccount vAccount;
	private String id;
	private String name;
	private String department;
	
	String Si;
	String Gu;
	String Dong;
	String Ho;
	
	private String address;
	private String email;
	private String phone;
	
	public PMyInfoDialog (VAccount vAccount) {
		this.vAccount = vAccount;
		
		setUserInfo(vAccount);
		
		LayoutManager layoutManager = new GridLayout(2,1);
		this.setLayout(layoutManager);
		this.setSize(Constants.CMyInfoDialog.WIDTH,Constants.CMyInfoDialog.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//화면 크기 고정
		this.setTitle(Locale.LMyInfoDialog.TITLE);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		ImageIcon userImage = getUserImage(vAccount);
		JLabel imgLabel = new JLabel(userImage);
		imagePanel.add(imgLabel,BorderLayout.CENTER);
		this.add(imagePanel);
		
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		JPanel leftPanel = new JPanel();
		infoPanel.add(leftPanel,BorderLayout.WEST);
		
		
		JPanel info_innerPanel = new JPanel();
		info_innerPanel.setLayout(new GridLayout(7,1));
		JLabel id_Label = new JLabel(Locale.LMyInfoDialog.ID_IS+this.id);
		info_innerPanel.add(id_Label);
		JLabel name_Label = new JLabel(Locale.LMyInfoDialog.NAME_IS+this.name);
		info_innerPanel.add(name_Label);
		JLabel dept_Label = new JLabel(Locale.LMyInfoDialog.DEPT_IS+this.department);
		info_innerPanel.add(dept_Label);
		JLabel address_Label = new JLabel(Locale.LMyInfoDialog.ADDRESS_IS+this.address);
		info_innerPanel.add(address_Label);
		JLabel email_Label = new JLabel(Locale.LMyInfoDialog.EMAIL_IS+this.email);
		info_innerPanel.add(email_Label);
		JLabel phone_Label = new JLabel(Locale.LMyInfoDialog.HP_IS+this.phone);
		info_innerPanel.add(phone_Label);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(8,2));
		JButton reviseBtn = new JButton(Locale.LMyInfoDialog.BTN_REVISE);
		reviseBtn.addActionListener(new ReviseAction());
		rightPanel.add(reviseBtn);
		infoPanel.add(rightPanel,BorderLayout.EAST);
		
		
		infoPanel.add(info_innerPanel,BorderLayout.CENTER);
		this.add(infoPanel);
	}
	
	public void setUserInfo(VAccount vAccount) {
		this.id = vAccount.getId();
		this.name = vAccount.getName();
		this.department = vAccount.getDepartment();
		
		//주소의 경우 파일에 서울시/서대문구/연희동 형식으로 저장하기 때문에 이를 분리해서 출력해야
		String[] subStr = vAccount.getAddress().split("/");
		this.Si = subStr[0];
		this.Gu = subStr[1];
		this.Dong = subStr[2];
		this.Ho = subStr[3];
		this.address = this.Si+" "+this.Gu+" "+this.Dong+" "+this.Ho;
		
		this.email = vAccount.getEmail();
		this.phone = vAccount.getPhone();
	}
	
	
	public ImageIcon getUserImage(VAccount vAccount) {

		ImageIcon sourceImage;
		
		String imagePath = Locale.LMyInfoDialog.IMAGE_LOCATION+vAccount.getId()+Locale.LMyInfoDialog.IMAGE_TYPE;
		
		//경로에서 이미지를 찾음
		File file = new File(imagePath);
		//만약 학번(id)으로 등록된 사진파일이 있으면
        if (file.exists()) {
        	//해당 경로의 이미지 객체 생성
        	sourceImage = new ImageIcon(imagePath);
        }
        //등록된 사진이 따로 없으면(경로에 파일이 존재하지 않으면)
        else {
        	//기본 이미지 경로의 객체 생성
        	imagePath = Locale.LMyInfoDialog.DEFAULT_IMAGE_LOCATION;
        	sourceImage = new ImageIcon(imagePath);
        }
		//객체에서 이미지를 가져오고
		Image image = sourceImage.getImage();
		//사이즈를 설정
		Image finalImage = image.getScaledInstance(Constants.CMyInfoDialog.IMG_WIDTH, Constants.CMyInfoDialog.IMG_HEIGHT, DO_NOTHING_ON_CLOSE);
		//가공이 완료된 이미지(userImage)의 객체를 생성
		ImageIcon userImage = new ImageIcon(finalImage);
		return userImage;
	}
	
	public void callReviseDialog(VAccount vAccount) {
		PReviseDialog pReviseDialoge = new PReviseDialog(vAccount,Si,Gu,Dong,Ho,email,phone);
		this.dispose();
		pReviseDialoge.setVisible(true);
		
	}
	
	
	private class ReviseAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			callReviseDialog(vAccount);	
		}
		
	}
}
