package com.siriwan.knowwho;

import java.util.ArrayList;

public class Main {
	private final static String CORPUS = "corpus";
	
	public static void main(String[] args) {
//		FileUtil.readFile("corpus/buu_1.html");
		ArrayList<String> files = FileUtil.listFilesForFolder(CORPUS);
		ArrayList<String> keywords = FileUtil.readFileToList("keywords/keyword.txt");
		
		for (String file : files) {
			FileUtil.readFileToString(CORPUS+"/"+file);

		}
	}
	
}
