package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class HigherOrderFunctions {

    public <T> Stream<T> filter1(Stream<T> stm, Predicate1<T> predicate)
    {
        List<T> list =new ArrayList<>();
        stm.forEach(x-> {
            if(predicate.test(x))
                list.add(x);
        });
        return list.stream();
    }

}
