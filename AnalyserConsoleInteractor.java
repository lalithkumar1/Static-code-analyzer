package com.philips.casestudy;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class AnalyserConsoleInteractor {
	
	public static void CreateXmlFile(String []command,String binPath,String projectName, String analyserType) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(command);
		Map<String, String> envMap = pb.environment();
		String path = envMap.get("Path");
		path +=binPath;
		envMap.put("Path", path);
		pb.start();
		TimeUnit.SECONDS.sleep(5);
		FindBugGetter.getBugs(projectName,analyserType);
	}

}
