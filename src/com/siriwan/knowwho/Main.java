package com.siriwan.knowwho;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;

import com.siriwan.knowwho.download.HttpDownloadUtility;
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
		/*ACMSearch acm = new ACMSearch();
		acm.search("Data cleaning");
		acm.getAllLeafNode();
		acm.getAllRootNode();*/
		String url = "http://dl.acm.org/results.cfm?query=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&querydisp=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&source_query=&start=1&srt=score%20dsc&short=0&source_disp=&since_month=&since_year=&before_month=&before_year=&coll=DL&dl=GUIDE&termshow=matchboolean&range_query=&zadv=1&CFID=485514724&CFTOKEN=54595634";
		String url2 = "http://dl.acm.org/results.cfm?query=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&querydisp=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&source_query=&start=1&srt=score%20dsc&short=0&source_disp=&since_month=&since_year=&before_month=&before_year=&coll=DL&dl=GUIDE&termshow=matchboolean&range_query=&zadv=1";
		String url3 = "http://www.google.com";
		HttpDownloadUtility.downloadHTML(url2);
	}
	

}
