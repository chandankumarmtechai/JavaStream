package com.company;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamExample {
    Stream<String> streamOfArray = Stream.of("a", "b", "c");
    String arr[] = {"abc", "cde","efg","hji"};
    Stream<String> streamOfArrayFull = Arrays.stream(arr);
    Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

    // Infinite streams and limit
    Stream<String> streamBuilder =
            Stream.<String>builder().add("a").add("b").add("c").build();

    Stream<String> streamGenerated =
            Stream.generate(() -> "element").limit(10);

    // Streams of primitives
    Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

    IntStream intStream = IntStream.range(1, 3);
    LongStream longStream = LongStream.rangeClosed(1, 3);

    Random random = new Random();
    DoubleStream doubleStream = random.doubles(3);

    // Stream of character
    IntStream streamOfChars = "abc".chars();

    Stream<String> streamOfString =
            Pattern.compile(", ").splitAsStream("a, b, c");

    // skip element
    Stream<String> onceModifiedStream =
            Stream.of("abcd", "bbcd", "cbcd").skip(1);

    List<Employee> list;

    {
        list = new ArrayList<>();
        list.add(new Employee("Ram","1",100.0,25));
        list.add(new Employee("Shyam","2",150.0,27));
        list.add(new Employee("Mohan","3",200.0,35));
        list.add(new Employee("Laxman","4",400.0,22));
        list.add(new Employee("lala","5",500.0,33));
    }

    public void reducingToSingleExample()
    {
        Employee emp = list.stream().filter(e->e.getId().equals("5")).collect(Collectors.toList()).get(0);
        System.out.println(emp.toString());
        Employee emp2 =  list.stream().filter(e->e.getId().equals("5")).findFirst().get();
        System.out.println(emp2.toString());
        Employee emp3 =  list.stream().filter(e->e.getId().equals("6")).findFirst().orElse(null);
        System.out.println(emp3 == null);
        Employee emp4 =  list.stream().filter(e->e.getId().equals("6")).findAny().orElse(null);
        System.out.println(emp4 == null);
        list.add(new Employee("birju","5",50.0,23));
        Employee emp5 =  list.stream().filter(e->e.getId().equals("5")).findAny().get();
        System.out.println(emp5);
        Employee emp6 =  list.stream().filter(e->e.getId().equals("4")).reduce(null,(a,b)->b);
        System.out.println(emp6);
        Employee emp7 =  list.stream().filter(e->e.getId().equals("4")).reduce((a,b)->b).get();
        System.out.println(emp7);
    }

    public void groupingElementsExample()
    {
        list.add(new Employee("kaliya","5",80.0,43));
        Map<String, List<Employee>> gid5 = list.stream().collect(Collectors.groupingBy(e->e.getId()));
        System.out.println(gid5);
        System.out.println(gid5.get("5"));
        Map<Boolean, List<Employee>> gsl = list.stream().collect(Collectors.groupingBy(e->e.getSalary() > 300));
        System.out.println(gsl);
        System.out.println(gsl.get(false));
        System.out.println(gsl.get(true));

    }

    public void matchersExample()
    {
        Boolean b1 = list.stream().anyMatch(e->e.getSalary()==400.0);
        System.out.println(b1);//true
        Boolean b2 = list.stream().allMatch(e->e.getSalary()==400.0);
        System.out.println(b2);//false
        Boolean b3 = list.stream().noneMatch(e->e.getSalary()==700.0);
        System.out.println(b3);//true
    }

    public void getStreamOfDifferentTypeExample()
    {
        List<Employee> lid5 = list.stream().filter(e->e.getId().equals("5")).collect(Collectors.toList());
        System.out.println(lid5);
    }

    public void flatMapExample()
    {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(new Integer[] {1,2,3}));
        matrix.add(Arrays.asList(new Integer[] {4,5,6}));
        matrix.add(Arrays.asList(new Integer[] {7,8,9}));
        System.out.println(matrix);

        List<Integer> integers = matrix.stream().flatMap(row->row.stream()).collect(Collectors.toList());
        System.out.println(integers);

        List<List<List<Integer>>> matrices = new ArrayList<>();
        List<List<Integer>> matrix1 = new ArrayList<>();
        matrix1.add(Arrays.asList(new Integer[] {1,2,3}));
        matrix1.add(Arrays.asList(new Integer[] {4,5,6}));
        matrix1.add(Arrays.asList(new Integer[] {7,8,9}));

        List<List<Integer>> matrix2 = new ArrayList<>();
        matrix2.add(Arrays.asList(new Integer[] {10,11,12}));
        matrix2.add(Arrays.asList(new Integer[] {13,14,15}));
        matrix2.add(Arrays.asList(new Integer[] {16,17,18}));

        matrices.add(matrix1);
        matrices.add(matrix2);
        System.out.println(matrices);

        List<Integer> numbers = matrices.stream().flatMap(mx->mx.stream()).
                flatMap(row->row.stream()).collect(Collectors.toList());
        System.out.println(numbers);

    }

    public void parallelStreamExample()
    {
        IntStream intStreamParallel = IntStream.range(1, 5).parallel();
        boolean isParallel = intStreamParallel.isParallel();
        System.out.println(isParallel);
        IntStream intStreamSequential = intStreamParallel.sequential();
        boolean isParallel1 = intStreamSequential.isParallel();
        System.out.println(isParallel1);

        int sum = intStreamSequential.reduce(10, (a,b) -> a+b);
        System.out.println(sum);

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    return a + b;
                });
        System.out.println(reducedParallel);

        Stream<Integer> five = Arrays.asList(1, 2, 3).parallelStream();
        int sum2 = five.reduce(10, (a,b) -> a+b, (a,b) -> a+b);
        System.out.println(sum2);
    }

    public void fileStreamExample() throws Exception
    {
        // stream of lines from file
        Path path = Paths.get
                ("C:\\Users\\chakumar19\\AppData\\Roaming\\JetBrains\\IdeaIC2021.1\\scratches\\scratch.txt");
        Stream<String> streamOfStrings = Files.lines(path);
        Stream<String> streamWithCharset =
                Files.lines(path, Charset.forName("UTF-8"));
    }
}
