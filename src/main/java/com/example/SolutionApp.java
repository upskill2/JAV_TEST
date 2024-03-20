package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SolutionApp {

    public static void main (String[] args) {
        //DEBUG PURPOSES
        //     System.out.println (calculateOutputNumber (3, 7, 5, "ABBABAAB"));
        //     String currentWorkingDir = System.getProperty("user.dir");
        //     System.out.println("Current working directory: " + currentWorkingDir);
        readAndParseFile ();
    }

    public static void readAndParseFile () {
        String fileName = "input.txt";

        try (BufferedReader br = new BufferedReader (new FileReader (fileName))) {
            String line;
            int lineNumber = 0;
            String S = "";
            List<Integer> resultList = new ArrayList<> ();
            while ((line = br.readLine ()) != null) {

                if (lineNumber == 1) {
                    S = line;
                }

                if (lineNumber > 1) {
                    String[] split = line.split (" ");
                    int result = calculateOutputNumber (
                            Integer.parseInt (split[0]),
                            Integer.parseInt (split[1]),
                            Integer.parseInt (split[2]),
                            S);
                    //DEBUG PURPOSES
                    //   System.out.println (result);
                    resultList.add (result);
                }
                //DEBUG PURPOSES
                // System.out.println(line);
                lineNumber++;
            }
            writeResultToFile (resultList);
        } catch (IOException e) {
            System.err.println ("Error reading file: " + e.getMessage ());
        }
    }

    public static void writeResultToFile (List<Integer> result) {
        String fileName = "output.txt";
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (fileName))) {
            for (Integer integer : result) {
                bw.write (integer.toString ());
                bw.newLine ();
            }
        } catch (IOException e) {
            System.err.println ("Error writing to file: " + e.getMessage ());
        }
    }

    public static int calculateOutputNumber (int l, int r, int k, String s) {

        String lookupSubString = s.substring (l - 1, r);
        char kChar = lookupSubString.charAt (k - 1);
        char charToCount = kChar == 'A' ? 'B' : 'A';

        if (r - l + 1 < k) {
            return -1;
        }

        //Map< Count, Index>
        Map<Integer, Integer> map = new HashMap<> ();
        int otherCharCount = 0;
        int lookupCharCount = 0;
        for (int i = 0; i < lookupSubString.length (); i++) {
            if (lookupSubString.charAt (i) == charToCount) {
                otherCharCount++;
                map.put (otherCharCount, i + 1);
            } else {
                if (i < k) {
                    lookupCharCount++;
                }
            }
        }

        if (map.containsKey (lookupCharCount)) {
            return map.get (lookupCharCount);
        }
        return -1;
    }
}
