package com.packtpub.e4.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.packtpub.e4.application.IStringService;

public class Rainbow {
	private static final Object [] rainbow = {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet" };

	@Inject
	EventBroker broker;
	
	@Inject
	private ESelectionService selectionService; 

	@Inject
	private IStringService stringService;
	
	@PostConstruct
	public void create(Composite parent) {
		ListViewer lv = new ListViewer(parent, SWT.NONE);
		lv.setContentProvider(new ArrayContentProvider());
		lv.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				Object colour =  sel.getFirstElement();
				broker.post("rainbow/colour", stringService.process(colour.toString()));
			}
		});
		lv.setInput(rainbow);
	}
	
}
