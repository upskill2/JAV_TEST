package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

public class SolutionApp {

    public static void main (String[] args) throws Exception {
        readFile ();
    }

    private static void readFile () throws Exception {
        String fileName = "input.txt";
        List<String> list;
        try (BufferedReader reader = new BufferedReader (new FileReader (fileName))) {
            list = reader.lines ().toList ();
        }
        processFile (list);
    }

    private static void processFile (final List<String> list) throws Exception {
        int numberOfLines = Integer.parseInt (list.get (0).split (" ")[1]) + 2;
        String s = list.get (1).trim ();
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
        writeFile (resultList);
    }

    private static void writeFile (List<String> result) throws Exception {
        String fileName = "output.txt";
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (fileName))) {
            for (int i = 0; i < result.size (); i++) {
                bw.write (result.get (i));
                if (i != result.size () - 1) {
                    bw.newLine ();
                }
            }
        }
    }

    public static int calculateOutputNumber (int l, int r, int k, String s) {
        char kChar = s.charAt (k - 1);
        char charToCount = kChar == 'A' ? 'B' : 'A';

        if (r - l + 1 < k) {
            return -1;
        }

        if (l > r || r < 1 || l < 1) {
            return -1;
        }

        int lookupCharCount = 0;
        int otherCharCount = 0;
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
