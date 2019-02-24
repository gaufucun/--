
import java.util.Scanner;

public class other {
    static int[][] chess=new int[1000][1000];
    static int number=1;//L�͹��Ʊ��
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();//����k,���̴�СΪ 2^k*2^k
        int x=sc.nextInt();//�����ȱ�������
        int y=sc.nextInt();

        int chessSize=(int) Math.pow(2, k);//�߳�Ϊ 2^k
        if(chessSize==0||x>=chessSize||y>=chessSize){//����Ƿ�
            System.out.println("input error");
            return;
        }
        //��ǲ�ȱλ�ã�x��y�� =-1
        chess[x][y]=-1;

        //���� �ݹ� ��������
        chessFill(0,0,x,y,chessSize);

        //��������
        for(int i=0;i<chessSize;i++){
            for(int j=0;j<chessSize;j++){
                System.out.print(chess[i][j]);
                if(j<chessSize-1){
                    System.out.print("\t");
                }
            }
            System.out.println();//���ÿ�лس�
            System.out.println();
        }
    }

    /**
     * @param i �����������ԭ�������chess[][] ����ʼλ��
     * @param j
     * @param x ��ȱ��λ��
     * @param y
     * @param chessSize �����̴�С
     */
    private static void chessFill(int row, int column, int x, int y, int chessSize) {
        // �ݹ����
        if (chessSize == 1) {
            return;
        }
        // �԰뻮�ֳ�2^(chessSize - 1) * 2^(chessSize - 1)������
        int s = chessSize / 2;
        // L���Ʊ������
        int t = number++;
        // �м�㣬�Դ��б�(x,y)���ĸ���������
        int centerRow = row + s;
        int centerColumn = column + s;

        // ��ɫ����������������
        if (x < centerRow && y < centerColumn) {
            chessFill(row, column, x, y, s);
        } else {
            // ������������������̵����½�
            chess[centerRow - 1][centerColumn - 1] = t;
            // Ȼ�󸲸��������ӣ�ע����ʱ(x,y)Ҫ���������λ��
            chessFill(row, column, centerRow - 1, centerColumn - 1, s);
        }

        // ��ɫ����������������
        if (x < centerRow && y >= centerColumn) {
            chessFill(row, centerColumn, x, y, s);
        } else {
            // ������������������̵����½�
            chess[centerRow - 1][centerColumn] = t;
            // Ȼ�󸲸��������ӣ�ע����ʱ(x,y)Ҫ���������λ��
            chessFill(row, centerColumn, centerRow - 1, centerColumn, s);
        }

        // ��ɫ����������������
        if (x >= centerRow && y < centerColumn) {
            chessFill(centerRow, column, x, y, s);
        } else {
            // ������������������̵����Ͻ�
            chess[centerRow][centerColumn - 1] = t;
            // Ȼ�󸲸��������ӣ�ע����ʱ(x,y)Ҫ���������λ��
            chessFill(centerRow, column, centerRow, centerColumn - 1, s);
        }

        // ��ɫ����������������
        if (x >= centerRow && y >= centerColumn) {
            chessFill(centerRow, centerColumn, x, y, s);
        } else {
            // ������������������̵����Ͻ�
            chess[centerRow][centerColumn] = t;
            // Ȼ�󸲸��������ӣ�ע����ʱ(x,y)Ҫ���������λ��
            chessFill(centerRow, centerColumn, centerRow, centerColumn, s);
        }
    }

}

