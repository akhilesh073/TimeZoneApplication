package com.akh.service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeZoneServiceImpl {

	public static Map<String, String> availableTimezones = new HashMap<String, String>();

	public static void getAvailableTimeZines() {
		String[] ids = TimeZone.getAvailableIDs();
		for (String id : ids) {
			setTimeZoneToMap(TimeZone.getTimeZone(id));
		}

	}
	public Map<String,String> getTimeZone(String[] requests){
		Map<String,String> timeZones=new HashMap<String,String>();
		for(String zone:requests){
			String tZoffset= availableTimezones.get(zone);
			int offset=Integer.valueOf(tZoffset.replace("GMT","").trim());
			String time=getTimeZoneFromOffset(offset);
			timeZones.put(zone,time);
		}
		return timeZones;
	}
	
	public static String getTimeZoneFromOffset(int offset) {
		Calendar c = Calendar.getInstance();
		TimeZone z = c.getTimeZone();
		int tzoffset = z.getOffset(c.getTimeInMillis()) / 1000 / 60;
		offset = offset - tzoffset;
		if (z.inDaylightTime(new Date())) {
			offset = offset + z.getDSTSavings();
		}
		int offsetHrs = offset / 60;
		int offsetMins = offset % 60;

		c.add(Calendar.HOUR_OF_DAY, (offsetHrs));
		c.add(Calendar.MINUTE, (offsetMins));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss a");
		return sdf.format(c.getTime());
	}
	

	public static void setTimeZoneToMap(TimeZone tz) {

		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
				- TimeUnit.HOURS.toMinutes(hours);
		minutes = Math.abs(minutes);
		long offset=hours*60+minutes;
		String result = "";
		if (hours > 0) {
			result = String.format("GMT+%3d", offset);
		} else {
			result = String.format("GMT%3d", offset);
		}
		availableTimezones.put(tz.getID(), result);
	}

	public void setZone(String zoneName, String offset) {
		availableTimezones.put(zoneName, offset);
	}
}
