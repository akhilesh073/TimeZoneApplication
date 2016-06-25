package com.akh.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeZoneServiceImpl {

	public static Map<String, String> availableTimezones = new HashMap<String, String>();

	public static void getAvailableTimeZines() {
		String[] ids = TimeZone.getAvailableIDs();
		
		//Taking the first 10 timeZones for testing purpose
		int count=0;
		for (String id : ids) {
			count++;
			setTimeZoneToMap(TimeZone.getTimeZone(id));
			if(count==10){
				break;
			}
		}

	}
	public Map<String,String> getTimeZone(String[] requests){
		Map<String,String> timeZones=new HashMap<String,String>();
		for(String zone:requests){
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss a");
			df.setTimeZone(TimeZone.getTimeZone(zone));
			timeZones.put(zone,df.format(date));				
		}
		return timeZones;
	}
	

	public static void setTimeZoneToMap(TimeZone tz) {

		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
				- TimeUnit.HOURS.toMinutes(hours);
		minutes = Math.abs(minutes);
		String result = "";
		if (hours > 0) {
			result = String.format("GMT+%d:%02d", hours, minutes);
		} else {
			result = String.format("GMT%d:%02d", hours, minutes);
		}
		availableTimezones.put(tz.getID(), result);
	}

	public void setZone(String zoneName, String offset) {
		availableTimezones.put(zoneName, offset);
	}
}
