import java.util.*;

class Test
{
private Selection select;
private int[] array;

public Test()
{
	select = new Selection();
}

public void buildArray(int len)
{
	array = new int[len];
	int i=0;
	while(i<len)
	{
		array[i] = (int)(Math.random()*(len*10)+1);
		i++;
	}
}

public long timeCounter(int k, int kselect)
{
	long startTime, runTime, avg = 0;
	int value = 0;
	for(int i=0;i<1;i++)
	{
		startTime = System.currentTimeMillis();
		if(kselect==1)
		{
			value = select.mergeSelect(array,k);
		}
		else if(kselect==2)
		{
			value = select.quickIterSelect(array,k,0,array.length-1);
		}
		else if(kselect==3)
		{
			value = select.quickRecSelect(array,k,0,array.length-1);
		}
		else
		{
			value = select.medianSelect(array,k,0,array.length-1);
		}
		
		runTime = System.currentTimeMillis() - startTime;
		avg+=runTime;
	}
	avg/=1;
	System.out.println("\t\t\t" + k + "-th value: " + value + "\t\tRUNTIME: " + avg);
	return avg;
}

public void testSelection(int n, int k)
{
	System.out.println("\n\nstart test for: n="+ n + " k="+ k);
	//printArray();
	System.out.print("MergeSelect testing...");
	timeCounter(k,1);
	System.out.print("Iterative QuickSelect testing...");
	timeCounter(k,2);
	System.out.print("Recursive QuickSelect testing...");
	timeCounter(k,3);
	System.out.print("MedianSelect testing...");
	timeCounter(k,4);
	//System.out.print("\nend test for: n="+ n + " k="+ k);
}

public void test1vs4(int n)
{
	long k1avg=0, k4avg=0;
	buildArray(n);
	System.out.println("\nArraySize: "+n);
	k1avg+=timeCounter(1,1);
	k1avg+=timeCounter(n/4,1);
	k1avg+=timeCounter(n/2,1);
	k1avg+=timeCounter((int)((.75)*n),1);
	k1avg+=timeCounter(n,1);
	k1avg/=5;
	System.out.println("K1 RUNTIME: "+k1avg);

	k4avg+=timeCounter(1,4);
	k4avg+=timeCounter(n/4,4);
	k4avg+=timeCounter(n/2,4);
	k4avg+=timeCounter((int)((.75)*n),4);
	k4avg+=timeCounter(n,4);
	k4avg/=5;
	System.out.println("K4 RUNTIME: "+k4avg);
}

public void testKth(int n)
{
	buildArray(n);
	testSelection(n, 1);
	System.out.print("\n_______________");
	testSelection(n, n/4);
	System.out.print("\n_______________");
	testSelection(n, n/2);
	System.out.print("\n_______________");
	testSelection(n, (int)((.75)*n));
	System.out.print("\n_______________");
	testSelection(n, n);
	System.out.print("\n_______________");
}

private void printArray()
{
	System.out.print("\nTest array: ");
	for(int i:array)
		System.out.print(i+" ");
	System.out.println();
}
} //end class Test