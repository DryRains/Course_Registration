package View;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Global.Constants;
import Global.Locale;
import Service.SWebData;
import ValueObject.VAccount;
import View.Main.ActionLogout;

public class PAccountPanel extends JPanel{
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	private VAccount vAccount;
	
	
	public PAccountPanel(VAccount vAccount, ActionLogout actionLogout) {
	this.vAccount = vAccount;
	
	
	LayoutManager layoutManager = new BorderLayout();
	this.setLayout(layoutManager);
	
	JPanel LeftPan = new JPanel();
	JButton openWebBtn = new JButton(Locale.LAccountPanel.VISIT_MJUWEB);
	openWebBtn.addActionListener(new ActionOpenWeb());
	LeftPan.add(openWebBtn);
	JButton scheduleBtn = new JButton(Locale.LAccountPanel.SCHEDULE);
	scheduleBtn.addActionListener(new ActionSchedule());
	LeftPan.add(scheduleBtn);
	
	this.add(LeftPan, BorderLayout.WEST);
	
	JPanel GreetingPan = new JPanel();
	JLabel lGreeting = new JLabel(vAccount.getName() + Locale.LAccountPanel.INSA_POSTFIX);
	GreetingPan.add(lGreeting);
	JLabel lLogin = new JLabel(Locale.LAccountPanel.LOGIN_TIME_PREFIX);
	GreetingPan.add(lLogin);
	//현재 시간 구해서 넣기
	SimpleDateFormat simpledate = new SimpleDateFormat(Locale.LAccountPanel.TIME_FORMAT);
	JLabel lTime = new JLabel(simpledate.format(new Date())+Locale.LAccountPanel.IPNIDA);
	GreetingPan.add(lTime);
	JLabel lWeather = new JLabel(getWeatherInfo());
	GreetingPan.add(lWeather);
	this.add(GreetingPan, BorderLayout.CENTER);
	
	
	JPanel RightPan = new JPanel();
	JButton myInfoBtn = new JButton(Locale.LAccountPanel.MYINFO);
	myInfoBtn.addActionListener(new ActionMyInfo());
	RightPan.add(myInfoBtn);
	JButton logoutBtn = new JButton(Locale.LAccountPanel.LOGOUT);
	logoutBtn.addActionListener(actionLogout);
	RightPan.add(logoutBtn);
	this.add(RightPan, BorderLayout.EAST);
	
	}
	
	public String getWeatherInfo() {
		SWebData sWebData = new SWebData();
		return sWebData.getWeatherInfo();
	}
	
	
	private class ActionMyInfo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			PMyInfoDialog myInfoDialog = new PMyInfoDialog(vAccount);
			myInfoDialog.setVisible(true);
		}
		
	}
	
	private class ActionOpenWeb implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String url = Locale.LAccountPanel.MJU_URL;
			
			try {
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException error) {
					// TODO Auto-generated catch block
					error.printStackTrace();
				}
				}catch (URISyntaxException error) {
				// TODO Auto-generated catch block
					error.printStackTrace();
				}
			}	
	}
	
	private class ActionSchedule implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			PScheduleDialog pScheduleDialog = new PScheduleDialog();
			pScheduleDialog.setVisible(true);
			
		}
		
	}
}



