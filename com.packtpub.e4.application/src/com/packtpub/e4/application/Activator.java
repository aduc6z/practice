package com.packtpub.e4.application;

import org.eclipse.e4.core.di.InjectorFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@SuppressWarnings("restriction")
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Application start up");
		Activator.context = bundleContext;
		InjectorFactory.getDefault().addBinding(IStringService.class).implementedBy(StringService.class);		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
				
	}
}
