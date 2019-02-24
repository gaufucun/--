import java.util.Scanner;

public class three_2 {
	static int rent[][] = new int[100][100];

	public static void main(String[] args) {
	 
		int n;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("输入有几个站");
		n=sc.nextInt();
		
		
		//将每个站之间的费用都存起来
		for(int i=1;i<n;i++) {
			for(int j=i+1;j<=n;j++) {
				System.out.println("第"+i+"站到第"+j+"的费用：");
				rent[i][j] = sc.nextInt();
			}
		}
		
		
		
		System.out.println("最少费用："+rentOfBoat(n)+"");

	}
	
	public static int rentOfBoat(int n) {
		int t;
		for(int i=n-2; i>=1; i--){
			for(int j=i+2; j<=n; j++){
				for(int k=i+1; k<j; k++){
					t = rent[i][k] + rent[k][j];
					//用较优解替代原来的解
					if(t<rent[i][j]){
						rent[i][j] = t;
					}
				}
			}
		}
	
		
		return rent[1][n];
	}

}
