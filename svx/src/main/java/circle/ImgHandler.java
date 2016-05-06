package circle;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImgHandler implements Cons
{
	protected Image image;
	public static BufferedImage[] bi;
	public static int IColors;
	/*
	 * colorArray keeps the color names that are used for loading images.
	 * if you add a color here you need to edit usedColors in Cons.java
	 * Ignore the "null" value as there is not a 0 value
	 * all values start at 1 for colorArray
	 * */
	public static String[] colorArray = {"null", "red", "yellow", "green", "blue", "white", "black", "purple", "pink", "dbrown", "lbrown"};
	/*
	 * cbody is much like colorArray. However
	 * with cbody you have to create the buttons to add objects.
	 * Will be changing this in the future to be like the other objects
	 * */
	public static String[] cbody = { "White", "Green", "Blue", "Red", "Black", "Yellow", "Purple", "Orange", "Zombie", "Goblin", "Orc", "Minotaur" , "Cyclops"};
	public final static String[] bodyColors = {"White", "Green", "Blue", "Red", "Black", "Yellow", "Purple", "Orange"};
	public String LoadedString = new String(), colorString = new String(), partString = new String(), ImageLocation = "Data/Sprites/";
	public Image[][] Male_Body = new Image[SprCre.usedColors + 1][SprCre.mbodyI + 1], Female_Body = new Image[SprCre.usedColors + 1][SprCre.fbodyI + 1];
	public Image[][][] Male_Face = new Image[SprCre.usedColors + 1][SprCre.faceI + 1][3], Female_Face = new Image[SprCre.usedColors + 1][SprCre.faceI + 1][3];
	public Image[][][] Male_Hair = new Image[SprCre.usedColors + 1][SprCre.mhairI + 1][4], Female_Hair = new Image[SprCre.usedColors + 1][SprCre.fhairI + 1][4];
	public Image[][][] Male_Hairop = new Image[SprCre.usedColors + 1][SprCre.mhairopI + 1][4], Female_Hairop = new Image[SprCre.usedColors + 1][SprCre.fhairopI + 1][4];
	public Image[][][] Male_Acce1 = new Image[SprCre.usedColors + 1][SprCre.macce1I + 1][4], Female_Acce1 = new Image[SprCre.usedColors + 1][SprCre.facce1I + 1][4];
	public Image[][][] Male_Acce2 = new Image[SprCre.usedColors + 1][SprCre.acce2I + 1][4], Female_Acce2 = new Image[SprCre.usedColors + 1][SprCre.acce2I + 1][4];
	public Image[][][] Male_Option = new Image[SprCre.usedColors + 1][SprCre.optionI + 1][5], Female_Option = new Image[SprCre.usedColors + 1][SprCre.optionI + 1][5];
	public Image[][][] Male_Mantle = new Image[SprCre.usedColors + 1][SprCre.mantleI + 1][4], Female_Mantle = new Image[SprCre.usedColors + 1][SprCre.mantleI + 1][4];
	public Image[][][] Male_Bangs = new Image[SprCre.usedColors + 1][SprCre.mbangsI +1][4], Female_Bangs = new Image[SprCre.usedColors + 1][SprCre.fbangsI + 1][4];
	public Image[][][] Male_Tail = new Image[SprCre.usedColors + 1][SprCre.tailI + 1][4], Female_Tail = new Image[SprCre.usedColors + 1][SprCre.tailI + 1][4];
	public Image[][][] Male_Ears = new Image[SprCre.usedColors + 1][SprCre.earsI + 1][4], Female_Ears = new Image[SprCre.usedColors + 1][SprCre.earsI + 1][4];
	public Image[][][] Male_Armor = new Image[SprCre.usedColors + 1][SprCre.armorI + 1][4], Female_Armor = new Image[SprCre.usedColors + 1][SprCre.armorI + 1][4];
	public Image MaleNoneAll, FemaleNoneAll;
	public volatile static BufferedImage AllSplit;
	public static BufferedImage src0;
	public static BufferedImage[] ScaledAllSplit = new BufferedImage[5];
	public static BufferedImage[] src = new BufferedImage[maxI + 1];
	public static boolean doneWithFirstBoard = false;
	
	public int INum;
	public Thread th = null;
	ImgHandler()
	{
		th = new Thread(new Runnable()
		{
			public void run()
			{
				
				while (true)
				{
					/*This is the preview image that is compiled by
					 * its own thread. This increases the responsiveness of the program on 
					 * slower computers
					 * */
					bi = MakeSmaller(AllSplit, ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY);
					try {
						Thread.sleep(10);
					} catch (Exception ex){}
				}
			}
		});
		th.start();
	}
	/*
	 * Overloaded method DrawParts this is the one you use
	 * the other overloaded method DrawParts will be called 
	 * */
	public void DrawParts(Graphics2D g, String IsSelected)
	{
		//If this were 1.7 Java I'd use a switch but 1.6 compliance states no strings in switch statements :(
		if (IsSelected.equals("Body"))
			DrawParts(g, SprCre.Mbody, SprCre.Fbody);	
		if (IsSelected.equals("Face"))
			DrawParts(g, SprCre.Mface, SprCre.Fface);
		if (IsSelected.equals("Hair"))
			DrawParts(g, SprCre.Mhair, SprCre.Fhair);
		if (IsSelected.equals("Hairop"))
			DrawParts(g, SprCre.Mhairop, SprCre.Fhairop);
		if (IsSelected.equals("Mantle"))
			DrawParts(g, SprCre.Mmantle, SprCre.Fmantle);
		if (IsSelected.equals("Acce1"))
			DrawParts(g, SprCre.Macce1, SprCre.Facce1);
		if (IsSelected.equals("Acce2"))
			DrawParts(g, SprCre.Macce2, SprCre.Facce2);
		if (IsSelected.equals("Option"))
			DrawParts(g, SprCre.Moption, SprCre.Foption);
		if (IsSelected.equals("Tail"))
			DrawParts(g, SprCre.Mtail, SprCre.Ftail);
		if (IsSelected.equals("Ears"))
			DrawParts(g, SprCre.Mears, SprCre.Fears);
		if (IsSelected.equals("Bangs"))
			DrawParts(g, SprCre.Mbangs, SprCre.Fbangs);
		if (IsSelected.equals("Armor"))
			DrawParts(g, SprCre.Marmor, SprCre.Farmor);
	}
	/*
	 * When adding new categories you must add above
	 * */
	/*
	 * This controls which selection window should be drawn
	 * I use Method overloading within it's own method
	 * to complete DrawParts 
	 * */
	public void DrawParts(Graphics2D g, Image Malepart, Image Femalepart)
	{
		if (SprCre.PickSex.equals("Male"))
		{
			g.drawImage(MaleNoneAll, SprCre.PartSelectWindowX, SprCre.PartSelectWindowY - ImageH, null);
			g.drawImage(Malepart, SprCre.PartSelectWindowX, SprCre.PartSelectWindowY, null);
		}
		else if (SprCre.PickSex.equals("Female"))
		{
			g.drawImage(FemaleNoneAll, SprCre.PartSelectWindowX, SprCre.PartSelectWindowY - ImageH, null);
			g.drawImage(Femalepart, SprCre.PartSelectWindowX,SprCre.PartSelectWindowY, null);
		}
	}
	/*
	 * This handles all the loading of the images. It controls how many layers and if the layer is used for both
	 * Male and Female or just one or the other
	 * */
	private int[] GetOverrides(int[][] i, int part)
	{
		int[] toReturn = new int[categories + 1];
		for (int a = 0; a <= categories; a++)
			toReturn[a] = -1;
		if (i[part][0] == -1)
			return toReturn;
		else
			for (int xx = 0; xx <= categories; xx++)
				toReturn[xx] = i[part][xx];
		return toReturn;
	}
	public void drawBoard()
	{
		/*
		 * This will be all config loaded soon.
		 * */
		if (!doneWithFirstBoard)
		{
			/*
			 * Male Start
			 * */
				SprCre.Mbody = setImage(Male_Body, null, Cons.usedColors, SprCre.mbodyI, SprCre.mfront, 5, 2, 		//set the image as if no override
						GetOverrides(IOHandler.overridesCFG, Cons.mbodyCFG), SprCre.mback, 8, 2);								//Check for override in config.cfg

				SprCre.Mhair = setImage(null, Male_Hair, Cons.usedColors, SprCre.mhairI, SprCre.mfront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.mhairCFG), SprCre.mback, 8, 2);

				SprCre.Mhairop = setImage(null, Male_Hairop, Cons.usedColors, SprCre.mhairopI, SprCre.mback, 8, 1, 
						GetOverrides(IOHandler.overridesCFG, Cons.mhairopCFG), SprCre.mfront, 5, 1);

				SprCre.Mmantle = setImage(null, Male_Mantle, Cons.usedColors, SprCre.mantleI, SprCre.mback, 8, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.mantleCFG), SprCre.mfront, 5, 2);

				SprCre.Moption = setImage(null, Male_Option, Cons.usedColors, SprCre.optionI, SprCre.mback, 8, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.optionCFG), SprCre.mfront, 5, 1);

				SprCre.Macce1 = setImage(null, Male_Acce1, Cons.usedColors, SprCre.macce1I, SprCre.mfront, 5, 1, 
						GetOverrides(IOHandler.overridesCFG, Cons.macce1CFG), SprCre.mback, 8, 1);

				SprCre.Macce2 = setImage(null, Male_Acce2, Cons.usedColors, SprCre.acce2I, SprCre.mfront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.acce2CFG), SprCre.mback, 8, 2);

				SprCre.Mtail = setImage(null, Male_Tail, Cons.usedColors, SprCre.tailI, SprCre.mside, 7, 1, 
						GetOverrides(IOHandler.overridesCFG, Cons.tailCFG), SprCre.mback, 8, 1);
				
				SprCre.Mears = setImage(null, Male_Ears, Cons.usedColors, SprCre.earsI, SprCre.mfront, 5, 2,
						GetOverrides(IOHandler.overridesCFG, Cons.earsCFG), SprCre.mback, 8, 1);

				SprCre.Mbangs = setImage(null, Male_Bangs, Cons.usedColors, SprCre.mbangsI, SprCre.mfront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.mbangsCFG), SprCre.mback, 8, 2);

				SprCre.Mface = setImage(null, Male_Face, Cons.usedColors, SprCre.faceI, SprCre.mfront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.faceCFG), SprCre.mback, 8, 2);

				SprCre.Marmor = setImage(null, Male_Armor, Cons.usedColors, SprCre.armorI, SprCre.mback, 8, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.armorCFG), SprCre.mfront, 5, 2);
				
			/*
			 * Female Start
			 * */
				SprCre.Fbody = setImage(Female_Body, null, Cons.usedColors, SprCre.fbodyI, SprCre.ffront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.fbodyCFG), SprCre.fback, 8, 2);
			
				SprCre.Fhair = setImage(null, Female_Hair, Cons.usedColors, SprCre.fhairI, SprCre.ffront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.fhairCFG), SprCre.fback, 8, 2);

				SprCre.Fhairop = setImage(null, Female_Hairop, Cons.usedColors, SprCre.fhairopI, SprCre.fback, 8, 1, 
						GetOverrides(IOHandler.overridesCFG, Cons.fhairopCFG), SprCre.ffront, 5, 1);

				SprCre.Fmantle = setImage(null, Female_Mantle, Cons.usedColors, SprCre.mantleI, SprCre.fback, 8, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.mantleCFG), SprCre.ffront, 5, 2);

				SprCre.Foption = setImage(null, Female_Option, Cons.usedColors, SprCre.optionI, SprCre.fback, 8, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.optionCFG), SprCre.ffront, 5, 2);

				SprCre.Facce1 = setImage(null, Female_Acce1, Cons.usedColors, SprCre.facce1I, SprCre.ffront, 5, 1, 
						GetOverrides(IOHandler.overridesCFG, Cons.facce1CFG), SprCre.fback, 8, 1);

				SprCre.Facce2 = setImage(null, Female_Acce2, Cons.usedColors, SprCre.acce2I, SprCre.ffront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.acce2CFG), SprCre.fback, 8, 2);

				SprCre.Ftail = setImage(null, Female_Tail, Cons.usedColors, SprCre.tailI, SprCre.fside, 7, 1, 
						GetOverrides(IOHandler.overridesCFG, Cons.tailCFG), SprCre.fback, 8, 1);
				
				SprCre.Fears = setImage(null, Female_Ears, Cons.usedColors, SprCre.earsI, SprCre.ffront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.earsCFG), SprCre.fback, 8, 1);

				SprCre.Fbangs = setImage(null, Female_Bangs, Cons.usedColors, SprCre.fbangsI, SprCre.ffront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.fbangsCFG), SprCre.fback, 8, 2);

				SprCre.Fface = setImage(null, Female_Face, Cons.usedColors, SprCre.faceI, SprCre.ffront, 5, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.faceCFG), SprCre.fback, 8, 2);

				SprCre.Farmor = setImage(null, Female_Armor, Cons.usedColors, SprCre.armorI, SprCre.fback, 8, 2, 
						GetOverrides(IOHandler.overridesCFG, Cons.armorCFG), SprCre.ffront, 5, 2);	
		}
		else return;
	}
	public BufferedImage createResizedCopy(BufferedImage originalImage, int scaledWidth, int scaledHeight)
    {
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaledBI.createGraphics();
		g.setComposite(AlphaComposite.Src);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
	/*
	 * This draws the Selection window
	 * Allows you to use a different frame than the default
	 * for what frames you select to be different
	 * */
	private BufferedImage setImage(Image[][] img, Image[][][] img2, int col, int count, Image back, int grabframe, int layer, int[] ovr, Image oImage, int ovrFrame, int ovrLayer)
	{
		int keepcount = 0;
		BufferedImage dest = new BufferedImage(col * ImageW * 2, ((count / 2) + 1) * ImageH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		boolean[] over = new boolean[count + 1];
		for (int lo = 0; lo <= count; lo++)
		{
			over[lo] = new Boolean(false);
			if (ovr[keepcount] == lo)
			{
				over[lo] = true;
				keepcount++;
			}
			else
				over[lo] = false;
		}
		for (int icount = 0; icount <= count; icount++)
		{
			/*
			 * This is for drawing the base template and 
			 * get the background image ready for putting the parts
			 * on them
			 * */
			if (icount <= count / 2)
			{
				/*
				 * This draws the first half of the selection window
				 * checking for an override value to use a different
				 * frame template incase the object is not inline
				 * with the rest of the images
				 * */
				if (ovr == null || !over[icount])
					g2.drawImage(back, 0, icount * ImageH - ImageH, null);
				else
					g2.drawImage(oImage, 0, icount * ImageH - ImageH, null);
			}
			else
			{
				/*
				 * This draws the second half of the selection window
				 * */
				if (icount <= count)
				{
					if (ovr == null || !over[icount])
						g2.drawImage(back, Cons.drawAcross, icount * ImageH - ImageH - (count / 2 * ImageH), null);
					else
						g2.drawImage(oImage, Cons.drawAcross, icount * ImageH - ImageH - (count / 2 * ImageH), null);					
				}
			}
			for (int icol = 0; icol <= col; icol++)
			{
				/*
				 * This is for drawing the parts onto the template
				 * */
				if (icount <= count / 2)
				{
					//This controls the first half of the selection window
					if (img2 == null)
					{
						if (ovr == null || !over[icount])
							g2.drawImage(MakeSmaller(img[icol][icount], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[grabframe],
									icol * ImageW - ImageW, icount * ImageH - ImageH, null);
						else
							g2.drawImage(MakeSmaller(img[icol][icount], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[ovrFrame],
									icol * ImageW - ImageW, icount * ImageH - ImageH, null);
					}
					else
					{
						if (ovr == null || !over[icount])
							g2.drawImage(MakeSmaller(img2[icol][icount][layer], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[grabframe],
									icol * ImageW - ImageW, icount * ImageH - ImageH, null);
						else
							g2.drawImage(MakeSmaller(img2[icol][icount][ovrLayer], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[ovrFrame],
									icol * ImageW - ImageW, icount * ImageH - ImageH, null);
					}
				}
				else
				{
					//This controls the second half of the selection window
					if (img2 == null)
					{
						if (ovr == null || !over[icount])
							g2.drawImage(MakeSmaller(img[icol][icount], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[grabframe],
									Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
						else
							g2.drawImage(MakeSmaller(img[icol][icount], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[ovrFrame],
									Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
					}
					else
					{
						if (ovr == null || !over[icount])
							g2.drawImage(MakeSmaller(img2[icol][icount][layer], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[grabframe],
									Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
						else
							g2.drawImage(MakeSmaller(img2[icol][icount][ovrLayer], ImageW, ImageH, ImageFrames, makeSmallerX, makeSmallerY)[ovrFrame],
									Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
					}
				}
			}
		}
		g2.dispose();
		return dest;
	}
	private void LoadSpriteIn(Image[][] MSpr, Image[][] FSpr, int CI, int NI, int INum, int IColors, String colorString, String partString)
	{
		String LoadedString;
		if (MSpr != null)
		{
			LoadedString = ImageLocation + "Male/" + partString + Integer.toString(INum) + "_" + colorString;
			MSpr[CI][NI] = load(LoadedString + ".png");
		}
		if (FSpr != null)
		{
			LoadedString = ImageLocation + "Female/" + partString + Integer.toString(INum) + "_" + colorString;
			FSpr[CI][NI] = load(LoadedString + ".png");
		}
	}
	private void LoadSpriteIn(Image[][][] MSpr3, Image[][][] FSpr3, boolean GetFront, boolean GetMiddle, boolean GetBack, int CI, int NI, boolean LoadBothFromSame, int INum, int IColors, String colorString, String partString)
	{
		String LoadedString;
		if (MSpr3 != null)
		{
			if (!LoadBothFromSame)
				LoadedString = ImageLocation + "Male/" + partString + Integer.toString(INum) + "_" + colorString;
			else
				LoadedString = ImageLocation + partString + Integer.toString(INum) + "_" + colorString;
			
			if (GetBack)
				MSpr3[CI][NI][1] = load(LoadedString + "_back" + ".png");
			
			if (GetFront)
					MSpr3[CI][NI][2] = load(LoadedString + "_front" + ".png");
			
			if (GetMiddle)
				MSpr3[CI][NI][3] = load(LoadedString + "_middle" + ".png");
		}
		if (FSpr3 != null)
		{
			if (!LoadBothFromSame)
				LoadedString = ImageLocation + "Female/" + partString + Integer.toString(INum) + "_" + colorString;
			else
				LoadedString = ImageLocation + partString + Integer.toString(INum) + "_" + colorString;
			
			if (GetBack)
				FSpr3[CI][NI][1] = load(LoadedString + "_back" + ".png");
			
			if (GetFront)
				FSpr3[CI][NI][2] = load(LoadedString + "_front" + ".png");
			
			if (GetMiddle)
				FSpr3[CI][NI][3] = load(LoadedString + "_middle" + ".png");
		}
	}
	/*
	 * This function calls private void LoadSpriteIn(Image[][] MSpr, Image[][] FSpr, Image[][][] MSpr3, Image[][][] FSpr3, boolean GetMiddle, int CI, int NI, boolean LoadBothFromSame)
	 * It loads all the images through a loop Just calling LoadAllSprites(); 
	 * will load every image that is needed for Sprite Creator 3
	 * */
	public void LoadAllSprites()
	{
		String colorString, partString;
		for (int IColors = 1; IColors <= Cons.usedColors; IColors++)
		{
			colorString = colorArray[IColors];
			for (int INum = 1; INum <= Cons.maxI; INum++)
			{
				partString = "Hair/";
				if (INum <= SprCre.fhairI)
					LoadSpriteIn(null, Female_Hair, true, false, true, IColors, INum, false, INum, IColors, colorString, partString);
				if (INum <= SprCre.mhairI)
					LoadSpriteIn(Male_Hair, null, true, false, true, IColors, INum, false, INum, IColors, colorString, partString);
				
				partString = "Body/";
				if (INum <= SprCre.fbodyI)
					LoadSpriteIn(null, Female_Body, IColors, INum, INum, IColors, colorString, partString);
				if (INum <= SprCre.mbodyI)
					LoadSpriteIn(Male_Body, null, IColors, INum, INum, IColors, colorString, partString);
				
				partString = "Bangs/";
				if (INum <= SprCre.fbangsI)
					LoadSpriteIn(null, Female_Bangs, true, false, false, IColors, INum, false, INum, IColors, colorString, partString);
				if (INum <= SprCre.mbangsI)
					LoadSpriteIn(Male_Bangs, null, true, false, false, IColors, INum, false, INum, IColors, colorString, partString);
				
				partString = "Mantle/";
				if (INum <= SprCre.mantleI)
					LoadSpriteIn(Male_Mantle, Female_Mantle, true, false, true, IColors, INum, true, INum, IColors, colorString, partString);
				
				partString = "Hairop/";
				if (INum <= SprCre.fhairopI)
					LoadSpriteIn(null, Female_Hairop, true, false, true, IColors, INum, false, INum, IColors, colorString, partString);
				if (INum <= SprCre.mhairopI)
					LoadSpriteIn(Male_Hairop, null, true, false, true, IColors, INum, false, INum, IColors, colorString, partString);
				
				partString = "Acce2/";
				if (INum <= SprCre.acce2I)
					LoadSpriteIn(Male_Acce2, Female_Acce2, true, false, false, IColors, INum, true, INum, IColors, colorString, partString);
				
				partString = "Acce1/";
				if (INum <= SprCre.facce1I)
					LoadSpriteIn(null, Female_Acce1, true, false, true, IColors, INum, false, INum, IColors, colorString, partString);
				if (INum <= SprCre.macce1I)
					LoadSpriteIn(Male_Acce1, null, true, false, true, IColors, INum, false, INum, IColors, colorString, partString);
				
				partString = "Tail/";
				if (INum <= SprCre.tailI)
					LoadSpriteIn(Male_Tail, Female_Tail, false, true, true, IColors, INum, true, INum, IColors, colorString, partString);
				
				partString = "Ears/";
				if (INum <= SprCre.earsI)
					LoadSpriteIn(Male_Ears, Female_Ears, true, true, true, IColors, INum, true, INum, IColors, colorString, partString);
				
				partString = "Option/";
				if (INum <= SprCre.optionI)
					LoadSpriteIn(Male_Option, Female_Option, true, false, true, IColors, INum, true, INum, IColors, colorString, partString);

				partString = "Face/";
				if (INum <= SprCre.faceI)
					LoadSpriteIn(Male_Face, Female_Face, true, false, true, IColors, INum, true, INum, IColors, colorString, partString);

				partString = "Armor/";
				if (INum <= SprCre.armorI)
					LoadSpriteIn(Male_Armor, Female_Armor, true, false, true, IColors, INum, true, INum, IColors, colorString, partString);
			}
		}
	}
	/*
	 * This Method stacks the image getting the image ready to be saved or animated
	 * */
	public BufferedImage StackImage()
	{
		src0 = toBufferedImage(SprCre.AllBlank);
		if (SprCre.PickSex.equals("Male"))
		{
			for (int i = Cons.StackFirst; i <= Cons.StackLast; i++) // 17 images starting at 2 ( I don't know why @ 2 )
			{
				if (i == Cons.Base)
				{
					if (SprCre.MHaveBody)
						src[i] = ReturnBody(SprCre.MTypeBody, "Male");
				}
				else
					src[i] = toBufferedImage(SprCre.Mimg[i]);
			}
		}
		if (SprCre.PickSex.equals("Female"))
		{
			for (int i = Cons.StackFirst; i <= Cons.StackLast; i++)
			{
				if (i == Cons.Base)
				{
					if (SprCre.FHaveBody)
						src[i] = ReturnBody(SprCre.FTypeBody, "Female");
				}
				else
					src[i] = toBufferedImage(SprCre.Fimg[i]);
			}
		}
		Graphics2D g3 = src0.createGraphics();
		for (int i = Cons.StackFirst; i <= Cons.StackLast; i++)
		{
			if (i == Cons.Base)
			{
				if (SprCre.MHaveBody && SprCre.PickSex.equals("Male") || SprCre.FHaveBody && SprCre.PickSex.equals("Female"))
					g3.drawImage(src[i], 0, 0, null);
			}
			else
				g3.drawImage(src[i], 0, 0, null);
		}
		if (SprCre.PickSex.equals("Female") && SprCre.FisGhost || SprCre.PickSex.equals("Male") && SprCre.MisGhost)
		{
			src0 = Ghostify(src0);
		}
		return src0;
	}
	/*
	 * This Method is used by the save method to get the proper name for the save file
	 * */
	public BufferedImage SaveASVX()
	{
		BufferedImage dest = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		BufferedImage bi = StackImage();
		g2.drawImage(bi, -32, 0, null);
		g2.drawImage(bi, 32, 0, null);
		g2.dispose();
		return dest;
	}
	public BufferedImage ReturnBody(String Body, String sex)
	{
		int inc = 0;
		for (String s : cbody)
		{
			if (Body.equals(s))
			{
				if (SprCre.PickSex.equals("Male"))
					return toBufferedImage(SprCre.Mbase[inc]);
				if (SprCre.PickSex.equals("Female"))
					return toBufferedImage(SprCre.Fbase[inc]);
			}
			inc++;
		}
		//Something went wrong if the program even reaches this point
		return null;
	}
	/*
	 * This Method is for converting an Image into a BufferedImage
	 * */
	public BufferedImage toBufferedImage(Image src)
	{
		BufferedImage dest = new BufferedImage(Cons.TileW, Cons.TileH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}
	/*
	 * This is for creating a ghost with the current selected opacity.
	 * */
	public BufferedImage Ghostify(BufferedImage bi)
	{
		BufferedImage dest = new BufferedImage(Cons.TileW, Cons.TileH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		if (SprCre.PickSex.equals("Male"))
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, SprCre.Mopacity));
		if (SprCre.PickSex.equals("Female"))
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, SprCre.Fopacity));
		g2.drawImage(bi, 0, 0, null);
		g2.dispose();
		return dest;
	}
	/*
	 * This is for splitting the 12 frame image (96x128) into 12 separate frames (32x32)
	 * */
	public BufferedImage[] MakeSmaller(Image src, int XSize, int YSize, int framesToBuff, int XMaxB, int YMaxB)
	{
		/*
		 * Function I wrote for splitting the image into frames.
		 * This function is also universal
		 * */
		/*
		 * Rather than grabbing each frame from the larger image
		 * we instead create a smaller image and then draw the larger image
		 * onto the smaller one just off setting the larger image into
		 * the correct location to get the frame
		 * */
		BufferedImage[] dest = new BufferedImage[framesToBuff + 1];
		Graphics2D[] g2 = new Graphics2D[framesToBuff + 1];
		for (int i = 1; i <= framesToBuff; i++)
		{
			//Create the buffered image with Alpha attributes ( limits me to png?? cause JPEG and BMP wont save )
			dest[i] = new BufferedImage(XSize, YSize, BufferedImage.TYPE_INT_ARGB);
			g2[i] = dest[i].createGraphics();
		}
		
		for (int x = 0, i = 1; x >= XMaxB; x -= XSize)// - numbers because we read the image backwards
			for (int y = 0; y >= YMaxB; y -= YSize, i++)// - numbers because we read the image backwards
				g2[i].drawImage(src, x, y, null);
		
		for (int i = 1; i <= framesToBuff; i++)
			g2[i].dispose();
		return dest;
	}
	/*
	 * getURL and load methods are used by the load method for getting the image from within the jar
	 * */
	private URL getURL(String filename)
	{
		URL url = null;
		try {
			url = ClassLoader.getSystemResource(filename);
		} catch(Exception e) {}
		return url;
	}

	public Image load(String filename) {
		return Toolkit.getDefaultToolkit().getImage(getURL(filename));
	}
}
