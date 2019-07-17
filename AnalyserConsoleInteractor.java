package com.philips.casestudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class AnalyserConsoleInteractor {

	public static void findBugCommand(String projectName) throws IOException
	{
	ProcessBuilder folderChange = new ProcessBuilder("cmd.exe","/c","cd C:\\Users\\320066613\\findbugs-3.0.1\\bin","& findbugs -textui -maxHeap 1500 -nested:false "
			+ "-output "+projectName+".xml -effort:max -low -sortByClass -xml "
			+ "sourcepath C:\\Users\\320066613\\eclipse-workspace\\" + 
    		projectName);
	        folderChange.redirectErrorStream(true);
	        Process p = folderChange.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	        
	    }
	    
	    public static void main(String[] args) {
			try {
				findBugCommand("com.philips.casestudy");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
