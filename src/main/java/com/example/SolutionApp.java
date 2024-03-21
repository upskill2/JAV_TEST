package com.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SolutionApp {
    public static final String INPUT_TXT = "input.txt";
    public static final String OUTPUT_TXT = "output.txt";
    private static String abString;
    private static final List<String> resultsList = new ArrayList<> ();

    public static void main (String[] args) throws IOException {
        readFile ();
    }

    private static void readFile () throws IOException {

        try (BufferedReader reader = Files.newBufferedReader (Paths.get (INPUT_TXT))) {
            String query;
            int lineNumber = 0;
            while ((query = reader.readLine ()) != null) {
                if (lineNumber == 1) {
                    abString = query;
                } else if (lineNumber > 1) {
                    int res = processLine (query);
                    resultsList.add (String.valueOf (res));
                }
                lineNumber++;
            }
        }
        writeFile ();
    }

    private static int processLine (String line) {

        String[] split = line.split ("\\s");
        int l = Integer.parseInt (split[0]);
        int r = Integer.parseInt (split[1]);
        int k = Integer.parseInt (split[2]);
        return calculateOutputNumber (l, r, k, abString.substring (l - 1, r));
    }

    public static int calculateOutputNumber (int l, int r, int k, String s) {
        if (k > s.length ()) {
            return -1;
        }

        int nonKCharCount = 0;
        int kCharCount = 0;
        char kChar = s.charAt (k - 1);
        for (int i = 0; i < k; i++) {
            if (s.charAt (i) == kChar) {
                kCharCount++;
            } else {
                nonKCharCount++;
            }
        }

        if (nonKCharCount >= kCharCount) {
            nonKCharCount = 0;
            for (int i = 0; i < k; i++) {
                if (s.charAt (i) != kChar) {
                    nonKCharCount++;
                    if (kCharCount == nonKCharCount) {
                        return i + 1;
                    }
                }
            }
        } else {
            for (int i = k; i < s.length (); i++) {
                if (s.charAt (i) != kChar) {
                    nonKCharCount++;
                }
                if (kCharCount == nonKCharCount) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    private static void writeFile () throws IOException {
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (OUTPUT_TXT))) {
            for (int i = 0; i < resultsList.size (); i++) {
                bw.write (resultsList.get (i));
                if (i != resultsList.size () - 1) {
                    bw.newLine ();
                }
            }
        }
    }

}
