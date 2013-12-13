package com.packtpub.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.service.log.LogService;
import org.osgi.service.prefs.BackingStoreException;

public class Hello {
	
	Label label;
	
	@Inject
	@Optional // This service can be null and must be guarded by null check
	private LogService log;
	
	@Inject
	private MWindow window;

	@Inject
	@Optional // Optional as there may be no selection when a part is created. This method will not be called at all.
	// If argument is marked with @Optional, the method will be called with optional argument is null
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		if (selection != null) {
			label.setText(selection.toString());
		}
	}
	
	@Inject
	@Optional
	public void receiverEvent(@UIEventTopic("rainbow/colour") String data) throws BackingStoreException { // Must use UIEventTopic, not EventTopic due as it is UI thread		
		label.setText(data);
		prefs.put("greeting", "I like " + data);
		System.out.println("Store greeting preference: " + greeting);
		prefs.sync();
	}
	
	@Inject	@Named("math.random")
	private Object random;
	
	@PostConstruct
	public void create(Composite parent) {
		label = new Label(parent, SWT.NONE);
		label.setText("--" + greeting + " " + window.getLabel() + " " + random);
		if (log != null) {
			log.log(LogService.LOG_ERROR, "Hello log");
		}
	}
	
	@Inject
	@Optional
	void setText(@Preference(nodePath="com.packtpub.e4.application", value="greeting") String text) {
		if (text != null && label != null && !label.isDisposed()) {
			label.setText(text);
		}
	}
	
	@Focus
	public void focus() {
		label.setFocus();
	}

	@Inject
	@Preference(nodePath="com.packtpub.e4.application")
	private IEclipsePreferences prefs;
	
	@Inject
	@Preference(nodePath="com.packtpub.e4.application", value="greeting")
	String greeting;
}