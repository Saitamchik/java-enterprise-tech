package com.skillbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StackImpl<E> implements Stack<E>{

    private List<E> list;
    private int capacity;

    @Override
    public void push(E element) throws StackException {
        if (isFull()) {
            throw new StackException("Unable to add item, stack is full.");
        }

        list.add(element);
    }

    @Override
    public E pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack is empty, impossible to delete.");
        }

        int indexLastElement = list.size() - 1;
        return list.remove(indexLastElement);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            int indexLastElement = list.size() - 1;
            return list.get(indexLastElement);
        }
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return list.size() == capacity;
    }

    @Override
    public void setMaxSize(int size) {
        capacity = Math.max(size, 1);
        list = new ArrayList<>(capacity);
    }

    @Override
    public void pushAll(Collection src) throws StackException {
        if ((capacity - list.size()) < src.size()) {
            throw new StackException("Cannot add all items from the source collection, the source is larger.");
        }

        list.addAll(src);
    }

    @Override
    public void popAll(Collection dst) throws StackException {
        if (list.isEmpty()) {
            throw new StackException("Unable to move items in a collection, stack is null.");
        }

        List<E> listReverse = new ArrayList<>(list);
        Collections.reverse(listReverse);
        dst.addAll(listReverse);
        list.clear();
    }
}
