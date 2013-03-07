package preprocessing;


import java.io.*;
import java.util.*;

import javax.xml.stream.events.Comment;
import datastr.Tweet;
import datastr.Word;
import datastr.Dataset;
import pilot.CommanderInChief;


/* This class will load the tweets file from Resources folder and preprocess them.
 * Pre-processing include: Removal of stop words, removal of urls.
 * This is followed by creating a wordList for each tweet and storing it in the corresponding 
 * tweet class
 * 
 * This class populates the dataset.
 */

@SuppressWarnings("unused")
public class Preprocessor {
	
	public String filename;
	public Dataset dataset;
	public String stop[];
	public int stopcount;
	
	
	public Preprocessor(String filename, Dataset dataset) throws IOException
	{
		this.stop=new String[1000];
		this.filename=filename;
		this.dataset=dataset;
		
		System.out.println("Check2");
		String file=filename.substring(filename.lastIndexOf('/')+1,filename.length());
		if(CommanderInChief.DEBUG_MODE == 0) System.out.println("Preprocessing tweets");
		// TODO: Make the stop word removal efficient by utilizing the Hashmap stopWords created at Dataset.  
		populateStopWords();
		System.out.println("Check3");
		fillTweets(filename,dataset);
		
		if(CommanderInChief.DEBUG_MODE == 1)
		{
			System.out.println("Filename = "+filename);
			System.out.println("Size of dataset from Preprocess = "+dataset.Tweets.size());
		}
	}
	
	public void populateStopWords()
	{		
            BufferedReader br;
			try {
				stopcount=0;
				br = new BufferedReader(new FileReader("src/resources/stopwords.txt"));
			    String word = br.readLine();
			    dataset.stopWords = new HashMap<String,String>();
			    while(word != null)
			    {
			    	word=word.trim();
			    	if(dataset.stopWords.containsKey(word)==false)
			    	{
			    		dataset.stopWords.put(word, word);
			    		stop[stopcount++]=word;
			    	}
			    	word = br.readLine();            	  
			    }
			    br.close();
				} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		System.out.println("Size of StopWords Hash Table = "+dataset.stopWords.size()+" value of stopcount= "+stopcount);
	}
	
	public void fillTweets(String filename, Dataset dataset)
	{
		/* This function will take in the text from the filename and process each tweet and adds it to the Dataset class
		 * variable Tweets
		 */
		int totalWordCount = 0;
		try{
			BufferedReader  brd = new BufferedReader(new FileReader(filename));
            String line = brd.readLine();
            int tweetNo=0;
            while(line != null)
            {
            	tweetNo++;
            	Tweet tw = new Tweet(line);
            	line = tw.getText();
            	line = cleanText(line);
				line = line.toLowerCase();
				line = removeStopWords(line);
				tw.setText_no_sw(line);
				line = line.trim();
				ArrayList<Word> wrdlst = new ArrayList<Word>();
            	String token[] = line.split(" ");//extracting words from the that particular tweet--stop words removed
            	int l=token.length;totalWordCount+=l;
            	for(int i=0;i<l;i++)
            	{
            		token[i]=token[i].trim();
            		Iterator<Word> it=wrdlst.iterator();
            		int flag=0;
            		while(it.hasNext())
            		{
            			Word wt=(Word) it.next();
            			if(wt.word.equals(token[i]))
            			{
            				wt.count++;
            				//if(wt.count > 5) {System.out.println("---------------"+wt.word+"__in__"+line);}
            				if(token[i].contains("start")) System.out.println("!!!!!!!!!!!!!!!"+wt.count);
            				flag=1;break;
            			}
            		}
            		if(flag==0)
            		{
            			Word w=new Word(token[i],1);
            			w.idf=1;
            			wrdlst.add(w);
            		}
            	}
            	tw.setWordList(wrdlst);
            	// check the wrdlist now
    			Iterator<Word> it = wrdlst.iterator();
    			while (it.hasNext())
    			{
    				Word ww = it.next();
    				if(ww.count > 5) System.out.println(ww.word+"__"+ww.count);
    			}	
            	dataset.Tweets.add(tw);
            	line = brd.readLine();            	  
            }
            brd.close();
            if(CommanderInChief.DEBUG_MODE == 1)
            {
            	System.out.println("Total no of tweets= "+tweetNo);
            }
         }catch(Exception e){System.out.println("ERROR IN PREPROCESS CATCHED"+e.getMessage());e.printStackTrace();}
	}
	
	public String cleanText(String line)
	{
		line = line.replaceAll("\\s*RT\\s*@\\w+:\\s*","");  //removes "RT @other_user:"
		line = line.replaceAll("\\s*@\\w+\\s*","");  //removes "@other_user"
		line = line.replaceAll("https?:[^\\s]*","");  //removes "http://foo" "https://bar"
		return line;
	}
	
	public String removeStopWords(String line)
	{
		try{
			int i=0;
			String word;
			for(i=0;i<stopcount;i++)
			{
				word=stop[i];
            	String temp=" "+word+" ";
            	word=temp;
            	while(line.contains(word))line=line.replaceAll(word," ");            	  
            }
         }catch(Exception e){}
		return line;
	}
}
