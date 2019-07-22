package com.philips.casestudy;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AnalyserConsoleInteractor {
	
	public static void CreateXmlFile(String []command,String binPath) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(command);
		Map<String, String> envMap = pb.environment();
		String path = envMap.get("Path");
		path +=binPath;
		envMap.put("Path", path);
		pb.start();
		TimeUnit.SECONDS.sleep(5);
	}

}
