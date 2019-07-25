package com.philips.casestudy;

class Commands {
	static String FindBugsBinPath="C:\\Users\\320065410\\Downloads\\findbugs-3.0.1\\findbugs-3.0.1\\bin";
	static String PmdBinPath="C:\\Users\\320065410\\Downloads\\pmd-bin-6.16.0\\pmd-bin-6.16.0\\bin";
	static String destinationPath="C:\\Users\\320065410\\OneDrive - Philips\\Desktop\\Reports\\";
	public static String[] GetFindbugCommand(String projectdir,String projectName) {
	String []FindbugCommand={ "cmd", "/c","findbugs", "-textui", "-maxHeap", "1500", "-nested:false", "-output",
			destinationPath + projectName + "findbugs.xml",
			"-effort:max", "-low", "-sortByClass", "-xml", "sourcepath",
			"C:\\Users\\320065410\\eclipse-workspace\\MyTraining" };
	return FindbugCommand;
	}
	
	public static String[] GetPmdCommand(String projectdir,String projectName) {
		String []PmdCommand= { "cmd", "/c","pmd", "-d", "C:\\Users\\320065410\\eclipse-workspace\\MyTraining", "-f",
				"xml", "-R", "rulesets/java/quickstart.xml", ">",
				destinationPath + projectName + "pmd.xml" };
		
		return PmdCommand;
	}
}
