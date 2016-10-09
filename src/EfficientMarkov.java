
import java.util.*;
public class EfficientMarkov implements MarkovInterface<String> {
	private String myText;
	private Random myRandom;
	private int myOrder;
	private Map<String, ArrayList<String>> myMap;
	
	private static String PSEUDO_EOS = "";
	private static int RANDOM_SEED = 1234;
	
	public EfficientMarkov(int order){
		myOrder = order;
		myRandom = new Random(RANDOM_SEED);
	}
	

	
	public void setTraining(String text) {
		myText = text;
		myMap= new HashMap<String, ArrayList<String>>();
		for(int i=0;i<myText.length()-myOrder;i++){
			
			String currentString = myText.substring(i, i+myOrder);
			
			
			if(i+myOrder>= myText.length()-1){
				if(!myMap.containsKey(currentString)){
				ArrayList<String> value = new ArrayList<String>();
				value.add(PSEUDO_EOS);
 				myMap.put(currentString, value);
 					}
				else{
					ArrayList<String> value = myMap.get(currentString);
					value.add(PSEUDO_EOS);
					myMap.put(currentString, value);
					}
			}
			if(! myMap.containsKey(currentString)){
				String nextLetter = myText.substring(i+myOrder, i+myOrder+1);
				ArrayList<String> value1 = new ArrayList<String>();
				value1.add(nextLetter);
				myMap.put(currentString, value1);
			}
			else{
				ArrayList<String> value = myMap.get(currentString);
				String nextLetter = myText.substring(i+myOrder, i+myOrder+1);
				value.add(nextLetter);
				myMap.put(currentString, value);
			}
		}
		
		
	}

	
	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - myOrder);

		String current = myText.substring(index, index + myOrder);
		//System.out.printf("first random %d for '%s'\n",index,current.toString(current.myWords);
		sb.append(current);
		for(int k=0; k < length-myOrder; k++){
			if(myMap.containsKey(current)){
			ArrayList<String> follows = getFollows(current);
			 index = myRandom.nextInt(follows.size());
			
			String nextItem = follows.get(index);
			
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
			}
			sb.append(nextItem);
			current = current.substring(1)+ nextItem;
			}
		}
		return sb.toString();
		
	}

	
	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows= myMap.get(key);
		return follows;
	}

	
	public int getOrder() {
		return myOrder;
	}


	 
 }