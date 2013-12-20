package com.packtpub.e4.junit.plugin;

import static org.junit.Assert.*;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class UITest {
	
	@Test
	public void testUI() {
		SWTWorkbenchBot bot = new SWTWorkbenchBot();
		SWTBotShell[] shells = bot.shells();
		for (SWTBotShell swtBotShell : shells) {
			if (swtBotShell.isVisible()) {
				assertEquals("Java - Eclipse SDK", swtBotShell.getText());
			}
		}
		
	}

}
