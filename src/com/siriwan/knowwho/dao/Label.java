package com.siriwan.knowwho.dao;

public class Label {
	private String value;
	private String lang;
	private boolean isPrefLabel;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public boolean isPrefLabel() {
		return isPrefLabel;
	}
	public void setPrefLabel(boolean isPrefLabel) {
		this.isPrefLabel = isPrefLabel;
	}
}
