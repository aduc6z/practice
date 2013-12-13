package com.packtpub.e4.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;

public class HelloHandler {

	@Execute
	public void hello() {
		System.out.println("Hello World");
	}
}