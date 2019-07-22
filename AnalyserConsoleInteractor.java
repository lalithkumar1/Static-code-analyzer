package com.philips.casestudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnalyserConsoleInteractor {

	public static void findBugCommand(String projectName, String analyser) throws IOException {

		switch (analyser) {
		case "findbugs": {
			ProcessBuilder folderChange = new ProcessBuilder("cmd.exe", "/c",
					"cd C:\\Users\\320066613\\findbugs-3.0.1\\bin",
					"& findbugs -textui -maxHeap 1500 -nested:false "
							+ "-output C:\\Users\\320066613\\CodeAnalysis\\XMLfiles\\" + projectName
							+ "-FindBugs.xml -effort:max -low -sortByClass -xml "
							+ "sourcepath C:\\Users\\320066613\\eclipse-workspace\\" + projectName);
			FindBugGetter<FindBugsData> functionCaller = new FindBugGetter<FindBugsData>();
			folderChange.redirectErrorStream(true);
			Process p = folderChange.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
			functionCaller.getBugs(projectName, analyser);
		}
			break;
		case "pmd": {
			ProcessBuilder folderChange = new ProcessBuilder("cmd.exe", "/c",
					"cd C:\\Users\\320066613\\pmd-bin-6.16.0\\bin",
					"& pmd -d \"C:\\Users\\320066613\\eclipse-workspace\\" + projectName + "\""
							+ " -f xml -R rulesets/java/quickstart.xml> C:\\Users\\320066613\\CodeAnalysis\\XMLfiles\\"
							+ projectName + "-PMD.xml");
			FindBugGetter<PMDData> functionCaller = new FindBugGetter<PMDData>();
			folderChange.redirectErrorStream(true);
			Process p = folderChange.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
			functionCaller.getBugs(projectName, analyser);
		}
			break;

		}

	}
}
