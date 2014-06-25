package com.siriwan.knowwho;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;

import com.siriwan.knowwho.file.FileUtil;


public class Main {
	private final static String CORPUS = "corpus";

	public void testHTMLParser(){
		HTMLParser parser = new HTMLParser();
		ArrayList<String> files = FileUtil.listFilesForFolder(CORPUS);
		ArrayList<String> keywords = FileUtil.readFileToList("keywords/keyword.txt");
		for (String file : files) {
			System.out.println("======================== Filename :"+file+ " ========================" );
			List<Element> tables = parser.getAllTableElements(CORPUS+"/"+file);
			int counter = 1; 
			for (Element table : tables) {
				System.out.println("--------- Table :"+counter++ + " ---------" );
				String tableContent = parser.elementToText(table);
				System.out.println(tableContent);
			}
		}
	}

	
	public static void main(String[] args) {
		ACMSearch acm = new ACMSearch();
		acm.search("Data cleaning");
		acm.getAllLeafNode();
		acm.getAllRootNode();
	}
	

}
