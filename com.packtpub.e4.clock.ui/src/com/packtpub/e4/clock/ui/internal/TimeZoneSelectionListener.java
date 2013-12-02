package com.packtpub.e4.clock.ui.internal;

import java.util.TimeZone;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

public class TimeZoneSelectionListener implements ISelectionListener {

	private Viewer viewer;
	private IWorkbenchPart part;
	
	public TimeZoneSelectionListener(Viewer v, IWorkbenchPart part) {
		this.viewer = v;
		this.part = part;
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part != this.part) {
			System.out.println("Changes are not from this part!");
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			Object current = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
			if (selected != current && selected instanceof TimeZone) {
				viewer.setSelection(selection);
				if (viewer instanceof StructuredViewer) {
					((StructuredViewer) viewer).reveal(selected);
				}
			}
		} else {
			System.out.println("This part has been changed!");
		}
	}

}
