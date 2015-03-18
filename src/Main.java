import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import util.RoundRobinIterator;

public class Main {

	static String[] data = new String[]{"val1", "val2", "val3", "val4", "val5"};
	static RoundRobinIterator<String> roundRobin;

	public static void main(String[] args) throws Exception {
		roundRobin = new RoundRobinIterator<>(Arrays.asList(data));
		Iterator<String> ite = roundRobin.iterator();

		// single-thread
		IntStream.iterate(1, n -> n + 1).limit(5).forEach(n -> System.out.println(ite.next()));

		// multi-thread
		List<String> list = Collections.synchronizedList(new ArrayList<>());
		IntStream.iterate(1, n -> n + 1).limit(10000).parallel()
			.forEach(n -> list.add(ite.next()));

		System.out.println("val1 = " + list.stream().filter(s -> s.equals("val1")).count());
		System.out.println("val2 = " + list.stream().filter(s -> s.equals("val2")).count());
		System.out.println("val3 = " + list.stream().filter(s -> s.equals("val3")).count());
		System.out.println("val4 = " + list.stream().filter(s -> s.equals("val4")).count());
		System.out.println("val5 = " + list.stream().filter(s -> s.equals("val5")).count());
	}
}
