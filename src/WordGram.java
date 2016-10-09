import java.util.*;
public class WordGram implements Comparable<WordGram> {
	public String[] myWords;
	private int myHash;
	public WordGram(String[] source, int start, int size){
		String[] answer = new String[size];
		
		for(int k=0; k<size;k++){
			answer[k] = source[k+start];
		}
		//System.out.println(answer.length);
		myWords = answer;
		myHash = this.hashCode();
		}
	
	public int hashCode(){
		String [] wordlist = myWords;
		int hash = 15;
		for(int k=0; k<wordlist.length; k++){
			hash+=((Math.pow(17, k+1))* wordlist[k].hashCode());
		
			
		}
			
		return hash;
		
		}
	
	public String toString(){
		StringBuilder answer = new StringBuilder();
		answer.append("{");
		for(String word:myWords){
			answer.append(word + ",");
			}
		answer.delete(answer.length()-1, answer.length());
		answer.append("}");
		return answer.toString();
		}
	
	public boolean equals(Object other){
		if(! (other instanceof WordGram)){
			return false;
		}
		WordGram wg = (WordGram) other;
		String [] wordlist1 = this.myWords;
		String [] wordlist2 = wg.myWords;
		
		if(wordlist1.length!= wordlist2.length) return false;
		for(int i =0; i<wordlist1.length; i++){
			if(!(wordlist1[i].equals(wordlist2[i]))) return false; //why doesnt this work why am i dumb
			}
		return true;
		
	}
	/*public static void main(String[] args){
		String[] ab = {"apple", "banana", "pear",  "pl", "banana"};
		WordGram c1 = new WordGram(ab, 0, 2);
		WordGram c2 = new WordGram(ab, 3, 2);
		System.out.println(c2.toString());
		System.out.println(c1.myHash);
		System.out.println(c1.myHash==c2.myHash);
		System.out.println(c1.equals(c2));
			Map<WordGram, String> maps = new HashMap<WordGram, String>();
			String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
			String[] array = str.split("\\s+");
			WordGram [] myGrams= new WordGram[array.length-2];
			for(int k=0; k < array.length-2; k++){
				myGrams[k] = new WordGram(array,k,3);
			}
			maps.put(myGrams[1], "aa");
			System.out.println(myGrams[0].myHash);
			System.out.println(maps.containsKey(myGrams[0].shiftAdd("aa")));
			System.out.println(myGrams[2].hashCode());
			System.out.println(myGrams[3].hashCode());
		}*/

	@Override
	public int compareTo(WordGram o) {
		// TODO Auto-generated method stub
		String [] mw1 = this.myWords; 
		String [] mw2 = o.myWords;
		int limit = Math.min(mw1.length, mw2.length);
		for(int k=0; k<limit; k++){
			if(!mw1[k].equals(mw2[k])){
				return mw1[k].compareTo(mw2[k]);
			}
		
		}
		if(mw1.length == mw2.length) return 0;
		else{
			return mw1.length - mw2.length;
		}
	}

	public WordGram shiftAdd(String word){
		String[] mw1 = this.myWords;
		
		LinkedList<String> shiftList =new LinkedList<String>(Arrays.asList(mw1));
		shiftList.remove(0);
		shiftList.add(shiftList.size(), word);
		String [] shiftedList = shiftList.toArray(new String[shiftList.size()]);
		WordGram wg = new WordGram(shiftedList, 0, shiftedList.length);
		
		return wg;
	}
	public int length(){
		return myWords.length;
	}
	
	
	}
