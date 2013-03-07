/*
 * Date Created: March 2
 *  @author: Rishabh, Aqueel
 */

package datastr;

import java.util.UUID;

public class Word {
	
	public String word;
	public int count;
	public double idf;
	public  UUID id;
	public int index;
	public Word(String word, int count)
	{
		this.word=word;
		this.count=count;
		this.idf=0;
		
	}

}
