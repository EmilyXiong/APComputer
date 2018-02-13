package com.xiongfamily.emily.apcomp;

// name:        date: 
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
	   //Make a frequency table of the letters
	   Map<String, Integer> frequencyTtable = new HashMap<String, Integer>();
	   for (int i = 0; i < message.length(); i++){
		   String letter = message.substring(i, i+1);
		   Integer frequency =  frequencyTtable.get(letter);
		   if(frequency != null){
			   frequency = frequency +1;
		   }
		   else{
			   frequency = 1;
		   }
		   frequencyTtable.put(letter, frequency);
	   }
	   
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).  
	   System.out.println("****** frequency table");
	   PriorityQueue<HuffmanTreeNode> pQueue = new PriorityQueue<HuffmanTreeNode>();
	   for(String letter: frequencyTtable.keySet()){
		   HuffmanTreeNode node = new HuffmanTreeNode(letter, frequencyTtable.get(letter));
		   pQueue.add(node);
		   System.out.println(letter + " " + frequencyTtable.get(letter));
	   }
   
	   PriorityQueue<HuffmanTreeNode> copy = new PriorityQueue<HuffmanTreeNode>(pQueue);
	   System.out.println("****** priority queue");
	   while(copy.size() > 0){
		   HuffmanTreeNode node = copy.remove();
		   System.out.print(" "+ node.getLetter() + ":" + node.getFrequency() + " ");
	   }
	   System.out.println("\n******");
      	//Use the priority queue of nodes to build the Huffman tree
	   while(pQueue.size() > 1){
		   HuffmanTreeNode node1 = pQueue.remove();
		   HuffmanTreeNode node2 = pQueue.remove();
		   
		   HuffmanTreeNode node3 = new HuffmanTreeNode("*", (node1.getFrequency() + node2.getFrequency()), node1, node2);	   
		   pQueue.add(node3);
	   }   
	   
	   HuffmanTreeNode huffumanTree = pQueue.remove();
	   System.out.println("\n***************** Huffman Tree\n\n" + display(huffumanTree, 0) + "\n*****************\n");

      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
	   
	   String binaryMessage = "";
	   for (int i = 0; i < message.length(); i++){
		   String ll = message.substring(i, i+1);
		   binaryMessage = binaryMessage + getHuffmanCode(ll , huffumanTree);
	   }

	   //System.out.println the binary message 
	   System.out.println("\n\nThe binary message: \n" + binaryMessage);
	   
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
	   String messageFileName = "message."+middlePart+".txt";
	   PrintWriter messageFile = new PrintWriter(new FileWriter(messageFileName));
	   messageFile.println(binaryMessage);
	   messageFile.close();

        //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
	   String schemeFileName = "scheme."+middlePart+".txt";
	   PrintWriter schemeFile = new PrintWriter(new FileWriter(schemeFileName));
	   System.out.println("****** scheme");
	   for(String letter: frequencyTtable.keySet()){
		   String scheme =  letter + getHuffmanCode(letter, huffumanTree);
		   schemeFile.println(scheme);
		   System.out.println(scheme);
	   }
	   schemeFile.close();
	   
	   System.out.println("****** compression rate \n" + binaryMessage.length()/8 + "/" + message.length());
            
   }
   
   public static String getHuffmanCode(String letter, HuffmanTreeNode huffumanTree){
	   
	   HuffmanTreeNode pointer = huffumanTree;
	   if( pointer.getLeft() != null && pointer.getLeft().getLetter().equals(letter)){
		   return "0" ;
	   }
	   else if(pointer.getRight() != null && pointer.getRight().getLetter().equals(letter)){
		   return "1" ;
	   }
	   
	   if(pointer.getLeft() != null ){
		   //must has both left and right children
		   String temp = getHuffmanCode(letter, pointer.getLeft());
		   if (temp != null && !temp.isEmpty()){
			   return "0" + temp;
		   }
		   else{
			   temp = getHuffmanCode(letter, pointer.getRight());
			   if (temp != null && !temp.isEmpty()){
				   return "1" + temp;
			   }

		   }
	   }
		   
	   return "";
   }
   
   /* copy the code that is in TreeLab */
	public static String display(HuffmanTreeNode t, int level) {
		String toRet = "";
		if (t == null)
			return "";
		toRet += display(t.getRight(), level + 1); // recurse right
		for (int k = 0; k < level; k++)
			toRet += "\t";
		toRet += t.getLetter() + ":" + t.getFrequency() + "\n";
		toRet += display(t.getLeft(), level + 1); // recurse left
		return toRet;
	}
   
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
 {
	private String letter;
	private int frequency;
	private HuffmanTreeNode left, right;

	public HuffmanTreeNode(String letter, int frequency) {
		this.letter = letter;
		this.frequency = frequency;
		left = null;
		right = null;
	}

	public HuffmanTreeNode(String letter, int frequency, HuffmanTreeNode initLeft, HuffmanTreeNode initRight) {
		this.letter = letter;
		this.frequency = frequency;
		left = initLeft;
		right = initRight;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public HuffmanTreeNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}

	public HuffmanTreeNode getRight() {
		return right;
	}

	public void setRight(HuffmanTreeNode right) {
		this.right = right;
	}

	@Override
	public int compareTo(HuffmanTreeNode o) {
		int ret = 0;
		
		if(this.frequency > o.getFrequency()){
			ret = 1;
		}
		else if(this.frequency < o.getFrequency()){
			ret = -1;
		}
		else{
			ret = o.getLetter().compareTo(this.getLetter());
			//ret = this.getLetter().compareTo(o.getLetter());
		}
		return ret;
	}

}
