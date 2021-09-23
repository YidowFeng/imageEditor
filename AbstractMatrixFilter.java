
/**
/***************************************************************************************
*								                              		   				   
*	filename	AbstractMatrixFilter					                              		   
*	author(s)	Yidou Feng & Joshua N. Wright				                           
*	email		yidowfeng@gmail.com &joshua.wright.systems@gmail.com                   
*	date		3 April 2018						                              		   
*												                              		   
* This superclass is a filter and it implements the Filter class       
* 
* This filter takes some amount of information from the surrounding pixels of each pixel 
* 		and applies that weighted information to the center
* 		This weights are not by initiated by default, so unless TheMatrix() is accessed,
*		the result will be a black image.
*
* Most recent change was foregoing making temporary pixels in favor of using the current
* 		row and col of the data[][] and temp[][], directly.
*
/***************************************************************************************/
public abstract class AbstractMatrixFilter implements Filter
{
	
	//this array will be used as the matrix that that determines the weight to be applied
	int[][] matrix = new int[3][3];
	int scaleback = 16;
	
	@Override 
  	public void filter(PixelImage pi)
  	{
		//data is our working source
		Pixel[][] data = pi.getData();
		
		//temp is our in-progress copy
		Pixel[][] temp = pi.getData();
    
    	for (int row = 1; row < pi.getHeight()-1; row++)
    	{
    		for (int col = 1; col < pi.getWidth()-1; col++)
    		{	
    			//takes each position around the target pixel and applies an appropriate amount of each color as defined by the matrix (DEFAULT IS 0. change this at least somewhere or get an image of black pixels)
    			temp[row][col].red = ( (data[row-1][col-1].red*matrix[0][0]) + (data[row-1][col].red*matrix[0][1]) + (data[row-1][col+1].red*matrix[0][2]) 
    					             + (data[row][col-1].red*matrix[1][0]) + (data[row][col].red*matrix[1][1]) + (data[row][col+1].red*matrix[1][2]) 
    					             + (data[row+1][col-1].red*matrix[2][0]) + (data[row+1][col].red*matrix[2][1]) + (data[row+1][col+1].red*matrix[2][2]) )/scaleback;
    			temp[row][col].green = ( (data[row-1][col-1].green*matrix[0][0]) + (data[row-1][col].green*matrix[0][1]) + (data[row-1][col+1].green*matrix[0][2]) 
    					             + (data[row][col-1].green*matrix[1][0]) + (data[row][col].green*matrix[1][1]) + (data[row][col+1].green*matrix[1][2]) 
    					             + (data[row+1][col-1].green*matrix[2][0]) + (data[row+1][col].green*matrix[2][1]) + (data[row+1][col+1].green*matrix[2][2]) )/scaleback;
    			temp[row][col].blue = ( (data[row-1][col-1].blue*matrix[0][0]) + (data[row-1][col].blue*matrix[0][1]) + (data[row-1][col+1].blue*matrix[0][2]) 
    					             + (data[row][col-1].blue*matrix[1][0]) + (data[row][col].blue*matrix[1][1]) + (data[row][col+1].blue*matrix[1][2]) 
    					             + (data[row+1][col-1].blue*matrix[2][0]) + (data[row+1][col].blue*matrix[2][1]) + (data[row+1][col+1].blue*matrix[2][2]) )/scaleback;
    		
    			temp[row][col].red = Math.min(255, Math.max(0, temp[row][col].red));
    			temp[row][col].green = Math.min(255, Math.max(0, temp[row][col].green));
    			temp[row][col].blue = Math.min(255, Math.max(0, temp[row][col].blue));
                
    		
    		}
    	}
    	//take the now-complete copy with all the appropriate changes and make set the data in the current pixel image
    	pi.setData(temp);
  	}
	
	 
	/***************************************************************************************/
	/* 
		Description:
			This takes the weight information for each of the 9 blocks and stuffs that into  
			the appropriate spot in the matrix.
			
			Use this to apply matrix-based, weighted changes to pixels with subclass filters!
			
			Parameters described below are relative to the middleCenter pixel.
			
	 	Parameters:
	 		- topLeft	
	 		
	 		- topCenter	
	 		
	 		- topRight
	 		
	 		- middleLeft 
	 		
	 		- middleCenter	the weight for the current pixel
	 		
	 		- middleRight
	 		
	 		- bottomLeft
	 		
	 		- bottomCenter
	 		
	 		- bottomRight
	 		
	 	 Return:
	 	 	- None
	/*
	/***************************************************************************************/
	public void TheMatrix(int topLeft, int topCenter, int topRight, int middleLeft, int middleCenter, int middleRight, int bottomLeft, int bottomCenter, int bottomRight)
	{
		matrix[0][0] = topLeft;
		matrix[0][1] = topCenter;
		matrix[0][2] = topRight;
		matrix[1][0] = middleLeft;
		matrix[1][1] = middleCenter;
		matrix[1][2] = middleRight;
		matrix[2][0] = bottomLeft;
		matrix[2][1] = bottomCenter;
		matrix[2][2] = bottomRight;
	}
	
}
