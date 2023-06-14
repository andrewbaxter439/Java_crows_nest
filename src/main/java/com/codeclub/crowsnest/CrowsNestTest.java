package com.codeclub.crowsnest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CrowsNestTest {

    private String out_format = "Ahoy, Captain, %s %s off the starboard bow!";
    private String[] consonant_words = {"brigantine", "clipper", "dreadnought", "frigate", "galleon", "haddock",
            "junk", "ketch", "longboat", "mullet", "narwhal", "porpoise", "quay",
            "regatta", "submarine", "tanker", "vessel", "whale", "xebec", "yatch",
            "zebrafish"};

    private String[] vowel_words = {"aviso", "eel", "iceberg", "octopus", "upbound"};
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void runs() {
        CrowsNest test_nest = new CrowsNest();
        Assertions.assertInstanceOf(CrowsNest.class, test_nest);
    }

    @Test
    public void helpOutput() {
        CrowsNest.main(new String[]{"-h"});
        Assertions.assertTrue(outputStreamCaptor.toString().indexOf("Usage") != -1);
    }

    @Test
    public void consonants() {
        for (String word: consonant_words) {
            CrowsNest.main(new String[]{word});
            Assertions.assertEquals(
                    String.format(out_format, "a", word),
                    outputStreamCaptor.toString().trim());
            outputStreamCaptor.reset();
        }
    }

    @Test
    public void vowels() {
        for (String word: vowel_words) {
            CrowsNest.main(new String[]{word});
            Assertions.assertEquals(
                    String.format(out_format, "an", word),
                    outputStreamCaptor.toString().trim());
            outputStreamCaptor.reset();
        }
    }

    @Test
    public void upperCase() {
        for (String word: consonant_words) {
            String first = Character.toString(word.charAt(0)).toUpperCase();
            word = first + word.substring(1);
            CrowsNest.main(new String[]{word});
            Assertions.assertEquals(
                    String.format(out_format, "A", word),
                    outputStreamCaptor.toString().trim());
            outputStreamCaptor.reset();
        }

        for (String word: vowel_words) {
            String first = Character.toString(word.charAt(0)).toUpperCase();
            word = first + word.substring(1);
            CrowsNest.main(new String[]{word});
            Assertions.assertEquals(
                    String.format(out_format, "An", word),
                    outputStreamCaptor.toString().trim());
            outputStreamCaptor.reset();
        }
    }
}