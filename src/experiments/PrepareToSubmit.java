package experiments;

import java.io.*;

public class PrepareToSubmit {
	
	public static String FILENAME = "src/resources/dataset/prediction.txt";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("src/resources/dataset/prediction.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("src/resources/dataset/validation.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("src/resources/dataset/prediction_submit.txt"));
		String line = br.readLine();
		String line2 = br2.readLine();
		int c=0;
		while(line!=null || line2!=null)
		{
			c++;
			String twid = line2.substring(0,line2.indexOf(" "));
			twid = twid.trim();
			System.out.println(line+"______"+line2);
			if(line.contains("POLITICS")) out.write(twid+" Politics");
			else out.write(twid+" "+"Sports");
			out.write("\n");
			line = br.readLine();
			line2 = br2.readLine();
			if(line == null && line2 != null) System.out.println("prediction got over, somethng still  left in validation");
			if(line != null && line2 == null) System.out.println("validation got over, somethng still  left in prediction");
		}
		System.out.println("TotalNo of lines written: "+c);
		out.close();
	}

}
