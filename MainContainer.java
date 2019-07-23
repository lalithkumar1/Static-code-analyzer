package com.philips.casestudy;

import java.util.Scanner;

public class MainContainer<T> {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Project name:");
		String projectname = sc.next();
		try {
			AnalyserConsoleInteractor.findBugCommand(Commands.GetFindbugCommand(projectname),Commands.FindBugsBinPath,projectname, "findbugs");
			AnalyserConsoleInteractor.findBugCommand(Commands.GetPmdCommand(projectname),Commands.PmdBinPath,projectname, "pmd");
		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}
}
