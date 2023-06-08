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
			//div 클래스명이 = list 인 태그를 긁어옴
			Elements elements = doc.select("div.list");
			//그 긁어온 태그들중 li태그만 선별
			Elements li = elements.select("li");
			//문자열 배열에 담는 과정
			for(int i=0; i<li.size();i++) {
				String schedule = li.get(i).text();
				
				/*JLabel,Jbutton에서의 줄바꿈은 html의 <br>태그를 이용해야 하고*/
				middleResult = middleResult+"<br> <br>"+schedule;
				
			}
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		/*양 바깥쪽은 <html></html>태그로 감싸주어야함*/
		result = header + middleResult + tail; 
		return result;
	}

	public String getWeatherInfo() {
		String result = "";
		try {
			String URL = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서대문구+남가좌동+날씨&oquery=서대문구+날씨&tqi=hGVgQwp0Yidss7wPI5ossssssx8-111577";
				Document doc = Jsoup.connect(URL).get();
				Elements elements = doc.select("div.temperature_text");
				Elements elements2 = elements.select("strong");
				//div class = temperature_text 중 strong 태그 중 첫번째 저장되어 있는게 현재 온도.
				String temperature = elements2.get(0).text();
				
				Elements elements3 = doc.select("div.temperature_info");
				Elements elements4 = elements3.select("span");
				//세번째 태그에 있는 항목이 날씨 정보
				String weather = elements4.get(2).text();
				
				result = temperature + ", " + weather;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}

}
