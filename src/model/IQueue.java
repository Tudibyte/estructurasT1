package model;

public interface IQueue<T> {
	boolean isEmpty();
	T front();
	T dequeue();
	boolean enqueue(T element);
}
