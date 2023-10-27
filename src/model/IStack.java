package model;

public interface IStack<T>{

	boolean isEmpty();
	T top();
	T pop();
	void push(T element);
}
