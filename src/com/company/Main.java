package com.company;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception{
        StreamExample streamExample = new StreamExample();
        // Reducing stream to single element
        //streamExample.reducingToSingleExample();
        // Grouping elements
        //streamExample.groupingElementsExample();
        // Matchers
        //streamExample.matchersExample();
        // flatMap
        //streamExample.flatMapExample();
        // parallel stream
        //streamExample.parallelStreamExample();

        AddOne<Integer,Integer> ap = (a)->a+1;
        System.out.println(ap.addOne(5));

        ApplyTwice<AddOne, Integer, Integer> add = (f,x) -> {
            return (Integer) f.addOne(f.addOne(x));
        };
        System.out.println(add.applytwice(ap,1));



       // System.out.println(add.applytwice(1).addOne(1));

        HigherOrderFunctions hfd = new HigherOrderFunctions();
        List<Integer> list = Arrays.asList(new Integer[] {1,2,3,4,5,6});
        Stream<Integer> lstream = list.stream();
        Stream<Integer> mstream = hfd.filter1(lstream, x->x>2);
        mstream.forEach(System.out::println);

    }
}
