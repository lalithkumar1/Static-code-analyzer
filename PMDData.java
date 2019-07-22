package com.philips.casestudy;

public class PmdData {
	String className;
	String ruleSet;
	String rule;
	String content;
	String line;

	public void setruleSet(String type) {
		this.ruleSet = type;
	}

	public void setrule(String category) {
		this.rule = category;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setContent(String className) {
		this.content = className;
	}

	public String getrule() {
		return rule;
	}

	public String getruleSet() {
		return ruleSet;
	}

	public String getClassName() {
		return className;
	}

	public String getContent() {
		return content;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String string) {
		this.line = string;
	}
}
