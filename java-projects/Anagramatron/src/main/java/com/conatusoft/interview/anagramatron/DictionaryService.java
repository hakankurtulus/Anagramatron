package com.conatusoft.interview.anagramatron;
import java.util.List;

/**
 * Created by hakank on 14/03/2016.
 * dictionary contract service that provides dictionary data
 */
public interface DictionaryService {
    List<String> retrieveWordList();
}
