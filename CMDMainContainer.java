package com.philips.casestudy;

import java.util.ArrayList;

public class CMDMainContainer {

	public static void main(String[] args) {

		ArrayList<FindBugsData> buglist = new ArrayList<FindBugsData>();
		switch (args[2]) {
		case "Finbugs":
		case "FindBugs":
		case "findbugs":
			try {
				AnalyserConsoleInteractor.findBugCommand(args[1]);
				buglist = FindBugGetter.getBugs(args[1]);
				BugWriter.writeData(buglist, args[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
