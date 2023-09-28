import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {
	private int size;
	private ArrayList<T> data;
	private int head, tail;
	private int used = 0;
	
	public MyQueue(int size) {
		this.size = size;
		this.data = new ArrayList<T>(size);
		// initialize default values
		head = 0;
		tail = 0;
	}
	
	@Override
	public boolean isEmpty() {
		if (used == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if (used != size) {
			return false;
		}
		return true;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (used == 0) {
			throw new QueueUnderflowException();
		}
		else {
			used--; // remember to decrement if removing element
			return data.get(head++);
		}
	}

	@Override
	public int size() {
		return used;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		else {
			data.add(tail, e);
			tail++;
			used++;
			return true;
		}
	}

	@Override
	public String toString(String delimiter) {
		String temp = "";
		for (int i = 0; i < used; i++) {
			temp += data.get(i);
			if (i != data.size() - 1) {
				temp += delimiter;
			}
		}
		
		return temp;
	}
	
	// Overloaded toString
	@Override public String toString() {
		String temp = "";
		for (int i = 0; i < used; i++) {
			temp += data.get(i);
		}
		
		return temp;
	}

	@Override
	public void fill(ArrayList<T> list) {
		for (int i = 0; i < list.size(); i++) {
			// increments used AFTER appending list.get(i)
			data.add(used++, list.get(i));
		}
	}
	
	
	
}
