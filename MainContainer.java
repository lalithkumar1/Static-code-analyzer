
package com.philips.casestudy;

import java.io.File;
import java.util.Scanner;

public class MainContainer {

	public static void main(String[] args) {
		System.out.println("Enter the Project Directory:\n");
		Scanner sc = new Scanner(System.in);
		String projectdir = sc.next();
		File dir=new File(projectdir);
		if(dir.exists()) {
		try {
			System.out.println("Enter the Project Name:\n");
			String projectname=sc.next();
			AnalyserConsoleInteractor.CreateXmlFile(Commands.GetFindbugCommand(projectdir,projectname),Commands.FindBugsBinPath,projectname, "findbugs");
			AnalyserConsoleInteractor.CreateXmlFile(Commands.GetPmdCommand(projectdir,projectname),Commands.PmdBinPath,projectname, "pmd");
			System.out.println("Excel Bug file is created at "+Commands.destinationPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else {
			System.out.println("The project does not exist in the provided directory");
		}
		sc.close();
	}
}