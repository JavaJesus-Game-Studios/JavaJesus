package ca.javajesus.game.gfx;

public class RotateMatrix {

	private int[] pixels;
	private int width;
	private int height;

	public RotateMatrix(Sprite sprite) {
		this.width = sprite.xSize;
		this.height = sprite.ySize;
		this.pixels = sprite.pixels;
	}
	// matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += (A[i][j] * x[j]);
        return y;
    }

	public int[] rotate(int angle) {

		double theta = Math.toRadians(angle);
		int[] pixels = new int[this.pixels.length];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double xx = x * Math.cos(theta) - y * Math.sin(theta);
				double yy = x * Math.sin(theta) + y * Math.cos(theta);
				if (xx + yy * width >= 0
						&& xx + yy * width < this.pixels.length)
					pixels[(int) (xx + yy * width)] = this.pixels[x + y * width];
			}
		}

		this.pixels = pixels;

		return pixels;
	}
	
	/**public int[] rotate(int angle) {

		double theta = Math.toRadians(angle);
		
		double[] list = new double[this.pixels.length];
		double[] list2 = new double[this.pixels.length];
		
		int[] pixels = new int[this.pixels.length];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
			    double xx = x * Math.cos(theta) - y * Math.sin(theta);
				double yy = x * Math.sin(theta) + y * Math.cos(theta);
				list[x + y * width] = xx + yy * width;
			}
		}
		
		list2 = list;
		Arrays.sort(list2);
		
		for (int i = 0; i < list2.length; i++) {
			for (int j = 0; j < list.length; j++) {
				if (list2[i] == list[j]) {
					pixels[i] = this.pixels[j];
					continue;
				}
			}
		}
		
		this.pixels = pixels;
		
		return pixels;
	} */
}
