package com.philips.casestudy;

import java.io.PrintWriter;
import java.util.ArrayList;

public class BugWriter {
	public static void writeData(ArrayList<FindBugsData> buglist, String Classname) throws Exception
	{
		try {
		PrintWriter pw  = new PrintWriter("C:\\Users\\320066613\\ErrorList\\"+Classname+".txt", "UTF-8");
		for (FindBugsData f : buglist)
		{
			pw.println("Error Category: "+f.getCategory());
			pw.println("Error type: "+f.getType());
			pw.println("in Class: "+f.getClassName()+"\n");
		}
		pw.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		}
}
