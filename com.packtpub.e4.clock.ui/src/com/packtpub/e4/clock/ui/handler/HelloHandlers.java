package com.packtpub.e4.clock.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class HelloHandlers extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Job job = new Job("About to say hello") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					
				}
//				MessageDialog.openInformation(null, "Hello", "World");
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						MessageDialog.openInformation(null, "Hello", "World");
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		return null;
	}

}
