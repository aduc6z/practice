package com.packtpub.e4.headless.application;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Headless application");
		return null;
	}

	@Override
	public void stop() {

	}

}
