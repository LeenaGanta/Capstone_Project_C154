package sorts;

/* 
 * Best Complexity: O(n)
 * Worst Complexity:O(n2)
 * Average Complexity:O(n2)
 * Auxiliary Space:O(1)
 */

import java.util.Scanner;

public class BubbleSort
{
   static void bubbleSort(int arr[],int n)
   {
	   int i,j,temp;
	   boolean swapped=false;
	   for(i=0;i<n-1;i++)
	   {
		   for(j=0;j<n-i-1;j++)
		   {
			   if(arr[j]>arr[j+1])
			   {
				   temp=arr[j];
				   arr[j]=arr[j+1];
				   arr[j+1]=temp;
				   swapped=true;
			   }
		   }
		   if(swapped==false)
			   break;
	   }   
   }
   static void display(int arr[],int n)
   {
	   for(int i=0;i<arr.length;i++)
	    System.out.print(arr[i]+" ");
   }
   public static void main(String[] args)
   {
	  Scanner sc=new Scanner(System.in);
	  System.out.println("Enter no. of elements:");
	  int n=sc.nextInt();
	  int []arr=new int[n];
	  for(int i=0;i<n;i++)
	  {
		  System.out.println("Enter number:");
		  arr[i]=sc.nextInt();
	  }
	 // int arr[]= {12,43,2,1,4};
	  //int n=arr.length;
	  bubbleSort(arr,n);
	  System.out.println("Sorted elements are:");
	  display(arr,n);  
	   
}
}


