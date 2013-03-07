package pilot;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

import datastr.*;
import preprocessing.*;

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
			sample.append("Label:\t").append(t.getLabel()).append("\t Representation:\t").append(Arrays.toString(t.getBagOfWordsRepresentation())).append('\n');
			
			bw.write(sample.toString()+'\n');
			if(++counter>50)break;
			System.out.println(sample.toString()+'\n');
			
		}
		bw.close();
	}
}
