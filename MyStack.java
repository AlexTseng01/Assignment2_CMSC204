import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
	private int size;
	private ArrayList<T> data;
	private int used = 0; // counts how many slots in the ArrayList have data
	
	public MyStack(int size) {
		this.size = size;
		this.data = new ArrayList<T>(size);
		this.used = 0;
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
	public T pop() throws StackUnderflowException {
		if (used == 0) {
			throw new StackUnderflowException();
		}
		else {
			
			return data.get(--used);
		}
	}

	@Override
	public T top() throws StackUnderflowException {
		if (used == 0) {
			throw new StackUnderflowException();
		}
		else {
			return data.get(used-1);
		}
	}

	@Override
	public int size() {
		return used;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		else {
			data.add(used++, e);
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
