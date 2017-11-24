package model;

public class Matrix {
	int c, l;
	int matrix[][];
	public Matrix(int l, int c) {
		this.l = l;
		this.c = c;
		matrix = new int[l][c];
	}
	
	public void setMatrix(int [][]m) {
		this.matrix = m;
	}
	
	public Matrix multiply(Matrix m, Matrix n) throws Exception {
	
		if(m.c != n.l)throw new Exception("Invalid parameters!");
	
		int [][] M = m.matrix;
		int[][] N = n.matrix;
		Matrix R = new Matrix(m.l, n.c);
		int [][] r = R.matrix;
		int aux = 0;
		
		for(int i = 0;i< m.l ;i++) {
			for(int j = 0; j< n.c;j++) {
				for(int k = 0; k < n.l;k++) {
					aux += M[i][k]*N[k][j];
				}
				r[i][j] = aux;
				aux = 0;
			}
		}
		R.setMatrix(r);
		
		return  R;
	}
	
	public static Matrix multiply(int [][] m, int [][] n) throws Exception {
		
		int [][] M = m;
		int[][] N = n;
		Matrix R = new Matrix(m.length, n[0].length);
		int [][] r = R.matrix;
		int aux = 0;
		
		for(int i = 0;i< m.length ;i++) {
			for(int j = 0; j< n[0].length;j++) {
				for(int k = 0; k < n.length;k++) {
					aux += M[i][k]*N[k][j];
				}
				r[i][j] = aux;
				aux = 0;
			}
		}
		R.setMatrix(r);
		
		return  R;
	}
	
	public static void toString(Matrix m) {
		int [][] matrix = m.matrix;
		for(int i = 0;i<m.l;i++) {
			for(int j = 0;j<m.c;j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
