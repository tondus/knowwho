package com.siriwan.knowwho.file;

import java.util.HashMap;
import java.util.Iterator;

public class WordCounter {
	public WordCounter(String source,String target){
		HashMap<String, Integer> wordsSource = FileUtil.readFileToHashMapWithLowerCase(source);
		HashMap<String, Integer> wordsTarget = FileUtil.readFileToHashMapWithLowerCase(target);
	 	Iterator<String> it =  wordsSource.keySet().iterator();
	 	while (it.hasNext()) {
			String wordSource = (String) it.next();
			if(wordsTarget.containsKey(wordSource)){
				System.out.println(wordSource+"----"+wordsSource.get(wordSource));
			}
		}
	}
}
