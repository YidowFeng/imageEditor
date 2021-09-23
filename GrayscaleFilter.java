import java.awt.Color;

/**
 * Filter that flips the image horizontally.
 * This class is COMPLETE. Don't change it. But model your other classes (such
 * as FlipVerticalFilter) after it.
 */
public class GrayscaleFilter implements Filter
{
  public void filter(PixelImage pi)
  {
    Pixel[][] data = pi.getData();

    for (int row = 0; row < pi.getHeight(); row++)
    {
      for (int col = 0; col < pi.getWidth(); col++)
      {
        Pixel temp = data[row][col];
        int r = temp.red;
        int g = temp.green;
        int b = temp.blue;
        int a = 0;
        
        
        //Color c = new Color(pi.getRGB(row,col));
		//int r = c.getRed();
		//int g = c.getGreen();
		//int b = c.getBlue();
       // int a = c.getAlpha();
        
        // SImple graysacaling = (r+g+b/ 3
        int gr = (r+g+b)/3;
        
        //Create graycolor
        Color gColor = new Color (gr, gr, gr, a);
        temp.red = gr;
        temp.green = gr;
        temp.blue = gr;
        
        //grayscaleImage.setRGB(row, col, gColor.getRGB());
        data[row][col] = temp;
      }
    }
    pi.setData(data);

  }
}