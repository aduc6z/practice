package com.packtpub.e4.clock.ui.internal;

import java.util.Date;
import java.util.TimeZone;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class TimeZonePropertySource implements IPropertySource {
	private TimeZone timezone;
	private static final Object ID = new Object();
	private static final Object DAYLIGHT = new Object();
	private static final Object NAME = new Object();
	
	public TimeZonePropertySource(TimeZone timeZone) {
		this.timezone = timeZone;
	}
	
	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] { new PropertyDescriptor(ID, "Time Zone"),
				new PropertyDescriptor(DAYLIGHT, "Daylight savings"),
				new PropertyDescriptor(NAME, "Name") };
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (ID.equals(id)) {
			return timezone.getID();
		} else if (DAYLIGHT.equals(id)) {
			return timezone.inDaylightTime(new Date());
		} else if (NAME.equals(id)) {
			return timezone.getDisplayName();
		} else {
			return null;
		}
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
	}

}
