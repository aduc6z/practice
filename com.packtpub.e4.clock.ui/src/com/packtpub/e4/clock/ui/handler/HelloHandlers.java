package com.packtpub.e4.clock.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;

import com.packtpub.e4.clock.ui.internal.UIJob;

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
				UIJob uiJob = new UIJob() {
					
					@Override
					public void runInUIThread() {
						MessageDialog.openInformation(null, "Hello", "World");
					}
				};
				uiJob.execute();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		return null;
	}

}
