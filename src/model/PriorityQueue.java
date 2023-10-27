package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PriorityQueue<T extends Comparable<T>> {
    private List<T> items;
    private Map<T, Integer> itemIndices;
    private Stack<Action<T>> actions;

    public PriorityQueue() {
        items = new ArrayList<>();
        itemIndices = new HashMap<>();
        actions = new Stack<>();
    }

    public void insert(T item) {
        items.add(item);
        itemIndices.put(item, items.size() - 1);
        siftUp(items.size() - 1);
        actions.push(new Action<>(ActionType.INSERT, item));
    }

    public T extractMax() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = items.get(0);
        swap(0, items.size() - 1);
        items.remove(items.size() - 1);
        itemIndices.remove(maxItem);
        siftDown(0);
        actions.push(new Action<>(ActionType.EXTRACT, maxItem));
        return maxItem;
    }

    public void changePriority(T item, T newPriority) {
        if (!itemIndices.containsKey(item)) {
            return;
        }
        int index = itemIndices.get(item);
        T oldPriority = items.get(index);
        items.set(index, newPriority);
        itemIndices.put(newPriority, index);
        itemIndices.remove(oldPriority);
        if (newPriority.compareTo(oldPriority) > 0) {
            siftUp(index);
        } else {
            siftDown(index);
        }
        actions.push(new Action<>(ActionType.CHANGE_PRIORITY, item, newPriority));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    public Stack<Action<T>> getActions() {
        return actions;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (items.get(index).compareTo(items.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int maxIndex = index;
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex < items.size() && items.get(leftChildIndex).compareTo(items.get(maxIndex)) > 0) {
            maxIndex = leftChildIndex;
        }

        if (rightChildIndex < items.size() && items.get(rightChildIndex).compareTo(items.get(maxIndex)) > 0) {
            maxIndex = rightChildIndex;
        }

        if (index != maxIndex) {
            swap(index, maxIndex);
            siftDown(maxIndex);
        }
    }

    private void swap(int i, int j) {
        Collections.swap(items, i, j);
        itemIndices.put(items.get(i), i);
        itemIndices.put(items.get(j), j);
    }

    private enum ActionType {
        INSERT, EXTRACT, CHANGE_PRIORITY
    }

    private static class Action<T> {
        private ActionType type;
        private T item;
        private T newPriority;

        public Action(ActionType type, T item) {
            this.type = type;
            this.item = item;
        }

        public Action(ActionType type, T item, T newPriority) {
            this.type = type;
            this.item = item;
            this.newPriority = newPriority;
        }

        public ActionType getType() {
            return type;
        }

        public T getItem() {
            return item;
        }

        public T getNewPriority() {
            return newPriority;
        }
    }
}