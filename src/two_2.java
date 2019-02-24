import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class two_2{//广搜
	static int sa,sb,sc,ea,eb,ec;
	static boolean vis[][][];//标记当前水的数量是否出现过
	static Queue<F> queue=null;
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		
			Init(input);
			if(ea+eb+ec!=sa){//开始状态和最终状态水的数量相同
				System.out.println(-1);
							}
			F f=new F(sa,0,0);//开始初始化，第一杯水满，第二第三水为空
			f.sum=0;//初始化操作次数为零
			vis=new boolean[sa+5][sb+5][sc+5];
			vis[sa][0][0]=true;//标记当前
			System.out.println(f.a[0]+" "+f.a[1]+" "+f.a[2]+"");
			queue=new LinkedList<F>();
			queue.add(f);
			System.out.println(bfs(queue));
		
	}
	private static int bfs(Queue<F> q) {//广度优先搜索
		while(!q.isEmpty()){
			F f=q.poll();
//			if(f.a[0]==ea&&f.a[1]==eb&&f.a[2]==ec){
//				return f.sum;
//			}
			
			int[] s=f.copy();
			if(s[0]>0&&s[1]<sb){//
				if(sb-s[1]<s[0]){//
					s[0]-=sb-s[1];
					s[1]=sb;
				}
				else{//全部倒完
					s[1]+=s[0];
					s[0]=0;
				}
				if(!vis[s[0]][s[1]][s[2]]){
					F g=new F(s[0],s[1],s[2]);
					g.sum=f.sum+1;
					if(s[0]==ea&&s[1]==eb&&s[2]==ec){
						System.out.println(ea+" "+eb+" "+ec);
						return g.sum;
					}
					q.add(g);
					vis[s[0]][s[1]][s[2]]=true;
					System.out.println(s[0]+" "+s[1]+" "+s[2]+"");
					
					
				}
			}
			int[] s1=f.copy();
			if(s1[0]>0&&s1[2]<sc){//
				if(sc-s1[2]<s1[0]){
					s1[0]-=sc-s1[2];
					s1[2]=sc;
				}
				else{
					s1[2]+=s1[0];
					s1[0]=0;
				}
				if(!vis[s1[0]][s1[1]][s1[2]]){
					F g=new F(s1[0],s1[1],s1[2]);
					g.sum=f.sum+1;
					if(s1[0]==ea&&s1[1]==eb&&s1[2]==ec){
						System.out.println(ea+" "+eb+" "+ec);
						return g.sum;
					}
					q.add(g);
					vis[s1[0]][s1[1]][s1[2]]=true;
					System.out.println(s1[0]+" "+s1[1]+" "+s1[2]+"");
					
					
				}
			}
			int[] s2=f.copy();
			if(s2[1]>0&&s2[0]<sa){//
				if(sa-s2[0]<s2[1]){
					s2[1]-=sa-s2[0];
					s2[0]=sa;
				}
				else{
					s2[0]+=s2[1];
					s2[1]=0;
				}
				if(!vis[s2[0]][s2[1]][s2[2]]){
					F g=new F(s2[0],s2[1],s2[2]);
					g.sum=f.sum+1;
					if(s2[0]==ea&&s2[1]==eb&&s2[2]==ec){
						System.out.println(ea+" "+eb+" "+ec);
						return g.sum;
					}
					q.add(g);
					vis[s2[0]][s2[1]][s2[2]]=true;
					System.out.println(s2[0]+" "+s2[1]+" "+s2[2]+"");
					
					
				}
			}
			int[] s3=f.copy();
			if(s3[1]>0&&s3[2]<sc){//第二水杯向第三水杯倒水
				if(sc-s3[2]<s3[1]){
					s3[1]-=sc-s3[2];
					s3[2]=sc;
				}
				else{
					s3[2]+=s3[1];
					s3[1]=0;
				}
				if(!vis[s3[0]][s3[1]][s3[2]]){
					F g=new F(s3[0],s3[1],s3[2]);
					g.sum=f.sum+1;
					if(s3[0]==ea&&s3[1]==eb&&s3[2]==ec){
						System.out.println(ea+" "+eb+" "+ec);
						return g.sum;
					}
					q.add(g);
					vis[s3[0]][s3[1]][s3[2]]=true;
					System.out.println(s3[0]+" "+s3[1]+" "+s3[2]+"");
					
				}
			}
			int[] s4=f.copy();
			if(s4[2]>0&&s4[0]<sa){//第三水杯向第一水杯倒水
				if(sa-s4[0]<s4[2]){
					s4[2]-=sa-s4[0];
					s4[0]=sa;
				}
				else{
					s4[0]+=s4[2];
					s4[2]=0;
				}
				if(!vis[s4[0]][s4[1]][s4[2]]){
					F g=new F(s4[0],s4[1],s4[2]);
					g.sum=f.sum+1;
					if(s4[0]==ea&&s4[1]==eb&&s4[2]==ec){
						System.out.println(ea+" "+eb+" "+ec);
						return g.sum;
					}
					q.add(g);
					vis[s4[0]][s4[1]][s4[2]]=true;
					System.out.println(s4[0]+" "+s4[1]+" "+s4[2]+"");
					
					
				}
			}
			int[] s5=f.copy();
			if(s5[2]>0&&s5[1]<sb){//第三水杯向第二水杯倒水
				if(sb-s5[1]<s5[2]){
					s5[2]-=sb-s5[1];
					s5[1]=sb;
				}
				else{
					s5[1]+=s5[2];
					s5[2]=0;
				}
				if(!vis[s5[0]][s5[1]][s5[2]]){
					
					F g=new F(s5[0],s5[1],s5[2]);
					g.sum=f.sum+1;
					if(s5[0]==ea&&s5[1]==eb&&s5[2]==ec){
						System.out.println(ea+" "+eb+" "+ec);
						return g.sum;
					}
					q.add(g);
					vis[s5[0]][s5[1]][s5[2]]=true; 
					System.out.println(s5[0]+" "+s5[1]+" "+s5[2]+"");
					
					
				}
			}
			
		}
		
				return -1;
	}
	private static void Init(Scanner input) {//初始化输入
		sa=input.nextInt();
		sb=input.nextInt();
		sc=input.nextInt();
		ea=input.nextInt();
		eb=input.nextInt();
		ec=input.nextInt();
	}
}
class F{//存当前三个水杯的水数量
	int a[];
	int sum;
	public F(int a1, int a2, int a3) {
		a=new int[3];
		this.a[0] = a1;
		this.a[1] = a2;
		this.a[2] = a3;
		
	}
	public int[] copy(){
		int s[]=new int[a.length];
		for(int i=0;i<a.length;i++)
			s[i]=a[i];
		return s;
	}
	F(){}
} 
