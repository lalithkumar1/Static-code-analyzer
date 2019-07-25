


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainContainer {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Enter the Project Directory:\n");
		Scanner sc = new Scanner(System.in);
		String projectdir = sc.next();
		File dir = new File(projectdir);
		while (!dir.exists()) {
			System.out.println("The project does not exist in the provided directory");
			System.out.println("Enter the correct Project Directory:\n");
			projectdir = sc.next();
			dir = new File(projectdir);
		}
		String dirs[] = projectdir.split("\\\\");
		String projectname = dirs[dirs.length - 1];
		System.out.println(projectname);
		System.out.println("Enter the destination file path:\n");
		Commands.destinationPath = sc.next();
		dir = new File(Commands.destinationPath);
		while (!dir.exists()) {
			System.out.println("Destination path not found\n");
			System.out.println("Enter the destination file path Correctly:\n");
			Commands.destinationPath = sc.next();
			dir = new File(Commands.destinationPath);
		}
		Commands.destinationPath+="\\";
		
		AnalyserConsoleInteractor.CreateXmlFile(Commands.GetFindbugCommand(projectdir, projectname),Commands.FindBugsBinPath, projectname, "findbugs");
		AnalyserConsoleInteractor.CreateXmlFile(Commands.GetPmdCommand(projectdir, projectname), Commands.PmdBinPath,projectname, "pmd");
		System.out.println("Excel Bug file is created at " + Commands.destinationPath);

		sc.close();
	}
}