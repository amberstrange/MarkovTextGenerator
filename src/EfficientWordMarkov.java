import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.*;

public class EfficientWordMarkov implements MarkovInterface<WordGram> {
	private String [] myText;
	private Random myRandom;
	private int myOrder;
	private Map<WordGram, ArrayList<String>> myMap;
	
	private static String PSEUDO_EOS = "";
	private static int RANDOM_SEED = 1234;
	public EfficientWordMarkov(int order){
		myOrder = order;
		myRandom = new Random(RANDOM_SEED);
	}
	public void setTraining(String text) {
		myText = text.split("\\s+");
		myMap = new HashMap<WordGram, ArrayList<String>>();
		for(int i = 0; i<=myText.length-myOrder; i++){
			WordGram currentGram = new WordGram(myText, i, myOrder);
				if(!myMap.containsKey(currentGram)){
					ArrayList<String> followList = new ArrayList<String>();
					if(i>= myText.length -myOrder){
						followList.add(PSEUDO_EOS);
						myMap.put(currentGram, followList);
						}
					else{
						followList.add(myText[i+myOrder]);
						myMap.put(currentGram, followList);
					}
			
				}
				else{
				ArrayList<String> followList = myMap.get(currentGram);
				if(i>= myText.length -myOrder){
					followList.add(PSEUDO_EOS);
					myMap.put(currentGram, followList);
					}
				else{
					followList.add(myText[i+myOrder]);
					myMap.put(currentGram, followList);
				}
				}
				}
				
			}
	
		
		
		

	
	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);

		WordGram current = new WordGram(myText, index, myOrder);
		System.out.printf("first random %d for '%s'\n",index,current.toString());
		sb.append(current.toString());
		for(int k=0; k < length - myOrder; k++){
			if(myMap.containsKey(current)){
			ArrayList<String> follows = getFollows(current);
			 index = myRandom.nextInt(follows.size());
			
			 
			String nextItem = follows.get(index);
			
			
			sb.append(nextItem + " ");
			current = current.shiftAdd(nextItem);
			
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
			}
			}
		}
		return sb.toString();
	}

	@Override
	public ArrayList<String> getFollows(WordGram key) {
		return myMap.get(key);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return this.myOrder;
	}
	

}
