package com.packtpub.e4.minimark.ui;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class MinimarkBuilder extends IncrementalProjectBuilder {

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
			System.out.println("Doing a incremental build");
		}
		
	}

	private void fullBuild(IProject project, IProgressMonitor monitor) throws CoreException {
		System.out.println("DOing a full build");
	}

}
