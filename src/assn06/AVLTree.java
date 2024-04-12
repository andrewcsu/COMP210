package assn06;

import java.util.ArrayList;

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
    public SelfBalancingBST<T> test() {
        return rotateRight();
    }
    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     @SuppressWarnings("SuspiciousNameCombination")
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         System.out.println("Rotating Left on " + _value);
         AVLTree<T> z;
         z = new AVLTree<T>();
         z.insert(_value);
         z._left = _left;
         AVLTree<T> x = _right;
         AVLTree<T> y = x._right;
         if (x._left != null) {
             z._right = x._left;
         } else {
             z._right = new AVLTree<T>();
         }
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
         System.out.println("Rotating Right on " + _value);
         AVLTree<T> z;
         z = new AVLTree<T>();
         z.insert(_value);
         z._right = _right;
         AVLTree<T> x = _left;
         AVLTree<T> y = x._left;
         if (x._right != null) {
             z._left = x._right;
         } else {
             z._left = new AVLTree<T>();
         }
         _right = z;
         _left = y;
         _value = x._value;
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
            return this;
        } else if (element.compareTo(_value) < 0) {
            _left = (AVLTree<T>) _left.insert(element);
        } else if (element.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.insert(element);
        }
        recalculateHS();
        balanceAVL();
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
                AVLTree<T> newTree = new AVLTree<T>();
                newTree._height = 0;
                newTree._size = 0;
                return newTree;
            } else if (_left.isEmpty()) {
                return _right;
            } else if (_right.isEmpty()) {
                return _left;
            } else {
                T min = _right.findMin();
                System.out.println("Min "+min);
                _value = min;
                System.out.println("Value " +_value);
                _right = (AVLTree<T>) _right.remove(min);
            }
        }
        recalculateHS();
        balanceAVL();
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
//    public void printTree() {
//        // Create an array of 10 empty ArrayLists
//        ArrayList<ArrayList<Integer>> printTree = new ArrayList<>(_height);
//
//        // Initialize each ArrayList in the array
//        for (int i = 0; i < printTree.size(); i++) {
//            printTree.set(i, new ArrayList<>());
//        }
//         System.out.print(_value + ", ");
//         if (! _left.isEmpty()) {
//             System.out.print("L");
//             _left.printTree();
//         }
//         if (! _right.isEmpty()) {
//             System.out.print("R");
//             _right.printTree();
//         }
//    }
    public void printTree() {
        printTree(this, "", true);
    }

    private void printTree(AVLTree<T> node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("└── ");
                indent += "    ";
            } else {
                System.out.print("├── ");
                indent += "│   ";
            }
            System.out.println(node._value);
            printTree(node._left, indent, false);
            printTree(node._right, indent, true);
        }
    }

    private void recalculateHS() {
        if (isEmpty()) {
            _height = 0;
            _size = 0;
        } else {
            _right.recalculateHS();
            int hR = _right.height();
            _left.recalculateHS();
            int hL = _left.height();
//            if (hR == -1) {
//                hR = 0;
//            }
//            if (hL == -1) {
//                hL = 0;
//            }
            _height = 1 + Math.max(hR, hL);
            _size = 1 + _left.size() + _right.size();
        }
    }

    private boolean isNotBalanced() {
         if (isEmpty()) {
            return false;
        }
        int hR = _right.height();
        int hL = _left.height();
        if (hR == -1) {
            hR = 0;
        }
        if (hL == -1) {
            hL = 0;
        }
        return Math.abs(hL-hR) > 1;
    }

    private SelfBalancingBST<T> balanceAVL() {
         if (isEmpty()) {
            return this;
        }
        if (isNotBalanced()) {
            if (_left.height() > _right.height()) {
                if (_left._left.height() >= _left._right.height()) {
                    return rotateRight();
                } else {
                    _left = _left.rotateLeft();
                    return rotateRight();
                }
            } else {
                if (_right._right.height() >= _right._left.height()) {
                    return rotateLeft();
                } else {
                    _right = _right.rotateRight();
                    return rotateLeft();
                }
            }
        }
        return this;
    }
}

