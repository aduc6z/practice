package com.packtpub.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.service.log.LogService;

public class Hello {
	
	Label label;
	
	@Inject
	@Optional // This service can be null and must be guarded by null check
	private LogService log;
	
	@PostConstruct
	public void create(Composite parent) {
		label = new Label(parent, SWT.NONE);
		label.setText("Hello");
		if (log != null) {
			log.log(LogService.LOG_ERROR, "Hello log");
		}
	}
	
	@Focus
	public void focus() {
		label.setFocus();
	}

}
