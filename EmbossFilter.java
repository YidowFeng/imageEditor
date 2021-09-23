
public class EmbossFilter implements Filter
{
	public static int weightedavrg(int a) {
        if      (a <   0) return 0;
        else if (a > 255) return 255;
        else              return 130;
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
		matrix[0][0] = -2;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[1][0] = 0;
		matrix[1][1] = 1;
		matrix[1][2] = 0;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = 2;
    
    	for (int row = 1; row < pi.getHeight()-1; row++)
    	{
    		for (int col = 1; col < pi.getWidth()-1; col++)
    		{	
    			//takes each position around the target pixel and applies an appropriate amount of each color as defined by the matrix (DEFAULT IS 0. change this at least somewhere or get an image of black pixels)
    			temp[row][col].red =  (data[row-1][col-1].red*matrix[0][0]) + (data[row-1][col].red*matrix[0][1]) + (data[row-1][col+1].red*matrix[0][2]) + (data[row][col-1].red*matrix[1][0]) + (data[row][col].red*matrix[1][1]) + (data[row][col+1].red*matrix[1][2]) + (data[row+1][col-1].red*matrix[2][0]) + (data[row+1][col].red*matrix[2][1]) + (data[row+1][col+1].red*matrix[2][2]) ;
    			temp[row][col].green =  (data[row-1][col-1].green*matrix[0][0]) + (data[row-1][col].green*matrix[0][1]) + (data[row-1][col+1].green*matrix[0][2]) + (data[row][col-1].green*matrix[1][0]) + (data[row][col].green*matrix[1][1]) + (data[row][col+1].green*matrix[1][2]) + (data[row+1][col-1].green*matrix[2][0]) + (data[row+1][col].green*matrix[2][1]) + (data[row+1][col+1].green*matrix[2][2]) ;
    			temp[row][col].blue =  (data[row-1][col-1].blue*matrix[0][0]) + (data[row-1][col].blue*matrix[0][1]) + (data[row-1][col+1].blue*matrix[0][2]) + (data[row][col-1].blue*matrix[1][0]) + (data[row][col].blue*matrix[1][1]) + (data[row][col+1].blue*matrix[1][2]) + (data[row+1][col-1].blue*matrix[2][0]) + (data[row+1][col].blue*matrix[2][1]) + (data[row+1][col+1].blue*matrix[2][2]) ;
    		    //int r= temp[row][col].red - temp[row-1][col-1].red;
    			//int gray = (weightedavrg(temp[row][col].red)+weightedavrg(temp[row][col].green)+weightedavrg(temp[row][col].blue))/3;
    			temp[row][col].red = Math.min((Math.abs(weightedavrg(temp[row][col].red)-weightedavrg(temp[row-1][col-1].red))/3)+128,255);
    			temp[row][col].green = Math.min((Math.abs(weightedavrg(temp[row][col].green)-weightedavrg(temp[row-1][col-1].green))/3)+128,255);
    			temp[row][col].blue = Math.min((Math.abs(weightedavrg(temp[row][col].blue)-weightedavrg(temp[row-1][col-1].blue))/3)+128,255);
    			
    			
    			
    			//temp[row][col].green = Math.min(Math.abs(weightedavrg(temp[row][col].green)/3)+128,255);
    			//temp[row][col].blue = Math.min(Math.abs(weightedavrg(temp[row][col].blue)/3)+128,255);
    			
    			
    			//temp[row][col].green = Math.min(Math.abs(gray[0][row][col]), arg1)weightedavrg(temp[row][col].green);
    			
    			//temp[row][col].green = weightedavrg(temp[row][col].green);
    			//temp[row][col].blue = weightedavrg(temp[row][col].blue);
    			//temp[row][col].red = weightedavrg(temp[row][col].red);
    			
                
    		
    		}
    	}
    	//take the now-complete copy with all the appropriate changes and make set the data in the current pixel image
    	pi.setData(temp);
  	}
	
}
	
	 
	