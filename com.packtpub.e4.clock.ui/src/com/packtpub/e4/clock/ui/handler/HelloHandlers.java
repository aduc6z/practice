package com.packtpub.e4.clock.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressConstants2;

import com.packtpub.e4.clock.ui.internal.UIJob;

public class HelloHandlers extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Job job = new Job("About to say hello") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					SubMonitor subMonitor = SubMonitor.convert(monitor, "Preparing", 5000);
//					monitor.beginTask("Preparing", 5000);
					for (int i = 0; i < 50 && !subMonitor.isCanceled(); i++) {
						switch(i) {
						case 10:
							subMonitor.subTask("Doing something");
							break;
						case 25:
							subMonitor.subTask("Doing something else");
							break;
						case 40:
							subMonitor.subTask("Nearly there");
							break;
						case 12: 
							checkDozen(subMonitor.newChild(100));
							continue;
						}
						Thread.sleep(100);
						subMonitor.worked(100);
					}
				} catch (InterruptedException e) {
					
				} finally {
					if (monitor != null) {
						monitor.done();
					}
				}
				if (!monitor.isCanceled()) {
					UIJob uiJob = new UIJob() {
						
						@Override
						public void runInUIThread() {
							MessageDialog.openInformation(null, "Hello", "World");
						}
					};
					uiJob.execute();
				}
				return Status.OK_STATUS;
			}

			private void checkDozen(IProgressMonitor monitor) {
				try {
					if (monitor == null) {
						monitor = new NullProgressMonitor();
					}
					monitor.beginTask("Check a dozen", 12);
					for (int i = 0; i < 12; i++) {
						monitor.subTask("changing " + i);
						Thread.sleep(100);
						monitor.worked(1);
					}
				} catch (Exception e) {
					
				} finally {
					monitor.done();
				}
				
			}
		};
		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command = service == null ? null : service.getCommand("com.packtpub.e4.clock.ui.command.hello");
		if (command != null) {
//			job.setProperty(IProgressConstants2.COMMAND_PROPERTY, command); // Need parameterized command
			job.setProperty(IProgressConstants2.COMMAND_PROPERTY, ParameterizedCommand.generateCommand(command, null));
			job.setProperty(IProgressConstants2.ICON_PROPERTY, ImageDescriptor.createFromURL(HelloHandlers.class.getResource("/icons/samle.gif")));
		}
		job.schedule();
		return null;
	}

}
