



public  class one_1 {
	public static int[][] a = new int[10000][10000];
	
	public  static void main(String[] args) {
		
//		System.out.println(bino1(50,20));//递归
		System.out.println(getBinomial(20,50));//备忘录
//		System.out.println(binomial(4,2));//迭代法
	}
	
	
	
//二项式的递归求法
public static  int bino1(int n, int r) 
{
	if (r == 0 || r == n)
		return 1;
	else
	return bino1(n - 1, r - 1) + bino1(n - 1, r);
 
}
//备忘录方法

public static int getBinomial(int k,int n){	
	
	if(k==0||k==n){
		return 1;
	}else if(a[n][k]!=0){//判断是否已经运算过。
		return a[n][k];
	}else{
		a[n][k] = getBinomial(k-1,n-1)+getBinomial(k,n-1);
	}
	
	return a[n][k];
}
//迭代法
public static int binomial(int n, int m)  
{  
	int value[] = new int[n+1];  
    for(int i=0;i<=n;i++)  
    {  
        value[i] = 1;  
  
        /* 边界条件m=0,n=m的情况 */  
        for(int j=i-1;j>0;j--)  
        {  
            value[j] = value[j] + value[j-1];  
         }  
    }  
    return value[m]; 
 

}  






}