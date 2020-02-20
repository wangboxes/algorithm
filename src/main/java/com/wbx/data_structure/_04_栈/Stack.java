package com.wbx.data_structure._04_栈;


import com.wbx.data_structure._03_链表.ArrayList;
import com.wbx.data_structure._03_链表.List;

/**
 * 后进先出的原则， Last In First Out， LIFO
 *
 * 栈的应用 – 浏览器的前进和后退(用两个栈实现)
 */
public class Stack<E> {

	private List<E> list = new ArrayList<>();
	
	public void clear() {
		list.clear();
	}
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E element) {
		list.add(element);
	}


	public E pop() {
		return list.remove(list.size() - 1);
	}


	public E top() {
		return list.get(list.size() - 1);
	}
}
