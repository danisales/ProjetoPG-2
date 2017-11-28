package model;

public class Matrix {
	int c, l;
	double matrix[][];
	public Matrix(int l, int c) {
		this.l = l;
		this.c = c;
		matrix = new double[l][c];
	}
	
	public void setMatrix(double [][]m) {
		this.matrix = m;
	}
	
	public Matrix multiply(Matrix m, Matrix n) throws Exception {
	
		if(m.c != n.l)throw new Exception("Invalid parameters!");
	
		double [][] M = m.matrix;
		double[][] N = n.matrix;
		Matrix R = new Matrix(m.l, n.c);
		double [][] r = R.matrix;
		double aux = 0;
		
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
	
	public static Matrix multiply(double [][] m, double [][] n) throws Exception {
		
		double [][] M = m;
		double[][] N = n;
		Matrix R = new Matrix(m.length, n[0].length);
		double [][] r = R.matrix;
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
		double [][] matrix = m.matrix;
		for(int i = 0;i<m.l;i++) {
			for(int j = 0;j<m.c;j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
