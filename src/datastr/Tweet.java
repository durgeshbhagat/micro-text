/*
 * Date Created: March 2
 *  @author: Rishabh, Aqueel
 */

package datastr;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Tweet {
	
	private String user;
	private String name;
	private Date date;
	private String text;
	private String text_no_sw;
	private ArrayList<Word> wordList = new ArrayList<Word>(); // after removing stop words
	public long tweetID;
	public int[] bagOfWordsRep;
	
	@SuppressWarnings("unused")
	private String url;
	
	private int label;
	
	// TODO: modify this once we know the format of the dataset.
	public Tweet(String tweet) throws ParseException
	{
		this.tweetID = Long.parseLong(tweet.substring(0, tweet.indexOf(" ")));
		tweet = tweet.substring(tweet.indexOf(" ")+1, tweet.length());
		tweet = tweet.trim();
		//  CHANGED TO SUIT VALIDATION DATA, 
		/*String temp = tweet.substring(0, tweet.indexOf(" "));
		if(temp.equals("Politics")) this.label = 0;
		else this.label = 1;
		*/
		
		tweet = tweet.substring(tweet.indexOf(" ")+1, tweet.length());
		tweet = tweet.trim();
		
		this.text = tweet.toLowerCase();
		//cleanText(); // not sure about this comment out    
	}                                                       

	
	
	//--------------------------------------------------------   
	
	
	
	public int[] getBagOfWordsRepresentation(){
		return bagOfWordsRep;
	}
	
	public void setBagOfWordsRepresentation(int[] BOWR){
		this.bagOfWordsRep= new int[BOWR.length];
		for(int i=0;i<BOWR.length;i++)
		{
			this.bagOfWordsRep[i] = BOWR[i];
		}
		//bagOfWordsRep=BOWR;
		//System.out.println(Arrays.toString(bagOfWordsRep));
	}
	
	public String getUser()
	{
		return this.user;
	}
	public String getName()
	{
		return this.name;
	}
	public int getLabel()
	{
		return this.label;
	}
	public Date getDate()
	{
		return this.date;
	}
	public String getText()
	{
		return this.text;
	}
	public String getTest_no_sw()
	{
		return this.text_no_sw;
	}
	public ArrayList<Word> getWordList()
	{
		return this.wordList;
	}
	//--------------------------------------------------------
	
	public void setUser(String user)
	{
		this.user=user;
	}
	public void setLabel(int label)
	{
		this.label=label;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setDate(Date date)
	{
		this.date=date;
	}
	
	public void setText(String text)
	{
		this.text=text;
	}
	public void setText_no_sw(String text)
	{
		this.text_no_sw=text;
	}
	public void setWordList(ArrayList<Word> wordList)
	{
		this.wordList=wordList;
	}
	
	//--------------------------------------------------------
	
	public void cleanText()
	{
		this.text=text.replaceAll("'"," ");
		this.text=text.replaceAll(","," ");
		this.text=text.replaceAll(": "," ");
		this.text=text.replaceAll("!"," ");
		this.text=text.replaceAll("-"," ");
		//this.text=text.replaceAll("#"," ");
		//text.replaceAll("?"," ");
		//text.replaceAll("("," ");
		//text.replaceAll(")"," ");
	}
	
}

