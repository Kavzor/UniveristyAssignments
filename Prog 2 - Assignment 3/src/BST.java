import java.util.ArrayList;

/**
 * Binary search tree with strings as keys.
 * @version 2017-09-13
 */
public class BST {
	/**
	 * Inner class for tree nodes
	 */
	private static class Node {
		private String key;
		private Node left, right;

		private Node(String key, Node left, Node right) {
			this.key = key;
			this.left  = left;
			this.right = right;
		}
	}

	/**
	 * Inner class for TreeExceptions
	 */
	public static class BSTException extends RuntimeException {
		private static final long serialVersionUID = -184166695032601334L;

		public BSTException(String msg) {
			super(msg);
		}
	}

	private Node root;

	/**
	 * Standard constructor
	 */
	public BST() { 
		root = null; 
	}

	private BST(Node r) { 
		root = r; 
	}

	/**
	 * Searches a specified key
	 * @param key the key to be searched
	 * @return true if the key is found, else false
	 */ 
	public boolean contains(String key) {
		return contains(key, root);
	}

	private static boolean contains(String key, Node r) {
		if (r==null) {
			return false;
		} else if (key.compareTo(r.key) < 0) {
			return contains(key, r.left);   
		} else if (key.compareTo(r.key) > 0) {
			return contains(key, r.right);  
		} else {
			return true;
		}
	}


	/**
	 * Insert a key preserving the sorted condition
	 * @param key the key to be inserted
	 */
	public void add(String key) {
		root = add(key, root);
	}

	private static Node add(String key, Node r) {
		if (r==null) {
			return new Node(key, null, null);
		} else if (key.compareTo(r.key) < 0) {
			r.left = add(key, r.left);
		} else if (key.compareTo(r.key) > 0) {
			r.right = add(key, r.right);
		} else {
			// Do nothing - the key is already in the tree
		}
		return r;
	}

	public String toString() {
		return "<" + toString(root) + ">";
	}

	private static String toString(Node r) {
		if (r==null) {
			return " ";
		} else {
			return toString(r.left) + " " + r.key + toString(r.right);
		}
	}

	/******************** Methods to be implemented *************/

	/**
	 * Compute the number of nodes in the tree
	 * @return the number of nodes
	 */
	public int size() {
		return size(root);
	}

	private static int size(Node root) {
		if(root == null) {
			return 0;
		}
		else {
			return 1 + size(root.left) + size(root.right);
		}
	}


	/**
	 * Compute the height.
	 * The height is defined as the number of nodes on 
	 * the longest path from the root to a leaf.
	 * @return the height
	 */ 
	public int height() {
		return height(root, 0);
	}
	
	private static int height(Node root, int height) {
		if(root == null) {
			return 0;
		}
		else if(root.left == null && root.right == null) {
			return 0;
		}
		int leftHeight = 1 + height(root.left, height + 1); 
		int rightHeight = 1 + height(root.right, height + 1);
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}


	/**
	 * Find the smallest (defined by compareTo()) key in the tree
	 * @return the smallest key
	 */
	public String smallest() {
		return smallest(root);
	}
	
	private static String smallest(Node root) {
		if(root == null) {
			return null;
		}
		else if(root.left == null) {
			return root.key;
		}
		else {
			return smallest(root.left);
		}
	}


	/**
	 * Construct an arraylist containing the keys from the nodes in symmetric order
	 * i.e. the keys will be stored in alphabetic order.
	 * @return an arraylist containing all keys from the tree i alphabetic order
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> arrayList = new ArrayList<>();
		return toArrayList(root, arrayList);
	}
	
	private static ArrayList<String> toArrayList(Node root, ArrayList<String> list) {
		if(root != null) {
			list = toArrayList(root.left, list);
			list.add(root.key);
			list = toArrayList(root.right, list);
		}
		return list;
	}
	/**
	 * Create a (deep) copy of the tree structure
	 * @return a tree containing a copy of this tree
	 */
	public BST copy() {
		return copy(root, new BST()); 
	}

	private static BST copy(Node root, BST target) {
		if(root != null) {

			target.add(root.key);
			target = copy(root.left, target);
			target = copy(root.right, target);
		}
		return target;
	}


	/**
	 * Check if this tree is equal to another tree.
	 * Equal means the same branching structure and the same keys in the nodes.
	 * @param t the tree to be compared with
	 * @return <code>true</code> if the trees are equal, else <code>false</code
	 */
	public boolean equals(BST t) {
		return equals(this.root, t.root, true);
	}

	private static boolean equals(Node fRoot, Node sRoot, boolean equal) {
		if(fRoot == null) {
			return sRoot == null;
		}
		else if(sRoot == null) {
			return fRoot == null;
		}
		else if(fRoot != null && sRoot != null && equal) {
				equal = fRoot.key.equals(sRoot.key);
				equal = equals(fRoot.left, sRoot.left, equal);
				equal = equals(fRoot.right, sRoot.right, equal);
		}
		return equal;
	}


	/**
	 * Check if two trees have exactly the same contents
	 * @param t the tree to be compared with
	 * @return <code>true</code> if the trees have the same contents, else <code>false</code>
	 */
	/**
	 * TODO: NOT CHECKED
	 */
	public boolean sameContents(BST t) {
		return sameContent(this, t.root, true);
	}

	private static boolean sameContent(BST bst, Node sRoot, boolean equal) {
		if(sRoot == null) {
			return true;
		}
		else {
			if(bst.contains(sRoot.key) && equal) {
				equal = sameContent(bst, sRoot.left, equal);
				equal = sameContent(bst, sRoot.right, equal);
				return equal;
			}
			else {
				return false;
			}
		}
		

		/*if(fRoot == null ) {
			return false;
		}
		else if(fRoot.key.equals(sRoot.key)) {
			return true;
		}
		else {
			equal = sameContent(fRoot.left, sRoot, equal);
			equal = sameContent(fRoot.right, sRoot, equal);
			return equal;
		}*/
	}


	/**
	 * Compute the internal path length.
	 * The internal path length can be defined as the
	 * sum of the depths of the individual nodes.
	 * The root has depth 1, the children of the root depth 2 etc.
	 * Thus, a tree with one node has ipl 1, 
	 * a tree with two nodes has ipl 3 and a tree with three nodes 
	 * ipl 5 or 6 depending on shape.
	 */
	/**
	 * TODO: NOT CHECKED
	 */
	public int ipl() {
		return ipl(root, 0);
	}

	private static int ipl(Node root, int pathLength) {
		if(root == null) {
			return 0;
		}
		else if(root.left == null && root.right == null) {
			return 1;
		}
		int leftHeight = 1 + height(root.left, pathLength + 1); 
		int rightHeight = 1 + height(root.right, pathLength + 1);
		return leftHeight + rightHeight;
	}


	/**
	 * Main-method showing calls to and results from all methods above
	 */

	public static void main(String[] args) {
		System.out.println("BST main version of 2017-09-13");
		BST bst = new BST();

//		bst.add("2");
//		bst.add("1");
//		bst.add("5");
//		bst.add("4");
//		bst.add("6");
//		bst.add("8");
//		bst.add("9");
//		bst.add("7");
//		bst.add("3");
		bst.add("anna");
		bst.add("kalle");
		bst.add("danne");
		bst.add("pizza");
		bst.add("apple");
		
		System.out.println("Tree: " + bst.toString() + "\n");
		System.out.println("Size of tree: " + bst.size() + "\n");
		System.out.println("Height: " + bst.height() + "\n");
		System.out.println("Smallest: " + bst.smallest() + "\n");
		System.out.println("To array: " + bst.toArrayList() + "\n");


		System.out.println("Tree: " + bst.toString() + ", size: " + bst.size() + ", height: " + bst.height());
		BST copiedTree = bst.copy();
		System.out.println("Copied: " + copiedTree.toString() + ", size: " + copiedTree.size() + ", height: " + copiedTree.height() + "\n");

		System.out.println("Trees equal: " + bst.equals(copiedTree));

		System.out.println("After adding to original tree");
		bst.add("dante");
		System.out.println("Tree: " + bst.toString());
		System.out.println("Copied: " + copiedTree.toString());

		System.out.println("Trees equal: " + bst.equals(copiedTree) + "\n");

		BST sameTree = new BST();
		sameTree.add("pizza");
		sameTree.add("anna");
		sameTree.add("kalle");
		sameTree.add("apple");
		sameTree.add("dante");
		sameTree.add("danne");
		
		System.out.println("Tree: " + bst.toString() + ", size: " + bst.size() + ", height: " + bst.height());
		System.out.println("Same content tree: " + sameTree.toString() + ", size: " + sameTree.size() + ", height: " + sameTree.height());
		System.out.println("Same content: " + bst.sameContents(sameTree));

		System.out.println("Added jinx to original tree");
		bst.add("jinx");

		System.out.println("Tree: " + bst.toString() + ", size: " + bst.size() + ", height: " + bst.height());
		System.out.println("Same content tree: " + sameTree.toString() + ", size: " + sameTree.size() + ", height: " + sameTree.height());
		System.out.println("Same content: " + bst.sameContents(sameTree)  + "\n");
	}
}
