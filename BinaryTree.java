public class BinaryTree<E extends Comparable>{

    private BinaryTreeNode<E> root;

    public int size(BinaryTreeNode<E> focusNode){
        if (focusNode == null){
            return 0;
        }else{
            return (size(focusNode.getLeft())+ 1 + size(focusNode.getRight())); 
        }

    }

    public int size(){
        return size(root);
    }

    public boolean contains(E item){
        BinaryTreeNode<E> focusNode = root;

        while (focusNode != null && !focusNode.getItem().equals(item)){
            if (item.compareTo(focusNode.getItem())<0){
                focusNode = focusNode.getLeft();
            }else{
                focusNode = focusNode.getRight();
            }
        }
        if (focusNode == null){
            return false;
        }
        return true;
    }

    public BinaryTreeNode findTreeNode(E item){
        BinaryTreeNode<E> focusNode = root;

        while (focusNode != null && !focusNode.getItem().equals(item)){
            if (item.compareTo(focusNode.getItem())<0){
                focusNode = focusNode.getLeft();
            }else{
                focusNode = focusNode.getRight();
            }
        }
        if (focusNode == null){
            return null;
        }
        return focusNode;
    }
    
    public void addOrdered(E item){

        BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(item);

        if (root == null){
            root = newNode;
        }else{
            BinaryTreeNode<E> focusNode = root;
            BinaryTreeNode<E> parent;

            while (true){
                parent = focusNode;
                if (item.compareTo(focusNode.getItem())<0){ 
                    focusNode = focusNode.getLeft();

                    if (focusNode == null){ 
                        parent.setLeft(newNode);
                        return;
                    }
                }else{
                    focusNode = focusNode.getRight();

                    if (focusNode == null){ 
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    public void addLeft(E item){
    }
    public void addRight(E item){

    }

     public String makeTreeString(BinaryTreeNode<E> focusNode){
        if (focusNode ==null){
           return null;
        }else if (focusNode.getItem() == null){
             return "(" + makeTreeString(focusNode.getLeft()) +" "+ makeTreeString(focusNode.getRight()) + ")";
        }else{
            return ""+focusNode.getItem();
        }
    }


    public boolean remove(E item){
        BinaryTreeNode<E> focusNode = root;
        BinaryTreeNode<E> parent = root;
        boolean isLeft = true;

        while(focusNode!= null && !focusNode.getItem().equals(item)){
            parent = focusNode;

            if (item.compareTo(focusNode.getItem())<0){
                isLeft = true;
                focusNode = focusNode.getLeft();
            }else{
                isLeft = false;
                focusNode = focusNode.getRight();
            }
            if (focusNode == null){
                return false;
            }


        }

        if (focusNode.isLeaf()){
            if (focusNode.equals(root)){
                root = null;
            }else if(isLeft){
                parent.setLeft(null);
            }else{
                parent.setRight(null);
            }
        }else if(focusNode.getRight() == null){
            if(focusNode.equals(root)){
                root = focusNode.getLeft();
            }else if(isLeft){
                parent.setLeft(focusNode.getLeft());
            }else{
                parent.setRight(focusNode.getLeft());
            }
        }else if(focusNode.getLeft() == null){
            if(focusNode.equals(root)){
                root = focusNode.getRight();
            }else if(isLeft){
                parent.setLeft(focusNode.getRight());
            }else{
                parent.setRight(focusNode.getRight());
            }
        }else{
            BinaryTreeNode<E> replacement = findReplacement(focusNode);
            if (focusNode == root){
                root = replacement;

            }else if(isLeft){
                parent.setLeft(replacement);
            }else{
                parent.setRight(replacement);
            }
            replacement.setLeft(focusNode.getLeft());
        }
        return true;
    }

    public BinaryTreeNode<E> findReplacement(BinaryTreeNode<E> replacedNode){
        BinaryTreeNode<E> replacementParent = replacedNode;
        BinaryTreeNode<E> replacement = replacedNode;

        BinaryTreeNode<E> focusNode = replacedNode.getRight();
        while(focusNode!=null){
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.getLeft();

        }

        if(replacement != replacedNode.getRight()){
            replacementParent.setLeft(replacement.getRight());
            replacement.setRight(replacedNode.getRight());
        }

        return replacement;
    }

    public void preOrderTraverseTree(BinaryTreeNode focusNode){

        if (focusNode != null) {
            System.out.println(focusNode.getItem());
            preOrderTraverseTree(focusNode.getLeft());
            preOrderTraverseTree(focusNode.getRight());

        }
    }

    public BinaryTreeNode<E> getRoot(){
        return this.root;
    }
}
