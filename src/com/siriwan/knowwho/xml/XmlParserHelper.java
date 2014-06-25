package com.siriwan.knowwho.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class XmlParserHelper {

	public static List<Element> getElementList(String xml) {
		List<Element> list = null;
		if (xml == null) {
			throw new NullPointerException("XML must be non-null");
		}
		try {
			SAXBuilder builder = new SAXBuilder();
			StringReader reader = new StringReader(xml);
			Document document =  builder.build(reader);
			Element rootNode = document.getRootElement();
	     	list = rootNode.getChildren();
			
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		
		if(list == null){
			list = new ArrayList<Element>();
		}
		return list;
	}
}
