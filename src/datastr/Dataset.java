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
	
	public ArrayList<Tweet> Tweets;
	public HashMap<String,Word> dictionary;
	public Date minDate;
	public Date maxDate;
	public HashMap<String,String> stopWords;

}
