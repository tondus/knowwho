package com.siriwan.knowwho;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;

import com.siriwan.knowwho.dao.Concept;
import com.siriwan.knowwho.dao.Concepts;
import com.siriwan.knowwho.dao.Label;
import com.siriwan.knowwho.file.FileUtil;
import com.siriwan.knowwho.xml.XmlParserHelper;

public class ACMSearch {
	private final static String ACM = "ontology/ACMComputingClassificationSystemSKOSTaxonomy.xml";
	private Concepts concepts;
	
	public ACMSearch(){
		this.concepts = loadACMModel();
		System.out.println("Total Concept = "+this.getConceptCount()+" concepts.");
	}
	
	public ArrayList<Concept> search(String query){
		ArrayList<Concept> result = new ArrayList<Concept>();
		Iterator<String> it = this.concepts.getConcepts().keySet().iterator();
		while (it.hasNext()) {
			String conceptId = (String) it.next();
			Concept concept = this.concepts.getConcept(conceptId);
			ArrayList<Label> labels = concept.getLabels();
			for (Label label : labels) {
				if(label.getValue().toLowerCase().contains(query.toLowerCase())){
					System.out.println(concept.getId());
					System.out.println(label.getValue());
					result.add(concept);
				}
			}
		}
		return result;
	}
	
	public int getConceptCount(){
		return this.concepts.size();
	}
	
	private Concepts loadACMModel(){
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
						concept.addLabels(label);
						
					}
					if (childNode.getName() == "altLabel") {
						List<Attribute> attrs = childNode.getAttributes();
						Label label = new Label();
						label.setLang(attrs.get(0).getValue());
						label.setValue(childNode.getValue());
						label.setPrefLabel(false);
						concept.addLabels(label);
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
						concept.addRelated(attrs.get(0).getValue());
					}
				}
				concepts.addConcepts(concept);
			}
		}

		return concepts;
	}
}
