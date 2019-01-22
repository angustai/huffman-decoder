/**
 * Decoder.java
 * Used to convert string representation into binary tree representaiton
 * Author: Angus Tai
 * Date: June 18th 2018
 */

public class Decoder {
  private BinaryTree<String> tree = new BinaryTree<>();
  private String chain;
  private Stack<String> stack;
  
  /**
   * Decoder
   * Initializes variables of Decoder
   * @param chain - string that needs to be put into a tree
   */ 
  public Decoder(String chain){
    this.chain = chain;
  }
  
  /**
   * setTree
   * A method that is the setter for the tree
   * @param tree - a binary tree which holds Strings
   */ 
  public void setTree(BinaryTree<String> tree) {
    this.tree = tree;
  }
  
  /**
   * getTree
   * A method that is the getter for the tree
   * @return tree - a binary tree
   */ 
  public BinaryTree<String> getTree() {
    return tree;
  }
  
  /**
   * setChain
   * Setter for the variable chain
   * @param chain
   */
  public void setChain(String chain) {
    this.chain = chain;
  }
  
  /**
   * getChain
   * This method returns chain
   * @return chain
   */
  public String getChain() {
    return chain;
  }
  /**
   * setStack
   * A method that is the setter for the stack
   * @param stack - a stack holding Strings
   */ 
  public void setStack(Stack<String> stack) {
    this.stack = stack;
  }
  
  /**
   * getStack
   * A method that is the getter for the stack
   * @return stack - a stack
   */ 
  public Stack<String> getStack() {
    return stack;
  }
  
  /**
   * switchString
   * This method switches from an ascii representation to a string representation
   * @param chain - the initial string of ascii values
   * @return String converted from ascii
   */
  public String switchString(String chain){ //this turns an ascii represented string tree to a character represented string tree
    for(int n = 0; n < chain.length(); n++){//runs through the length of the chain
      int end = n;
      if (!(chain.substring(n,n+1).equals("("))&& !(chain.substring(n,n+1).equals(" "))&& !(chain.substring(n,n+1).equals(")")) ) {//if one of the ascii values
        do{
          end++;//keeps track of the length of the ascii value (as ascii values are generally larger than one character)
        }while(!(chain.substring(end,end+1).equals("("))&& !(chain.substring(end,end+1).equals(" "))&& !(chain.substring(end,end+1).equals(")")) );//while is it not a bracket or a space     
        chain = chain.substring(0, n) + (char)Integer.parseInt(chain.substring(n,end)) + chain.substring(end);//adds the string represented by the ascii value 
      }
    }
    return chain;//gives back the chain as a string
  }
  
  /**
   * switchAscii
   * This method switches from a string representation to an ascii representaiton
   * @param chain - the initial string
   * @return String converted to ascii
   */
  public String switchAscii(String chain){//this turns a character represented string tree to a ascii represented string tree
    for(int n = 0; n<chain.length(); n++){     
      if (!(chain.substring(n,n+1).equals("("))&& !(chain.substring(n,n+1).equals(" "))&& !(chain.substring(n,n+1).equals(")")) ) {//if the string is an actual character in the tree
        chain = chain.substring(0, n) + (int)chain.charAt(n)+"" + chain.substring(n + 1);
        n = n + (int)chain.charAt(n)+"".length();//accounts for ascii values being more than 1 character
      }
    }
    return chain;
  }
  
  /**
   * stringStackConversion
   * This method changes the string representation into a stack of characters
   * @param chain - the initial string
   * @return stringStack - stack of characters
   */
  public Stack stringStackConversion(String chain){
    Stack<String>stringStack = new Stack<>();//create a new stack
    for(int n = 0; n < chain.length(); n++){//run through the length of the chain
      stringStack.push(chain.substring(n, n+1));//put each character in the chain into a stack 
    }
    return stringStack;//gives the stack back
  }
  
  /**
   * stackRootConversion
   * This method changes an item in a stack into the root of a binary tree
   */
  public void stackRootConversion(){
    stack.pop();//pops the first character from the stack (right bracket) 
    tree.addOrdered(null);//represents the root of the tree
  }
  
  /**
   * stackTreeConversion
   * This method converts the characters in the stack into items in a binary tree
   * @param currentNode - a BinaryTreeNode holding a String, shiftLeft - a boolean representing whether next focus node is to the left or not
   */
  public void stackTreeConversion(BinaryTreeNode<String> currentNode, boolean shiftLeft){
    if (stack.isEmpty() == true){//if the stack is empty
      return;//base case
    }else{   
      String temp = stack.pop("");//pops a string which is assigned to temp
      if(temp.equals("(")){//if the character popped is a left bracket 
        
        return;//end of recursive calls
        
      }else if(temp.equals(" ")){//if the character popped is a space 
        
        stackTreeConversion(currentNode, true);//this means that the next character will be on the left       
        
      }else if (temp.equals(")")){//if the character popped is a right bracket 
        
        if (shiftLeft == true){
          currentNode.setLeft(new BinaryTreeNode<>(null));//make a new node left of the current node
          stackTreeConversion(currentNode.getLeft(), false);//recursive call on the node to the left of the current node
          stackTreeConversion(currentNode, false);//recursive call on current node               
        }else{
          currentNode.setRight(new BinaryTreeNode<>(null));//make a new node right of the current node 
          stackTreeConversion(currentNode.getRight(), false);//recursive call on the node to the right of the current node
          stackTreeConversion(currentNode, false);//recursive call on current node        
        }  
        
      }else{//if the character popped is actually part of the message
        if (shiftLeft == true){
          currentNode.setLeft(new BinaryTreeNode<>(temp));//set the left node to holding the character as the item      
        }else{
          currentNode.setRight(new BinaryTreeNode<>(temp));//set the right node to holding the character as the item
        }
        stackTreeConversion(currentNode,false);//call method recursively on the new current node      
      }
    }
  }
}
