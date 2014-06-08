package com.siriwan.knowwho;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;

public class HTMLParser {
	public void displayAllElements(String filename){
		try {
			filename="file:"+filename;
			MicrosoftConditionalCommentTagTypes.register();
			PHPTagTypes.register();
			PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this example otherwise they override processing instructions
			MasonTagTypes.register();
			Source source = new Source(new URL(filename));
			List<Element> elementList=source.getAllElements();
			for (Element element : elementList) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println(element.getDebugInfo());
				if (element.getAttributes()!=null) System.out.println("XHTML StartTag:\n"+element.getStartTag().tidy(true));
				System.out.println("Source text with content:\n"+element);
			}
			System.out.println(source.getCacheDebugInfo());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
