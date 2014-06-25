package com.siriwan.knowwho;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.modelmbean.XMLParseException;

import net.htmlparser.jericho.Element;

import org.jdom.Attribute;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.skos.SKOSAnnotation;
import org.semanticweb.skos.SKOSConcept;
import org.semanticweb.skos.SKOSCreationException;
import org.semanticweb.skos.SKOSDataFactory;
import org.semanticweb.skos.SKOSDataRelationAssertion;
import org.semanticweb.skos.SKOSDataset;
import org.semanticweb.skos.SKOSEntity;
import org.semanticweb.skos.SKOSLiteral;
import org.semanticweb.skos.SKOSObjectProperty;
import org.semanticweb.skos.SKOSObjectRelationAssertion;
import org.semanticweb.skos.SKOSTypedLiteral;
import org.semanticweb.skos.SKOSUntypedLiteral;
import org.semanticweb.skosapibinding.SKOSManager;
import org.semanticweb.skosapibinding.SKOStoOWLConverter;

import com.siriwan.knowwho.dao.Concept;
import com.siriwan.knowwho.dao.Concepts;
import com.siriwan.knowwho.dao.Label;
import com.siriwan.knowwho.file.FileUtil;
import com.siriwan.knowwho.xml.XmlParserHelper;


public class Main {
	private final static String CORPUS = "corpus";
	private final static String ACM = "ontology/ACMComputingClassificationSystemSKOSTaxonomy.xml";

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
	}

}
