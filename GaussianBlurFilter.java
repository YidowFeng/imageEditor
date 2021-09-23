/**
/***************************************************************************************
/*								                              		   				   
/*	filename	GaussianblurFilter					                              		   
/*	author(s)	Joshua Wright	                           
/*	email		joshua.wright.systems@gmail.com                   
/*	date		3 April 2018						                              		   
/*												                              		   
/* This class is an abstract matrix filter and it extends the AbstractMatrixFilter class       
/* 
/* This abstract matrix filter creates a blurring effect by applying a little bit of  
/* 		 color information from the surrounding pixels of each pixel and applying that 
/*		 information to the center pixel.                                                                                    
/*
/***************************************************************************************/

public class GaussianBlurFilter extends AbstractMatrixFilter
//public class GaussianBlurFilter implements Filter
{
	/***************************************************************************************/
	/* 
		Description:
			Constructor for the GaussianBlurFilter 
			Don't forget that this extends the AbstractMatrixFilter class, so a lot of
			what gets accessed here comes from that, so we don't have to repeat too much
			code. 
	 	
	 	Parameters:
	 		- None
	 	[NOTICE!!]: While the constructor doesn't take parameters, TheMatrix() does!
	 	[NOTICE!!]: Be sure to double check TheMatrix() in AbstractMatrixFilter for details!  
	 	 
	 	 Return:
	 	 	- None
	/*
	/***************************************************************************************/
	GaussianBlurFilter()
	{
		
		this.TheMatrix(1, 2, 1, 2, 4, 2, 1, 2, 1);
		this.scaleback = 16;
		
	}
}
