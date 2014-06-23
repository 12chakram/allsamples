package com.ku.learn;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;
 
public class MaxDuplicateWordCount1 {
 
	
	
public void urlReaderAndWriteIntoFile() throws Exception{
		
		
URL oracle = new URL("http://blog.covestor.com/feed");

int contentcount = 0;

//URL oracle = new URL("http://investing.covestor.com/feed");


		try{
			
	
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
		String inputLine;
		boolean addFlag = false;
		
		File file = new File("G:/interview/filename.xml");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
	
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		while((inputLine = in.readLine())!=null){
			if(inputLine.trim().contains("<content:encoded>") || addFlag)
				addFlag = true;
			
			if(inputLine.trim().contains("</content:encoded>"))	
				 addFlag = false;
			
			if(addFlag || inputLine.endsWith("</content:encoded>") ){
				
				System.out.println(inputLine);
				bw.write(inputLine);
			
				
			}
			
	}
		  bw.close();
		  in.close();
 
		  System.out.println("Done");
		  System.out.println(contentcount);
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}//urlreader
	




	public Map<String, Integer> getWordCount(String fileName){
		
		String tmp = null;
 
        FileInputStream fis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            
           
            
            String line = null; 
            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                
                	
                     tmp = st.nextToken().toLowerCase();
                    
                  
                    if(wordMap.containsKey(tmp)){
                        wordMap.put(tmp, wordMap.get(tmp)+1);
                    } else {
                        wordMap.put(tmp, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return wordMap;
    }
     
    public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){
         
        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
    }
     
    public static void main(String a[]){
    	
    	
        MaxDuplicateWordCount1 mdc = new MaxDuplicateWordCount1();
        
       
        try {
			mdc.urlReaderAndWriteIntoFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Map<String, Integer> wordMap = mdc.getWordCount("G:/interview/filename.xml");
        List<Entry<String, Integer>> list = mdc.sortByValue(wordMap);
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(entry.getKey()+" ==== "+entry.getValue());
        }
    }
}