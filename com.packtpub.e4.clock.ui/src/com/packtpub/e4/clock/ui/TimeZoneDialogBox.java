package com.packtpub.e4.clock.ui;

import java.util.TimeZone;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class TimeZoneDialogBox extends MessageDialog {

	private TimeZone timezone;

	public TimeZoneDialogBox(Shell parentShell, TimeZone timezone) {
		super(parentShell, timezone.getID(), null, "Time zone " + timezone.getID(), INFORMATION, new String[] { IDialogConstants.OK_LABEL }, 0);
		this.timezone = timezone;
	}

	protected Control createCustomArea(Composite parent) {
		ClockWidget clock = new ClockWidget(parent, SWT.NONE, new RGB(128, 255, 0));
		clock.setOffset((TimeZone.getDefault().getOffset(System.currentTimeMillis()) - timezone.getOffset(System.currentTimeMillis())) / 3600000);
		return parent;
	}
}
