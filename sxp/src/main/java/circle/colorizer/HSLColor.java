package circle.colorizer;


import java.awt.Color;

public class HSLColor
{
	private Color rgb;
	private float[] hsl;
	private float alpha;

	public HSLColor(Color rgb)
	{
		this.rgb = rgb;
		hsl = fromRGB( rgb , null);
		alpha = rgb.getAlpha() / 255.0f;
	}

	public HSLColor(float h, float s, float l) { this(h, s, l, 1.0f); }
	
	public HSLColor(float h, float s, float l, float alpha)
	{
		hsl = new float[] {h, s, l};
		this.alpha = alpha;
		rgb = new Color(toRGB(hsl, alpha), true);
	}

	public HSLColor(float[] hsl) { this(hsl, 1.0f); }

	public HSLColor(float[] hsl, float alpha)
	{
		this.hsl = hsl;
		this.alpha = alpha;
		rgb = new Color(toRGB(hsl, alpha), true);
	}

	public int adjustHue(float degrees) { return toRGB(degrees, hsl[1], hsl[2], alpha); }
	public int adjustLuminance(float percent) { return toRGB(hsl[0], hsl[1], percent, alpha); }
	public int adjustSaturation(float percent) { return toRGB(hsl[0], percent, hsl[2], alpha); }

	public int adjustShade(float percent)
	{
		float multiplier = (100.0f - percent) / 100.0f;
		float l = Math.max(0.0f, hsl[2] * multiplier);

		return toRGB(hsl[0], hsl[1], l, alpha);
	}

	public int adjustTone(float percent)
	{
		float multiplier = (100.0f + percent) / 100.0f;
		float l = Math.min(100.0f, hsl[2] * multiplier);

		return toRGB(hsl[0], hsl[1], l, alpha);
	}

	public float getAlpha() { return alpha; }

	public int getComplementary()
	{
		float hue = (hsl[0] + 180.0f) % 360.0f;
		return toRGB(hue, hsl[1], hsl[2]);
	}

	public float getHue() { return hsl[0]; }
	public float[] getHSL() { return hsl; }
	public float getLuminance() { return hsl[2]; }
	public Color getRGB() { return rgb; }
	public float getSaturation() { return hsl[1]; }

	public String toString()
	{
		String toString = "HSLColor[h=" + hsl[0] + ",s=" + hsl[1] + ",l=" + hsl[2] + ",alpha=" + alpha + "]";
		return toString;
	}
	
	public float[] fromRGB(Color color, float[] dest)
	{
		float f[] = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
		for (int i = 0; i <= 2; i++) 
			System.out.println(f[i]);
		System.out.println(color.getRed());
		System.out.println(color.getGreen());
		System.out.println(color.getBlue());
		System.out.println('\n');
		System.out.println();
		float r = (color.getRed() / 255f);
		float g = (color.getGreen() / 255f);
		float b = (color.getBlue() / 255f);

		float min = Math.min(r, Math.min(g, b));
		float max = Math.max(r, Math.max(g, b));

		// Hue
		float h = 0;
		
		if (max == min)
			h = 0;
		else if (max == r)
			h = (60 * (g - b) / (max - min)) + 360;
		else if (max == g)
			h = (60 * (b - r) / (max - min)) + 120;
		else if (max == b)
			h = (60 * (r - g) / (max - min)) + 240;

		h %= 360;
		// Luminance
		float l = (max + min) / 2;

		// Saturation
		float s = 0;

		if (max == min)
			s = 0;
		else if (l <= .5f)
			s = (max - min) / (max + min);
		else
			s = (max - min) / (2 - max - min);
		
		if (dest == null)
			dest = new float[3];
		dest[0] = h;
		dest[1] = s * 100;
		dest[2] = l * 100;

		return dest;
	}

	public static int toRGB(float[] hsl) { return toRGB(hsl, 1.0f); }
	public static int toRGB(float[] hsl, float alpha) { return toRGB(hsl[0], hsl[1], hsl[2], alpha); }
	public static int toRGB(float h, float s, float l) { return toRGB(h, s, l, 1.0f); }

	public static int toRGB(float h, float s, float l, float alpha)
	{
		if (s <0.0f || s > 100.0f)
		{
			String message = "Color parameter outside of expected range - Saturation";
			throw new IllegalArgumentException( message );
		}

		if (l <0.0f || l > 100.0f)
		{
			String message = "Color parameter outside of expected range - Luminance";
			throw new IllegalArgumentException( message );
		}

		if (alpha <0.0f || alpha > 1.0f)
		{
			String message = "Color parameter outside of expected range - Alpha";
			throw new IllegalArgumentException( message );
		}

		h = h % 360.0f;
		h /= 360f;
		s /= 100f;
		l /= 100f;

		float q = 0;

		if (l < 0.5)
			q = l * (1 + s);
		else
			q = (l + s) - (s * l);

		float p = 2 * l - q;

		int r = (int)(255 * Math.max(0, HueToRGB(p, q, h + (1.0f / 3.0f))));
		int g = (int)(255 * Math.max(0, HueToRGB(p, q, h)));
		int b = (int)(255 * Math.max(0, HueToRGB(p, q, h - (1.0f / 3.0f))));

		int alphaInt = (int)(255*alpha);
		
		return (alphaInt << 24) + (r << 16) + (g << 8)  + (b);
	}
	private static float HueToRGB(float p, float q, float h)
	{
		if (h < 0) 
			h += 1;

		if (h > 1 ) 
			h -= 1;

		if (6 * h < 1)
			return p + ((q - p) * 6 * h);

		if (2 * h < 1 )
			return  q;

		if (3 * h < 2)
			return p + ( (q - p) * 6 * ((2.0f / 3.0f) - h) );

   		return p;
	}
}
