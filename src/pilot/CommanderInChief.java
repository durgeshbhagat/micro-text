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
		saveMapToFile(dataset);
		writeRepToFile(dataset);
	}
	
	public static void saveMapToFile(Dataset dataset) throws IOException
	{
		FileOutputStream fs = new FileOutputStream(new File("src/resources/dataset/dictionary4Rep"));
		ObjectOutputStream oos = new ObjectOutputStream(fs);
		oos.writeObject(dataset.dictionary4Rep);
		oos.close(); fs.close();
	}
	
	public static void writeRepToFile(Dataset dataset) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/dataset/train.txt"));
		Iterator<Tweet> it=dataset.Tweets.iterator();
		int counter=0;
		int numAttributes = dataset.Tweets.get(0).getBagOfWordsRepresentation().length;

		while(it.hasNext())
		{

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


		bw = new BufferedWriter(new FileWriter("src/resources/dataset/train2.txt"));

		bw.write("@RELATION twit\n");

		for(int i=0;i<numAttributes;i++){
			bw.write("@ATTRIBUTE W"+i+" NUMERIC"+"\n");
		}

		bw.write("@ATTRIBUTE class {POLITICS,SPORTS}");

		bw.write("\n\n@DATA\n");
		it=dataset.Tweets.iterator();
		while(it.hasNext())
		{
			Tweet t=(Tweet) it.next();
			int[] bowr = t.getBagOfWordsRepresentation();	
			for(int i=0;i<bowr.length;i++)
			{
				bw.write(bowr[i]+",");
			}
			if(t.getLabel()==0){bw.write("POLITICS\n");}
			else{bw.write("SPORTS\n");}

		}

		bw.close(); 
	}

}