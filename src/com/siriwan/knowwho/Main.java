package com.siriwan.knowwho;

import java.util.ArrayList;

public class Main {
	private final static String CORPUS = "corpus";
	 
	public static void main(String[] args) {
//		FileUtil.readFile("corpus/buu_1.html");
		HTMLParser parser = new HTMLParser();
		ArrayList<String> files = FileUtil.listFilesForFolder(CORPUS);
		ArrayList<String> keywords = FileUtil.readFileToList("keywords/keyword.txt");
		parser.displayAllElements(CORPUS+"/"+"buu_1.html");
		/*for (String file : files) {
			String html = FileUtil.readFileToString(CORPUS+"/"+file);
			System.out.println(html);
			
			break;
		}*/
	}
	
}
