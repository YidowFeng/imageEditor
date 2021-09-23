
public class LaplacianFilter implements Filter
{
	public static int weightedavrg(int a) {
        if      (a <   0) return 0;
        else if (a > 255) return 255;
        else              return a;
    }
	//this array will be used as the matrix that that determines the weight to be applied
	int[][] matrix = new int[3][3];
	//int scaleback = 16;
	
	@Override 
  	public void filter(PixelImage pi)
  	{
		//data is our working source
		Pixel[][] data = pi.getData();
		
		//temp is our in-progress copy
		Pixel[][] temp = pi.getData();
		matrix[0][0] = -1;
		matrix[0][1] = -1;
		matrix[0][2] = -1;
		matrix[1][0] = -1;
		matrix[1][1] = 8;
		matrix[1][2] = -1;
		matrix[2][0] = -1;
		matrix[2][1] = -1;
		matrix[2][2] = -1;
    
    	for (int row = 1; row < pi.getHeight()-1; row++)
    	{
    		for (int col = 1; col < pi.getWidth()-1; col++)
    		{	
    			//takes each position around the target pixel and applies an appropriate amount of each color as defined by the matrix (DEFAULT IS 0. change this at least somewhere or get an image of black pixels)
    			temp[row][col].red =  (data[row-1][col-1].red*matrix[0][0]) + (data[row-1][col].red*matrix[0][1]) + (data[row-1][col+1].red*matrix[0][2]) + (data[row][col-1].red*matrix[1][0]) + (data[row][col].red*matrix[1][1]) + (data[row][col+1].red*matrix[1][2]) + (data[row+1][col-1].red*matrix[2][0]) + (data[row+1][col].red*matrix[2][1]) + (data[row+1][col+1].red*matrix[2][2]) ;
    			temp[row][col].green =  (data[row-1][col-1].green*matrix[0][0]) + (data[row-1][col].green*matrix[0][1]) + (data[row-1][col+1].green*matrix[0][2]) + (data[row][col-1].green*matrix[1][0]) + (data[row][col].green*matrix[1][1]) + (data[row][col+1].green*matrix[1][2]) + (data[row+1][col-1].green*matrix[2][0]) + (data[row+1][col].green*matrix[2][1]) + (data[row+1][col+1].green*matrix[2][2]) ;
    			temp[row][col].blue =  (data[row-1][col-1].blue*matrix[0][0]) + (data[row-1][col].blue*matrix[0][1]) + (data[row-1][col+1].blue*matrix[0][2]) + (data[row][col-1].blue*matrix[1][0]) + (data[row][col].blue*matrix[1][1]) + (data[row][col+1].blue*matrix[1][2]) + (data[row+1][col-1].blue*matrix[2][0]) + (data[row+1][col].blue*matrix[2][1]) + (data[row+1][col+1].blue*matrix[2][2]) ;
    		
    			temp[row][col].red = weightedavrg(temp[row][col].red);
    			temp[row][col].green = weightedavrg(temp[row][col].green);
    			temp[row][col].blue = weightedavrg(temp[row][col].blue);
                
    		
    		}
    	}
    	//take the now-complete copy with all the appropriate changes and make set the data in the current pixel image
    	pi.setData(temp);
  	}
	
}
	
	 
	