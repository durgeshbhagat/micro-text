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
			//sample.append("Label:\t").append(t.getLabel()).append("\tTweet:\t"+t.getTest_no_sw()).append("\t Representation:\t").append(Arrays.toString(t.getBagOfWordsRepresentation())).append('\n');
			int[] bowr = t.getBagOfWordsRepresentation();
			//bw.write(t.getTest_no_sw()+"\n");
			int l = t.getWordList().size();
			/*ArrayList<Word> al = t.getWordList();
			for(int i=0;i<l;i++)
			{
				bw.write(al.get(i).word+"---"+al.get(i).count+"\n");
			}
			bw.write("---------------------------------"+bowr[793]);*/
			for(int i=0;i<bowr.length;i++)
			{
				bw.write(bowr[i]+",");
				//if(bowr[i] >= 1) bw.write("___");
			}
			bw.write(t.getLabel()+"\n");
			//bw.write(sample.toString()+'\n');
			//if(++counter>50)break;
			//System.out.println(sample.toString()+'\n');
		}
		bw.close();
	}
}
