package Entity;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class EWebData {

	public EWebData() {
		// TODO Auto-generated constructor stub
	}

	public String getSchoolSchedule() {
		String result = "";
		String middleResult = "";
		String header = "<html><body>";
		String tail = "</body></html>";
		String URL = "https://www.mju.ac.kr/mjukr/262/subview.do";
		try {
			Document doc = Jsoup.connect(URL).get();
			//div Ŭ�������� = list �� �±׸� �ܾ��
			Elements elements = doc.select("div.list");
			//�� �ܾ�� �±׵��� li�±׸� ����
			Elements li = elements.select("li");
			//���ڿ� �迭�� ��� ����
			for(int i=0; i<li.size();i++) {
				String schedule = li.get(i).text();
				
				/*JLabel,Jbutton������ �ٹٲ��� html�� <br>�±׸� �̿��ؾ� �ϰ�*/
				middleResult = middleResult+"<br> <br>"+schedule;
				
			}
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		/*�� �ٱ����� <html></html>�±׷� �����־����*/
		result = header + middleResult + tail; 
		return result;
	}

	public String getWeatherInfo() {
		String result = "";
		try {
			String URL = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=���빮��+�����µ�+����&oquery=���빮��+����&tqi=hGVgQwp0Yidss7wPI5ossssssx8-111577";
				Document doc = Jsoup.connect(URL).get();
				Elements elements = doc.select("div.temperature_text");
				Elements elements2 = elements.select("strong");
				//div class = temperature_text �� strong �±� �� ù��° ����Ǿ� �ִ°� ���� �µ�.
				String temperature = elements2.get(0).text();
				
				Elements elements3 = doc.select("div.temperature_info");
				Elements elements4 = elements3.select("span");
				//����° �±׿� �ִ� �׸��� ���� ����
				String weather = elements4.get(2).text();
				
				result = temperature + ", " + weather;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}

}
