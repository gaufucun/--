
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

 
public class one_2 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tree t = new Tree();

	public one_2(){
		this.add(t);
		JPanel panel = new JPanel();
		t.run();

		this.add(panel,BorderLayout.SOUTH);

			}


	public static void main(String[] args) {
		one_2 f = new one_2();
		f.setTitle("µÝ¹é·ÖÐÎÊ÷");
		f.setSize(1000,1000);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
 
class Tree extends JPanel{
	
	private int n =1;
	private double A,B,C;
	private double PI = Math.acos(-1.0);
	
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		Point p1 = new Point(this.getWidth()/2,this.getHeight()-300);
		Point p2 = new Point(this.getWidth()/2,this.getHeight()/2);
		display(g,n,p1,p2);
	}
	
	public void run(){
		
		repaint();
	}
	private void display(Graphics g,int n,Point p1,Point p2){
		
		if (n>=0){
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			Point p3 = mid1(p1,p2);
			Point p4 = mid2(p1,p2);
			//System.out.println(p1.x+" "+p1.y+" "+p2.x+" "+p2.y);
			
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))/1.5;
			if(C<5.0) {
				n=0;
			}
			else 
				n++;
			display(g, n-1, p2, p3);
			display(g, n-1, p2, p4);
			
			
		}
	}
	private Point mid1(Point p1,Point p2){
		Point p = new Point();
		A = Math.atan2(p2.x-p1.x,p1.y-p2.y);
		//A = Math.atan((double)(p2.x-p1.x)/(p1.y-p2.y));
		B = A - PI/6;
		C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))/1.5;
		p.x= (int)(p2.x + C*Math.sin(B));
		p.y= (int)(p2.y - C*Math.cos(B));
		return p;
	}
	private Point mid2(Point p1,Point p2){
		Point p = new Point();
		A = Math.atan2(p2.x-p1.x,p1.y-p2.y);
		B = A + PI/5;
		C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))/1.5;
		p.x= (int)(p2.x + C*Math.sin(B));
		p.y= (int)(p2.y - C*Math.cos(B));
		if (p.x==0){
			System.out.println(p1.x+" "+p1.y+" "+p2.x+" "+p2.y+" "+A);
		}
		return p;
	}
}

