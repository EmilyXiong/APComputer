package com.xiongfamily.emily.apcomp;

//name :Emily Xiong   date:1/26/17

import java.util.*;
public class McRonaldPQ
{
   public static final int TIME = 1079;  //18 hrs * 60 min
   private static int[] totalCustomers;
   private static int[] totalWait;
   private static int[] longestWait;
   private static HeapPriorityQueue<Customer> line;
   
   
   public static void main(String[] args)
   {
      line = new HeapPriorityQueue<Customer>();
      totalCustomers = new int[4];
      totalWait = new int[4];
      longestWait = new int[4];
      
      int areas = 3;     
      ServiceArea[] service = new ServiceArea[areas];
      for(int i = 0; i < areas; i++)
      {
         service[i] = new ServiceArea();
      } 
      
      for(int min = 0; min <= TIME ; min++)
      {
         if(Math.random() < 0.6)
         {
            Customer newcustomer = new Customer(min);
            line.add(newcustomer);
            totalCustomers[newcustomer.getSchoolYear() - 1]++;
         }
         for(int j = 0; j < areas; j++)
         {
            if(!service[j].isServing() && !line.isEmpty())
            {
               service[j].startService(line.remove());
            }
            service[j].progressOneMinute();
            
            if(!service[j].isServing() && service[j].getCustomer() != null)
            {
               if(min > 400)
               {
                  int hh = 0;
               }
               int waitTime = min - service[j].getCustomer().getWait();//get wait time
               int schoolYear = service[j].getCustomer().getSchoolYear();
               totalWait[schoolYear - 1] += waitTime;
               if(longestWait[schoolYear - 1] < waitTime)
               {
                  longestWait[schoolYear - 1] = waitTime;                         
               }
               service[j].setCustomer(null);
               service[j].setServingTimeLeft(0);
            }
         } 
                 
         }
         
      
    
      System.out.println("Customer      Total Served    Longest Wait    Average Wait");
      System.out.println("Senior        " + totalCustomers[3] + "              " + longestWait[3] + "              " + (double)totalWait[3]/totalCustomers[3]);
      System.out.println("Junior        " + totalCustomers[2] + "              " + longestWait[2] + "              " + (double)totalWait[2]/totalCustomers[2]);
      System.out.println("Sophomore     " + totalCustomers[1] + "              " + longestWait[1] + "              " + (double)totalWait[1]/totalCustomers[1]);
      System.out.println("Freshman      " + totalCustomers[0] + "              " + longestWait[0] + "              " + (double)totalWait[0]/totalCustomers[0]);
   }
}
class ServiceArea
{
   Customer customer;
   int servingTimeLeft;
   
   public ServiceArea()
   {
      customer = null;
      servingTimeLeft = -1;
   }
   public void startService(Customer c)
   {
      customer = c;
      servingTimeLeft = (int) (Math.random() * 6) + 2;
   }
   public boolean isServing()
   {
      boolean serve = false;
      if(servingTimeLeft > 0)
      {
         serve = true;
      }
      return serve;
   }
   public void progressOneMinute()
   {
      if(servingTimeLeft > 0)
      {
         servingTimeLeft--;
      }     
   }
   public Customer getCustomer()
   {
      return customer;
   }
   public void setCustomer(Customer c)
   {
      customer = c;
   }
   public void setServingTimeLeft(int i)
   {
      servingTimeLeft = i;
   }

}
class Customer implements Comparable<Customer>
{
   private int schoolYear;
   private int time;
   
   public Customer(int t)
   {
      schoolYear = (int) (Math.random() * 4) + 1;
      time = t;
   }
   public int compareTo(Customer c)
   {
      return c.getSchoolYear() - getSchoolYear();
   }
   public int getSchoolYear()
   {
      return schoolYear;
   }
   public int getWait()
   {
      return time;
   }
}