
class Main {
  public static void main(String[] args) 
	{
		Test test = new Test();
		test.testKth(400000);
		//test.testKth(6000000);
		//test.testKth(7000000);
		//test.testKth(8000000);
		//test.testKth(9000000);
		//test.testKth(10000000);

	}

	/*
	Selection find = new Selection();

		int[] arr = {143,26,47,4,128,79};
		System.out.println("\n\n\nInitial array:");
		for(int i:arr)
		{
			System.out.print(i+" ");
		}
		int k = 6;
		int k1 = find.mergeSelect(arr, k);
		int k2 = find.quickIterSelect(arr, k, 0, arr.length-1);
		int k3 = find.quickRecSelect(arr, k, 0, arr.length-1);
		int k4 = find.medianSelect(arr, k, 0, arr.length-1);
		System.out.println("\nkth value="+k);
		System.out.println("\n(MergeSelect)\nK1 value="+k1);
		System.out.println("\n(Iterative QuickSelect)\nK2 value="+k2);
		System.out.println("\n(Recursive QuickSelect)\nK3 value="+k3);
		System.out.println("\n(MedianofMedianSelect)\nK4 value="+k4+"\n\nFinal array:");
		for(int i:arr)
		{
			System.out.print(i+" ");
		}
	*/
}