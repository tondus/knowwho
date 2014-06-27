package com.siriwan.knowwho.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Concepts {
	private HashMap<String, Concept> concepts = new HashMap<String, Concept>();

	public Concept getConcept(String id) {
		if(this.concepts.containsKey(id)){
			return concepts.get(id);
		}else{
			return null;
		}
	}
	public ArrayList<Concept> getAllConcepts(){
		ArrayList<Concept> concepts = new ArrayList<Concept>();
		Iterator<String> it = this.concepts.keySet().iterator();
		while (it.hasNext()) {
			String conceptID = (String) it.next();
			concepts.add(this.concepts.get(conceptID));
		}
		return concepts;
	}
	public void addConcepts(Concept c) {
		if(!this.concepts.containsKey(c.getId())){
			this.concepts.put(c.getId(), c);
		}
	}
	public int size(){
		return concepts.size();
	}

	public HashMap<String, Concept> getConcepts() {
		return concepts;
	}
 
	
}
