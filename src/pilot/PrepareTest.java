package pilot;

import java.io.*;
import java.util.*;

import datastr.*;
import preprocessing.*;
import preprocessing.Dictionary;


public class PrepareTest implements Serializable {
	
	public static HashMap<String,Word> dictionary4Rep;
	private static final long serialVersionUID = 1L;
	public static String FILENAME = "src/resources/dataset/validation.txt";

	public static void main(String[] args) throws IOException,ClassNotFoundException {
		
		loadMapFromFile();
		Dataset dataset = new Dataset();
		new Preprocessor(FILENAME, dataset);
		Dictionary d = new Dictionary(dataset);
		dataset.dictionary4Rep = dictionary4Rep;
		d.generateBOWM(dataset);
		writeRepToFile(dataset);
	}
	
	public static void writeRepToFile(Dataset dataset) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/resources/dataset/validateGEN.txt"));
		Iterator<Tweet> it=dataset.Tweets.iterator();
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


		bw = new BufferedWriter(new FileWriter("src/resources/dataset/WEKA_Validate.txt"));

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
			if(t.getLabel()==0){bw.write("?\n");}
			else{bw.write("?\n");}

		}

		bw.close(); 
	}
	
	public static void loadMapFromFile() throws IOException, ClassNotFoundException
	{
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/resources/dataset/dictionary4Rep"));
		dictionary4Rep = (HashMap<String,Word>) input.readObject();
		input.close();
		System.out.println("No of unique words in the dictionary:"+dictionary4Rep.size());
	}

}
