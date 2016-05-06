package circle.colorizer;


import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorBalanceHandler
{
	public ColorBalanceHandler() {}

	public BufferedImage changeTone(BufferedImage image, float adjust)//0-100
	{
		int height = image.getHeight();
		int width = image.getWidth();
		HSLColor hsl;
		Color color;
		BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		while (height-- != 0)
		{
			width = image.getWidth();

			while (width-- != 0)
			{
				color = new Color(image.getRGB(width, height), true);
				if (color.getAlpha() <= 0)
					continue;
				hsl = new HSLColor(color);
				
				color = new Color(hsl.adjustTone(adjust), true);
				outImage.setRGB(width, height, color.getRGB());
			}
		}
		return outImage;
	}
	public BufferedImage changeLuminocity(BufferedImage image, float adjust)
	{
		int height = image.getHeight();
		int width = image.getWidth();
		HSLColor hsl;
		Color color;
		BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		while (height-- != 0)
		{
			width = image.getWidth();
			while(width-- != 0)
			{
				color = new Color(image.getRGB(width, height), true);
				if (color.getAlpha() <= 0)
					continue;
				hsl = new HSLColor(color);
				
				color = new Color(hsl.adjustSaturation(adjust), true);
				outImage.setRGB(width, height, color.getRGB());
			}
		}
		return outImage;
	}
	public BufferedImage changeShade(BufferedImage image, float adjust)//0-100
	{
		int height = image.getHeight();
		int width = image.getWidth();
		HSLColor hsl;
		Color color;
		BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		while (height-- != 0)
		{
			width = image.getWidth();

			while (width-- != 0)
			{
				color = new Color(image.getRGB(width, height), true);
				if (color.getAlpha() <= 0)
					continue;
				hsl = new HSLColor(color);
				
				color = new Color(hsl.adjustShade(adjust), true);
				outImage.setRGB(width, height, color.getRGB());
			}
		}
		return outImage;
	}
	public BufferedImage changeContrast(BufferedImage inImage, float increasingFactor)
	{
		int w = inImage.getWidth();
		int h = inImage.getHeight();
		increasingFactor /= 50;
		BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < w; i++)
		{
			for (int j = 0; j < h; j++)
			{
				Color color = new Color(inImage.getRGB(i, j), true);
				if (color.getAlpha() <= 0)
					continue;
				int r, g, b, a;
				float fr, fg, fb;

				r = color.getRed();
				fr = (r - 128) * increasingFactor + 128;
				r = (int) fr;
				r = keep256(r);

				g = color.getGreen();
				fg = (g - 128) * increasingFactor + 128;
				g = (int) fg;
				g = keep256(g);

				b = color.getBlue();
				fb = (b - 128) * increasingFactor + 128;
				b = (int) fb;
				b = keep256(b);

				a = color.getAlpha();

				outImage.setRGB(i, j, new Color(r, g, b, a).getRGB());
			}
		}
		return outImage;
	}
	public BufferedImage changeBrightness(BufferedImage inImage, int increasingFactor)
	{
		
		int w = inImage.getWidth();
		int h = inImage.getHeight();

		BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < w; i++)
		{
			for (int j = 0; j < h; j++)
			{
				Color color = new Color(inImage.getRGB(i, j), true);
				if (color.getAlpha() <= 0)
					continue;
				
				int r, g, b, a;

				r = keep256(color.getRed() + increasingFactor);
				g = keep256(color.getGreen() + increasingFactor);
				b = keep256(color.getBlue() + increasingFactor);
				a = color.getAlpha();

				outImage.setRGB(i, j, new Color(r, g, b, a).getRGB());
			}
		}
		return outImage;
	}

	public int keep256(int i)
	{
		if (i < 256 && i >= 0)
			return i;
		if (i > 255)
			return 255;
		if (i < 0)
			return 0;
		return -50;
	}
}
