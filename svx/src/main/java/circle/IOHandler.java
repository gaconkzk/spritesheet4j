package circle;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class IOHandler implements Cons
{
	protected int[] cfg;
	public static int[][] overridesCFG = new int[categories + 1][maxI + 1];
	private static String arrayed;
	public static boolean SaveBoth = false;
	public static boolean normalSave = true;
	/*
	 * When adding new categories you must add above
	 * increase to add decrease to remove
	 * */
	/*
	 * Next two strings are used for creating the config file if it gets deleted
	 * */
	protected String[] cfgtext = {
			"############### These lines are ignored by the loader due to having # at the start of the line",
			"############### This config file is for those who want to add custom images to Sprite Creator 3",
			"############### Blank lines are ignored as well"
			};
	protected String[] cfglist = {
			"#Female Hair",
			"#Male Hair",
			"#Male Body",
			"#Female Body",
			"#Male Hairop",
			"#Female Hairop",
			"#Male Bangs",
			"#Female Bangs",
			"#Mantle",
			"#Female Acce1",
			"#Male Acce1",
			"#Acce2",
			"#Tail",
			"#Ears", 
			"#Option",
			"#Face",
			"#Armor"};
	/*
	 * overrides are used for creating a config if it is deleted or missing
	 * */
	protected String[] overrides = { "-o", "10", "-" };
	protected String[] overrides2 = { "-o", "2", "-" };
	public IOHandler(String CtL)
	{
		populateuse();
		LoadCFG(CtL);
	}
	public int[] getCFG() { return this.cfg; }
	public int getCFG(int i) { return this.cfg[i]; }
	
	private void populateuse()
	{
		for (int x = 0; x <= categories; x++)
			for (int y = 0; y <= maxI ; y++)
				overridesCFG[x][y] = -1;
	}
	private void populateDefault()
	{
		//Values from Cons
		cfg[0] = 22;
		cfg[1] = 22;
		cfg[2] = 22;
		cfg[3] = 22;
		cfg[4] = 4;
		cfg[5] = 12;
		cfg[6] = 16;
		cfg[7] = 16;
		cfg[8] = 15;
		cfg[9] = 10;
		cfg[10] = 10;
		cfg[11] = 23;
		cfg[12] = 5;
		cfg[13] = 5;
		cfg[14] = 4;
		cfg[15] = 5;
		cfg[16] = 2;
	}
	/*
	 * If the config file is missing or deleted for some reason
	 * Sprite Creator 3 will create the default Config file for you
	 * */
	private void makeFile()
	{
		BufferedWriter writer = null;
		try {
			File logFile = new File("Config.cfg");
			populateDefault();
			writer = new BufferedWriter(new BufferedWriter(new FileWriter(
					logFile)));
			for (int i = 0; i <= categories; i++) 
			{
				/*
				 * commented code in config
				 * */
				if (i == 0)
					for (int k = 0; k <= 2; k++)
					{
						writer.write(cfgtext[k]);
						writer.write('\n');
					}
				/*
				 * Commented code in config
				 * */
				writer.write(cfglist[i]);
				writer.write('\n');
				/*
				 * Create the override for Female Acce1
				 * */
				if (i == 9)
					for (int k = 0; k < overrides.length; k++)
					{
						writer.write(overrides[k]);
						writer.write('\n');
					}
				if (i == 12)
					for (int k = 0; k < overrides2.length; k++)
					{
						writer.write(overrides2[k]);
						writer.write('\n');
					}
				/*
				 * Here are the actually used values in the config
				 * */
				writer.write(Integer.toString(cfg[i]));
				for (int k = 0; k <= 1; k++)
					writer.write('\n');
			}
		} catch (IOException e){
			
		} finally {
			try {
				writer.close();
			} catch (Exception EX3) {}
		}
	}
	private void LoadCFG(String ConfigToLoad)
	{
		File fileCFG = new File(ConfigToLoad);
		BufferedReader reader = null;
		boolean stillneed = false;
		cfg = new int[categories + 1];
		int cfginc = 0;
		int count = 0;
		int arrayc = 0;
		try {
		    reader = new BufferedReader(new FileReader(fileCFG));
		    String text = null;
		    while ((text = reader.readLine()) != null)
		    {
		    	if (text.trim().length() != 0)
		    	{
		    		if (text.replace(" ", "").charAt(0) != '#' && text.replace(" ", "").charAt(0) != '-')
		    		{
		    			cfg[cfginc++] = Integer.parseInt(text.replace(" ",""));
		    			count++;
		    		}
		    		else if (text.replace(" ", "").charAt(0) == '-')
		    		{
		    			//THIS MEANS OVERRIDE
		    			if (text.replace(" ", "").charAt(1) == 'o')
		    			{
		    				stillneed = true;
		    				arrayc = 0;
		    				/*
		    				 * Here we will make the reader check which object is being loaded
		    				 * and then it will create an array until -o is no longer used
		    				 * */
		    				while(stillneed && (text = reader.readLine()) != null)
		    				{
		    					if (text.trim().length() != 0)
		    					{
		    						if (text.replace(" ", "").charAt(0) != '#' && text.replace(" ", "").charAt(0) != '-')
		    						{
		    							if (arrayc <= maxI)
		    								overridesCFG[count][arrayc++] = Integer.parseInt(text.replace(" ", ""));
		    						}
		    						else if (text.replace(" ", "").charAt(0) != '#')
		    							stillneed = false;
		    					}
		    				}
		    			}
		    		}
		    	}
		    }
		} catch (FileNotFoundException e) {
			//makeFile();
		} catch (IOException e) {
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}
		if (count != categories + 1)
		{
			populateDefault();
			makeFile();
		}
	}
	/*
	 * Saving and naming the files. Controls if one or both are saved at a time
	 * */
	public void save(boolean d, String SexToSave)
	{
		/*
		 * All were doing here is naming the image and depending on certain options
		 * naming folders as well
		 * */
		SaveBoth = d;
		String SexHold = new String();
		SexHold = SprCre.PickSex;
		if ((SexToSave.equals("Male") || SaveBoth))
		{
			SprCre.PickSex = "Male";
			SprCre.fileName = "Saved/";
			if (SprCre.MisGhost)
				SprCre.fileName = SprCre.fileName + "Ghost_";
			if (SprCre.MisGhost && !SprCre.MHaveBody)
				SprCre.fileName = SprCre.fileName + "_";
			if (!SprCre.MHaveBody)
				SprCre.fileName = SprCre.fileName + "NoBody";
			else
				SprCre.fileName = SprCre.fileName + SprCre.MTypeBody;
			SprCre.fileName = SprCre.fileName + "_" + SprCre.PickSex + "_" + returnType(Obcount, SprCre.Mtype) + (int)(SprCre.Mopacity * 100);
			SavePoint(SprCre.fileName);
		}
		if ((!SexToSave.equals("Male") || SaveBoth) && !normalSave)
			SavePoint(SprCre.fileName);
		if ((SexToSave.equals("Female") || SaveBoth))
		{
			SprCre.PickSex = "Female";
			SprCre.fileName = "Saved/";
			if (SprCre.FisGhost)
				SprCre.fileName = SprCre.fileName + "Ghost_";
			if (SprCre.FisGhost && !SprCre.FHaveBody)
				SprCre.fileName = SprCre.fileName + "_";
			if (!SprCre.FHaveBody)
				SprCre.fileName = SprCre.fileName + "NoBody";
			else
				SprCre.fileName = SprCre.fileName + SprCre.FTypeBody;
			SprCre.fileName = SprCre.fileName + "_" + SprCre.PickSex + "_" + returnType(Obcount, SprCre.Ftype) + (int)(SprCre.Fopacity * 100);
			SavePoint(SprCre.fileName);
		}
		if ((!SexToSave.equals("Female") || SaveBoth) && !normalSave)
			SavePoint(SprCre.fileName);
		
		SprCre.PickSex = SexHold;
		SaveBoth = false;
	}
	/*
	 * returnType is used for the naming process in the method public void save(String ext, boolean d, String SexToSave)
	 * */
	private String returnType(int t, String[] s)
	{
		arrayed = s[t];
		if (t == 0)
			return " " + arrayed;
		else
			if (arrayed != null)
				return arrayed + returnType(t - 1, s);
			else
				//null is passed once this removes it from the string
				return arrayed.replace("null", "") + returnType(t - 1, s);
	}
	/*
	 * Checks if the saved folder exists
	 * if not create it so the program 
	 * can save normally
	 * */
	private void SavePoint(String fileName)
	{
		//Get the folder Saved ready
		File folder = new File("Saved");
		//does Saved exist in the directory?
		if (!folder.exists())
			//if not lets make it
			if (!folder.mkdir())
				//if we can't make it display an error
				JOptionPane.showMessageDialog(SprCre.apl, "Cannot create folder \"Saved\". Create the folder yourself to save.",
						"Error in SavePoint(String ext, String fileName)", JOptionPane.ERROR_MESSAGE);
		try{
			//Once the folder is created lets save the image
			//This is the default way of saving the compiled image
			if (!SprCre.SaveAsXP)
			{
				fileName = fileName + "_VX";
				ImageIO.write(SprCre.IH.StackImage(), "png", new File(fileName + "." + "png"));
			}
			else
			{
				fileName = fileName + "_XP";
				ImageIO.write(SprCre.IH.SaveASVX(), "png", new File(fileName + "." + "png"));
			}
			
			
			//This is how to save each layer. Skipping layers that are not used.
			
			if (!normalSave)
			{
				//Creating the folder with the same name as the Compiled image that is saved
				//This makes it easier to see which parts go to which character
				File folderunc = new File(fileName);
				
				if (!folderunc.exists())
					if (!folderunc.mkdir())
						JOptionPane.showMessageDialog(SprCre.apl, "Cannot create folder for uncompiled images.", "Error in SavePoint", JOptionPane.ERROR_MESSAGE);
				for (int k = 2; k <= Cons.StackLast; k++)
				{
					if (SprCre.PickSex.equals("Male") && !Cons.ObjectValues[k - 2].equals("Tail_L3"))
					{
						//SprCre.Mimg contains every layer in an array. Layers that have not been used or were
						//cleared are cleared with SprCre.AllBlank
						//That is why seen here I am testing to see that Mimg[k] != SprCre.AllBlank
						if (SprCre.Mimg[k] != SprCre.AllBlank)
						{
							if (!SprCre.MisGhost)
								ImageIO.write(SprCre.IH.toBufferedImage(SprCre.Mimg[k]), "png" , new File(fileName + "/" + "10" + k + Cons.ObjectValues[k - 2] + "." + "png"));
							else
								ImageIO.write(SprCre.IH.Ghostify(SprCre.IH.toBufferedImage(SprCre.Mimg[k])), "png" , new File(fileName + "/" + "10" + k + Cons.ObjectValues[k - 2] + "." + "png"));
						}
					}
					if (SprCre.PickSex.equals("Female") && !Cons.ObjectValues[k - 2].equals("Tail_L3"))
						if (SprCre.Fimg[k] != SprCre.AllBlank)
						{
							if (!SprCre.FisGhost)
								ImageIO.write(SprCre.IH.toBufferedImage(SprCre.Fimg[k]), "png" , new File(fileName + "/" + "10" + k + Cons.ObjectValues[k - 2] + "." + "png"));
							else
								ImageIO.write(SprCre.IH.Ghostify(SprCre.IH.toBufferedImage(SprCre.Fimg[k])), "png" , new File(fileName + "/" + "10" + k + Cons.ObjectValues[k - 2] + "." + "png"));
						}
				}
				//This is just for saving the Base body. The base body is handled with a different process
				if (SprCre.PickSex.equals("Male"))
				{
					if (!SprCre.MisGhost)
						ImageIO.write(SprCre.IH.ReturnBody(SprCre.MTypeBody, "Male"), "png" , new File (fileName + "/" + "107_Base.png"));
					else
						ImageIO.write(SprCre.IH.Ghostify(SprCre.IH.ReturnBody(SprCre.MTypeBody, "Male")), "png" , new File (fileName + "/" + "107_Base.png"));
				}
				else
				{
					if (!SprCre.FisGhost)
						ImageIO.write(SprCre.IH.ReturnBody(SprCre.FTypeBody, "Female"), "png" , new File (fileName + "/" + "107_Base.png"));
					else
						ImageIO.write(SprCre.IH.Ghostify(SprCre.IH.ReturnBody(SprCre.FTypeBody, "Female")), "png" , new File (fileName + "/" + "107_Base.png"));
				}
				
			}
		}catch (IOException e){
			/*
			 * if something goes wrong error :)
			 * This should never execute.
			 * */
			JOptionPane.showMessageDialog(SprCre.apl, "Unable to save image(s). This error is usually due to the folder not having been created.",
					"Error in SavePoint(String ext, String fileName)", JOptionPane.ERROR_MESSAGE);
		}
	}
}
