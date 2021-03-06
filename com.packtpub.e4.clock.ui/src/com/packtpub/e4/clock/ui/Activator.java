package com.packtpub.e4.clock.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.packtpub.e4.clock.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private TrayItem trayItem;
	private Image image;
	private Shell shell;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		final Display display = Display.getCurrent();
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				image = new Image(display, Activator.class.getResourceAsStream("/icons/sample.gif"));
				Tray tray = display.getSystemTray();
				if (tray != null && image != null) {
					trayItem = new TrayItem(tray, SWT.NONE);
					trayItem.setToolTipText("Hello World");
					trayItem.setVisible(true);
					trayItem.setText("Hello World text");
					trayItem.setImage(new Image(trayItem.getDisplay(), Activator.class.getResourceAsStream("/icons/sample.gif")));
					trayItem.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetSelected(SelectionEvent e) {
							if (shell == null) {
								System.out.println("Tray item selected!");
								shell = new Shell(trayItem.getDisplay());
								shell.setLayout(new FillLayout());
								new ClockWidget(shell, SWT.NONE, new RGB(255, 255, 0));
								shell.pack();
							}
							shell.open();
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							// TODO Auto-generated method stub
						}
					});
				}
			}
		});
		int lauchCount = getPreferenceStore().getInt("launchCount");
		System.out.println("I have been launched " + lauchCount + " times");
		getPreferenceStore().setValue("launchCount", lauchCount + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		if (trayItem != null && !trayItem.isDisposed()) {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					if (trayItem != null && !trayItem.isDisposed()) {
						trayItem.dispose();
					}
				}
			});
			if (image != null && !image.isDisposed()) {
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						if (image != null && !image.isDisposed()) {
							image.dispose();
						}
					}

				});
			}
		}

	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
