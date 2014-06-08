package com.siriwan.knowwho;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.EndTagType;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTagType;

public class HTMLParser {
	public List<Element> getAllTableElements(String filename){
		List<Element> tables = new ArrayList<Element>() ;
		try {
			filename="file:"+filename;
			MicrosoftConditionalCommentTagTypes.register();
			MasonTagTypes.register();
			Source source=new Source(new URL(filename));
			PHPTagTypes.register(); // register PHPTagTypes after searching for XML processing instructions, otherwise PHP short tags override them.
			StartTagType.XML_DECLARATION.deregister(); // deregister XML declarations so they are recognised as PHP short tags, consistent with the real PHP parser.
			source=new Source(source); // have to create a new Source object after changing tag type registrations otherwise cache might contain tags found with previous configuration.

			tables =  source.getAllElements(HTMLElementName.TABLE);
	  
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}
	
	public static String elementToText(Element element){
		return element.getContent().getTextExtractor().toString();
	}
}
