package circle.handlers;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import circle.Cons;
import circle.SprCre;

public class ImgHandler implements Cons
{
	public static BufferedImage[]			bi;
	public static int						IColors;

	public static String[]					colorArray			= { "null", "red", "yellow", "green", "blue", "white", "black", "purple", "pink", "dbrown", "lbrown" };

	/*
	 * This limits each object to the designated amount in Cons.java denoted by maxI
	 * */
	public int								fhairI				= limitObj(SprCre.io.getCFG(SprCre.io.fhairCFG));
	public int								mhairI				= limitObj(SprCre.io.getCFG(SprCre.io.mhairCFG));
	public int								mchestI				= limitObj(SprCre.io.getCFG(SprCre.io.mchestCFG));
	public int								fchestI				= limitObj(SprCre.io.getCFG(SprCre.io.fchestCFG));
	public int								mshoulderI			= limitObj(SprCre.io.getCFG(SprCre.io.mshoulderCFG));
	public int								fshoulderI			= limitObj(SprCre.io.getCFG(SprCre.io.fshoulderCFG));
	public int								marmsI				= limitObj(SprCre.io.getCFG(SprCre.io.marmsCFG));
	public int								farmsI				= limitObj(SprCre.io.getCFG(SprCre.io.farmsCFG));
	public int								mpantsI				= limitObj(SprCre.io.getCFG(SprCre.io.mpantsCFG));
	public int								fpantsI				= limitObj(SprCre.io.getCFG(SprCre.io.fpantsCFG));
	public int								mwaistI				= limitObj(SprCre.io.getCFG(SprCre.io.mwaistCFG));
	public int								fwaistI				= limitObj(SprCre.io.getCFG(SprCre.io.fwaistCFG));
	public int								mbootsI				= limitObj(SprCre.io.getCFG(SprCre.io.mbootsCFG));
	public int								fbootsI				= limitObj(SprCre.io.getCFG(SprCre.io.fbootsCFG));
	public int								mhatI				= limitObj(SprCre.io.getCFG(SprCre.io.mhatCFG));
	public int								fhatI				= limitObj(SprCre.io.getCFG(SprCre.io.fhatCFG));
	public int								mshirtI				= limitObj(SprCre.io.getCFG(SprCre.io.mshirtCFG));
	public int								fshirtI				= limitObj(SprCre.io.getCFG(SprCre.io.fshirtCFG));
	public int								mlongshirtI			= limitObj(SprCre.io.getCFG(SprCre.io.mlongshirtCFG));
	public int								flongshirtI			= limitObj(SprCre.io.getCFG(SprCre.io.flongshirtCFG));

	public int[]							objectsI			= dogetobjectsI(CFGlist);
	// TODO
	public static String[]					cbody				= { "White", "Green", "Blue", "Red", "Black", "Yellow", "Purple", "Orange" };							// , "Zombie", "Goblin", "Orc", "Minotaur" , "Cyclops"};
	public final static String[]			bodyColors			= { "White", "Green", "Blue", "Red", "Black", "Yellow", "Purple", "Orange" };
	public String							LoadedString		= new String(), colorString = new String(), partString = new String(), ImageLocation = "Data/Sprites/";
	public volatile Image[][]				Fbase				= new Image[5][cbody.length + 1], Mbase = new Image[5][cbody.length + 1];								// ImageBases + 1 where 9 is

	//Do not change these values. They are automatic. Change the values in the config file instead. All obects already have support for up to 4 layers including 0
	//If you add a category you must add that category here as well.
	public volatile Image[][][]				Male_Chest			= new Image[SprCre.usedColors + 1][mchestI + 1][4], Female_Chest = new Image[SprCre.usedColors + 1][fchestI + 1][4];
	public volatile Image[][][]				Male_Hair			= new Image[SprCre.usedColors + 1][mhairI + 1][4], Female_Hair = new Image[SprCre.usedColors + 1][fhairI + 1][4];
	public volatile Image[][][]				Male_Shoulder		= new Image[SprCre.usedColors + 1][mshoulderI + 1][4], Female_Shoulder = new Image[SprCre.usedColors + 1][fshoulderI + 1][4];
	public volatile Image[][][]				Male_Pants			= new Image[SprCre.usedColors + 1][mpantsI + 1][4], Female_Pants = new Image[SprCre.usedColors + 1][fpantsI + 1][4];
	public volatile Image[][][]				Male_Boots			= new Image[SprCre.usedColors + 1][mbootsI + 1][4], Female_Boots = new Image[SprCre.usedColors + 1][fbootsI + 1][4];
	public volatile Image[][][]				Male_Arms			= new Image[SprCre.usedColors + 1][marmsI + 1][4], Female_Arms = new Image[SprCre.usedColors + 1][farmsI + 1][4];
	public volatile Image[][][]				Male_Waist			= new Image[SprCre.usedColors + 1][mwaistI + 1][4], Female_Waist = new Image[SprCre.usedColors + 1][fwaistI + 1][4];
	// TODO
	public volatile Image[][][]				Male_Hat			= new Image[SprCre.usedColors + 1][mhatI + 1][4], Female_Hat = new Image[SprCre.usedColors + 1][fhatI + 1][4];
	public volatile Image[][][]				Male_Shirt			= new Image[SprCre.usedColors + 1][mshirtI + 1][4], Female_Shirt = new Image[SprCre.usedColors + 1][fshirtI + 1][4];
	public volatile Image[][][]				Male_LongShirt		= new Image[SprCre.usedColors + 1][mlongshirtI + 1][4], Female_LongShirt = new Image[SprCre.usedColors + 1][flongshirtI + 1][4];

	//If you add a category you must add that category here as well.
	public volatile Image					Fchest, Fhair, Fshoulder, Fboots, Fpants, Fmantle, Mchest, Mhair, Mshoulder, Mboots, Mpants, Mmantle, Marms, Farms;
	public volatile Image					Mwaist, Fwaist, Mhat, Fhat, Mshirt, Fshirt, Mlongshirt, Flongshirt, Mhorn, Fhorn;

	public static int[][][]					mint				= new int[colorArray.length][maxI + 1][5], fint = new int[colorArray.length][maxI + 1][5];
	public boolean							MhasEars			= true, FhasEars = true;
	public Image							MaleNoneAll, FemaleNoneAll;
	public volatile static BufferedImage	AllSplit;
	public static BufferedImage				src0;
	public static BufferedImage[]			ScaledAllSplit		= new BufferedImage[5];
	public static volatile boolean			doneWithFirstBoard	= false;

	public int								INum;

	public ImgHandler()
	{

	}

	public int[] dogetobjectsI(String[] g)
	{
		int[] r = new int[g.length + 1];
		for (int i = 0; i < g.length; i++)
			r[i] = limitObj(SprCre.cho.mfCFG[i]);
		return r;
	}

	private int limitObj(int limit)
	{
		if (limit > maxI)
			return maxI;
		else
			return limit;
	}

	public void DrawParts(Graphics2D g, String IsSelected)
	{
		//If you added a category you must also add it here
		if (IsSelected.equals("Chest"))
			DrawParts(g, Mchest, Fchest);
		else if (IsSelected.equals("Hair"))
			DrawParts(g, Mhair, Fhair);
		else if (IsSelected.equals("Shoulder"))
			DrawParts(g, Mshoulder, Fshoulder);
		else if (IsSelected.equals("Mantle"))
			DrawParts(g, Mmantle, Fmantle);
		else if (IsSelected.equals("Pants"))
			DrawParts(g, Mpants, Fpants);
		else if (IsSelected.equals("Boots"))
			DrawParts(g, Mboots, Fboots);
		else if (IsSelected.equals("Arms"))
			DrawParts(g, Marms, Farms);
		else if (IsSelected.equals("Waist"))
			DrawParts(g, Mwaist, Fwaist);
		else if (IsSelected.equals("Hat"))
			DrawParts(g, Mhat, Fhat);
		else if (IsSelected.equals("Shirt"))
			DrawParts(g, Mshirt, Fshirt);
		else if (IsSelected.equals("LongShirt"))
			DrawParts(g, Mlongshirt, Flongshirt);
	}

	public void DrawParts(Graphics2D g, Image malepart, Image femalepart)
	{
		if (SprCre.apl.PickSex.equals("Male"))
		{
			g.drawImage(MaleNoneAll, SprCre.apl.PartSelectWindowX, SprCre.apl.PartSelectWindowY - ImageH, null);
			g.drawImage(malepart, SprCre.apl.PartSelectWindowX, SprCre.apl.PartSelectWindowY, null);
		}
		else if (SprCre.apl.PickSex.equals("Female"))
		{
			g.drawImage(FemaleNoneAll, SprCre.apl.PartSelectWindowX, SprCre.apl.PartSelectWindowY - ImageH, null);
			g.drawImage(femalepart, SprCre.apl.PartSelectWindowX, SprCre.apl.PartSelectWindowY, null);
		}
	}

	private int[] GetOverrides(int[][] i, int part)
	{
		int[] toReturn = new int[Cons.CFGlist.length];
		for (int a = 0; a <= Cons.CFGlist.length - 1; a++)
			toReturn[a] = -1;
		if (i[part][0] == -1)
			return toReturn;
		else
			for (int xx = 0; xx <= Cons.CFGlist.length - 1; xx++)
				toReturn[xx] = i[part][xx];
		return toReturn;
	}

	public void drawBoard()
	{
		/*
		 * If you added a category you must also add it here
		 * */
		if (!doneWithFirstBoard)
		{
			Mchest = setImage(Male_Chest, mchestI, SprCre.apl.mfront, SprCre.io.mchestCFG, SprCre.apl.mfront);
			Mhair = setImage(Male_Hair, mhairI, SprCre.apl.mfront, SprCre.io.mhairCFG, SprCre.apl.mback);
			Mshoulder = setImage(Male_Shoulder, mshoulderI, SprCre.apl.mfront, SprCre.io.mshoulderCFG, SprCre.apl.mback);
			Mpants = setImage(Male_Pants, mpantsI, SprCre.apl.mfront, SprCre.io.mpantsCFG, SprCre.apl.mback);
			Mboots = setImage(Male_Boots, mbootsI, SprCre.apl.mfront, SprCre.io.mbootsCFG, SprCre.apl.mback);
			Marms = setImage(Male_Arms, marmsI, SprCre.apl.mfront, SprCre.io.marmsCFG, SprCre.apl.mback);
			Mwaist = setImage(Male_Waist, mwaistI, SprCre.apl.mfront, SprCre.io.mwaistCFG, SprCre.apl.mback);
			Mhat = setImage(Male_Hat, mhatI, SprCre.apl.mfront, SprCre.io.mhatCFG, SprCre.apl.mback);
			Mshirt = setImage(Male_Shirt, mshirtI, SprCre.apl.mfront, SprCre.io.mshirtCFG, SprCre.apl.mback);
			Mlongshirt = setImage(Male_LongShirt, mlongshirtI, SprCre.apl.mfront, SprCre.io.mlongshirtCFG, SprCre.apl.mback);
			// TODO
			Fchest = setImage(Female_Chest, fchestI, SprCre.apl.ffront, SprCre.io.fchestCFG, SprCre.apl.fback);
			Fhair = setImage(Female_Hair, fhairI, SprCre.apl.ffront, SprCre.io.fhairCFG, SprCre.apl.fback);
			Fshoulder = setImage(Female_Shoulder, fshoulderI, SprCre.apl.ffront, SprCre.io.fshoulderCFG, SprCre.apl.fback);
			Fpants = setImage(Female_Pants, fpantsI, SprCre.apl.ffront, SprCre.io.fpantsCFG, SprCre.apl.fback);
			Fboots = setImage(Female_Boots, fbootsI, SprCre.apl.ffront, SprCre.io.fbootsCFG, SprCre.apl.fback);
			Farms = setImage(Female_Arms, farmsI, SprCre.apl.ffront, SprCre.io.farmsCFG, SprCre.apl.fback);
			Fwaist = setImage(Female_Waist, fwaistI, SprCre.apl.ffront, SprCre.io.fwaistCFG, SprCre.apl.fback);
			Fhat = setImage(Female_Hat, fhatI, SprCre.apl.ffront, SprCre.io.fhatCFG, SprCre.apl.fback);
			Fshirt = setImage(Female_Shirt, fshirtI, SprCre.apl.ffront, SprCre.io.fshirtCFG, SprCre.apl.fback);
			Flongshirt = setImage(Female_LongShirt, flongshirtI, SprCre.apl.ffront, SprCre.io.flongshirtCFG, SprCre.apl.fback);
		}
	}

	private BufferedImage setImage(Image[][][] img2, int count, Image back, int cfg, Image oImage)
	{
		int keepcount = 0;
		int[] ovr = GetOverrides(SprCre.io.overridesCFG, cfg);
		BufferedImage dest = new BufferedImage(Cons.usedColors * ImageW * 2, ((count / 2) + 1) * ImageH, BufferedImage.TYPE_INT_ARGB);
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
			for (int icol = 0; icol <= Cons.usedColors; icol++)
			{
				if (icount <= count / 2)
				{
					if (ovr == null || !over[icount])
						g2.drawImage(MakeSmaller(img2[icol][icount][1])[1], icol * ImageW - ImageW, icount * ImageH - ImageH, null);
					else
						g2.drawImage(MakeSmaller(img2[icol][icount][1])[4], icol * ImageW - ImageW, icount * ImageH - ImageH, null);
				}
				else
				{
					if (ovr == null || !over[icount])
						g2.drawImage(MakeSmaller(img2[icol][icount][1])[1], Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
					else
						g2.drawImage(MakeSmaller(img2[icol][icount][1])[4], Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
				}
			}
		}
		for (int icount = 0; icount <= count; icount++)
		{

			if (icount <= count / 2)
			{
				if (ovr == null || !over[icount])
					g2.drawImage(back, 0, icount * ImageH - ImageH, null);
				else
					g2.drawImage(oImage, 0, icount * ImageH - ImageH, null);
			}
			else
			{
				if (icount <= count)
				{
					if (ovr == null || !over[icount])
						g2.drawImage(back, Cons.drawAcross, icount * ImageH - ImageH - (count / 2 * ImageH), null);
					else
						g2.drawImage(oImage, Cons.drawAcross, icount * ImageH - ImageH - (count / 2 * ImageH), null);
				}
			}
			for (int icol = 0; icol <= Cons.usedColors; icol++)
			{
				if (icount <= count / 2)
				{
					if (ovr == null || !over[icount])
						g2.drawImage(MakeSmaller(img2[icol][icount][0])[1], icol * ImageW - ImageW, icount * ImageH - ImageH, null);
					else
						g2.drawImage(MakeSmaller(img2[icol][icount][0])[4], icol * ImageW - ImageW, icount * ImageH - ImageH, null);
				}
				else
				{
					if (ovr == null || !over[icount])
						g2.drawImage(MakeSmaller(img2[icol][icount][0])[1], Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
					else
						g2.drawImage(MakeSmaller(img2[icol][icount][0])[4], Cons.drawAcross - ImageW + icol * ImageW, icount * ImageH - ImageH - (count / 2 * ImageH), null);
				}
			}
		}
		g2.dispose();
		return dest;
	}

	public void setint(int i, boolean keepValue, int CI, int NI, String sex)
	{
		if (sex.equals("Male"))
			mint[CI][NI][i] = keepValue ? i : -1;
		if (sex.equals("Female"))
			fint[CI][NI][i] = keepValue ? i : -1;
	}

	private void LoadSpriteIn(Image[][][] Spr, int CI, int NI, String colorString, String partString, boolean LoadSex, boolean f, boolean m, boolean b, boolean h, String sex)
	{
		String LoadedString;

		LoadedString = ImageLocation;
		if (LoadSex)
			LoadedString = LoadedString + sex + "/";
		LoadedString = LoadedString + partString + "/" + Integer.toString(NI) + "_" + colorString;
		if (SprCre.io.multilayer[SprCre.cho.findadj(partString, sex)][NI] != -1 && sex.equals("Male") || SprCre.io.multilayer[SprCre.cho.findadj(partString, sex)][NI] != -1 && sex.equals("Female"))
		{
			if (b)
			{
				Spr[CI][NI][1] = load(LoadedString + "_b" + ".png");
				setint(1, true, CI, NI, sex);
			}
			else
			{
				Spr[CI][NI][1] = SprCre.apl.AllBlank;
				setint(1, false, CI, NI, sex);
			}
			if (f)
			{
				Spr[CI][NI][2] = load(LoadedString + "_f" + ".png");
				setint(2, true, CI, NI, sex);
			}
			else
			{
				Spr[CI][NI][2] = SprCre.apl.AllBlank;
				setint(2, false, CI, NI, sex);
			}
		}
		Spr[CI][NI][0] = load(LoadedString + ".png");
		setint(0, true, CI, NI, sex);
	}

	public void LoadAllSprites()
	{
		/*
		 * If you added a category you must also add it here.
		 * */
		String colorString;
		for (int IColors = 1; IColors <= Cons.usedColors; IColors++)
		{
			colorString = colorArray[IColors];
			for (int INum = 1; INum <= Cons.maxI; INum++)
			{
				if (INum <= fhairI)//Find the function LoadSpriteIn to see how these are set up. You will see 5 boolean values
					LoadSpriteIn(Female_Hair, IColors, INum, colorString, "Hair", true, false, false, true, false, "Female");
				if (INum <= mhairI)
					LoadSpriteIn(Male_Hair, IColors, INum, colorString, "Hair", true, false, false, true, false, "Male");

				if (INum <= fchestI)
					LoadSpriteIn(Female_Chest, IColors, INum, colorString, "Chest", true, false, false, false, false, "Female");
				if (INum <= mchestI)
					LoadSpriteIn(Male_Chest, IColors, INum, colorString, "Chest", true, false, false, false, false, "Male");

				if (INum <= farmsI)
					LoadSpriteIn(Female_Arms, IColors, INum, colorString, "Arms", true, false, false, false, false, "Female");
				if (INum <= marmsI)
					LoadSpriteIn(Male_Arms, IColors, INum, colorString, "Arms", true, false, false, false, false, "Male");

				if (INum <= fshoulderI)
					LoadSpriteIn(Female_Shoulder, IColors, INum, colorString, "Shoulder", true, false, false, false, false, "Female");
				if (INum <= mshoulderI)
					LoadSpriteIn(Male_Shoulder, IColors, INum, colorString, "Shoulder", true, false, false, false, false, "Male");

				if (INum <= mhatI)
					LoadSpriteIn(Male_Hat, IColors, INum, colorString, "Hat", true, false, false, false, false, "Male");
				if (INum <= fhatI)
					LoadSpriteIn(Female_Hat, IColors, INum, colorString, "Hat", true, false, false, false, false, "Female");

				if (INum <= mpantsI)
					LoadSpriteIn(Male_Pants, IColors, INum, colorString, "Pants", true, false, false, false, false, "Male");
				if (INum <= fpantsI)
					LoadSpriteIn(Female_Pants, IColors, INum, colorString, "Pants", true, false, false, false, false, "Female");

				if (INum <= mbootsI)
					LoadSpriteIn(Male_Boots, IColors, INum, colorString, "Boots", true, false, false, false, false, "Male");
				if (INum <= fbootsI)
					LoadSpriteIn(Female_Boots, IColors, INum, colorString, "Boots", true, false, false, false, false, "Female");

				if (INum <= mwaistI)
					LoadSpriteIn(Male_Waist, IColors, INum, colorString, "Waist", true, false, false, false, false, "Male");
				if (INum <= fwaistI)
					LoadSpriteIn(Female_Waist, IColors, INum, colorString, "Waist", true, false, false, false, false, "Female");
				// TODO
				if (INum <= mshirtI)
					LoadSpriteIn(Male_Shirt, IColors, INum, colorString, "Shirt", true, false, false, false, false, "Male");
				if (INum <= fshirtI)
					LoadSpriteIn(Female_Shirt, IColors, INum, colorString, "Shirt", true, false, false, false, false, "Female");

				if (INum <= mlongshirtI)
					LoadSpriteIn(Male_LongShirt, IColors, INum, colorString, "LongShirt", true, false, false, false, false, "Male");
				if (INum <= flongshirtI)
					LoadSpriteIn(Female_LongShirt, IColors, INum, colorString, "LongShirt", true, false, false, false, false, "Female");

			}
		}
	}

	public static boolean	MneedsBody	= true, FneedsBody = true;

	public BufferedImage StackImage()
	{
		int keepgoing = 0;
		src0 = toBufferedImage(SprCre.apl.AllBlank);
		if (SprCre.apl.PickSex.equals("Male"))
		{
			for (int i = Cons.StackFirst; i <= Cons.ObjectValues.length - 1; i++)
			{
				if (ObjectValues[i].equals("Base"))
				{
					if (CheckOb.MHaveBody)
					{
						if (MneedsBody)
							SprCre.mColorObject.setImage(i, ReturnBody(SprCre.cho.MTypeBody, "Male"));
					}
				}
				else if (ObjectValues[i].equals("Ear"))
				{
					if (CheckOb.MHaveBody)
					{
						for (String g : ObjectValues)
						{
							if (g.equals("Ear"))
							{
								if (MneedsBody)
									SprCre.mColorObject.setImage(keepgoing, ReturnEar(SprCre.cho.MTypeBody, "Male"));
								break;
							}
							else
								keepgoing++;
						}
					}
				}
			}
		}
		else if (SprCre.apl.PickSex.equals("Female"))
		{
			for (int i = Cons.StackFirst; i <= Cons.ObjectValues.length - 1; i++)
			{
				if (ObjectValues[i].equals("Base"))
				{
					if (CheckOb.FHaveBody)
					{
						if (FneedsBody)
							SprCre.fColorObject.setImage(i, ReturnBody(SprCre.cho.FTypeBody, "Female"));
					}
				}
				else if (ObjectValues[i].equals("Ear"))
				{
					if (CheckOb.FHaveBody)
					{
						for (String g : ObjectValues)
						{
							if (g.equals("Ear"))
							{
								if (FneedsBody)
									SprCre.fColorObject.setImage(keepgoing, ReturnEar(SprCre.cho.FTypeBody, "Female"));
								break;
							}
							else
								keepgoing++;
						}
					}
				}
			}
		}
		Graphics2D g3 = src0.createGraphics();
		//Boot and pant layer swap
		for (int i = Cons.StackFirst; i <= Cons.ObjectValues.length - 1; i++)
		{
			if (ObjectValues[i].equals("Ear"))
			{
				if (SprCre.apl.PickSex.equals("Male") && CheckOb.MHaveBody)
					g3.drawImage(SprCre.mColorObject.getImage(i), 0, 0, null);
				else if (SprCre.apl.PickSex.equals("Female") && CheckOb.FHaveBody)
					g3.drawImage(SprCre.fColorObject.getImage(i), 0, 0, null);
			}
			else if (ObjectValues[i].equals("Base"))
			{
				if (CheckOb.MHaveBody && SprCre.apl.PickSex.equals("Male"))
					g3.drawImage(SprCre.mColorObject.getImage(i), 0, 0, null);
				else if (CheckOb.FHaveBody && SprCre.apl.PickSex.equals("Female"))
					g3.drawImage(SprCre.fColorObject.getImage(i), 0, 0, null);
			}
			else if (ObjectValues[i].equals("Boots_L1") && (CheckOb.fbootFirst || CheckOb.mbootFirst))
			{
				if (SprCre.apl.PickSex.equals("Male"))
					g3.drawImage(SprCre.mColorObject.getImage(i + 1), 0, 0, null);
				else if (SprCre.apl.PickSex.equals("Female"))
					g3.drawImage(SprCre.fColorObject.getImage(i + 1), 0, 0, null);
			}
			else if (ObjectValues[i].equals("Pants_L1") && (CheckOb.fbootFirst || CheckOb.mbootFirst))
			{
				if (SprCre.apl.PickSex.equals("Male"))
					g3.drawImage(SprCre.mColorObject.getImage(i - 1), 0, 0, null);
				else if (SprCre.apl.PickSex.equals("Female"))
					g3.drawImage(SprCre.fColorObject.getImage(i - 1), 0, 0, null);
			}
			else
			{
				if (SprCre.apl.PickSex.equals("Male"))
					g3.drawImage(SprCre.mColorObject.getImage(i), 0, 0, null);
				else if (SprCre.apl.PickSex.equals("Female"))
					g3.drawImage(SprCre.fColorObject.getImage(i), 0, 0, null);
			}
		}
		if (SprCre.apl.PickSex.equals("Male") && CheckOb.MisGhost || SprCre.apl.PickSex.equals("Female") && CheckOb.FisGhost)
			return Ghostify(src0);

		return src0;
	}

	public static float	ba	= 50;
	public static int	ba2	= 0;

	public BufferedImage SaveASXP()
	{
		BufferedImage dest = new BufferedImage(96, 192, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(ImgHandler.AllSplit, -32, 0, null);
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
				if (SprCre.apl.PickSex.equals("Male"))
				{
					if (MhasEars)
						return toBufferedImage(Mbase[0][inc]);
					else
						return toBufferedImage(Mbase[1][inc]);
				}
				if (SprCre.apl.PickSex.equals("Female"))
				{
					if (FhasEars)
						return toBufferedImage(Fbase[0][inc]);
					else
						return toBufferedImage(Fbase[1][inc]);
				}
			}
			inc++;
		}
		return null;
	}

	public BufferedImage ReturnEar(String Body, String sex)
	{
		int inc = 0;
		for (String s : cbody)
		{
			if (Body.equals(s))
			{
				if (SprCre.apl.PickSex.equals("Male"))
				{
					if (MhasEars)
						return toBufferedImage(Mbase[2][inc]);
					else
						return toBufferedImage(Mbase[3][inc]);
				}
				if (SprCre.apl.PickSex.equals("Female"))
				{
					if (FhasEars)
						return toBufferedImage(Fbase[2][inc]);
					else
						return toBufferedImage(Fbase[3][inc]);
				}
			}
			inc++;
		}
		return null;
	}

	public BufferedImage toBufferedImage(Image src)
	{
		BufferedImage dest = new BufferedImage(Cons.TileW, Cons.TileH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}

	public BufferedImage Ghostify(BufferedImage bi)
	{
		BufferedImage dest = new BufferedImage(Cons.TileW, Cons.TileH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		if (SprCre.apl.PickSex.equals("Male"))
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, SprCre.cho.Mopacity));
		if (SprCre.apl.PickSex.equals("Female"))
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, SprCre.cho.Fopacity));
		g2.drawImage(bi, 0, 0, null);
		g2.dispose();
		return dest;
	}

	public BufferedImage[] MakeSmaller(Image src)
	{
		BufferedImage[] dest = new BufferedImage[Cons.ImageFrames + 1];
		Graphics2D[] g2 = new Graphics2D[Cons.ImageFrames + 1];
		for (int i = 1; i <= Cons.ImageFrames; i++)
		{
			dest[i] = new BufferedImage(Cons.ImageW, Cons.ImageH, BufferedImage.TYPE_INT_ARGB);
			g2[i] = dest[i].createGraphics();
		}

		for (int x = 0, i = 1; x >= Cons.makeSmallerX; x -= Cons.ImageW)
			for (int y = 0; y >= Cons.makeSmallerY; y -= Cons.ImageH, i++)
				g2[i].drawImage(src, x, y, null);

		for (int i = 1; i <= Cons.ImageFrames; i++)
			g2[i].dispose();
		return dest;
	}

	private URL getURL(String filename)
	{
		URL url = null;
		url = ClassLoader.getSystemResource(filename);
		return url;
	}

	public Image load(String filename) {
		return Toolkit.getDefaultToolkit().getImage(getURL(filename));
	}

}
