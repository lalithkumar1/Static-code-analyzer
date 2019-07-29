
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

class StaticToolAnalyzer {

	public static void generateReport(String[] command, String binPath, String analyserType)
			throws IOException, InterruptedException, ExecutionException {
		ProcessBuilder pb = new ProcessBuilder(command);
		Map<String, String> envMap = pb.environment();
		String path = envMap.get("Path");
		path += binPath;
		envMap.put("Path", path);
		pb.start();
		Thread.sleep(6000);
		System.out.println(analyserType + " XML file is created\n");
		Parse.parseXml(StaticToolApplication.projectname, analyserType);
	}

}
