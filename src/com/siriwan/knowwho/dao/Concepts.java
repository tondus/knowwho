package com.siriwan.knowwho.dao;

import java.util.HashMap;

public class Concepts {
	private HashMap<String, Concept> concepts = new HashMap<String, Concept>();

	public Concept getConcept(String id) {
		if(this.concepts.containsKey(id)){
			return concepts.get(id);
		}else{
			return null;
		}
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
