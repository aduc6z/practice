package com.packtpub.e4.clock.ui;

import java.net.URL;
import java.util.TimeZone;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.internal.TimeZoneComparator;
import com.packtpub.e4.clock.ui.internal.TimeZoneContentProvider;
import com.packtpub.e4.clock.ui.internal.TimeZoneLabelProvider;
import com.packtpub.e4.clock.ui.internal.TimeZoneSelectionListener;
import com.packtpub.e4.clock.ui.internal.TimeZoneViewerComparator;
import com.packtpub.e4.clock.ui.internal.TimeZoneViewerFinder;

public class TimeZoneTreeView extends ViewPart {
	
	private TreeViewer treeViewer;
	TimeZoneSelectionListener selectionListener;
	
	public TimeZoneTreeView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		ResourceManager rm  = JFaceResources.getResources();
		LocalResourceManager lrm = new LocalResourceManager(rm, parent);
		ImageRegistry ir = new ImageRegistry(lrm);
		URL sample = getClass().getResource("/icons/sample.gif");
		ir.put("sample", ImageDescriptor.createFromURL(sample));
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		FontRegistry fr = JFaceResources.getFontRegistry();
		treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new TimeZoneLabelProvider(ir, fr)));
		treeViewer.setContentProvider(new TimeZoneContentProvider());
		treeViewer.setInput(new Object[] { TimeZoneComparator.getTimeZones()});
		treeViewer.setData("REVERSE", Boolean.TRUE);
		treeViewer.setComparator(new TimeZoneViewerComparator());
//		treeViewer.setFilters(new ViewerFilter[] { new TimeZoneViewerFinder("GMT")});
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Viewer viewer = event.getViewer();
				Shell shell = viewer.getControl().getShell();
				MessageDialog.openInformation(shell, "Double click", "Double click detected");
				ISelection sel = viewer.getSelection();
				Object selectedValue;
				if (!(sel instanceof IStructuredSelection) || sel.isEmpty()) {
					selectedValue = null;
				} else {
					selectedValue = ((IStructuredSelection) sel).getFirstElement();
					if (selectedValue instanceof TimeZone) {
						TimeZone timezone = (TimeZone) selectedValue;
//						MessageDialog.openInformation(shell, timezone.getID(), timezone.toString());
						new TimeZoneDialogBox(shell, timezone).open();
					}
				}
			}
		});
		getSite().setSelectionProvider(treeViewer);
		selectionListener = new TimeZoneSelectionListener(treeViewer, getSite().getPart());
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
		hookContextMenu(treeViewer);
	}
	
	private void hookContextMenu(Viewer viewer) {
		MenuManager manager = new MenuManager("#PopupMenu");
		Menu menu = manager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(manager, viewer);
		
	}
	
	public void dispose() {
		if (selectionListener != null) {
			getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
			selectionListener = null;
		}
		super.dispose();
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

}