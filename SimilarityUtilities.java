/*
 * Copyright 2021 Marc Liberatore.
 */

package similarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sets.SetUtilities;

public class SimilarityUtilities {
	/**
	 * Returns the set of non-empty lines contained in a text, trimmed of
	 * leading and trailing whitespace.
	 * 
	 * @param text
	 * @return the trimmed set of lines
	 */
	public static Set<String> trimmedLines(String text) {
		if (text == null)
           return null;

       Set<String> trimmedSetOfLines = new HashSet<String>();
       String[] tokens = text.split("\\n");
       String trimmedToken;
      
       for (int i = 0; i < tokens.length; i++)
       {
           trimmedToken = tokens[i].trim();
           if (!trimmedToken.isEmpty())
           {
               trimmedSetOfLines.add(trimmedToken);
           }

       }

       return trimmedSetOfLines;
	}

	/**
	 * Returns a list of words in the text, in the order they appeared in the text, 
	 * converted to lowercase.
	 * 
	 * Words are defined as a contiguous, non-empty sequence of letters and numbers.
	 *
	 * @param text
	 * @return a list of lowercase words
	 */
	public static List<String> asLowercaseWords(String text) {
		List<String> listOfLowercaseWords = new ArrayList<String>();
		String textInLowercase = text.toLowerCase();
		String[] tokens = textInLowercase.split("\\W+");
		String trimmedToken;
	   
		for (int i = 0; i < tokens.length; i++)
		{
			trimmedToken = tokens[i].trim();
			if (!trimmedToken.isEmpty())
			{
				listOfLowercaseWords.add(trimmedToken);
			}
		}
	   
		return listOfLowercaseWords;
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2) {
		return SetUtilities.jaccardIndex(trimmedLines(text1), trimmedLines(text2));
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines, less the set of trimmed lines from the templateText. Removes
	 * the template text from consideration after trimming lines, not before.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @param templateText
	 *            a template, representing things the two texts have in common
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2, String templateText) {
		Set<String> trimmedSetOfLines1 = trimmedLines(text1);
       Set<String> trimmedSetOfLines2 = trimmedLines(text2);
       Set<String> trimmedSetOfLines3 = trimmedLines(templateText);
       Set<String> setDifference13 = SetUtilities.setDifference(trimmedSetOfLines1, trimmedSetOfLines3);
       Set<String> setDifference23 = SetUtilities.setDifference(trimmedSetOfLines2, trimmedSetOfLines3);
       return SetUtilities.jaccardIndex(setDifference13, setDifference23);
	}

	/**
	 * Returns a set of strings representing the shingling of the given length
	 * of a list of words.
	 * 
	 * A shingling of length k of a list of words is the set of all k-shingles
	 * of that list.
	 * 
	 * A k-shingle is the concatenation of k adjacent words.
	 * 
	 * For example, a 3-shingle of the list: ["a" "very" "fine" "young" "man"
	 * "I" "know"] is the set: {"averyfine" "veryfineyoung" "fineyoungman"
	 * "youngmanI" "manIknow"}.
	 * 
	 * @param words
	 * @param shingleLength
	 * @return 
	 */
	public static Set<String> shingle(List<String> words, int shingleLength) {
		Set<String> shingleListOfWords = new HashSet<String>();
		String str;
	   
		for (int i = 0; i < (words.size() - shingleLength + 1); i++)
		{
			str = "";
			for (int j = i; j < i + shingleLength; j++)
			{
				str += words.get(j);
			}
		   
			shingleListOfWords.add(str);
		}
	   
		return shingleListOfWords;
	}

	/**
	 * Returns the shingled word similarity of two texts.
	 * 
	 * The shingled word similarity is the Jaccard index between each text's
	 * shingle set.
	 * 
	 * A text's shingle set is the set of shingles (of the given length) for the
	 * entire text, as defined by shingle and asLowercaseWords, 
	 * less the shingle set of the templateText. Removes the templateText 
	 * from consideration after shingling, not before.
	 * 
	 * @param text1
	 * @param text2
	 * @param templateText
	 * @param shingleLength
	 * @return
	 */
	public static double shingleSimilarity(String text1, String text2, String templateText, int shingleLength) {
		List<String> trimmedSetOfLines1 = asLowercaseWords(text1);
		List<String> trimmedSetOfLines2 = asLowercaseWords(text2);
		List<String> trimmedSetOfLines3 = asLowercaseWords(templateText);
	   
		Set<String> shingleListOfWord1 = shingle(trimmedSetOfLines1, shingleLength);
		Set<String> shingleListOfWord2 = shingle(trimmedSetOfLines2, shingleLength);
		Set<String> shingleListOfWord3 = shingle(trimmedSetOfLines3, shingleLength);
 
		Set<String> setDifference13 = SetUtilities.setDifference(shingleListOfWord1, shingleListOfWord3);
		Set<String> setDifference23 = SetUtilities.setDifference(shingleListOfWord2, shingleListOfWord3);
 
		return SetUtilities.jaccardIndex(setDifference13, setDifference23);
	
	}
}
