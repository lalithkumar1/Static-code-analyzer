package com.philips.casestudy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.philips.casestudy.AnalyserConsoleInteractor;
public class MainContainer {

	public static void main(String[] args) {
	
		String projectname;
		ArrayList<FindBugsData> buglist=new ArrayList<FindBugsData>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Project name");
		projectname=sc.next();
		System.out.println("Enter the Code Analyser:\n1. FindBugs");
		int option=sc.nextInt();
		switch(option)
		{
		case 1:
			try {
				AnalyserConsoleInteractor.findBugCommand(projectname);
				buglist=FindBugGetter.getBugs(projectname);
				BugWriter.writeData(buglist,projectname);
			}
			catch(Exception e)
			{e.printStackTrace();}
			
		}
		
		sc.close();
	}
}
