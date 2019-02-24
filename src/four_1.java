/* 
 * �������������ȶ���ʽ��֧�޽編�������С��������������⡣
 * �㷨˼·��������ĳһ����Ӧ���Ƿ���ĳһ��������Խ�������̳���Ϊ�Ӽ���ģ�͡�
 * �����ĵ�i��������i������Ĺ��������ÿ���̼�j��Ӧһ���������Ӹ��ڵ㿪ʼ�����ڵ�ǰ���۵�
 * �ڵ����ǽ�֮������չ�ڵ㣬��������չ�ڵ�������ӽڵ㣬�����з����������ӽڵ�ȫ����������
 * �����У��ж��������ü�֦�������������ۣ�����������󣬸ýڵ��Ϊ���ڵ㣬�����ȶ�����ȡ��
 * �ڵ�ǰ�����������С�Ľڵ�������½��е�����ֱ������ĳҶ�ӽڵ�󣬼�¼�µ�ǰ����µ���С
 * ������Ȼ����������ȶ����еĻ�ڵ����γ��ӣ�ֱ�����ȶ���Ϊ�գ������Ӽ���ֻʣ��һ������·����
 * 
 * ��֦�������ڶ�ĳ���ڵ��Ƿ�����������ж��У�������Լ���������޽纯����˫���жϡ�
 * Լ�����������ڸýڵ㵽����Ҷ�ӽڵ������нڵ�۸����Сֵ�ĺͳ�����Ҫ��Ľڵ㣬ֱ��
 * ��Ϊ���ڵ㣬���ٷ������ȶ��У��������ӽڵ㶼�����ٽ������ۡ�
 * �޽纯�������ڸýڵ㵽����Ҷ�ӽڵ������нڵ�������Сֵ�ĺ��Ѿ�С����Ŀǰ�Ѿ����������������Сֵ��
 * ��ֱ����Ϊ���ڵ㣬���������ȶ��У��������ӽڵ㶼�����ٽ������ۡ�
 * */
 
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
//����ڵ��࣬�ֱ�洢�ýڵ�Ĳ���level���ýڵ�ĸ��ڵ㣬�Ӹ��ڵ㵽�ýڵ�ļ�ֵ�ܺͣ������ܺ�
//�ýڵ���̼���š�����compareTo���������Ȱ��������Խڵ���бȽϡ�
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
	//���������У���Ʒ�������洢��w[][]�У��۸�洢��c[][]�С��Ǳ�ȫ����1��ʼ��
	//����n�������Ҫ����ͬһ���������m���̼������ۣ����ѵ�����Ϊd��
	public static void getMinWeight(int[][] c,int[][] w,int m,int n,int d){
//		int minValue = Integer.MAX_VALUE;
		int minWeight = Integer.MAX_VALUE;
		//���幺���·�����顣
		int[] way = new int[n+1]; 
		//�������ȶ��С�
		PriorityQueue<Node> heap = new PriorityQueue<Node>();
		//��ʼ��һ��ȫΪ0�Ľڵ㣬�������ȶ��У���ʼѭ����
		Node initial = new Node();
		heap.add(initial);
		//ֻҪ���ȶ��зǿգ�ѭ���ͼ�����ȥ�����μ���ȡ�����ȶ����еĽڵ㡣
		while(!heap.isEmpty()){
			Node fatherNode = heap.poll();
			//��ȡ���Ľڵ��Ѿ�����Ҷ�ӽڵ�ʱ��������ҵ�������·�������������С��֮ǰ�������������
			//����·���͵�ǰ�ѷ��ֵ���С������
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
			//����Ͷ���ȡ���Ľڵ��Ƚ��м�֦�жϡ�
			else{
				int min_weight = fatherNode.weight;
				int min_value = fatherNode.value;
				//�����ýڵ㵽Ҷ�ӽڵ���������·�������нڵ㡣
				for(int i = fatherNode.level+1;i<n+1;i++){
					int temp_min_value = Integer.MAX_VALUE;
					int temp_min_weight = Integer.MAX_VALUE;
					//ѡȡ����ÿһ���е�������С�Ľڵ�ͼ�ֵ��С�Ľڵ㡣
					for(int j=1;j<m+1;j++){
						if(c[i][j]<temp_min_value)
							temp_min_value = c[i][j];
						if(w[i][j]<temp_min_weight)
							temp_min_weight = c[i][j];
					}
					//���ܹ���ȡ������������ֵȡ�������Ƿ�����޽纯����Լ��������
					//ע�⣺��ʱ������ֵ��һ����ͬʱȡ������Ϊ�������ŵĵ�ͼ�ֵ���ŵĵ㲻һ����ͬһ���ڵ㡣
					min_weight += temp_min_weight;
					min_value += temp_min_value;
				}
				//���ڿ�Լ������������Ľڵ㣬ֱ����Ϊ���ڵ㣬����������ʣ��ڵ㡣
				if(min_weight > minWeight || min_value > d){
					continue;
				}
				//ʣ�µ��ܹ�����Ҫ��������ӽڵ�ȫ�����뵽���ȶ�����ȥ��
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
		//����ܹ�ȡ�õ���С�����͹���·����
		System.out.println(minWeight+"");
		for(int i = 1;i<n+1;i++)
			System.out.print(way[i]+" ");
	}
	
	//���������ڶԸ�����ֵ���г�ʼ������E��input.txt�ļ��¶�ȡ��ʼ��ֵ��
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
		//���øú����õ������
		getMinWeight(c,w,m,n,d);
		//���ڲ����������ݡ�
		//for(int k : w[1])
		//	System.out.println(k);
		bufr.close();
			
	}
}
