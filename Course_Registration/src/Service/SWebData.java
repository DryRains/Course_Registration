package Service;

import Entity.EWebData;

public class SWebData {
	private EWebData eWebData;

	public SWebData() {
		eWebData = new EWebData();
	}

	public String getSchoolSchedule() {
		return eWebData.getSchoolSchedule();
	}

	public String getWeatherInfo() {
		return eWebData.getWeatherInfo();
	}

}
