package com.philips.casestudy;

import java.util.Scanner;
import com.philips.casestudy.AnalyserConsoleInteractor;

public class MainContainer {

	public static void main(String[] args) throws Exception {

		String projectname;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Project name");
		projectname = sc.next();
		try {
			AnalyserConsoleInteractor.CreateXmlFile(Commands.GetFindbugCommand(projectname), Commands.FindBugsBinPath);
			Bugs<FindBugsData> findbugs=new StaticToolBugs<>();
			findbugs.getBugs(projectname, "findbugs");
			findbugs.writeData(projectname, "findbugs");
			
			AnalyserConsoleInteractor.CreateXmlFile(Commands.GetPmdCommand(projectname), Commands.PmdBinPath);
			Bugs<PmdData> pmd=new StaticToolBugs<>();
			pmd.getBugs(projectname, "pmd");
			pmd.writeData(projectname, "pmd");

		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}
}
