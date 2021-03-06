package com.siriwan.knowwho;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;

import com.siriwan.knowwho.dao.Concept;
import com.siriwan.knowwho.dao.Concepts;
import com.siriwan.knowwho.download.HttpDownloadUtility;
import com.siriwan.knowwho.file.FileUtil;
import com.siriwan.knowwho.file.WordCounter;


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

	public static void testEncoding(){
		
		
		try {
			String outputfile = "files/test.txt";
		String encoding = "UTF8";
		Writer bfw = new OutputStreamWriter(new FileOutputStream(outputfile+ ".tag"), encoding);
		bfw.write("str");
		bfw.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		WordCounter.printStat("files/test1.out.ml", "files/systems_security.txt.tag.out");
//		ACMSearch acm = new ACMSearch();
//		acm.search("Data cleaning");
//		Concepts concepts = acm.getAllConcepts();
//		ArrayList<Concept> list = concepts.getAllConcepts();
//		for (Concept concept : list) {
//			System.out.println(concept.getPrefLabel().getValue());
//		}
////		acm.getAllLeafNode();
//		acm.getAllRootNode();
/*		String url = "http://dl.acm.org/results.cfm?query=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&querydisp=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&source_query=&start=1&srt=score%20dsc&short=0&source_disp=&since_month=&since_year=&before_month=&before_year=&coll=DL&dl=GUIDE&termshow=matchboolean&range_query=&zadv=1&CFID=485514724&CFTOKEN=54595634";
		String url2 = "http://dl.acm.org/results.cfm?query=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&querydisp=Owner%3AGUIDE%28CCS%3AArtificial%20CCS%3Aintelligence%20%29&source_query=&start=1&srt=score%20dsc&short=0&source_disp=&since_month=&since_year=&before_month=&before_year=&coll=DL&dl=GUIDE&termshow=matchboolean&range_query=&zadv=1";
		String url3 = "http://www.google.com";
		HttpDownloadUtility.downloadHTML(url);*/
	}
	

}
