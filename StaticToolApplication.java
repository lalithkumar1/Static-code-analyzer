
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class StaticToolApplication {
	public static String projectname, projectdir;
	public static Scanner sc = new Scanner(System.in);

	public static void userInputs() {
		System.out.println("Enter the Project Directory:\n");
		projectdir = sc.next();
		File dir = new File(projectdir);
		while (!dir.exists()) {
			System.out.println("The project does not exist in the provided directory");
			System.out.println("Enter the correct Project Directory:\n");
			projectdir = sc.next();
			dir = new File(projectdir);
		}
		String dirs[] = projectdir.split("\\\\");
		projectname = dirs[dirs.length - 1];
		System.out.println("Enter the destination file path:\n");
		Commands.destinationPath = sc.next();
		dir = new File(Commands.destinationPath);
		while (!dir.exists()) {
			System.out.println("Destination path not found\n");
			System.out.println("Enter the destination file path Correctly:\n");
			Commands.destinationPath = sc.next();
			dir = new File(Commands.destinationPath);
		}
		Commands.destinationPath += "\\";
	}

	public static void analyze() throws IOException, InterruptedException, ExecutionException {
		StaticToolAnalyzer.generateReport(Commands.GetFindbugCommand(projectdir, projectname),
				Commands.FindBugsBinPath, "findbugs");
		StaticToolAnalyzer.generateReport(Commands.GetPmdCommand(projectdir, projectname), Commands.PmdBinPath,
				"pmd");
		System.out.println("Excel Bug file " + projectname + ".xlsx is created at " + Commands.destinationPath);
	}

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		// C:\Users\320065410\eclipse-workspace\MyTraining
		// C:\Static-code-analyzer
		boolean flag = true;
		while (flag) {
			userInputs();
			analyze();
			System.out.println("Press 0 to Exit\nPress 1 to Continue with next project\n");
			if (sc.nextInt() == 0)
				flag = false;
		}
		sc.close();
	}

}