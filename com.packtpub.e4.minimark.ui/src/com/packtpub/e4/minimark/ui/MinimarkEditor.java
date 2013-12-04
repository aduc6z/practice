package com.packtpub.e4.minimark.ui;

import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.texteditor.AbstractTextEditor;

/**
 * Class to edit file type with .minimark extension
 * @author duc
 *
 */
public class MinimarkEditor extends AbstractTextEditor {
	
	public MinimarkEditor() {
		setDocumentProvider(new TextFileDocumentProvider());
	}

}