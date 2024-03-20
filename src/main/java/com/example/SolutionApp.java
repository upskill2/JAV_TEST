package com.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolutionApp {

    public static void main (String[] args) {
        readAndParseFile ();
    }

    public static void readAndParseFile () {
        String fileName = "input.txt";
        List<String> list = new ArrayList<> ();
        try (Stream<String> lines = Files.lines (Paths.get (fileName))) {
          list = lines.collect (Collectors.toList ());
        } catch (IOException e) {
            System.err.println ("Error reading file: " + e.getMessage ());
        }
        int numberOfLines = Integer.parseInt (list.get (0).split (" ")[1]) + 2;
        String s = list.get (1);
        List<String> resultList = new ArrayList<> ();
        for (int i = 2; i < numberOfLines; i++) {
            String[] split = list.get (i).split (" ");
            int l = Integer.parseInt (split[0]);
            int r = Integer.parseInt (split[1]);
            int k = Integer.parseInt (split[2]);
            if (k > s.length ()) {
                resultList.add ("-1");
            } else {
                int result = calculateOutputNumber (l, r, k, s.substring (l - 1, r));
                resultList.add (String.valueOf (result));
            }
        }
        writeResultToFile (resultList);
    }
    private static void writeResultToFile (List<String> result) {
        String fileName = "output.txt";
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (fileName))) {
            for (int i = 0; i < result.size (); i++) {
                bw.write (result.get (i));
                if (i != result.size () - 1) {
                    bw.newLine ();
                }
            }
        } catch (IOException e) {
            System.err.println ("Error writing to file: " + e.getMessage ());
        }
    }

    public static int calculateOutputNumber (int l, int r, int k, String s) {
        char kChar = s.charAt (k - 1);
        char charToCount = kChar == 'A' ? 'B' : 'A';

        if (r - l + 1 < k) {
            return -1;
        }

        int otherCharCount = 0;
        int lookupCharCount = 0;
        for (int i = 0; i < k; i++) {
            if (s.charAt (i) == kChar) {
                otherCharCount++;
            }
        }

        for (int i = 0; i < s.length (); i++) {
            if (s.charAt (i) == charToCount) {
                lookupCharCount++;
            }
            if (otherCharCount == lookupCharCount) {
                return i + 1;
            }
        }
        return -1;
    }
}
