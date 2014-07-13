package com.siriwan.knowwho.file;

import java.util.HashMap;

public class WordCounter {
	public WordCounter(String source,String target){
		HashMap<String, String> source = FileUtil.readFileToHashMapWithLowerCase(source);
		HashMap<String, String> target = FileUtil.readFileToHashMapWithLowerCase(target);
	}
}
