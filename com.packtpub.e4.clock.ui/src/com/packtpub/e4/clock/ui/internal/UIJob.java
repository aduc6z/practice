package com.packtpub.e4.clock.ui.internal;

import org.eclipse.swt.widgets.Display;

public abstract class UIJob {
	
	public void execute() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				runInUIThread();
			}
		});
	}
	
	protected abstract void runInUIThread();
}
