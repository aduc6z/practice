package com.packtpub.e4.minimark.ui;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

/**
 * Class used to trigger build or processing task when files are changed or builded
 * @author duc
 *
 */
public class MinimarkVisitor implements IResourceProxyVisitor, IResourceDeltaVisitor {

	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		boolean deleted = (IResourceDelta.REMOVED & delta.getKind()) != 0;
		IResource resource = delta.getResource();
		String name = resource.getName();
		if (name.endsWith(".minimark")) {
			if (deleted) {
				String htmlName = name.replace(".minimark", ".html");
				IFile htmlFile = resource.getParent().getFile(new Path(htmlName));
				if (htmlFile.exists()) {
					htmlFile.delete(true, null);
				}
			} else {
				processResource(resource);
			}	
		} else if(name.endsWith(".html")) {
			String minimarkName = name.replace(".html", ".minimark");
			IFile minimarkFile = resource.getParent().getFile(new Path(minimarkName));
			if (minimarkFile.exists()) {
				processResource(minimarkFile);
			}
		}
		return true;
	}

	@Override
	public boolean visit(IResourceProxy proxy) throws CoreException {
		String name = proxy.getName();
		if (name != null && name.endsWith(".minimark")) {
//			System.out.println("Processing " + name);
			processResource(proxy.requestResource());
		}
		return true;
	}
	
	void processResource(IResource resource) throws CoreException {
		resource.deleteMarkers("com.packtpub.e4.minimark.ui.MinimarkMarker", true, IResource.DEPTH_INFINITE);
		if (resource instanceof IFile && resource.exists()) {
			try {
				IFile file = (IFile) resource;
				String htmlName = file.getName().replace(".minimark", ".html");
				IContainer container = file.getParent();
				IFile htmlFile = container.getFile(new Path(htmlName));
				InputStream in = file.getContents();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				MinimarkTranslator.convert(new InputStreamReader(in), new OutputStreamWriter(baos));
				ByteArrayInputStream contents = new ByteArrayInputStream(baos.toByteArray());
				if (baos.size() < 100) {
//					System.out.println("Minimark file is empty!"); // Won't be seen by user - need to add marker
//					IMarker marker = resource.createMarker(IMarker.PROBLEM); // Replaced by marker declared in plugin.xml below
					IMarker marker = resource.createMarker("com.packtpub.e4.minimark.ui.MinimarkMarker");
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					marker.setAttribute(IMarker.MESSAGE, "Minimark file is empty!");
					marker.setAttribute(IMarker.LINE_NUMBER, 0);
					marker.setAttribute(IMarker.CHAR_START, 0);
					marker.setAttribute(IMarker.CHAR_END, 0);
				}
				if (htmlFile.exists()) {
					htmlFile.setContents(contents, true, false, null);
				} else {
					htmlFile.create(contents, true, null);
				}
				htmlFile.setDerived(true); // Tell Eclipse that this file is generated during build - not user edited one
			} catch (IOException e) {
				throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to generate resource", e));
			}
		}
	}

}
