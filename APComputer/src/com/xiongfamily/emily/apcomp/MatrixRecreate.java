package com.xiongfamily.emily.apcomp;


public class MatrixRecreate
{
   public static void main(String[] args)
   {
      int[][] matrix = TheMatrix.create();
      int[] rowcount = new int[matrix.length];
      int[] colcount = new int[matrix[0].length];
      TheMatrix.count(matrix, rowcount, colcount);
      TheMatrix.display(matrix, rowcount, colcount);
      //TheMatrix.re_create(rowcount, colcount);
      //System.out.println("\n\n**** re-create");
      //TheMatrix.display(TheMatrix.getRecreatedMatrix(), rowcount, colcount);
   }
}
class TheMatrix
{
	//do not instantiate recreatedMatrix yet. Only instantiate and set that in recur.
   private static int[][] recreatedMatrix;
   
   public static int[][] getRecreatedMatrix()
   {
      return recreatedMatrix;
   }
   public static int[][] create()
   {
	   
      int row = 10 + (int)(Math.random()*5);
      int col = 10 + (int)(Math.random()*5);
      int[][] matrix = new int[row][col];
      for(int j = 0; j < matrix.length; j++)
      {
         for(int c = 0; c < matrix[j].length; c++)
         {
            matrix[j][c] = ((int)(Math.random()*3))==0 ? 0 : 1;
         }
      }  
      System.out.println("*** matrix[" + row + "]["  + col+ "]\n");
      return matrix;
   }
   
   public static void count(int[][] matrix, int[] rowcount, int[] colcount)
   {
      //do the row count. matrix.length = number of rows
      for(int row = 0; row < rowcount.length; row++)
      {
    	  for (int col = 0; col < colcount.length; col++){
    		  rowcount[row] = rowcount[row] + matrix[row][col];
    	  }

      }
      //do col count. 
      for(int col =0; col <colcount.length; col++){
    	  for(int row =0; row < rowcount.length; row++){
    		  colcount[col] = colcount[col] + matrix[row][col];
    	  }
      }
   }
   
   public static void display(int[][] matrix, int[] rowcount, int[] colcount)
   {
	   System.out.print("  ");
	   for (int col = 0; col < colcount.length; col++){
			   System.out.print(colcount[col]+" ");
	   }
	   System.out.print("\n");
	   System.out.print("  ");
	   for (int col = 0; col < colcount.length; col++){
			   System.out.print("--");
	   }
	   System.out.print("\n");
	   
	   for (int row =0; row < rowcount.length; row++){
		   
		   System.out.print(rowcount[row] + "|");
		   for (int col = 0; col < colcount.length; col++){
			   System.out.print(matrix[row][col]+" " );
		   }
		   System.out.print("\n");
	   }
	   
   }

   
   
   
   
   //should call recur.
   public static void re_create(int[] rowcount, int[] colcount)
   {
      int [][] m = new int[rowcount.length][colcount.length];
      
      m[0][0] = 0;
      recur(m,rowcount, colcount, 0, 0);
      if (recreatedMatrix == null){
    	  m[0][0] = 1;
          recur(m,rowcount, colcount, 0, 0);
      }
   }
   
   
   private static void recur(int[][] m, int[] rowcount, int[] colcount, int row, int col)
   {

      if(row==rowcount.length-1 && col==colcount.length-1 && compare(m, rowcount, colcount))    //base case: if new matrix works, then copy over to recreatedMatrix
      {
      	//copy over from m to recreatedMatrix (not just references)
         recreatedMatrix = new int[m.length][];
         for(int i = 0; i < m.length; i++)
         {
            recreatedMatrix[i] = new int[m[i].length];
            for (int j = 0; j < m[i].length; j++)
            {
               recreatedMatrix[i][j] = m[i][j];
            }
         } 
         return; //we're done!
      }
      
      
      if(col < colcount.length-1){
    	  m[row][col+1] =0;
    	  recur(m,rowcount,colcount,row, col+1);
    	  m[row][col+1] =1;
    	  recur(m,rowcount,colcount,row, col+1);
      }
      else if (row < rowcount.length-1){
    	  m[row+1][0] =0;
    	  recur(m,rowcount,colcount,row+1, 0);
    	  m[row+1][0] =1;
    	  recur(m,rowcount,colcount,row+1, 0);
      }

   }
   
   public static boolean compare(int[][] m, int[] rowcount, int[] colcount)
   {
	   //count the matrix m

	   int[] m_rowcount = new int[m.length];
	   int[] m_colcount = new int[m[0].length];
	   count(m, m_rowcount, m_colcount);
	   //compare if the counts are the same as the given onces
	   for (int i = 0; i < rowcount.length; i++){
		   if (rowcount[i] != m_rowcount[i]){
			   return false;
		   }
	   }
	   
	   for (int j = 0; j < colcount.length; j++){
		   if (colcount[j] != m_colcount[j]){
			   return false;
		   }
	   }
	   	
	   return true;
   }
}
