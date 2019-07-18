package com.philips.casestudy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.philips.casestudy.AnalyserConsoleInteractor;
public class MainContainer {

	public static void main(String[] args) {
	
		String projectname;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Project name");
		projectname=sc.next();
		System.out.println("Enter the Code Analyser:\n1. FindBugs\n2. PMD");
		int option=sc.nextInt();
		switch(option)
		{
		case 1:
			try {
				ArrayList<FindBugsData> buglist=new ArrayList<FindBugsData>();
				AnalyserConsoleInteractor.findBugCommand(projectname);
				buglist=FindBugGetter.getBugs(projectname);
				BugWriter.writeData(buglist,projectname);
			}
			catch(Exception e)
			{e.printStackTrace();}
			break;
		case 2:
			try {
				ArrayList<PMDData> buglist=new ArrayList<PMDData>();
				AnalyserConsoleInteractor.pmdCommand(projectname);
				buglist=FindBugGetter.getPMD(projectname);
				BugWriter.writeDataPMD(buglist,projectname);
			}
			catch(Exception e)
			{e.printStackTrace();}
			
		}
		
		sc.close();
	}
}
