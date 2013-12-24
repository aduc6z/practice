package com.packtpub.e4.application;

import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class RandomFunction implements IContextFunction {

	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		return Math.random();
	}
}
