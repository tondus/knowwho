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

	public void testACMSearch(String query){


		/*
		 * How to load a SKOS vocabulary and print out the concepts and any assertions on these
		 * The main object in the API include a SKOSManager which manages the loading, saving a dn editing of dataset,
		 * The SKOSDataset object is a container for your SKOS vocabularies, each manager can have multiple dataset which
		 * are accessed via a URIs. Finally there is a SKOSDataFactory object for creating and retrieving SKOSObject from your dataset.
		 */

		try {
			// First create a new SKOSManager
			SKOSManager manager = new SKOSManager();

			// use the manager to load a SKOS vocabulary from a URI (either physical or on the web)
			//            SKOSDataset dataset = manager.loadDataset(new URI("https://dl.dropboxusercontent.com/u/7640017/ACMComputingClassificationSystemSKOSTaxonomy.xml"));
			//            SKOSDataset dataset = manager.loadDatasetFromPhysicalURI(URI.create("file:/Users/ton/Developer/workspace/knowwho/ontology/ACMComputingClassificationSystemSKOSTaxonomy.xml"));
			//            SKOSDataset dataset = manager.loadDatasetFromPhysicalURI(URI.create("file:/Users/ton/Developer/workspace/knowwho/ontology/NASA.subjects.skos.xml"));
			SKOSDataset dataset = manager.loadDatasetFromPhysicalURI(URI.create("file:/Users/ton/Developer/workspace/knowwho/ontology/ACMComputingClassificationSystemSKOSTaxonomy.xml"));

			// get all the concepts in this vocabulay and print out the URI
			for (SKOSConcept concept : dataset.getSKOSConcepts()) {
				System.out.println("Concept: " + concept.getURI());


				/* SKOS entities such as Concepts, ConceptSchemes (See SKOSEntity in Javadoc for full list), are related to other
				 * entities or literal values by three different types of relationships.
				 * ObjectPropertyAssertions - These are relationships between two SKOS entities
				 * DataPropertyAssertion - These relate entities to Literal values
				 *SKOSAnnotation - These are either literal or entity annotation on a particular entity
				 */

				// print out object assertions
				concept.getSKOSAnnotationsByURI(dataset, manager.getSKOSDataFactory().getSKOSBroaderProperty().getURI());


				System.out.println("\tObject property assertions:");
				for (SKOSObjectRelationAssertion objectAssertion : dataset.getSKOSObjectRelationAssertions(concept)) {
					System.out.println("\t\t" + objectAssertion.getSKOSProperty().getURI().getFragment() + " " + objectAssertion.getSKOSObject().getURI().getFragment());

				}
				System.out.println("");


				// print out any data property assertions
				System.out.println("\tData property assertions:");
				for (SKOSDataRelationAssertion assertion : dataset.getSKOSDataRelationAssertions(concept)) {

					// the object of a data assertion can be either a typed or untyped literal
					SKOSLiteral literal = assertion.getSKOSObject();
					String lang = "";
					if (literal.isTyped()) {

						SKOSTypedLiteral typedLiteral = literal.getAsSKOSTypedLiteral();
						System.out.println("\t\t" + assertion.getSKOSProperty().getURI().getFragment() + " " + literal.getLiteral() + " Type:" + typedLiteral.getDataType().getURI() );
					}
					else {

						// if it has  language
						SKOSUntypedLiteral untypedLiteral = literal.getAsSKOSUntypedLiteral();
						if (untypedLiteral.hasLang()) {
							lang = untypedLiteral.getLang();
						}
						System.out.println("\t\t" + assertion.getSKOSProperty().getURI().getFragment() + " " + literal.getLiteral() + " Lang:" + lang);

					}
				}
				System.out.println("");


				// finally get any OWL annotations - the object of a annotation property can be a literal or an entity
				System.out.println("\tAnnotation property assertions:");
				for (SKOSAnnotation assertion : dataset.getSKOSAnnotations(concept)) {

					// if the annotation is a literal annotation?
					String lang = "";
					String value = "";

					if (assertion.isAnnotationByConstant()) {

						SKOSLiteral literal = assertion.getAnnotationValueAsConstant();
						value = literal.getLiteral();
						if (!literal.isTyped()) {
							// if it has  language
							SKOSUntypedLiteral untypedLiteral = literal.getAsSKOSUntypedLiteral();
							if (untypedLiteral.hasLang()) {
								lang = untypedLiteral.getLang();
							}
						}
					}
					else {
						// annotation is some resource
						SKOSEntity entity = assertion.getAnnotationValue();
						value = entity.getURI().getFragment();
					}

					System.out.println("\t\t" + assertion.getURI().getFragment() + " " + value + " Lang:" + lang);
					if(assertion.getURI().getFragment() != null && assertion.getURI().getFragment().contains(query)){
						System.out.println("FOUNDED!!!");
						break;
					}
					if( value !=null && value.contains(query)){
						System.out.println("FOUNDED!!!");
						break;
					}
					if(lang !=null && lang.contains(query)){
						System.out.println("FOUNDED!!!");
						break;
					}

				}
				System.out.println("-------------------------------------------------------");

			}
		} catch (SKOSCreationException e) {
			e.printStackTrace();
		}

	}
	public void testXMLParser(){
		String xml = FileUtil.readFileAsString(ACM);
		System.out.println("-----------Load file finish-----------");
		List<org.jdom.Element> list = XmlParserHelper.getElementList(xml);
		Concepts concepts = new Concepts();
		for (int i = 0; i < list.size(); i++) {
			org.jdom.Element node = (org.jdom.Element) list.get(i);
			if (node.getName() == "Concept") {
				Concept concept = new Concept();
				
				List<Attribute> conceptAttrs = node.getAttributes();
				String about = conceptAttrs.get(0).getValue();
				String lang = conceptAttrs.get(1).getValue();
				concept.setId(about);
				concept.setLang(lang);
				
				List<org.jdom.Element> conceptChilds = node.getChildren();
				for (int j = 0; j < conceptChilds.size(); j++) {
					org.jdom.Element childNode = (org.jdom.Element) conceptChilds.get(j);
					if (childNode.getName() == "prefLabel") {
						List<Attribute> attrs = childNode.getAttributes();
						Label label = new Label();
						label.setLang(attrs.get(0).getValue());
						label.setValue(childNode.getValue());
						label.setPrefLabel(true);
						
					}
					if (childNode.getName() == "altLabel") {
						List<Attribute> attrs = childNode.getAttributes();
						Label label = new Label();
						label.setLang(attrs.get(0).getValue());
						label.setValue(childNode.getValue());
						label.setPrefLabel(false);
					}
					if (childNode.getName() == "broader") {
						List<Attribute> attrs = childNode.getAttributes();
						concept.setParent(attrs.get(0).getValue());
					}
					if (childNode.getName() == "narrower") {
						List<Attribute> attrs = childNode.getAttributes();
						concept.addChild(attrs.get(0).getValue());
					}
					if (childNode.getName() == "related") {
						List<Attribute> attrs = childNode.getAttributes();
						concept.addChild(attrs.get(0).getValue());
					}
				}
				concepts.addConcepts(concept);
			}
		}

		System.out.println("Total Concepts = "+concepts.size());
	}

	public static void main(String[] args) {
		ACMSearch acm = new ACMSearch();
		acm.search("Data cleaning");
	}

}
