package preprocessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import pilot.CommanderInChief;

import datastr.*;

/* This class will use the Tweets arraylist of Dataset
 * and build a dictionary of all words found in the Tweets for this dataset
 */
@SuppressWarnings("unused")
public class Dictionary {

	public Dictionary(Dataset dataset) {
		int i, j, k, l, m, n;
		int len = dataset.Tweets.size();
		Iterator<Tweet> itr = dataset.Tweets.iterator();
		int tempidf = 0;
		int twno = 0;
		int index = 0;
		int counter = 0;
		
		sanityCheck(dataset);
		//-------------------------------------------------------------
		
		while (itr.hasNext()) {
			// now deal with each tweet and insert the words in each tweet in
			// Dictionary if its not present already
			tempidf = 0;
			twno++;
			int flag = 0;
			Tweet tw = itr.next();
			ArrayList<Word> list = tw.getWordList();
			Iterator<Word> wrditr = list.iterator();
			//Word tempw;
			tempidf = 0;
			// for each word in this particular tweet modify the dictionary &
			// wcl.list accordingly

			while (wrditr.hasNext()) {
				Word w = wrditr.next();
				w.word = w.word.trim();
				w.word = w.word.toLowerCase();
				String temp = w.word;
				if (temp.length() > 1) {
					// ---------------------------------------------insert in
					// Dictionary start---------------------------------------
					if (dataset.dictionary.containsKey(temp))
					{
						
						Word tempw = dataset.dictionary.get(temp);
						/*if (tempw.index == 0) {
							System.out.println("something wrong!!");
							System.exit(0);
						}*/
						int t = w.count;
						tempw.count = tempw.count + t;
						tempw.idf++;
						dataset.dictionary.put(tempw.word, tempw);// added this on 6th June 2012
					}
					else
					{
						// if(index==0)
						// System.out.println("anomaly counter ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
						// +counter++);
						// System.exit(0);
					//	if(w.count > 5) System.out.println("~~!!@@~~!!@@!~~~~~~~"+w.word+"_"+w.count);
						Word tempw = new Word(w.word,w.count);
						tempw.id = UUID.randomUUID();
						tempw.index = index++;
						// System.out.println("Index = _"+index);
						dataset.dictionary.put(tempw.word, tempw);
					}
					// ---------------------------------------------insert in
					// Dictionary end---------------------------------------
				}
			}
		}
		sanityCheck(dataset);
		//System.exit(0);
		if (CommanderInChief.DEBUG_MODE == 1)
			System.out.println("Total Dictionary elements Count= "
					+ dataset.dictionary.size());
		// our dictionary has been populated now...Yay!
		// we have the dictionary with each word having the corresponding word
		// count...
		populateDict4Rep(dataset);
		generateBOWM(dataset);
	}
	
	public void sanityCheck(Dataset dataset)
	{
		Iterator<Tweet> itr = dataset.Tweets.iterator();
		while(itr.hasNext())
		{
			Tweet tt =itr.next();
			Iterator<Word> it = tt.getWordList().iterator();
			while (it.hasNext())
			{
				Word ww = it.next();
				if(ww.count > 5) System.out.println(ww.word+"__"+ww.count);
			}
		}
		//System.exit(0);
	}

	public void populateDict4Rep(Dataset dataset) {
		int count = 0;
		int index = 1;
		int totalUniqueWordCount = dataset.dictionary.size();
		Iterator<String> itr = dataset.dictionary.keySet().iterator();
		while (itr.hasNext()) {
			String word = itr.next();
			Word w = dataset.dictionary.get(word);
			if (w.count > 2) {
				count++;
				// System.out.println(w.word+"__"+w.count);
				w.index = index++;
				dataset.dictionary4Rep.put(word, w);
			}
		}
		System.out.println("dictionary4rep populated with _" + count
				+ "_number of words");
		System.out.println("\n populating the BOWR now...");
		// System.exit(0);
	}

	public void generateBOWM(Dataset dataset) {
		int totalUniqueWordCount4Rep = dataset.dictionary4Rep.size();
		System.out.println("totalUniqueWordCount4Rep =_"
				+ totalUniqueWordCount4Rep);
		//System.exit(0);
		Iterator<Tweet> iter = dataset.Tweets.iterator();
		int bagOfWordsRep[] = new int[totalUniqueWordCount4Rep + 1];
		
		while (iter.hasNext())
		{
			Arrays.fill(bagOfWordsRep, 0);
			Tweet tw = (Tweet) iter.next();
			ArrayList<Word> wrdlst = tw.getWordList();
			Iterator<Word> it = wrdlst.iterator();

			// System.out.println(totalUniqueWordCount);

			while (it.hasNext())
			{
				Word wt = it.next();
				//System.out.println(wt.count+"__"+wt.word);
				if (dataset.dictionary4Rep.containsKey(wt.word))
				{
					Word w = dataset.dictionary4Rep.get(wt.word);
					bagOfWordsRep[w.index] += wt.count;
					if(wt.count > 2)
					{
						System.out.println("!!~~~~~~~~at_"+w.index+"__count= "+wt.count+"__++__"+wt.word+"__++_"+tw.getText());
						//System.exit(0);
					} 
					//System.out.println(w.word + w.index);
					// System.out.println("one written");
				}
			}
			tw.setBagOfWordsRepresentation(bagOfWordsRep);
		}
	}
}
