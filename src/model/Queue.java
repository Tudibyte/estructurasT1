package model;

public class Queue<T> implements IQueue<T>{

	private Node<T> front;
	private Node<T> back;
	private int size;
	
	public Queue() {
		this.front=null;
		this.back=null;
		this.size=0;
	}
	
	//añade desde el back (index n-1)
	public boolean enqueue(T element) {
		Node<T> current=new Node<>(element);
		if(isEmpty()) {
			front=current;
			back=current;
		} else {
			back.setNext(current);
			back=current;
		}
		size++;
		return true;
	}

	//remueve desde el frente (index 0)
	public T dequeue() {
		if(isEmpty()){
			throw new IllegalStateException("Queue is empty");
		}
		if(front==null) {
			back=null;
		}
		T element = front.getElement();
		front=front.getNext();
		size--;
		
		return element;
	}
	
	public boolean isEmpty() {
		return front==null;
	}

	public T front() {
		if(isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		
		return front.getElement();
	}
	
	public void reverseQueue(Queue<T> queue) {
		Queue<T> temp = new Queue<>();
		
		while(!queue.isEmpty()) {
			temp.enqueue(queue.dequeue());
		}
		
		while(!temp.isEmpty()) {
			queue.enqueue(temp.dequeue());
		}
	}
	
	public int getSize() {
		return size;
	}

}
