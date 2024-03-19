package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO (Task 2A): enqueue
    public void enqueue(V value) {
        Patient patient = new Patient<>(value);
        _heap.add(patient);
        bubbleUp(_heap.size() -1);
    }

    // TODO (Task 2A): enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        _heap.add(patient);
        bubbleUp(_heap.size() -1);
    }

    // TODO (Task 2A): dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        } else if (_heap.size() == 1) {
            return _heap.remove(0).getValue();
        } else {
            V retValue = _heap.get(0).getValue();
            _heap.set(0, _heap.remove(_heap.size() - 1));
            bubbleDown(0);
            return retValue;
        }

    }

    // TODO (Task 2A): getMax
    @Override
    public V getMax() {
        if (_heap.isEmpty()) {
            return null;
        } else {
            return _heap.get(0).getValue();
        }
    }

    // TODO (part 2B) : updatePriority
    public void updatePriority(V value, P newPriority) {
        Patient patient = new Patient(value, (Integer) newPriority);
        for (int i=0; i<_heap.size(); i++) {
            if (_heap.get(i).getValue().equals(value)) {
                _heap.set(i, patient);
                bubbleUp(i);
                bubbleDown(i);
            }
        }
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (int i=0; i<initialEntries.length; i++) {
            enqueue(initialEntries[i].getValue(), initialEntries[i].getPriority());
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    int bubbleDown(int index) {
        if (!hasLeftChild(index) && !hasRightChild(index)) {
            return index;
        } else if (!hasRightChild(index)) {
            Prioritized<V,P> parent = _heap.get(index);
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(index));
            if (leftChild.compareTo(parent) >= 0) {
                _heap.set(index, leftChild);
                _heap.set(leftChildInd(index), parent);
                return bubbleDown(leftChildInd(index));
            } else {
                return index;
            }
        } else if (!hasLeftChild(index)) {
            Prioritized<V,P> parent = _heap.get(index);
            Prioritized<V,P> rightChild = _heap.get(rightChildInd(index));
            if (rightChild.compareTo(parent) >= 0) {
                _heap.set(index, rightChild);
                _heap.set(rightChildInd(index), parent);
                return bubbleDown(rightChildInd(index));
            } else {
                return index;
            }
        }
        else {
            Prioritized<V,P> parent = _heap.get(index);
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(index));
            Prioritized<V,P> rightChild = _heap.get(rightChildInd(index));
            if (leftChild.compareTo(rightChild) >= 0) {
                if (leftChild.compareTo(parent) >= 0) {
                    _heap.set(index, leftChild);
                    _heap.set(leftChildInd(index), parent);
                    return bubbleDown(leftChildInd(index));
                } else {
                    return index;
                }
            } else {
                if (rightChild.compareTo(parent) >= 0) {
                    _heap.set(index, rightChild);
                    _heap.set(rightChildInd(index), parent);
                    return bubbleDown(rightChildInd(index));
                } else {
                    return index;
                }
            }
        }

    }

    int bubbleUp(int index) {
        if (index==0) {
            return index;
        } else {
            Prioritized<V,P> child = _heap.get(index);
            Prioritized<V,P> parent = _heap.get(parentInd(index));
            if (child.compareTo(parent) >= 0) {
                _heap.set(parentInd(index), child);
                _heap.set(index, parent);
                return bubbleUp(parentInd(index));
            } else {
                return index;
            }
        }


    }

    boolean hasLeftChild(int index){
        return(validIndex(leftChildInd(index)));}

    boolean hasRightChild(int index){
        return(validIndex(rightChildInd(index)));}

    static int leftChildInd(int index){
        return (2*index+1);}

    static int rightChildInd(int index){
        return (2*index+2);}

    static int parentInd(int index){
        return ((index-1)/2);}

    boolean validIndex(int index){
        if ((index >= 0) && (index <= _heap.size()-1)){
            return true;}
        else {return false;}}
}
