package com.siriwan.knowwho.dao;

import java.util.ArrayList;

public class Concept {
	private String id;
	private String lang;
	private String parent = null;
	private boolean hasParent = false ;
	private ArrayList<Label> labels = new ArrayList<Label>();
	private ArrayList<String> child = new ArrayList<String>();
	private ArrayList<String> related = new ArrayList<String>();
	private boolean isLeafNode;
	private boolean isRoot;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public ArrayList<Label> getLabels() {
		return labels;
	}
	public void addLabels(Label label) {
		this.labels.add(label);
	}
	public boolean hasParent() {
		return (this.parent == null) ? false : true;
	}
	public ArrayList<String> getChild() {
		return child;
	}
	public void addChild(String child) {
		if(!this.child.contains(child)){
			this.child.add(child);
		}
	}
	public ArrayList<String> getRelated() {
		return related;
	}
	public void addRelated(String related) {
		if(!this.related.contains(related)){
			this.related.add(related);
		}
	}
	public boolean isLeafNode() {
		return (this.child.size() == 0) ? true:false;
	}
	
	public boolean isRoot() {
		return (this.parent == null)?true:false;
	}
}
