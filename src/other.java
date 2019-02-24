
import java.util.Scanner;

public class other {
    static int[][] chess=new int[1000][1000];
    static int number=1;//L型骨牌编号
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();//输入k,棋盘大小为 2^k*2^k
        int x=sc.nextInt();//输入残缺块的坐标
        int y=sc.nextInt();

        int chessSize=(int) Math.pow(2, k);//边长为 2^k
        if(chessSize==0||x>=chessSize||y>=chessSize){//输入非法
            System.out.println("input error");
            return;
        }
        //标记残缺位置（x，y） =-1
        chess[x][y]=-1;

        //分治 递归 填满棋盘
        chessFill(0,0,x,y,chessSize);

        //输出填充结果
        for(int i=0;i<chessSize;i++){
            for(int j=0;j<chessSize;j++){
                System.out.print(chess[i][j]);
                if(j<chessSize-1){
                    System.out.print("\t");
                }
            }
            System.out.println();//输出每行回车
            System.out.println();
        }
    }

    /**
     * @param i 子棋盘相对于原最大棋盘chess[][] 的起始位置
     * @param j
     * @param x 残缺点位置
     * @param y
     * @param chessSize 子棋盘大小
     */
    private static void chessFill(int row, int column, int x, int y, int chessSize) {
        // 递归出口
        if (chessSize == 1) {
            return;
        }
        // 对半划分成2^(chessSize - 1) * 2^(chessSize - 1)的棋盘
        int s = chessSize / 2;
        // L型牌编号自增
        int t = number++;
        // 中间点，以此判别(x,y)在哪个子棋盘中
        int centerRow = row + s;
        int centerColumn = column + s;

        // 黑色方格在左上子棋盘
        if (x < centerRow && y < centerColumn) {
            chessFill(row, column, x, y, s);
        } else {
            // 不在则填充左上子棋盘的右下角
            chess[centerRow - 1][centerColumn - 1] = t;
            // 然后覆盖其他格子，注意这时(x,y)要看做已填充位置
            chessFill(row, column, centerRow - 1, centerColumn - 1, s);
        }

        // 黑色方格在右上子棋盘
        if (x < centerRow && y >= centerColumn) {
            chessFill(row, centerColumn, x, y, s);
        } else {
            // 不在则填充右上子棋盘的左下角
            chess[centerRow - 1][centerColumn] = t;
            // 然后覆盖其他格子，注意这时(x,y)要看做已填充位置
            chessFill(row, centerColumn, centerRow - 1, centerColumn, s);
        }

        // 黑色方格在左下子棋盘
        if (x >= centerRow && y < centerColumn) {
            chessFill(centerRow, column, x, y, s);
        } else {
            // 不在则填充左下子棋盘的右上角
            chess[centerRow][centerColumn - 1] = t;
            // 然后覆盖其他格子，注意这时(x,y)要看做已填充位置
            chessFill(centerRow, column, centerRow, centerColumn - 1, s);
        }

        // 黑色方格在右下子棋盘
        if (x >= centerRow && y >= centerColumn) {
            chessFill(centerRow, centerColumn, x, y, s);
        } else {
            // 不在则填充右下子棋盘的左上角
            chess[centerRow][centerColumn] = t;
            // 然后覆盖其他格子，注意这时(x,y)要看做已填充位置
            chessFill(centerRow, centerColumn, centerRow, centerColumn, s);
        }
    }

}

