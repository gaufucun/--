/* 
 * 本代码运用优先队列式分支限界法解决了最小重量机器设计问题。
 * 算法思路：对于在某一个供应商是否购买某一零件，可以将这个过程抽象化为子集树模型。
 * 该树的第i层则代表第i个零件的购买情况，每个商家j对应一棵子树。从根节点开始，对于当前讨论的
 * 节点我们将之当作扩展节点，遍历该扩展节点的所有子节点，将其中符合条件的子节点全部插入优先
 * 队列中（判断条件运用剪枝函数，下面讨论）。当遍历完后，该节点成为死节点，从优先队列中取出
 * 在当前情况下重量最小的节点继续向下进行迭代，直到到达某叶子节点后，记录下当前情况下的最小
 * 重量，然后继续将优先队列中的活节点依次出队，直到优先队列为空，整个子集树只剩下一条最优路径。
 * 
 * 剪枝函数：在对某个节点是否符合条件的判断中，采用了约束函数和限界函数的双重判断。
 * 约束函数：对于该节点到最终叶子节点中所有节点价格的最小值的和超过了要求的节点，直接
 * 置为死节点，不再放入优先队列，并且其子节点都不必再进行讨论。
 * 限界函数：对于该节点到最终叶子节点中所有节点重量最小值的和已经小于了目前已经计算出的重量的最小值。
 * 则直接置为死节点，不放入优先队列，并且其子节点都不必再进行讨论。
 * */
 
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
//定义节点类，分别存储该节点的层数level，该节点的父节点，从根节点到该节点的价值总和，重量总和
//该节点的商家序号。覆盖compareTo方法，优先按照重量对节点进行比较。
class Node implements Comparable<Node>{
	int value = 0;
	int weight = 0;
	int level = 0;
	int source = 0;
	Node father = null;
	public int compareTo(Node d){
		if(this.weight<d.weight) return -1;
		else if(this.weight==d.weight) return(this.value-d.value);
		else return 1;
	}
}
 
public class four_1 {
	//以下讨论中，商品的重量存储在w[][]中，价格存储在c[][]中。角标全部从1开始。
	//共有n个零件需要购买，同一个零件共有m个商家在销售，消费的上限为d。
	public static void getMinWeight(int[][] c,int[][] w,int m,int n,int d){
//		int minValue = Integer.MAX_VALUE;
		int minWeight = Integer.MAX_VALUE;
		//定义购买的路径数组。
		int[] way = new int[n+1]; 
		//定义优先队列。
		PriorityQueue<Node> heap = new PriorityQueue<Node>();
		//初始化一个全为0的节点，放入优先队列，开始循环。
		Node initial = new Node();
		heap.add(initial);
		//只要优先队列非空，循环就继续下去，依次继续取出优先队列中的节点。
		while(!heap.isEmpty()){
			Node fatherNode = heap.poll();
			//当取出的节点已经到达叶子节点时，如果所找到的这条路径的零件总重量小于之前的重量，则更新
			//购买路径和当前已发现的最小重量。
			if(fatherNode.level == n){
				if(fatherNode.weight < minWeight){
					minWeight = fatherNode.weight;
//					minValue = fatherNode.value;
					for(int i=n;i>=1;i--){
						way[i] = fatherNode.source;
						fatherNode = fatherNode.father; 
					}
				}
					
			}
			//否则就对于取出的节点先进行剪枝判断。
			else{
				int min_weight = fatherNode.weight;
				int min_value = fatherNode.value;
				//遍历该节点到叶子节点后面的最优路径的所有节点。
				for(int i = fatherNode.level+1;i<n+1;i++){
					int temp_min_value = Integer.MAX_VALUE;
					int temp_min_weight = Integer.MAX_VALUE;
					//选取下面每一层中的重量最小的节点和价值最小的节点。
					for(int j=1;j<m+1;j++){
						if(c[i][j]<temp_min_value)
							temp_min_value = c[i][j];
						if(w[i][j]<temp_min_weight)
							temp_min_weight = c[i][j];
					}
					//将能够获取到的理想最优值取出，看是否符合限界函数和约束函数。
					//注意：此时的最优值不一定能同时取到，因为重量最优的点和价值最优的点不一定是同一个节点。
					min_weight += temp_min_weight;
					min_value += temp_min_value;
				}
				//对于宽约束都不能满足的节点，直接置为死节点，不再讨论其剩余节点。
				if(min_weight > minWeight || min_value > d){
					continue;
				}
				//剩下的能够符合要求的所有子节点全部放入到优先队列中去。
				for(int i=1;i<m+1;i++){
					if(fatherNode.value+c[fatherNode.level+1][i] <=d &&
							fatherNode.weight+w[fatherNode.level+1][i]<minWeight){
						Node newNode = new Node();
						newNode.father = fatherNode;
						newNode.level = fatherNode.level+1;
						newNode.source = i;
						newNode.value = fatherNode.value+c[fatherNode.level+1][i];
						newNode.weight = fatherNode.weight+w[fatherNode.level+1][i];
						heap.add(newNode);
					}
				}
				
					
			}
		}
		//输出能够取得的最小重量和购买路径。
		System.out.println(minWeight+"");
		for(int i = 1;i<n+1;i++)
			System.out.print(way[i]+" ");
	}
	
	//主函数用于对各项数值进行初始化。从E盘input.txt文件下读取初始数值。
	public static void main(String[] args) throws IOException{
		int m,n,d;
		BufferedReader bufr = new BufferedReader(new FileReader("D://input.txt"));
		String[] str = bufr.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		d = Integer.parseInt(str[2]);
		int[][] c = new int[n+1][m+1];
		int[][] w = new int[n+1][m+1];
		for(int i=1;i<n+1;i++){
			str = bufr.readLine().split(" ");
			for(int j=1;j<m+1;j++)
				c[i][j] = Integer.parseInt(str[j-1]);
		}
		for(int i=1;i<n+1;i++){
			str = bufr.readLine().split(" ");
			for(int j=1;j<m+1;j++)
				w[i][j] = Integer.parseInt(str[j-1]);
		}
		//调用该函数得到结果。
		getMinWeight(c,w,m,n,d);
		//用于测试输入数据。
		//for(int k : w[1])
		//	System.out.println(k);
		bufr.close();
			
	}
}
