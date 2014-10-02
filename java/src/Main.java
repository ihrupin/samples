import java.util.ArrayList;
import java.util.Collections;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create an ArrayList object
        ArrayList words = new ArrayList();

        //Add elements to Arraylist
        words.add("A");
        words.add("B");
        words.add("C");
        words.add("D");
        words.add("E");

        System.out.println("Before swaping, ArrayList contains : " + words);

        /*
      To swap elements of Java ArrayList use,
      static void swap(List list, int firstElement, int secondElement)
      method of Collections class. Where firstElement is the index of first
      element to be swapped and secondElement is the index of the second element
      to be swapped.

      If the specified positions are equal, list remains unchanged.

      Please note that, this method can throw IndexOutOfBoundsException if
      any of the index values is not in range.        */

        Collections.swap(words, 0, words.size() - 1);

        System.out.println("After swaping, ArrayList contains : " + words);    
	}

}
