package com.packtpub.e4.junit.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.BeforeClass;
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
	
	private static SWTWorkbenchBot bot;
	
	@BeforeClass
	public static void beforeClass() {
		bot = new SWTWorkbenchBot();
		try {
			bot.viewByTitle("Welcome").close();
		} catch (WidgetNotFoundException ex) {
			System.out.println("Ignore exception");
		}
		
	}
	
	@Test
	public void testTimeZoneView() {
		bot.menu("Window").menu("Show View").menu("Other...").click();
		SWTBotShell shell = bot.shell("Show View");
		shell.activate();
		bot.tree().expandNode("Timekeeping").select("Time Zone View");
		bot.button("OK").click();
		SWTBotView timeZoneView = bot.viewByTitle("Time Zone View");
		assertNotNull(timeZoneView);
	}
	
	
	@Test
	public void createProject() {
		SWTWorkbenchBot bot = new SWTWorkbenchBot();
		try {
			bot.viewByTitle("Welcome").close();
			bot.viewByTitle("Problem Occurs").close();
		} catch (WidgetNotFoundException e) {
			System.out.println("Ignore error!");
		}
		bot.menu("File").menu("Project...").click();
		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		bot.tree().expandNode("General").select("Project");
		bot.button("Next >").click();
		bot.textWithLabel("Project name:").setText("SWTBot Test Project");
		bot.button("Finish").click();
	}


	
}
