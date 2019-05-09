import java.util.*; 

class Selection
{
public int mergeSelect(int[] arr, int k)
{
	mergeSort(arr, 0, arr.length-1);
	return arr[k-1];
}

private void mergeSort(int[] arr, int left, int right)
{
	if (left<right)
	{
		int mid = (left+right)/2;
		mergeSort(arr,left,mid);
		mergeSort(arr,mid+1,right);
		merge(arr,left,mid,right);
	}
}

private void merge(int[] arr,int left,int mid,int right)
{
		int Lsz = mid-left+1;
		int Rsz = right-mid;
		int[] Larr = new int[Lsz];
		int[] Rarr = new int[Rsz];

		for(int i=0;i<Lsz;i++)
			Larr[i] = arr[left+i];
		for(int i=0;i<Rsz;i++)
			Rarr[i] = arr[mid+i+1];

		int l=0, r=0, k=left;
		while(l<Lsz && r<Rsz)
		{
			if(Larr[l]<=Rarr[r])
				arr[k] = Larr[l++];
			else
				arr[k] = Rarr[r++];
			k++;
		}

		while(l<Lsz && k<arr.length)
			arr[k++] = Larr[l++];

		while(r<Rsz && k<arr.length)
			arr[k++] = Rarr[r++];
}


//selectK2
public int quickIterSelect(int[] arr, int k, int left, int right)
{
	int pivot = partition(arr, left, right, arr[left]);
	while(pivot!=(k-1))
	{
		if(pivot>(k-1))
			right=pivot-1;
		else
			left=pivot+1;
		pivot = partition(arr, left, right, arr[left]);
	}
	return arr[pivot];
}

//partitions around pivot element
private int partition(int arr[], int left, int right, int pivot) 
{ 
	int i=left;
	while(arr[i]!=pivot)
		i++;
	swap(arr, i, right); //swap last value w/ pivot

	i=left;
	for(int x=left;x<=right-1;x++)
	{
		if(arr[x]<=pivot)
		{
			swap(arr,i,x);
			i++;
		}
	}
	swap(arr,i,right);
	return i;
} 

//selectK3
public int quickRecSelect(int[] arr, int k, int low, int high)
{
	int pivot = partition(arr, low, high, arr[low]);
	if(pivot==(k-1))
		return arr[pivot];
	else if(pivot>(k-1))
		return quickRecSelect(arr, k, low, pivot-1);
	else
		return quickRecSelect(arr, k, pivot+1,high);	
}

//selectK4
public int medianSelect(int[] arr, int k, int left, int right)
{ //call selection on k-1, bc methods use array idx 0
	return arr[select(arr,left,right,k-1)];
}

private int select(int[] arr, int left, int right, int k)
{
	while(left<=right)
	{
		if(left==right)
			return left; //return left
		int pivot = pivot(arr, left, right);
		pivot = partition(arr, left, right, pivot, k);
		if(pivot==k)
			return k; //return kth index
		else if(pivot > k)
			right = pivot-1;
		else
			left = pivot+1;
	}
	return -1; //not found
}

private int pivot(int[] arr, int left, int right)
{
	if(right-left<5) //when <=5 get median directly
		return median5(arr,left,right);
	int median = 0, subright;
	for(int i=left;i<right;i+=5)
	{ 
		//get median position
		subright = i+4;
		if(subright>right)
			subright=right;
		median = median5(arr,i,subright);
		int pos = left + (i-left)/5;
		//swap median & position
		swap(arr,median,pos);
	}
	int mid = right-left / (10+left+1);
	subright = left+(right-left)/5;  //reuse var from loop
	return select(arr,left,subright,mid);
}

private int partition(int[] arr,int left,int right,int pivot,int k)
{
	int value = arr[pivot];
	arr[pivot] = arr[right];
	arr[right] = value;
	int index = left;
	for(int i=left;i<right-1;i++)
	{
		if(arr[i] < value)
		{
			swap(arr,i,index);
			index++;
		}
	}
	int indexEq = index;
	for(int j=index;j<right-1;j++)
	{
		if(arr[j] == value)
		{
			swap(arr,j,indexEq);
			indexEq++;
		}
	}
	swap(arr,right,indexEq);
	if(k<index)
		return index;
	else if(k<=indexEq)
		return k;
	else
		return indexEq;
}

//called only on arrays of <=5 elements
private int median5(int[] arr, int left, int right)
{ 
	mergeSort(arr,left,right);
	return (left+right)/2;
}

private void swap(int[] arr, int idx1, int idx2)
{
	int temp = arr[idx1];
	arr[idx1] = arr[idx2];
	arr[idx2] = temp;
}
}