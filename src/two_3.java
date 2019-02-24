 
import java.util.ArrayList;
import java.util.Scanner;
 

public class two_3 {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Experssion exp= new Experssion();
		Thinker ti=new Thinker(exp);
		ArrayList<Integer> card=new ArrayList<Integer>();
		int sum=24;
		Scanner sc=new Scanner(System.in);
		int t;
		for(int i=0;i<4;i++){
			t=sc.nextInt();
			card.add(t);
		}
		ti.count(card,card.size()-1,sum);
		
	}
 
}
 
 
class Experssion{
	private ArrayList<Integer> num=new ArrayList<Integer>();
	private ArrayList<String> sign=new ArrayList<String>();
	
	public void add(int n){
		num.add(n);
	}
	
	public void add(String s){
		sign.add(s);
	}
	
	private int getPriority(String s){
		if(sign.equals("+")) return 1;
		if(sign.equals("-")) return 1;
		if(sign.equals("*")) return 2;
		if(sign.equals("/")) return 2;
		return -1;
	}
	
	private String toString(int la){
		if(la==0){
			return num.get(0)+sign.get(0)+num.get(1);
		}
		else{
			String result=this.toString(la-1);
			if(getPriority(sign.get(la))>=getPriority(sign.get(la-1)))
				result="("+result+")";
			result+=sign.get(la)+num.get(la+1);
			return result;
		}
	}
	
	public String toString(){
		return toString(2);
	}
	
	public void clear(){
		num.clear();
		sign.clear();
	}
	
}
 
 
class Thinker{
	private Experssion exp;
	public Thinker(Experssion expp){
		exp=expp;
	}
	
	public boolean count(ArrayList<Integer>array,int num, int target){
		//第一次num=3  target=24
		//第二次num=2  target=24-->()
		//第三次num=1  
		if(num==1){
			if(array.get(0)+array.get(1)==target){
				exp.add(array.get(0));
				exp.add(array.get(1));
				exp.add("+");
				return true;
			}
			
			if(array.get(0)-array.get(1)==target){
				exp.add(array.get(0));
				exp.add(array.get(1));
				exp.add("-");
				return true;
			}
			
			if(array.get(1)-array.get(0)==target){
				exp.add(array.get(1));
				exp.add(array.get(0));
				exp.add("-");
				return true;
			}
			
			if(array.get(0)*array.get(1)==target){
				exp.add(array.get(1));
				exp.add(array.get(0));
				exp.add("*");
				return true;
			}
			
			if(array.get(0)*target==array.get(1)){
				exp.add(array.get(0));
				exp.add(array.get(1));
				exp.add("/");
				return true;
			}
			
			if(array.get(1)*target==array.get(0)){
				exp.add(array.get(1));
				exp.add(array.get(0));
				exp.add("/");
				return true;
			}
			
			return false;
			
		}
		else{
			//current  form 0->3
			for(int current=0;current<array.size();current++){
				
				ArrayList<Integer>array1=new ArrayList<Integer>();
				int currentNum=array.get(current);
				
				for(int i=0;i<array.size();i++){
					if(i!=current){
						array1.add(array.get(i));   //将剩余的三个数存进数组
					}
				}
				
				if(count(array1,num-1,target-currentNum)){
					exp.add("+");
					exp.add(currentNum);
					if(num==3){
						System.out.println(exp.toString());
						exp.clear();
					}
					if(num!=3) return true;
				}
				if(count(array1,num-1,target+currentNum)){
					exp.add("-");
					exp.add(currentNum);
					if(num==3){
						System.out.println(exp.toString());
						exp.clear();
					}
					if(num!=3) return true;
				}
				
				
				if(count(array1,num-1,target*currentNum)){
					exp.add("/");
					exp.add(currentNum);
					if(num==3){
						System.out.println(exp.toString());
						exp.clear();
					}
					if(num!=3) return true;
				}
				
				if(target%currentNum==0){
					if(count(array1,num-1,(int)(target/currentNum))){
						exp.add("*");
						exp.add(currentNum);
						if(num==3){
						System.out.println(exp.toString());
						exp.clear();
					}
					if(num!=3) return true;
				}
				
				
				
			}
		}
		return false;
	}
}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
