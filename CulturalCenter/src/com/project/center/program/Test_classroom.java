package com.project.center.program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import data.Path;

public class Test_classroom {
	
	public static void main(String[] args) {
		
		final String PATH = Path.PROGRAMLIST;
		File file = new File(PATH);
		String input="삭제할 강의실: ";
		System.out.println(input);

		try
		           {        
		               BufferedReader reader = new BufferedReader
		                       (new InputStreamReader (System.in));
		               input = reader.readLine();

		               String inFile="list.txt";
		               String line = "";


		               while(!line.equals("x"))
		               { 
		                   switch(line)
		                   {                   

		                       case "d":

		                       line = reader.readLine();
		                       System.out.println("Remove: " + line);
		                       String lineToRemove="";

		                       FileWriter removeLine=new FileWriter(inFile);
		                       BufferedWriter change=new BufferedWriter(removeLine);
		                       PrintWriter replace=new PrintWriter(change);

		                       while (line != null) {
		                          if (!line.trim().equals(lineToRemove))
		                          {
		                                replace.println(line);
		                                replace.flush();
		                          }   
		                       }
		                       replace.close();
		                       change.close();
		                       break;

		                   }
		                   System.out.println(input);
		                   line = reader.readLine();  


		               }

		           }
		           catch(Exception e){
		               System.out.println("삭제할 수 없는 강의실 입니다.");
		           }
		
		
		
	}

}
