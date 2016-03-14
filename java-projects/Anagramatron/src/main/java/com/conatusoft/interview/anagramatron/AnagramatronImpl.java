package com.conatusoft.interview.anagramatron;
import java.util.*;

/**
 * Created by hakank on 13/03/2016.
 * Implementation of main functionalities provided by Anagram service.
 * Application Logic: If two word anagrams then sorted forms of those words are the same.
 * So application walks over the dictionary data which is provided by the Dictionary service,
 * then check whether the word should be added to the HashMap as a key or should be added to
 * the list of existing key node.
 * Thus, hashmap can be filled by the key words and their anagram word list.
 * Finally the most longest anagram set can be found.
 *
 *
 */
public class AnagramatronImpl implements Anagramatron{

    DictionaryService dictionaryService;

    private List<String> maxAnagram = new ArrayList<String>();

    public AnagramatronImpl(DictionaryService service) {
        dictionaryService = service;
    }

    public List<String> retrieveMaxAnagramSet() {
        Map<String, List<String>> anagramMap = new HashMap<String, List<String>>();
        List<String> maxAnagram = new ArrayList<String>();

        Iterator<String> wordIterator = dictionaryService.retrieveWordList().iterator();

        while (wordIterator.hasNext()) {
            List<String> anagrams = addToAnagramMap(anagramMap, wordIterator.next());

            if (anagrams.size() > maxAnagram.size()) {
                maxAnagram = anagrams;
            }
        }

        return maxAnagram;
        /*StringBuilder maxAnagramBuilder = new StringBuilder();
        Iterator<String> anagramIterator = maxAnagram.iterator();
        while (anagramIterator.hasNext()) {
            maxAnagramBuilder.append(anagramIterator.next()).append(',');
        }
        return maxAnagramBuilder.toString();*/
    }

    private List<String> addToAnagramMap(Map<String, List<String>> anagramMap, String word) {
        String sorted = sortLetters(word);
        List<String> anagrams = anagramMap.get(sorted);
        if (anagrams == null) {
            anagrams = new ArrayList<String>();
            anagramMap.put(sorted, anagrams);
        }
        anagrams.add(word);
        return anagrams;
    }

    private String sortLetters(final String word) {
        char[] letters = word.toLowerCase().toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }


}

