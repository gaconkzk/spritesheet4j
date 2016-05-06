package circle;
public interface Cons
{
	/*Created this interface for easier readability in main program
	 * The numbers assigned to the variable represent the order of which
	 * each item is drawn
	 * L2 stands for Layer 2
	 * L1 stands for Layer 1
	 * There are 17 layers of each sprite image. Changing the numbers around
	 * will get a different result. Technically I could remove the final variable
	 * and make the layers adjustable. Or make more layers and add the ablity to
	 * use more than just one item from each selection
	 * Could add Checkboxes for controlling layer sets...(Interesting...I think I might do this next)
	 * 
	 * The variables marked _L1 _L2 and the numbers associated to them are the drawing order.
	 * Changing the numbers changes the drawing order. 
	 */
	
	//A little organization as I search by name as well
	//Javadocs here help with that being able to quickly
	//see where each option is by finding one.
	public static String[] ObjectValues = {"Option_L1", "Hairop_L1", "Hair_L1", "Mantle_L1", "Tail_L2", "Ears_L2", "Body", "Face_L1", 
		"Tail_L1", "Ears_L3", "Acce1_L2", "Mantle_L2", "Hair_L2", "Acce2_L1", "Acce1_L1", "Bangs_L1", "Ears_L1", "Tail_L3", "Hairop_L2", "Option_L2", "Armore_L1"};
	public static final int Option_L1 = 2;//Option
	public static final int Hairop_L1 = 3;//		Hairop
	public static final int Hair_L1 = 4;//					Hair
	public static final int Mantle_L1 = 5;//						Mantle
	public static final int Tail_L2 = 6;//									Tail
	public static final int Ears_L2 = 7;//											Ears
	public static final int Body_L1 = 8;//													
	public static final int Face_L1 = 9;//													Face
	public static final int Tail_L1 = 10;//									Tail
	public static final int Ears_L3 = 11;//											Ears
	public static final int Acce1_L2 = 12;//														Acce1
	public static final int Mantle_L2 = 13;//						Mantle
	public static final int Hair_L2 = 14;//					Hair
	public static final int Acce2_L1 = 15;//																Acce2
	public static final int Acce1_L1 = 16;//														Acce1
	public static final int Bangs_L1 = 17;//																		Bangs
	public static final int Ears_L1 = 18;//											Ears
	public static final int Hairop_L2 = 19;//		Hairop
	public static final int Option_L2 = 20;//Option
	public static final int Armor_L1 = 21;//																				Armor
	
	public static final int Option = 0;
	public static final int Acce2 = 1;
	public static final int Acce1 = 2;
	public static final int Mantle = 3;
	public static final int Hairop = 4;
	public static final int Body = 5;
	public static final int Base = Body + 2;
	public static final int Face = 6;
	public static final int Hair = 7;
	public static final int Tail = 8;
	public static final int Ears = 9;
	public static final int Bangs = 10;
	public static final int Armor = 11;

	public static final int Obcount = 11;
	/*
	 * This number reflects how many base objects there are
	 * 10 at this current time is due to "Armor" being equal to 10
	 * and it is the last number in line.
	 * */
	
	//How many layers and where to start/end
	public static final int StackFirst = 2;
	public static final int StackLast = 21;
	
	//used by public BufferedImage toBufferedImage(Image src) in ImgHandler
	public static final int ImageW = 32;
	public static final int ImageH = 32;
	
	public static final int TileW = 96;
	public static final int TileH = 128;
	
	public static final int ImageFrames = 12;
	
	public static final int makeSmallerX = -64;
	public static final int makeSmallerY = -96;
	
	/* This one line control ALL the loading of the images
	 * where colors are concerned.
	 * however if you add a color to one part you MUST
	 * add that color to every object part
	 * Also if you increase this you need to edit
	 * the string colorArray in ImgHandler.java
	 * Should be right one the top
	 * */
	public static final int usedColors = 10;
	public static final int ImageBases = 13;
	public static final int drawAcross = usedColors * 32;
	public static final int totalAcross = drawAcross * 2;
	
	public static final int maxI = 26;
	public static final int categories = 16;
	/*
	 * When adding new categories you must add above
	 * */
	
	public static final int fhairCFG = 0;
	public static final int mhairCFG = 1;
	public static final int mbodyCFG = 2;
	public static final int fbodyCFG = 3;
	public static final int mhairopCFG = 4;
	public static final int fhairopCFG = 5;
	public static final int mbangsCFG = 6;
	public static final int fbangsCFG = 7;
	public static final int mantleCFG = 8;
	public static final int facce1CFG = 9;
	public static final int macce1CFG = 10;
	public static final int acce2CFG = 11;
	public static final int tailCFG = 12;
	public static final int earsCFG = 13;
	public static final int optionCFG = 14;
	public static final int faceCFG = 15;
	public static final int armorCFG = 16;
	/*
	 * When adding new categories you must add above
	 * */
}
