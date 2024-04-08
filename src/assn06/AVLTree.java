package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }
    @Override
    public AVLTree<T> test() {
        return rotateLeft();
    }
    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         AVLTree<T> z;
         z = new AVLTree<T>();
         z._left = _left;
         z._value = _value;
         AVLTree<T> x = _right;
         AVLTree<T> y = x._right;
         _left = z;
         _right = y;
         _value = x._value;
         return this;
     }
    
    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         AVLTree<T> z;
         z = new AVLTree<T>();
         z._left = (AVLTree<T>) getRight();
         z._value = getValue();
         SelfBalancingBST<T> x = getLeft();
         SelfBalancingBST<T> y = x.getLeft();
         _right = z;
         _left = (AVLTree<T>) y;
         _value = x.getValue();
         return this;
     }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
    	// TODO
        if (isEmpty()) {
            _value = element;
            _left = new AVLTree<T>();
            _right = new AVLTree<T>();
            _height = 1;
            _size = 1;
        } else if (element.compareTo(_value) < 0) {
            _left = (AVLTree<T>) _left.insert(element);
        } else if (element.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.insert(element);
        }
        recalculateHS();
        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	// TODO
        if (isEmpty()) {
            return this;
        } else if (element.compareTo(_value) < 0) {
            _left = (AVLTree<T>) _left.remove(element);
        } else if (element.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.remove(element);
        } else {
            if (_left.isEmpty() && _right.isEmpty()) {
                return new AVLTree<T>();
            } else if (_left.isEmpty()) {
                return _right;
            } else if (_right.isEmpty()) {
                return _left;
            } else {
                T min = _right.findMin();
                _value = min;
                _size = _size-1;
                _right = (AVLTree<T>) _right.remove(min);
            }
        }
        recalculateHS();
        return this;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        if (_left.isEmpty()) {
            return _value;
        } else {
            return _left.findMin();
        }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        if (_right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        if (isEmpty()) {
            return false;
        } else if (element.compareTo(_value) < 0) {
            return _left.contains(element);
        } else if (element.compareTo(_value) > 0) {
            return _right.contains(element);
        }
        return true;
    }


    @Override
    public boolean rangeContain(T start, T end) {
        // TODO
        if (isEmpty()) {
            return false;
        } else if (start.compareTo(_value) <= 0 && end.compareTo(_value) >= 0) {
            return true;
        } else if (start.compareTo(_value) < 0) {
            return _left.rangeContain(start, end);
        } else if (end.compareTo(_value) > 0) {
            return _right.rangeContain(start, end);
        }
        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
         return _right;
    }

    @Override
    public void printTree() {
         System.out.print(_value + ", ");
         if (! _left.isEmpty()) {
             System.out.print("L");
             _left.printTree();
         }
         if (! _right.isEmpty()) {
             System.out.print("R");
             _right.printTree();
         }
    }

    private void recalculateHS() {
        if (isEmpty()) {
            _height = 0;
            _size = 0;
        } else {
            _height = 1 + Math.max(_left.height(), _right.height());
            _size = 1 + _left.size() + _right.size();
        }
    }

    private boolean isNotBalanced() {
         if (isEmpty()) {
            return false;
        }
        return Math.abs(_left.height() - _right.height()) > 1;
    }
}

