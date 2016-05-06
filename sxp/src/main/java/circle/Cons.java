package circle;

import circle.handlers.CheckOb;
import circle.handlers.ImgHandler;



public interface Cons
{
	/*
	 * Layer names. _L1 denotes layer 1...ect for 2. The order here is very important. 
	 * The order here is the stacking order. Ear is the first layer, Hat_L1 is the last layer insert your
	 * layer according to where you want it in the order.
	 * */
	public static String[]	ObjectValues	= { "Ear", "Hair_L2", "Base", "Boots_L1", "Pants_L1", "Shirt_L1", "Chest_L1", "LongShirt_L1",
											"Arms_L1", "Waist_L1", "Shoulder_L1", "Hair_L1", "Hat_L1" };

	/*
	 * This is the categories. The actual buttons that you press. This section is automated and you only need to insert the Sring
	 * you would like to call your button.
	 * */
	public static String[]	ObjectsUseable	= { "Chest", "Shirt", "LongShirt", "Arms", "Shoulder", "Waist", "Pants", "Boots", "Hair", "Hat" };

	/*
	 * The order here is also just as important. This order must coincide with ObjectValues above. ObjectOrder and ObjectValues should
	 * look nearly identical. Difference is secondary layers are not included in the list. This list is used by the Colorizer. Multi-layered support
	 * it done within the colorizer.
	 * */
	public static String[]	ObjectOrder		= { "Ear", "Hair", "Base", "Boots", "Pants", "Shirt", "Chest", "LongShirt",
											"Arms", "Waist", "Shoulder", "Hair", "Hat" };

	/*
	 * Changing anything below could render the program useless.
	 * These variables control what and how much of each image it's size, color and where to display
	 * */
	//CheckOb.doLayerStack(); required for initial display of image base.
	public int[]			ObjectLayers	= CheckOb.doLayerStack();
	public final int		Obcount			= ObjectsUseable.length - 1;

	public final int		StackFirst		= 0;

	//each image frame size
	public final int		ImageW			= 32;
	public final int		ImageH			= 48;
	//main image grid 4x4 XP 3x4 VX
	public final int		ImageFramesX	= 4;
	public final int		ImageFramesY	= 4;

	public final int		TileW			= ImageW * ImageFramesX;
	public final int		TileH			= ImageH * ImageFramesY;

	public final int		ImageFrames		= ImageFramesX * ImageFramesY;

	public final int		makeSmallerX	= -TileW + ImageW;																																//-96
	public final int		makeSmallerY	= -TileH + ImageH;																																//-144;

	public final int		usedColors		= ImgHandler.colorArray.length - 1;
	public final int		ImageBases		= ImgHandler.cbody.length;
	public final int		drawAcross		= usedColors * ImageW;
	public final int		totalAcross		= drawAcross * 2;																																//2 Left and Right Selection window

	public final int		maxI			= 18;																																			//max number of objects that can fit in a window. 9 on each side.
	//This controls the max amount of objects allowed in each category

	//Names for Config.cfg
	//the order here is only important if the original config file gets deleted. Otherwise this is never needed or used
	public final String[]	cfglist			= {
											"#Female Hair",
											"#Male Hair",
											"#Male Chest",
											"#Female Chest",
											"#Male Shoulder",
											"#Female Shoulder",
											"#Male Arms",
											"#Female Arms",
											"#Male Pants",
											"#Female Pants",
											"#Male Waist",
											"#Female Waist",
											"#Male Boots",
											"#Female Boots",
											"#Male Hat",
											"#Female Hat",
											"#Male Shirt",
											"#Female Shirt",
											"#Male Long Shirt",
											"#Female Long Shirt" };
	//Config list order
	//This list requires to be identical to cfglist replacing Female with f and Male with m
	public final String[]	CFGlist			= { "fHair", "mHair", "mChest", "fChest", "mShoulder", "fShoulder",
											"mArms", "fArms", "mPants", "fPants", "mWaist", "fWaist", "mBoots", "fBoots", "mHat", "fHat", "mShirt", "fShirt", "mLongShirt", "fLongShirt" };
}
