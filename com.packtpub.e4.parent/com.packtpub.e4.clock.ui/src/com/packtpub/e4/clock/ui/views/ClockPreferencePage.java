package com.packtpub.e4.clock.ui.views;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.packtpub.e4.clock.ui.Activator;

public class ClockPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	
	@Override
	protected void createFieldEditors() {
		addField(new IntegerFieldEditor("lauchCount", "Number of times it has been launched", getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
}
