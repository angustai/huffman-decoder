public class Test {
    public static void main (String args[]){
        BinaryTree<String> myTree = new BinaryTree<String>();

        Stack<String> myStack = new Stack<>();
        myTree.addOrdered(null);
        String theTree ="((c d) ((a b) (e f)))";


        Decoder myD = new Decoder(theTree);

        myTree.getRoot().setLeft(new BinaryTreeNode<>(null));
        myTree.getRoot().setRight(new BinaryTreeNode<>("c"));
        myTree.getRoot().getLeft().setLeft(new BinaryTreeNode<>("a"));

        myTree.getRoot().getLeft().setRight(new BinaryTreeNode<>("b"));

        //theTree = myTree.makeTreeString(myTree.getRoot());
        System.out.println(theTree);

        myD.setStack(myD.stringStackConversion(myD.getChain()));
        myD.stackRootConversion();
        myD.stackTreeConversion(myD.getTree().getRoot(), false);
        System.out.println("11111111111111");
        myD.getTree().preOrderTraverseTree(myD.getTree().getRoot());
        String newtree = myD.getTree().makeTreeString(myD.getTree().getRoot());
        System.out.println(newtree);

        System.out.println(myD.switchAscii(newtree));
        System.out.println(myD.switchString(myD.switchAscii(newtree)));
    }
}
