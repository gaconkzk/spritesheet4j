package circle.handlers;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import circle.Cons;
import circle.SprCre;


public class CheckOb
{	
	public static boolean MisGhost = false, FisGhost = false;
	public static boolean MHaveBody = true, FHaveBody = true;
	public static boolean mbootFirst = false, fbootFirst = false;
	
	private boolean ClickChecked = false;
	private boolean HasBeenClicked = false;
	
	public static int layerstack = 0;
	public static int obcountstack = 0;
	
	public int obSelected = 2;
	public int FobSelected = 2, MobSelected = 2;
	
	private int M_x, M_y, PressedX, PressedY;
	
	public final int[] mfCFG = dogetincs(Cons.CFGlist);
	
	public float Fopacity = 0.5f, Mopacity = 0.5f;
	
	public String FTypeBody = "White";
	public String MTypeBody = "White", ObjectSelect = Cons.ObjectsUseable[0], MObjectSelect = Cons.ObjectsUseable[0], FObjectSelect = Cons.ObjectsUseable[0];
	
	public static String[] Ftype = new String[20];
	public static String[] Mtype = new String[20];
	
	public CheckOb()
	{
		SprCre.apl.addMouseListener(new MouseAdapter()
		{
			@Override public void mousePressed(MouseEvent e)
			{
				HasBeenClicked = true;
				M_x = MouseInfo.getPointerInfo().getLocation().x - SprCre.apl.getLocationOnScreen().x;
				M_y = MouseInfo.getPointerInfo().getLocation().y - SprCre.apl.getLocationOnScreen().y;
			}
			@Override public void mouseReleased(MouseEvent e)
			{
				HasBeenClicked = false;
				ClickChecked = false;
			}
		});
	}
	private int adj(int I) { return I / 2 * Cons.ImageH; }
	public int[] dogetincs(String[] g)
	{
		int[] s = new int[g.length];
		for (int i = 0; i < g.length; i++) 
			s[i] = SprCre.io.CFGi++;
		SprCre.io.CFGi = 0;
		return s;
	}
	public int addone(int i)
	{
		float ii = i;
		if (ii / 2 == i / 2)
			return 0;
		else
			return Cons.ImageH;
	}
	public int findadj(String g, String sex)
	{
		int i = 0;
		for (String n : Cons.CFGlist)
		{
			if (sex.equals("Male") && n.equals("m" + g) || sex.equals("Female") && n.equals("f" + g))
				return i;
			i++;
		}	
		return 0;
	}
	public int findadj(String g)
	{
		int i = 0;
		for (String n : Cons.CFGlist)
		{
			if (SprCre.apl.PickSex.equals("Male") && n.equals("m" + g) || SprCre.apl.PickSex.equals("Female") && n.equals("f" + g))
				return SprCre.io.cfg[i];
			i++;
		}	
		return 0;
	}
	public void ReloadAll(boolean b, String SexToReload)
	{
		if (SexToReload.equals("Male") || b)
		{
			for (int i = Cons.StackFirst; i <= Cons.ObjectValues.length - 1; i++)
				SprCre.mColorObject.setImage(i, SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
			for (int i = 0; i <= Cons.ObjectsUseable.length - 1; i++)
				Mtype[i] = "00_";
			MTypeBody = "White";
			if (SprCre.apl.PickSex.equals("Male"))
				ObjectSelect = Cons.ObjectsUseable[0];
			MObjectSelect = Cons.ObjectsUseable[0];
			MHaveBody = true;
			MisGhost = false;
			mbootFirst = false;
			Mopacity = 0.5f;
		}
		if (SexToReload.equals("Female") || b)
		{
			for (int i = Cons.StackFirst; i <= Cons.ObjectValues.length - 1; i++)
				SprCre.fColorObject.setImage(i, SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
			for (int i = 0; i <= Cons.ObjectsUseable.length - 1; i++)
				Ftype[i] = "00_";
			FTypeBody = "White";
			if (SprCre.apl.PickSex.equals("Female"))
				ObjectSelect = Cons.ObjectsUseable[0];
			FObjectSelect = Cons.ObjectsUseable[0];
			FHaveBody = true;
			FisGhost = false;
			fbootFirst = false;
			Fopacity = 0.5f;
		}
		if (b && SexToReload.equals(""))
			SprCre.apl.PickSex = "Male";
		ImgHandler.AllSplit = SprCre.IH.StackImage();
		ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
	}
	private void parts(String obs, int x, int y, Image[][][] ii, int os, String s)
	{
		int img = 0;
		if (SprCre.apl.PickSex.equals("Male"))
		{
			for (int i = 1; i <= 3; i++)
			{
				if (getLayer(obs, i) > -1)
				{
					img = getLayer(obs, i);
					SprCre.mColorObject.setImage(img, SprCre.IH.toBufferedImage(ii[x][y][i - 1]));
					ImgHandler.AllSplit = SprCre.IH.StackImage();
					ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
				}
			}
			Mtype[os] = s;
			SprCre.mColorObject.setneedStack(img, true);
		}
		if (SprCre.apl.PickSex.equals("Female"))
		{
			for (int i = 1; i <= 3; i++)
			{
				if (getLayer(obs, i) > - 1)
				{
					img = getLayer(obs, i);
					SprCre.fColorObject.setImage(img, SprCre.IH.toBufferedImage(ii[x][y][i - 1]));
					ImgHandler.AllSplit = SprCre.IH.StackImage();
					ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
				}
			}
			Ftype[os] = s;
			SprCre.fColorObject.setneedStack(img, true);
		}
	}
	private void CheckObject(String obs, Image[][][] i)
	{
		String s;
		if (ObjectSelect.equals(obs))
		{
			if (M_x > SprCre.apl.PartSelectWindowX && M_x < SprCre.apl.PartSelectWindowX + Cons.drawAcross)
			{
				if (M_y > SprCre.apl.PartSelectWindowY && M_y < SprCre.apl.PartSelectWindowY + adj(findadj(obs)))
				{
					PressedX = ((M_x - SprCre.apl.PartSelectWindowX) / Cons.ImageW) + 1;
					PressedY = ((M_y - SprCre.apl.PartSelectWindowY) / Cons.ImageH) + 1;
					s = Integer.toString(PressedX) + Integer.toString(PressedY) + "_";
					parts(obs, PressedX, PressedY, i, obSelected, s);
				}
			}
			if (M_x > SprCre.apl.PartSelectWindowX + Cons.drawAcross && M_x < SprCre.apl.PartSelectWindowX + Cons.totalAcross)
			{
				if (M_y > SprCre.apl.PartSelectWindowY && M_y < SprCre.apl.PartSelectWindowY + adj(findadj(obs)) + addone(findadj(obs)))
				{
					PressedX = ((M_x - SprCre.apl.PartSelectWindowX - Cons.drawAcross) / Cons.ImageW) + 1;
					PressedY = ((M_y - SprCre.apl.PartSelectWindowY) / Cons.ImageH) + (adj(findadj(obs)) / Cons.ImageH) + 1;
					s = Integer.toString(PressedX) + Integer.toString(PressedY + (adj(findadj(obs)) + addone(findadj(obs)) / Cons.ImageH)) + "_";
					parts(obs, PressedX, PressedY, i, obSelected, s);
				}
			}
		}
	}
	public int retlayerStack() { return layerstack; }
	public static int[] doLayerStack()
	{
		int i = 0;
		int[] r = new int[Cons.ObjectValues.length];
		for (int j = 0; j < Cons.ObjectValues.length; j++)
			r[i++] = layerstack++;
		return r;
	}
	public void makeBlank(String obs)
	{
		if (ObjectSelect.equals(obs))
		{
			if (SprCre.apl.PickSex.equals("Male"))
			{
				if (getLayer(obs,1) != -1)
					SprCre.mColorObject.setImage(getLayer(obs, 1), SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
				if (getLayer(obs, 2) != -1)
					SprCre.mColorObject.setImage(getLayer(obs, 2), SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
				if (getLayer(obs, 3) != -1)
					SprCre.mColorObject.setImage(getLayer(obs, 3), SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
				if (obSelected != -1)
					Mtype[MobSelected] = "00_";
			}
			else
			{
				if (getLayer(obs,1) != -1)
					SprCre.fColorObject.setImage(getLayer(obs, 1), SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
				if (getLayer(obs, 2) != -1)
					SprCre.fColorObject.setImage(getLayer(obs, 2), SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
				if (getLayer(obs, 3) != -1)
					SprCre.fColorObject.setImage(getLayer(obs, 3), SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
				if (obSelected != -1)
					Ftype[FobSelected] = "00_";
			}
			ImgHandler.AllSplit = SprCre.IH.StackImage();
			ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
		}
	}
	private int getLayer(String ob, int l)
	{
		int keepPlace = 0;
		for (String s : Cons.ObjectValues)
			if ((ob + "_L" + Integer.toString(l)).equals(s))
				return Cons.ObjectLayers[keepPlace];
			else
				keepPlace++;
		return -1;
	}
	private Image[][][] ChooseImage(Image[][][] m, Image[][][] f)
	{
		if (SprCre.apl.PickSex.equals("Male"))
			return m;
		else
			return f;
	}

	public void CheckTheMouse()
	{
		if (HasBeenClicked && !ClickChecked)
		{
			if (M_x > SprCre.apl.PartSelectWindowX && M_x < SprCre.apl.PartSelectWindowX + Cons.ImageW)
			{
				if (M_x > SprCre.apl.PartSelectWindowY - Cons.ImageH && M_y < SprCre.apl.PartSelectWindowY)
				{
					for (String g : Cons.ObjectsUseable)
						makeBlank(g);
				}
			}
			//TODO
			/*
			 * If you added a category you must also add it here
			 * */
			CheckObject("Chest", ChooseImage(SprCre.IH.Male_Chest, SprCre.IH.Female_Chest));
			CheckObject("Hair", ChooseImage(SprCre.IH.Male_Hair, SprCre.IH.Female_Hair));
			CheckObject("Shoulder", ChooseImage(SprCre.IH.Male_Shoulder, SprCre.IH.Female_Shoulder));
			CheckObject("Pants", ChooseImage(SprCre.IH.Male_Pants, SprCre.IH.Female_Pants));
			CheckObject("Boots", ChooseImage(SprCre.IH.Male_Boots, SprCre.IH.Female_Boots));
			CheckObject("Arms", ChooseImage(SprCre.IH.Male_Arms, SprCre.IH.Female_Arms));
			CheckObject("Waist", ChooseImage(SprCre.IH.Male_Waist, SprCre.IH.Female_Waist));
			CheckObject("Hat", ChooseImage(SprCre.IH.Male_Hat, SprCre.IH.Female_Hat));
			CheckObject("Shirt", ChooseImage(SprCre.IH.Male_Shirt, SprCre.IH.Female_Shirt));
			CheckObject("LongShirt", ChooseImage(SprCre.IH.Male_LongShirt, SprCre.IH.Female_LongShirt));
			ClickChecked = true;
		}
	}
}
