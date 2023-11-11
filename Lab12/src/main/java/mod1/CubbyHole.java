package mod1;

import java.util.LinkedList;
import java.util.Queue;

class CubbyHole {
	private int seq;
	private boolean available = false;
	private Queue<Integer> values = new LinkedList<>();

	public synchronized int get() {
		while (values.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException ignored) {
			}
		}
		available = false;
		notify();
		return values.poll();
	}

	public synchronized void put(int value) {
		while (values.size() >= 10) {
			try {
				wait();
			} catch (InterruptedException ignored) {
			}
		}
		values.add(value);
		//available = true;
		notify();
		
	}
}