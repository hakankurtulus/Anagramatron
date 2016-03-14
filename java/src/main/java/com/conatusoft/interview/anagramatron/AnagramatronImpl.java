package com.conatusoft.interview.anagramatron;

import java.util.*;

/**
 * Created by hakank on 13/03/2016.
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

