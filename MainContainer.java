package com.philips.casestudy;
import java.util.Scanner;

public class MainContainer<T> {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Project name:");
		String projectname = sc.next();
		System.out.println("Enter the Code Analyser:\n1. FindBugs\n2. PMD");
		String option = sc.next().toLowerCase();
			try {
				AnalyserConsoleInteractor.findBugCommand(projectname,option);
			
			}catch (Exception e) {
				e.printStackTrace();
			}

		sc.close();
	}
}
