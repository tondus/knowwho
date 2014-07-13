package com.siriwan.knowwho.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUtil {
	public static String readFileToString(String filename){
		String result = "";
		try {
			File fileDir = new File(filename);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileDir), "UTF8"));

			String str;

			while ((str = in.readLine()) != null) {
				//				System.out.println(str);
				result += str+"\n";
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

	public static String readFileAsString(String filePath) {
		StringBuffer fileData = new StringBuffer();
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			char[] buf = new char[1024];
			int numRead=0;
			while((numRead=reader.read(buf)) != -1){
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileData.toString();
	}

	public static ArrayList<String> readFileToList(String filename){
		ArrayList<String> lines = new ArrayList<String>();
		try {
			File fileDir = new File(filename);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileDir), "UTF8"));

			String str;

			while ((str = in.readLine()) != null) {
				lines.add(str);
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
		return lines;
	}
	
	public static HashMap<String,Integer> readFileToHashMapWithLowerCase(String filename){
		HashMap<String, Integer> words = new HashMap<String, Integer>();
		try {
			File fileDir = new File(filename);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileDir), "UTF8"));

			String line;

			while ((line = in.readLine()) != null) {
				String[] list = line.split("\\t");
				for (String str : list) {
					str = str.toLowerCase().trim();
					if(!words.containsKey(str)){
						words.put(str, 1);
					}else{
						words.put(str, words.get(str)+1);
					}
				}
				
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
		return words;
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
