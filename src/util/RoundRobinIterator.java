package util;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinIterator<T> implements Iterable<T> {

	private final List<T> list;
	private final int size;
	private final AtomicInteger idx = new AtomicInteger();

	public RoundRobinIterator(List<T> list) {
		if (list == null) {
			throw new IllegalArgumentException();
		}
		this.list = list;
		this.size = list.size();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				// always true
				return false;
			}

			@Override
			public T next() {
				return list.get(Math.abs(idx.getAndIncrement() % size));
			}

			/* Under JDK 1.7 or lower
			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
			*/
		};
	}

}
