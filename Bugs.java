package com.philips.casestudy;


public interface Bugs<T> {
	public void getBugs(String projectName, String analyserName);
	public  void writeData(String Classname, String AnalysisType);
}
