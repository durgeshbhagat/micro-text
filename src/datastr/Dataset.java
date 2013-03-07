/*
 * Date Created: March 2
 *  @author: Rishabh, Aqueel
 */

package datastr;

import datastr.Tweet;
import datastr.Word;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Dataset {
	
	public ArrayList<Tweet> Tweets = new ArrayList<Tweet>();;
	public HashMap<String,Word> dictionary = new HashMap<String,Word>();
	public Date minDate;
	public Date maxDate;
	public HashMap<String,String> stopWords;

}
