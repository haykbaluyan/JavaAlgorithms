import java.util.ArrayList;


public class Main {

	enum Direction {DIAG,UP, LEFT};
	static class cell{
		int row;
		int col;
		public cell(int row, int col){
			this.row=row;
			this.col=col;
		}
		public void printCell(){
			System.out.print("("+row+","+col+") ");
		}
	}
	public static int minPathInMazeRec(int[][] cost,int m, int n,ArrayList<cell> path){
		
		if(m==0){
			int horizCost=0;
			for(int j=0;j<=n;j++){
				path.add(new cell(0,j));
				horizCost+=cost[0][j];
			}
			return horizCost;
			
		}
		
		if(n==0){
			
			int vertCost=0;
			for(int i=0;i<=m;i++){
				path.add(new cell(i,0));
				vertCost+=cost[i][0];
			}
			return vertCost;
			
		}
		ArrayList<cell> pathDecM=new ArrayList<cell>();
		ArrayList<cell> pathDecN=new ArrayList<cell>();
		ArrayList<cell> pathDecDiag=new ArrayList<cell>();
		int decDiag=minPathInMazeRec(cost, m-1,n-1,pathDecDiag);
		int decM=minPathInMazeRec(cost, m-1,n,pathDecM);
		int decN=minPathInMazeRec(cost, m,n-1,pathDecN);
		
		int minCost=decDiag;
		Direction dir=Direction.DIAG;
		
		if(minCost>decM){
			dir=Direction.UP;
			minCost=decM;
		}
		if(minCost>decN){
			dir=Direction.LEFT;
			minCost=decN;
		}
	
		switch(dir){
		case UP:
			path.addAll(pathDecM);
			break;
		case LEFT:
			path.addAll(pathDecN);
			break;
		case DIAG:
			path.addAll(pathDecDiag);
			break;
			
		}
		path.add(new cell(m,n));
		return minCost+cost[m][n];
		
		
	}
	
	public static int minPathInMazeDP(int[][] cost, int m, int n){
		
		int L[][]=new int[m][n];
		for(int i=0;i<m;i++){
			int vertCost=0;
			for(int j=0;j<=i;j++){
				
				vertCost+=cost[j][0];
			}
			L[i][0]=vertCost;
		}
		
		for(int i=0;i<n;i++){
			int horizCost=0;
			for(int j=0;j<=i;j++){
				
				horizCost+=cost[0][j];
			}
			L[0][i]=horizCost;
		}
		
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){

				L[i][j]=Math.min(Math.min(L[i-1][j], L[i][j-1]), L[i-1][j-1])+cost[i][j];
				
			}
		}
		return L[m-1][n-1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] cost={{1,2,3},{4,8,2},{1,5,3}};
		ArrayList<cell> path=new ArrayList<cell>();
		System.out.println(minPathInMazeRec(cost,2,2,path));
		for(cell c : path)
			c.printCell();
		System.out.println();
		System.out.println(minPathInMazeDP(cost,3,3));

	}

}
