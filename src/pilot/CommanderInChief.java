package pilot;

import java.io.*;
import java.util.*;

import datastr.*;
import preprocessing.*;
import preprocessing.Dictionary;

public class CommanderInChief {
	
	public static String FILENAME = "src/resources/dataset/training.txt";
	
	
	public static int DEBUG_MODE = 0; 
	
	public static void main(String[] args) throws IOException {
		System.out.println("Ahoy!");
		Dataset dataset = new Dataset();
		System.out.println("Check1");
		new Preprocessor(FILENAME, dataset);
		new Dictionary(dataset);
		
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/dataset/train.txt"));
		Iterator<Tweet> it=dataset.Tweets.iterator();
		int counter=0;
		while(it.hasNext())
		{
			StringBuffer sample=new StringBuffer("");
			Tweet t=(Tweet) it.next();
			int[] bowr = t.getBagOfWordsRepresentation();
			int l = t.getWordList().size();
			/*ArrayList<Word> al = t.getWordList();
			for(int i=0;i<l;i++)
			{
				bw.write(al.get(i).word+"---"+al.get(i).count+"\n");
			}*/
			for(int i=0;i<bowr.length;i++)
			{
				bw.write(bowr[i]+",");
			}
			bw.write(t.getLabel()+"\n");
		}
		bw.close();
	}
}
