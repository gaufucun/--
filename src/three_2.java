import java.util.Scanner;

public class three_2 {
	static int rent[][] = new int[100][100];

	public static void main(String[] args) {
	 
		int n;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("�����м���վ");
		n=sc.nextInt();
		
		
		//��ÿ��վ֮��ķ��ö�������
		for(int i=1;i<n;i++) {
			for(int j=i+1;j<=n;j++) {
				System.out.println("��"+i+"վ����"+j+"�ķ��ã�");
				rent[i][j] = sc.nextInt();
			}
		}
		
		
		
		System.out.println("���ٷ��ã�"+rentOfBoat(n)+"");

	}
	
	public static int rentOfBoat(int n) {
		int t;
		for(int i=n-2; i>=1; i--){
			for(int j=i+2; j<=n; j++){
				for(int k=i+1; k<j; k++){
					t = rent[i][k] + rent[k][j];
					//�ý��Ž����ԭ���Ľ�
					if(t<rent[i][j]){
						rent[i][j] = t;
					}
				}
			}
		}
	
		
		return rent[1][n];
	}

}
