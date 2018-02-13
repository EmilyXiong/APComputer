package com.xiongfamily.emily.apcomp;

// Name:              Date:
import java.util.Scanner;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
	   
	   TreeNode huffmanTreeRoot = new TreeNode(null, null, null);
	   String line = null;
	   char letter;
	   while(huffLines.hasNextLine()){
		   line = huffLines.nextLine();
		   if (line != null && !line.isEmpty()){
			   letter = line.charAt(0);
			   TreeNode node = huffmanTreeRoot;
			   char child;
			   for (int i = 1; i < line.length(); i++){
				   child = line.charAt(i);
				   if (child == '0'){
					   if (node.getLeft() == null){
						   node.setLeft(new TreeNode(null, null, null));
					   }
					   node = node.getLeft();
				   }
				   else if(child == '1'){
					   if (node.getRight() == null){
						   node.setRight(new TreeNode(null, null, null));
					   }
					   node = node.getRight();
				   }
				   if(i == line.length()-1){
					   node.setValue("" + letter);
				   }
			   }
			   
		   }
	   }
	   
	   System.out.println("\n*****************tj66"
	   		+ "\n\n" + display(huffmanTreeRoot, 0) + "\n*****************\n");
	   return huffmanTreeRoot;
   }
   public static String dehuff(String text, TreeNode root)
   {
	   String message = "";
	   TreeNode node = root;
	   for(int i=0; i < text.length(); i++){
		   char c = text.charAt(i);
		   if(c == '0'){
			   node = node.getLeft();
		   }
		   else if(c == '1'){
			   node = node.getRight();
		   }
		   
		   if(node.getLeft() == null && node.getRight() == null){
			   message = message + (String) node.getValue();
			   node = root;
		   }
	   }
	   return message;
   }
   
	/* copy the code that is in TreeLab */
	public static String display(TreeNode t, int level) {
		String toRet = "";
		if (t == null)
			return "";
		toRet += display(t.getRight(), level + 1); // recurse right
		for (int k = 0; k < level; k++)
			toRet += "\t";
		toRet += t.getValue() + "\n";
		toRet += display(t.getLeft(), level + 1); // recurse left
		return toRet;
	}
}

