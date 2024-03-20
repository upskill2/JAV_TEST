package com.example;

import junit.framework.TestCase;

import static com.example.SolutionApp.calculateOutputNumber;

public class SolutionAppTest extends TestCase {

    public void testSolution () {
       assertEquals (3, calculateOutputNumber (1, 4, 4, "ABBABAAB".substring (1 - 1, 4)));
        assertEquals (3, calculateOutputNumber (2, 6, 1, "ABBABAAB".substring (2 - 1, 6)));
        assertEquals (-1, calculateOutputNumber (3, 7, 5, "ABBABAAB".substring (3 - 1, 7)));
    }
}