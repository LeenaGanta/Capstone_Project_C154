package sorts;
/*
 * Best Case Complexity: O(n2)
 * Average Case Complexity: O(n2)
 * Worst Case Complexity: O(n2)
 * Auxiliary Space: O(1)
 */
import java.util.Scanner;

public class SelectionSort 
{
	static void selectionSort(int arr[],int n)
	{
		int min_idx,temp;
		for(int i=0;i<n-1;i++)
			{
				min_idx=i;
				for(int j=i+1;j<n;j++)
				{
					if(arr[min_idx]>arr[j])
						min_idx=j;
				}
				temp=arr[min_idx];
				arr[min_idx]=arr[i];
				arr[i]=temp;
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
		  //int arr[]= {12,43,2,1,4};
		 // int n=arr.length;
		  selectionSort(arr,n);
		  System.out.println("Sorted elements are:");
		  display(arr,n);  
		   
	}

}
