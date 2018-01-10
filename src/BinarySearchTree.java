/**
 * this is the generic Binary Search tree. It extends the comparable method of the Fraction class,
 * inserts recursively and also traverses the tree recursively... the inner Node
 * class is also in this.
 * 
 * @author Michelle Decaire due 12/3/2017
 *
 * @param <T>
 */

public class BinarySearchTree<T extends Comparable<T>> {
	/**
	 * this node class is the inner class of a tree builder it defines the left and
	 * right sides of the tree it is generic to accept both integer and fraction
	 * inputs
	 * 
	 * @author Michelle Decaire due 12/3/2017
	 *
	 * @param <T>
	 */
	private class Node<T> {

		private Node<T> left, right;
		private T value;

		// constructor for null input
		Node() {
			left = null;
			right = null;
			value = null;
		}

		// constructor when input is added
		public Node(T token) {
			value = token;
			left = null;
			right = null;
		}

		public T getValue() {
			return value;
		}

	}

	// variables for the binary search tree
	private String orderedList = null;
	private Node<T> root;

	// contructor which initilizes tree
	public BinarySearchTree() {
		root = null;
	}

	// public initializes root and calls a private method to build tree
	public void insert(T token) {
		root = insert(root, token);
	}

	/**
	 * private method to build nodes and add nodes to right or left sides
	 * 
	 * @param node
	 * @param token
	 * @return
	 */
	private Node<T> insert(Node<T> node, T token) {
		int nodecomp = 10;

		if (node == null) {
			node = new Node<T>(token);
		} else {
			nodecomp = token.compareTo(node.getValue());

			if (nodecomp <= 0) {
				node.left = insert(node.left, token);
			} else if (nodecomp == 1) {
				node.right = insert(node.right, token);
			}
		}
		return node;
	}

	// public initializes ascending which calls a private method
	public String inOrdertreetraversal() {
		String l = ascending(root);
		return l;
	}

	/**
	 * this method builds a list in ascending order
	 */
	private String ascending(Node<T> root) {
		String sortOrder = " ";
		if (root != null) {

			sortOrder = ascending(root.left);
			sortOrder += (root.getValue().toString());
			sortOrder += ascending(root.right);
		}
		return sortOrder;
	}

	// public initializes descending list calls private method
	public String descendingOrder() {
		String list = descending(root);
		return list;
	}

	/**
	 * this methods builds the list in descending order
	 * 
	 * @param root
	 * @return
	 */
	private String descending(Node<T> root) {
		String desOrder = " ";
		if (root != null) {
			desOrder = descending(root.right);
			desOrder += (root.getValue().toString());
			desOrder += descending(root.left);
		}
		return desOrder;
	}

	// to string to return the ordered list
	public String toString() {
		return orderedList;
	}
}