public class NegativeFilter implements Filter
{
  public void filter(PixelImage pi)
  {
    Pixel[][] data = pi.getData();

    for(int row = 0; row<pi.getHeight();row++){
		for(int col = 0; col<pi.getWidth();col++){
			Pixel temp = data[row][col];
			
	        int r = temp.red;
	        int g = temp.green;
	        int b = temp.blue;
	        int a = 0;
			
			r = 255 - r;
			g = 255-g;
			b = 255-b;
			
			int p = (a<<24)|(r<<16)|(g<<8)|b;
			//img.setRGB(x, y, p);;
			temp.red = r;
	        temp.green = g;
	        temp.blue = b;
	        data[row][col] = temp;
		}
    }

    pi.setData(data);
  }
}