package goat_latin;

import java.util.*;

public class Solution {
    /*
    The rules of Goat Latin are as follows:

    If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
    For example, the word 'apple' becomes 'applema'.

    If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
    For example, the word "goat" becomes "oatgma".

    Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
    For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
    Return the final sentence representing the conversion from S to Goat Latin.
     */
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }
    public String toGoatLatin(String S) {
        String [] words = S.split(" ");
        Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        List<String> goatSentence = new ArrayList<>();
        for(int i=0;i<words.length;i++){
         String word = words[i];
         boolean containsVowel = false;
         if(vowels.contains(Character.toLowerCase(word.charAt(0)))){
             containsVowel = true;
         }
         String suffix = containsVowel ? "ma" : word.charAt(0) +"ma";
         for(int j=0;j<i;j++){
             suffix += "a";
         }
         if(!containsVowel){
             goatSentence.add(word.substring(1) + suffix);
         }
         else{
             goatSentence.add(word + suffix);
         }
        }
        return String.join(" ", goatSentence);
    }
}
