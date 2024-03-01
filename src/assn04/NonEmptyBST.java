package assn04;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element){
		if (_element == element) {
			return this;
		}
		if (_left.isEmpty() && _right.isEmpty()) {
			if (_element.compareTo(element) > 0) {
				_left = new NonEmptyBST<T>(element);
			} else {
				_right = new NonEmptyBST<T>(element);
			}
		} else if (_element.compareTo(element) > 0) {
			_left = _left.insert(element);
		} else {
			_right = _right.insert(element);
		}
		return this;
	}
	
	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (element.compareTo(_element) == 0) {
//			System.out.println("Removing "+_element);
			if (_left.isEmpty() && _right.isEmpty()) {
				return new EmptyBST<T>();
			}
			if (_left.isEmpty()) {
				return _right;
			} else if (_right.isEmpty()) {
				return _left;
			} else {
				T min = _right.findMin();
				_right = _right.remove(min);
				_element = min;
			}
		}
		if (element.compareTo(_element) < 0) {
//			System.out.println("Going left");
			_left = _left.remove(element);
		} else {
//			System.out.println("Going right");
			_right = _right.remove(element);
		}
		return this;
	}
	
	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {
//		printPreOrderTraversal();
//		System.out.println("" + _element + start + end);
		while (_element.compareTo(start) >= 0 && _element.compareTo(end) <= 0) {
//			System.out.println("Removing "+_element);
			if (_right.isEmpty()) {
				return _left;
			}
			T min = _right.findMin();
			_right = _right.remove(min);
			_element = min;
//			printPreOrderTraversal();
		}
//		System.out.println("Going Foward");
		_left = _left.remove_range(start, end);
		_right = _right.remove_range(start, end);
		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element+" ");
		_left.printPreOrderTraversal();
		_right.printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		_left.printPostOrderTraversal();
		_right.printPostOrderTraversal();
		System.out.print(_element+" ");
	}

	// The findMin method returns the minimum value in the tree.
	@Override
	public T findMin() {
		if(_left.isEmpty()) {
			return _element;
		}
		return _left.findMin();
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
