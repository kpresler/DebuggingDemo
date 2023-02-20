package edu.bowdoin.csci.DebuggingDemo;

import java.util.AbstractList;

public class LinkedList<E> extends AbstractList<E> {

	private int size;
	private Node head;

	// Default constructor
	public LinkedList() {

	}

	// appends the specified element to the end of this list.
	public boolean add(E data) {

		Node newNode = new Node(data);

		if (null == head) {
			head = newNode;
			size++;
			return true;
		}

		Node currentNode = head;

		// starting at the head node, crawl to the end of the list and then add element
		// after last node
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}

		// the last node's "next" reference set to our new node
		currentNode.setNext(newNode);

		// increment the number of elements variable
		size++;
		return true;
	}

	// inserts the specified element at the specified position in this list
	public void add(int index, E data) {
		
		if (null == data) {
			throw null;
		}
		if (contains(data)) {
			throw new IllegalArgumentException();
		}
		
		Node newNode = new Node(data);

		if (null == head && index == 0) {
			head = newNode;
			size++;
			return;
		} else if (null == head) {
			throw new IndexOutOfBoundsException();
		}
		else if (index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0) {
			newNode.setNext(head);
			head = newNode;
			return;
		}

		Node currentNode = head;

		// crawl to the requested index or the last element in the list, whichever comes
		// first
		for (int i = 0; i < index - 1 && currentNode.getNext() != null; i++) {
			currentNode = currentNode.getNext();
		}

		// set the new node's next-node reference to this node's next-node reference
		newNode.setNext(currentNode.getNext());

		// now set this node's next-node reference to the new node
		currentNode.setNext(newNode);

		// increment the number of elements variable
		size++;
	}

	// returns the element at the specified position in this list.
	public E get(int index) {
		// index must be 1 or higher
		if (index < 0 || null == head)
			throw new IndexOutOfBoundsException();
		Node currentNode = head;
		for (int i = 0; i < index; i++) {
			if (currentNode.getNext() == null)
				throw new IndexOutOfBoundsException();

			currentNode = currentNode.getNext();
		}
		return currentNode.getData();

	}

	// removes the element at the specified position in this list.
	public E set(int index, E data) {

		if (null == data) {
			throw null;
		}

		if (contains(data)) {
			throw new IllegalArgumentException();
		}

		// if the index is out of range, exit
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else if (null == head) {
			throw new IndexOutOfBoundsException();
		}

		E toReturn = null;

		Node currentNode = head;
		for (int i = 0; i < index; i++) {
			if (currentNode.getNext() == null)
				throw new IndexOutOfBoundsException();

			currentNode = currentNode.getNext();
		}
		toReturn = currentNode.getData();
		currentNode.setData(data);

		// decrement the number of elements variable
		return toReturn;

	}

	// removes the element at the specified position in this list.
	public E remove(int index) {

		E toReturn = null;

		// if the index is out of range, exit
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else if (null == head) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			toReturn = head.getData();
			head = head.getNext();
			size--;
			return toReturn;
		}

		Node currentNode = head;
		for (int i = 0; i < index; i++) {
			if (currentNode.getNext() == null)
				throw new IndexOutOfBoundsException();

			currentNode = currentNode.getNext();
		}
		toReturn = currentNode.getNext().getData();
		currentNode.setNext(currentNode.getNext().getNext());

		// decrement the number of elements variable
		size--;
		return toReturn;

	}

	// returns the number of elements in this list.
	public int size() {
		return size;
	}

	public String toString() {
		String output = "";

		Node currentNode = head;
		while (currentNode != null) {
			output += "[" + currentNode.getData().toString() + "]";
			currentNode = currentNode.getNext();
		}

		return output;
	}

	private class Node {
		// reference to the next node in the chain, or null if there isn't one.
		Node next;

		// data carried by this node. could be of any type you need.
		E data;

		// Node constructor
		public Node(E dataValue) {
			next = null;
			data = dataValue;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node nextValue) {
			next = nextValue;
		}

	}

}