package assn04;

public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You should add more examples and use this class to debug your code
    */
    BST<Integer> bst = new NonEmptyBST<Integer>(0);
      bst = bst.insert(1);
      bst = bst.insert(2);
      bst = bst.insert(3);
      bst = bst.insert(4);
      bst = bst.insert(5);
      bst = bst.insert(6);
      bst.printPreOrderTraversal();
    System.out.println("");
    BST<Integer> gst = new NonEmptyBST<Integer>(4);
      gst = gst.insert(5);
      gst = gst.insert(1);
      gst = gst.insert(0);
      gst = gst.insert(-1);
      gst = gst.insert(3);
      gst = gst.insert(9);
    gst = gst.insert(9);
    gst = gst.insert(6);
      gst.printPreOrderTraversal();
      gst = gst.remove_range(1, 6);
      System.out.println("");
    gst.printPreOrderTraversal();
  }

}
