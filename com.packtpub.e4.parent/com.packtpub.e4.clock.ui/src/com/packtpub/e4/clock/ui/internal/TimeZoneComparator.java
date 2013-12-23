package com.packtpub.e4.clock.ui.internal;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

public class TimeZoneComparator implements Comparator<TimeZone> {

	@Override
	public int compare(TimeZone arg0, TimeZone arg1) {
		return arg0.getID().compareTo(arg1.getID());
	}
	
	public static Map<String, Set<TimeZone>	> getTimeZones() {
		String [] ids = TimeZone.getAvailableIDs();
		Map<String, Set<TimeZone>> timeZones = new TreeMap<String, Set<TimeZone>>();
		for (String id : ids) {
			String[] idParts = id.split("/");
			if (idParts.length == 2) {
				String region = idParts[0];
				Set<TimeZone> zones = timeZones.get(region);
				if (zones == null) {
					zones = new TreeSet<TimeZone>(new TimeZoneComparator());
					timeZones.put(region, zones);
				}
				TimeZone timeZone = TimeZone.getTimeZone(id);
				zones.add(timeZone);
			}
		}
		return timeZones;
	}
	
	
}

