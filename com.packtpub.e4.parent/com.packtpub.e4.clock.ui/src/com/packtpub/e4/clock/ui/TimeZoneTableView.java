package com.packtpub.e4.clock.ui;

import java.util.TimeZone;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;

import com.packtpub.e4.clock.ui.internal.TimeZoneDisplayNameColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneIDColumn;
import com.packtpub.e4.clock.ui.internal.TimeZoneSelectionListener;

public class TimeZoneTableView extends ViewPart {

	private TableViewer tableViewer;
	
	TimeZoneSelectionListener selectionListener ;
	@Override
	public void createPartControl(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
//		tableViewer.setInput(TimeZone.getAvailableIDs());
		String [] ids = TimeZone.getAvailableIDs();
		TimeZone[] timezones = new TimeZone[ids.length];
		for (int i = 0; i < timezones.length; i++) {
			timezones[i] = TimeZone.getTimeZone(ids[i]);
		}
		new TimeZoneIDColumn().addColumnTo(tableViewer);
		new TimeZoneDisplayNameColumn().addColumnTo(tableViewer);
		tableViewer.setInput(timezones);
		getSite().setSelectionProvider(tableViewer);
		selectionListener = new TimeZoneSelectionListener(tableViewer, getSite().getPart());
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
		/*MenuManager manager = new MenuManager("#Popup Menu");
		Menu menu = manager.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
		
		Action deprecated = new Action() {
			public void run() {
				MessageDialog.openInformation(null, "Hello", "World");
			}
		};
		deprecated.setText("Hello context menu");
		manager.add(deprecated);*/
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
		tableViewer.getControl().setFocus();
	}
	
	private void hookContextMenu(Viewer viewer) {
		MenuManager manager = new MenuManager("#PopupMenu");
		Menu menu = manager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(manager, viewer);
		
	}

}