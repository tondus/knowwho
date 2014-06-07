package com.siriwan.knowwho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileUtil {
	public static String readFile(String filename){
		String result = null;
		try {
			File fileDir = new File(filename);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileDir), "UTF8"));

			String str;

			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}

			in.close();
		} 
		catch (UnsupportedEncodingException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public static ArrayList<String> listFilesForFolder(final String folderName) {
	    ArrayList<String> files = new ArrayList<String>();
		File  folder= new File(folderName);
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry.getName());
	        } else {
	            files.add(fileEntry.getName());
	        }
	    }
		return files;
	}

}
