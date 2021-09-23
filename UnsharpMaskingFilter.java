public class UnsharpMaskingFilter extends AbstractMatrixFilter

{
	
	UnsharpMaskingFilter()
	{
		
		this.TheMatrix(-1, -2, -1, -2, 28, -2, -1, -2, -1);
		this.scaleback = 16;
		
	}
}
