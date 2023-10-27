package model;

public class Stack<T>  implements IStack<T>{

	private Node<T> top;
	private int size;
	
	public Stack() {
		this.top=null;
		this.size=0;
	}

	public void push(T element) {
		Node<T>current=new Node<>(element);
		
		if(top==null) {
			top=current;
		}else {
			current.setNext(top);
			top=current;
		}
		size++;
	}	
	
	public T pop() {
		if(isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		
		T element = top.getElement();
		top=top.getNext();
		size--;
		return element;
	}

	public T top() {
		if(isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		
		return top.getElement();
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	
	public int getSize() {
		return size;
	}
	
	public Stack<T> reverseStack(Stack<T> stack) {
		Stack<T> temp = new Stack<>();
		
		while(!stack.isEmpty()) {
			temp.push(stack.pop());
		}
		
		while(!temp.isEmpty()) {
			stack.push(temp.pop());
			System.out.println(stack.top());
		}
		
		return temp;
		
	}

}
