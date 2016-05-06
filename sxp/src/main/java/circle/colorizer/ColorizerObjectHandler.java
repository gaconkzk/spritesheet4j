package circle.colorizer;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import circle.Cons;

public class ColorizerObjectHandler
{
	public BufferedImage[] image;
	//Boolean for controlling if to Sat/hue/lit image
	private volatile boolean[] HSBFlag;
	
	private float[] luminocity;
	private float[] shade;
	private float[] tone;
	private boolean[] needStack;
	private float[] contrast;
	
	public int[] brightness;
	//how many layers is in the image
	private int length;
	
	ColorBalanceHandler CBH = new ColorBalanceHandler();
	
	public ColorizerObjectHandler(int layers)
	{
		this.image = new BufferedImage[layers + 2];

		for (int i = 0; i < layers + 1; i++)
		{
			this.image[i] = new BufferedImage(Cons.TileW, Cons.TileH, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = image[i].createGraphics();
			g2.drawImage(image[i], 0, 0, null);
			g2.dispose();
		}
		setImage(image);
		setLength();
		init();
	}
	public void init()
	{
		/*
		 * Create usable variables and give them their size
		 * */
		this.HSBFlag = new boolean[getLength()];
		this.luminocity = new float[getLength()];
		this.brightness = new int[getLength()];
		this.shade = new float[getLength()];
		this.tone = new float[getLength()];
		this.contrast = new float[getLength()];
		this.needStack = new boolean[getLength()];
		/*
		 * Initialize Variables
		 * */
		for (int i = 0; i < length - 1; i ++)
		{
			setLuminocity(i, 50);
			setBrightness(i, 0);
			setContrast(i, 50);
			setTone(i, 0);
			setShade(i, 0);
			setHSBFlag(i, false);
			setneedStack(i, true);
		}
	}
	public BufferedImage preColor(int i, BufferedImage bi, String s)
	{
		if (s.equals("l"))
		{
			if (getLuminocity(i) != 50)
				bi = CBH.changeLuminocity(bi, this.luminocity[i]);
		}
		else if (s.equals("t"))
		{
			if (getTone(i) != 0)
				bi = CBH.changeTone(bi, this.tone[i]);
		}
		else if (s.equals("s"))
		{
			if (getShade(i) != 0)
				bi = CBH.changeShade(bi, this.shade[i]);
		}
		else if (s.equals("b"))
		{
			if (getBrightness(i) != 180)
				bi = CBH.changeBrightness(bi, this.brightness[i]);
		}
		else if (s.equals("c"))
		{
			if (getContrast(i) != 50)
				bi = CBH.changeContrast(bi, this.contrast[i]);
		}
		return bi;
	}
	public void setneedStack(int i, boolean b)
	{
		this.needStack[i] = b;
	}
	public boolean getneedStack(int i) { return this.needStack[i]; }
	//Hue Saturation Lightness control
	//Brightness Contrast control
	public void setBC(int i, int brightness, float contrast)
	{
		this.brightness[i] = brightness;
		this.contrast[i] = contrast;
	}
	//Luminocity
	public float getLuminocity(int i) { return this.luminocity[i]; }
	public void setLuminocity(int i, float luminocity) { this.luminocity[i] = luminocity; }
	public void setLuminocity(float[] luminocity) { this.luminocity = luminocity.clone(); }
	
	//Shade
	public float getShade(int i) { return this.shade[i]; }
	public void setShade(int i, float shade) { this.shade[i] = shade; }
	public void setShade(float[] shade) { this.shade = shade.clone(); }
	
	//Tone
	public float getTone(int i) { return this.tone[i]; }
	public void setTone(int i, float tone) { this.tone[i] = tone; }
	public void setTone(float[] tone) { this.tone = tone.clone(); }
	
	//Brightness
	public int getBrightness(int i) { return this.brightness[i]; }
	public void setBrightness(int i, int brightness) { this.brightness[i] = brightness; }
	public void setBrightness(int[] brightness) { this.brightness = brightness.clone(); }
		
	//Contrast
	public float getContrast(int i) { return this.contrast[i]; }
	public void setContrast(int i, float contrast) { this.contrast[i] = contrast; }
	public void setContrast(float[] contrast) { this.contrast = contrast.clone(); }
	
	//HSBFlag
	public boolean getHSBFlag(int i) { return HSBFlag[i]; }
	public void setHSBFlag(int i, boolean setTo) { HSBFlag[i] = setTo; }
	public void setHSBFlag(boolean[] HSBFlag) { this.HSBFlag = HSBFlag.clone(); }
	
	//Length
	public int getLength() { return this.length; }
	public void setLength() { length = this.image.length; }
	
	//Image
	public BufferedImage getImage(int i) { return this.image[i]; }
	public BufferedImage[] getImage() { return image; }
	public void setImage(int i, BufferedImage image) { this.image[i] = image; }
	public void setImage(BufferedImage[] image) { this.image = image.clone(); }
}





