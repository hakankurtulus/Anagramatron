package com.conatusoft.interview.anagramatron.test;

import com.conatusoft.interview.anagramatron.Anagramatron;
import com.conatusoft.interview.anagramatron.AnagramatronImpl;
import com.conatusoft.interview.anagramatron.DictionaryService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hakank on 13/03/2016.
 * Tests with data which is provided by the mock Dictionary Service
 * and checks whether the length of the longest anagram set could be found  properly.
 */
public class AnagramatronTest {

    DictionaryService mockedDictionaryService;


    private List<String> readWordsListFromFile(String fileName) {
        Scanner scanner = null;
        String data = "";
        try {
            scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
            data = scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        return Arrays.asList(data.split("\n"));
    }

    @Before
    public void setUp() {
        mockedDictionaryService = mock(DictionaryService.class);
    }


    @Test
    public void testRetieveMaxAnagramSet() {
        when(mockedDictionaryService.retrieveWordList()).thenReturn(
                Arrays.asList("Miles", "dog", "peek", "dummy", "keep", "Limes", "god", "skating", "takings", "Smile", "Slime", "dummy", "tasking"));

        Anagramatron anagramatron = new AnagramatronImpl(mockedDictionaryService);

        List<String> maxAnagramSet = anagramatron.retrieveMaxAnagramSet();
        System.out.println(maxAnagramSet);
        assertEquals(maxAnagramSet.size(), 4);
    }

    @Test
    public void testRetieveMaxAnagramSetFromFile() {
        when(mockedDictionaryService.retrieveWordList()).thenReturn(
                readWordsListFromFile("wordlist.txt"));

        Anagramatron anagramatron = new AnagramatronImpl(mockedDictionaryService);

        List<String> maxAnagramSet = anagramatron.retrieveMaxAnagramSet();
        System.out.println(maxAnagramSet);
        assertEquals(maxAnagramSet.size(), 14);
    }

}