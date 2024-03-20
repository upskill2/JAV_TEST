package com.example;

import junit.framework.TestCase;

public class SolutionAppTest extends TestCase {

    SolutionApp solutionApp = new SolutionApp ();

    public void testSolution () {
        assertEquals (3, solutionApp.calculateOutputNumber (1, 4, 4, "ABBABAAB"));
        assertEquals (3, solutionApp.calculateOutputNumber (2, 6, 1, "ABBABAAB"));
        assertEquals (-1, solutionApp.calculateOutputNumber (3, 7, 5, "ABBABAAB"));
    }

}