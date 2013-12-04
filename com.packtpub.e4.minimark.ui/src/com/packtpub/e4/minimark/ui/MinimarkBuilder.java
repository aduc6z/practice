package com.packtpub.e4.minimark.ui;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Builder class to build file with .minimark extension
 * Must be registerer with project in .project file
 * @author duc
 *
 */
public class MinimarkBuilder extends IncrementalProjectBuilder {

	public static final String ID = "com.packtpub.e4.minimark.ui.MinimarkBuilder";
	
	@Override
	protected IProject[] build(int kind, Map<String, String> args,	IProgressMonitor monitor) throws CoreException {
		switch (kind) {
			case FULL_BUILD:
				fullBuild(getProject(), monitor);
				break;
			default:
				incrementalBuild(getProject(), monitor, getDelta(getProject()));
				break;
		}
		return null;
	}

	private void incrementalBuild(IProject project, IProgressMonitor monitor, IResourceDelta delta) throws CoreException {
		if (delta == null) {
			fullBuild(project,monitor); 
		} else {
//			System.out.println("Doing a incremental build"); 
			delta.accept(new MinimarkVisitor());			
		}
		
	}

	private void fullBuild(IProject project, IProgressMonitor monitor) throws CoreException {
//		System.out.println("DOing a full build");
		project.accept(new MinimarkVisitor(), IResource.NONE);
	}

}
