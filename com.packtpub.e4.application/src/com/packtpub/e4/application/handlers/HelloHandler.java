package com.packtpub.e4.application.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class HelloHandler {

	@Execute
	public void hello(@Named(IServiceConstants.ACTIVE_SHELL) Shell s) {
		MessageDialog.openInformation(s, "Hello World", "Welcome to Eclipse 4 technology");
	}
}