package com.packtpub.e4.minimark.ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Class used to convert file content from plain text to html format
 * Used by builder to translate file content
 * @author duc
 *
 */
public class MinimarkTranslator {
	
	public static void convert(Reader reader, Writer writer) throws IOException {
		BufferedReader lines = new BufferedReader(reader);
		String line;
		String title= String.valueOf(lines.readLine());
		writer.write("<html><head><title>");
		writer.write(title);
		writer.write("</title></head><body><h1");
		writer.write("</h1><p>");
		while (null != (line = lines.readLine())) {
			if ("".equals(line) ) {
				writer.write("</p><p>");
			} else {
				writer.write(line);
				writer.write('\n');
			}
		}
		writer.write("</p></body></html>");
		writer.flush();
	}
	
}
