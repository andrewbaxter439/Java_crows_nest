package com.codeclub.crowsnest;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Objects;

@Command(name = "crowsnest")
public class CrowsNest {

    @Parameters(paramLabel = "Word", description = "A word to look for", defaultValue = "")
    private String word;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    public static void main(String[] args) {
        CrowsNest crowsnest = new CrowsNest();

        new CommandLine(crowsnest).parseArgs(args);

        if (crowsnest.helpRequested | Objects.equals(crowsnest.word, "")) {
            CommandLine.usage(new CrowsNest(), System.out);
            return;
        }
        crowsnest.run();
    }

    public void run() {

        String article = "a";

        String VOWELS = "aeiouAEIOU";

        if (VOWELS.indexOf(word.charAt(0)) != -1) {
            article = "an";
        }

        if (Character.isUpperCase(word.charAt(0))) {
            article = "A" + article.substring(1);
        }

        System.out.printf("Ahoy, Captain, %s %s off the starboard bow!%n", article, word);
    }
}
