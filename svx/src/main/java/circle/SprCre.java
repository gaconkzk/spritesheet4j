package circle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.ToolTipManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//
public class SprCre extends JPanel implements Runnable, MouseListener, Cons
{
	private static final long serialVersionUID = 1L;
	/*
	 * The build number. I added this incase people had problems later
	 * I could then ask their build number and if it is the latest
	 * update I can check out the problem
	 * otherwise I can suggest they download the newest version and
	 * see if their problems go away
	 * */
	private String BuildNumber = "facebook.com/SpriteCreator3";
	/*
	 * Get the thread ready for creation
	 * */
	private volatile static Thread th;
	/*
	 * Main frame for the program
	 * */
	public static JFrame frame;
	/*
	 * This is for placing the part selection window
	 * */
	public static final int PartSelectWindowX = 255, PartSelectWindowY = 82;
	/*
	 * This line below you dont change instead change the line above
	 * (which should be "private int PartSelectWindowX = 255, PartSelectWindowY = 82;")
	 * if not then that is the line you should change instead of this one
	 * */
	private final int PartButtonX = PartSelectWindowX;
	private final int PartButtonY = PartSelectWindowY - 72;
	private final int PartButtonSizeX = 106, PartButtonSizeY = 20;
	//This controls where the colorButtons are drawn onto the screen
	public static final int ColorButtonX = 5, ColorButtonY = 32;
	/*
	 * ColorButtonSize changes the font size as well as the button size
	 * it also assists in the drawing of the line under the text
	 * */
	public static final int ColorButtonSize = 27, SingleImageSize = 32;
	public static int tempColorButtonSize = ColorButtonSize;
	/*
	 * For drawing the line It keeps the line the exact size of the array of buttons
	 * and the Choose Skin Color text
	 * */
	private final int ColorButtonLineMaxX = (ColorButtonSize * 8);
	//This is really unneeded but is kept incase I want to move the applet window around
	private final int AppletLocationX = 0, AppletLocationY = 0;
	/*
	 * Change only AppletSize* (* = x or y)
	 * This will keep the frame and applet the exact size needed
	 * */
	private final static int AppletSizeX = 911, AppletSizeY = 502; 
	private final static int FrameSizeX = AppletSizeX + 2;
	private final static int FrameSizeY = AppletSizeY + 50;
	/*
	 * Location of the Sprite Creator 3 Splash screen
	 * */
	private final int BackgroundX = 20, BackgroundY = AppletSizeY - 45;
	/*
	 * Location of the OPSlider
	 * Do not change this unless you want to unalign the ColorButtons with the OpacitySlider
	 * */
	private final int OPSliderX = ColorButtonX + 60, OPSliderY = ColorButtonY + 345;
	/*
	 * Location of the arrow buttons
	 * */
	private final int arrowsX = OPSliderX + 45, arrowsY = OPSliderY + 50;
	/*
	 * Working with an applet inside a JFrame. This gives us access to the applet
	 * */
	public static SprCre apl = new SprCre();
	public static IOHandler io = new IOHandler("Config.cfg");;
	private static JMenuBar menubar = new JMenuBar();
	
	public static int fhairI = limitObj(io.getCFG(fhairCFG));
	public static int mhairI = limitObj(io.getCFG(mhairCFG));
	public static int mbodyI = limitObj(io.getCFG(mbodyCFG));
	public static int fbodyI = limitObj(io.getCFG(fbodyCFG));
	public static int mhairopI = limitObj(io.getCFG(mhairopCFG));
	public static int fhairopI = limitObj(io.getCFG(fhairopCFG));
	public static int mbangsI = limitObj(io.getCFG(mbangsCFG));
	public static int fbangsI = limitObj(io.getCFG(fbangsCFG));
	public static int mantleI = limitObj(io.getCFG(mantleCFG));
	public static int facce1I = limitObj(io.getCFG(facce1CFG));
	public static int macce1I = limitObj(io.getCFG(macce1CFG));
	public static int acce2I = limitObj(io.getCFG(acce2CFG));
	public static int tailI = limitObj(io.getCFG(tailCFG));
	public static int earsI = limitObj(io.getCFG(earsCFG));
	public static int optionI = limitObj(io.getCFG(optionCFG));
	public static int faceI = limitObj(io.getCFG(faceCFG));
	public static int armorI = limitObj(io.getCFG(armorCFG));
	/*
	 * When adding new categories you must add above
	 * */
	
	public static boolean fiGhost = true;
	public static boolean miGhost = true;
	public static ImgHandler IH = new ImgHandler();
	public static timerHandler TH;
	public static createButtons CB = new createButtons();
	
	/*Can only have 22 objects displayed on the screen
	* as there is no page flipping on objects
	* So we limit how many objects you can use
	* to 22 no matter what the config says
	* */
	private static int limitObj(int limit)
	{
		if (limit > maxI)
			return maxI;
		else
			return limit;
	}
	public static void main(String[] args)
	{
		/*
		 * Start the applet and setup the frame
		 * */
		ImageIO.setUseCache(false);
		apl.setLayout(null);
		apl.start();
		apl.init();
		/*
		 * get the frame ready and set up the menubar
		 * */
		frame.setJMenuBar(menubar);
		frame.setVisible(true);
	}	
	/*
	 * This is used when creating menus I use it to create a blank spot in the menu
	 * */
	public void newSeperator(JMenu menuToSeparate)
	{
		JMenuItem BlankSpot = new JMenuItem();
		BlankSpot.setEnabled(false);
		menuToSeparate.add(BlankSpot);
	}
	/*
	 * This is called at program start.
	 * All images immediately needed are loaded first
	 * */
	public void init()
	{
		//Tooltips on the color JButtons
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(5000);
		String ImageLocation = IH.ImageLocation;
		Back_Ground = IH.load(ImageLocation + "Background/background.png");
		for (int i = 0; i <= ImageBases - 1; i++)
		{
			Mbase[i] = IH.load(ImageLocation + "Male/Base/" + ImgHandler.cbody[i] + ".png");
			Fbase[i] = IH.load(ImageLocation + "Female/Base/" + ImgHandler.cbody[i] + ".png");
		}
		IH.MaleNoneAll = IH.load(ImageLocation + "Male/Base/NoneAll.gif");
		IH.FemaleNoneAll = IH.load(ImageLocation + "Female/Base/NoneAll.gif");
		AllBlank = IH.load(ImageLocation + "BaseBlank/Blank.png");
		ffront = IH.load(ImageLocation + "Background/femalefront.png");
		mfront = IH.load(ImageLocation + "Background/malefront.png");
		mback = IH.load(ImageLocation + "Background/maleback.png");
		fback = IH.load(ImageLocation + "Background/femaleback.png");
		mside = IH.load(ImageLocation + "Background/maleside.png");
		fside = IH.load(ImageLocation + "Background/femaleside.png");
		/*
		 * Reload everything. Makes sure everything is working just fine
		 * and it doesn't hurt to make sure
		 * It also makes sure all values are correctly set to default on
		 * program run
		 * */
		ReloadAll(true, ".");
		addMouseListener(this);
		//I didn't want the applet background to be completely white
		this.setBackground(new Color(250, 250, 250));
		this.setSize(AppletSizeX, AppletSizeY);
		this.setLocation(AppletLocationX, AppletLocationY);
		width = this.getWidth();
		height = this.getHeight();
		firstFont = new Font("TimesRoman", Font.BOLD, ColorButtonSize);
		secondFont = new Font("TimesRoman", Font.BOLD, 15);
		this.setFont(firstFont);
		this.setLayout(null);
		/*
		 * Set up the frame. Some things really are not needed here but
		 * I kept them in just in case I wanted a quick reference on what
		 * I can do
		 * */
		frame = new JFrame("Sprite Creator 3 VX - " + BuildNumber);
		frame.setIconImage(IH.load(ImageLocation + "Background/BodyIcon.png"));
		frame.setLayout(null);
		frame.getContentPane().add(apl);
		frame.setSize(FrameSizeX, FrameSizeY);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(250, 250, 250));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// I want to control how the program closes
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)//Here we control when the [X] is pressed
			{
				int result = JOptionPane.showConfirmDialog(frame, 
						"Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					//timerHandler.displayTimer.stop();
					destroy();
				}
				else System.out.println("No");
			}
		});
		/*
		 * Here we are setting up the entire menubar and all the menu items
		 * */
		menubar.setOpaque(true);
		JMenu HelpMenu = new JMenu("Help");
		JMenuItem AboutWin = new JMenuItem("About");
		AboutWin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(frame, 
						"Sprite Creator 3\n"
						+ "Programmed by Circle.\n"
						+ "\n"
						+ "Jensen_305@yahoo.com\n"
						+ "facebook.com/SpriteCreator3\n"
						+ "\n"
						+ "Special Thanks to:\n"
						+ "EnterBrain\n"
						+ "Famitsu\n"
						+ "Holder\n"
						+ "Axerax\n"
						+ "Ying\n"
						+ "Zac Ray"
						+ "\n"
						+ "build " + BuildNumber, "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		HelpMenu.add(AboutWin);
		JMenu FileMenu = new JMenu("File");
		JMenu Saveone = new JMenu("Save");
		FileMenu.add(Saveone);
		JMenuItem SaveMale = new JMenuItem("Male");
		SaveMale.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				io.save(false, "Male");
			}
		});
		Saveone.add(SaveMale);
		JMenuItem SaveFemale = new JMenuItem("Female");
		SaveFemale.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				io.save(false, "Female");
			}
		});
		Saveone.add(SaveFemale);
		JMenuItem SaveAllItem = new JMenuItem("Save All");
		SaveAllItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				io.save(true, "");
			}
		});
		FileMenu.add(SaveAllItem);
		apl.newSeperator(FileMenu);
		JMenu ReloadMenu = new JMenu("Reload");
		FileMenu.add(ReloadMenu);
		JMenuItem ReloadMale = new JMenuItem("Male");
		ReloadMale.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(frame, "Reload Male?", "Reload Male", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					apl.ReloadAll(false, "Male");
					ImgHandler.doneWithFirstBoard = false;
				}
			}
		});
		ReloadMenu.add(ReloadMale);
		JMenuItem ReloadFemale = new JMenuItem("Female");
		ReloadFemale.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(frame, "Reload Female?", "Reload Female", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					apl.ReloadAll(false, "Female");
					ImgHandler.doneWithFirstBoard = false;
				}
			}
		});
		ReloadMenu.add(ReloadFemale);
		JMenuItem ReloadAllItem = new JMenuItem("Reload All");
		ReloadAllItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(frame, "Reload Male and Female?", "Reload Both?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					apl.ReloadAll(true, "");
					ImgHandler.doneWithFirstBoard = false;
				}
			}
		});
		FileMenu.add(ReloadAllItem);
		apl.newSeperator(FileMenu);
		JMenuItem ExitItem = new JMenuItem("Exit");
		ExitItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					//timerHandler.displayTimer.stop();
					destroy();
				}
			}
		});
		FileMenu.add(ExitItem);
		menubar.setVisible(true);
		menubar.add(FileMenu);
		menubar.add(HelpMenu);
		/*
		 * Create the part buttons using the variables that are at the top
		 * public void PartButtons (JButton butt, int x, int y, int sx, int sy, final String label)
		 * */
		CB.PartButtons(PartButtonX, PartButtonY, PartButtonSizeX, PartButtonSizeY, "Body");
		CB.PartButtons(PartButtonX + (PartButtonSizeX), PartButtonY, PartButtonSizeX, PartButtonSizeY, "Hair");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 2), PartButtonY, PartButtonSizeX, PartButtonSizeY, "Hairop");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 3), PartButtonY, PartButtonSizeX, PartButtonSizeY, "Bangs");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 4), PartButtonY, PartButtonSizeX, PartButtonSizeY, "Acce1");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 5), PartButtonY, PartButtonSizeX, PartButtonSizeY, "Acce2");
		CB.PartButtons(PartButtonX + (PartButtonSizeX) - PartButtonSizeX, PartButtonY + 20, PartButtonSizeX, PartButtonSizeY, "Option");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 2) - PartButtonSizeX, PartButtonY + 20, PartButtonSizeX, PartButtonSizeY, "Tail");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 3) - PartButtonSizeX, PartButtonY + 20, PartButtonSizeX, PartButtonSizeY, "Ears");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 4) - PartButtonSizeX, PartButtonY + 20, PartButtonSizeX, PartButtonSizeY, "Mantle");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 5) - PartButtonSizeX, PartButtonY + 20, PartButtonSizeX, PartButtonSizeY, "Face");
		CB.PartButtons(PartButtonX + (PartButtonSizeX * 6) - PartButtonSizeX, PartButtonY + 20, PartButtonSizeX, PartButtonSizeY, "Armor");
		/*
		 * When adding new categories you must add above
		 * */
		/*
		 * Setup the Color Changing Buttons
		 * private void addColorJButton(JButton butt, int x, int y, Color color, Color TextColor, final String scolor)
		 * */
		tempColorButtonSize = 0;
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(255, 217, 173), new Color(0, 0, 0), "White");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(40, 254, 4), new Color(0, 0, 0), "Green");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(89, 167, 255), new Color(0, 0, 0), "Blue");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(254, 132, 135), new Color(0, 0, 0), "Red");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(109, 60, 36), new Color(255, 255, 255), "Black");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(255, 231, 67), new Color(0, 0, 0), "Yellow");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(215, 110, 254), new Color(0, 0, 0), "Purple");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(255, 178, 109), new Color(0, 0, 0), "Orange");
		/*
		 * Add the arrow keys for controlling the Icon image that is animated in the title bar
		 * public void ArrowKeys(JButton butt, int x, int y, int sx, int sy, final int behold, Image iconImage)
		 * */
		CB.ArrowKeys(arrowsX + 4, arrowsY, 42, 20, 6, IH.load(IH.ImageLocation + "Images/larrow.png"));
		CB.ArrowKeys(arrowsX + 46, arrowsY, 42, 20, 7, IH.load(IH.ImageLocation + "Images/rarrow.png"));
		CB.ArrowKeys(arrowsX - 80, arrowsY, 42, 20, 8, IH.load(IH.ImageLocation + "Images/uarrow.png"));
		CB.ArrowKeys(arrowsX - 38, arrowsY, 42, 20, 5, IH.load(IH.ImageLocation + "Images/darrow.png"));
		
		CB.BodyButton("Orc", ColorButtonX + 20, ColorButtonY + 210, 90, 20);
		CB.BodyButton("Zombie", ColorButtonX + 20, ColorButtonY + 190, 90, 20);
		CB.BodyButton("Goblin", ColorButtonX + 110, ColorButtonY + 190, 90, 20);
		CB.BodyButton("Minotaur", ColorButtonX + 110, ColorButtonY + 210, 90, 20);
		CB.BodyButton("Cyclops", ColorButtonX + 20, ColorButtonY + 230, 90, 20); 
		/*
		 * The opacity slider controls the Opacity of the image from 0 - 100
		 * */
		OpacitySlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
		OpacitySlider.setBounds(OPSliderX - 40, OPSliderY + 5, 180, 20);
		OpacitySlider.addChangeListener(new ChangeListener()
		{
			@Override public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider)e.getSource();
				float opacity = (float)source.getValue() / 100;
				if (PickSex.equals("Male"))
					Mopacity = opacity;
				else if (PickSex.equals("Female"))
					Fopacity = opacity;
			}
		});
		add(OpacitySlider);
		/*
		 * This removes the base template so you can put it on your own custom template
		 * as the Object images are heavily modified and are well within the bounds of
		 * no longer being under copyright by their original owners due to a more than 70%
		 * change in the images.
		 * 
		 * However the templates have not changed
		 * */
		HaveBodyC = new JCheckBox("Show Base");
		HaveBodyC.setBounds(ColorButtonX + 20, ColorButtonY + 270, 90, 20);
		HaveBodyC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				if (PickSex.equals("Male"))
					MHaveBody = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				if (PickSex.equals("Female"))
					FHaveBody = e.getStateChange() == ItemEvent.SELECTED ? true : false;
			}
		});
		add(HaveBodyC);
		SaveAsXPC = new JCheckBox("Save As XP");
		SaveAsXPC.setBounds(25, 342, 95, 20);
		SaveAsXPC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				SaveAsXP = e.getStateChange() == ItemEvent.SELECTED ? true : false;
			}
		});
		add(SaveAsXPC);
		JCheckBox SaveUnc = new JCheckBox("Save All Layers");
		SaveUnc.setBounds(ColorButtonX + 20, ColorButtonY + 290, 150, 20);
		SaveUnc.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				IOHandler.normalSave = e.getStateChange() == ItemEvent.SELECTED ? false : true;
			}
		});
		add(SaveUnc);
		/*
		 * This is for creating a ghost
		 * */
		GhostC = new JCheckBox("Ghost");
		GhostC.setBounds(ColorButtonX + 110, ColorButtonY + 270, 90, 20);
		GhostC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{	
				if (PickSex.equals("Male"))
					MisGhost = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				if (PickSex.equals("Female"))
					FisGhost = e.getStateChange() == ItemEvent.SELECTED ? true : false;
			}
		});
		add(GhostC);
		/*
		 * Random button is too big to keep here
		 * so I put it in its own class
		 * */
		new RandButton();
		
		MaleC = new JButton("Male");
		MaleC.setBounds(ColorButtonX, ColorButtonY + 30, 100, 20);
		add(MaleC);
		MaleC.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{	
				ObjectSelect = MObjectSelect;
				PickSex = "Male";
			}
		});
		FemaleC = new JButton("Female");
		FemaleC.setBounds(ColorButtonX + 116, ColorButtonY + 30, 100, 20);
		add(FemaleC);
		FemaleC.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				ObjectSelect = FObjectSelect;
				PickSex = "Female";
			}
		});
		//Timers in their own class
		TH = new timerHandler();
	}
	/*
	 * This is for creating the part buttons. Body, Hair, Hairop ect...
	 * */
	
	/*
	 * This function is used to created the scaled animation preview
	 * */

	public void start()
	{
		th = new Thread(this);
		th.start();
		/*
		 * Create a runnable for SprCre so that I can have better response times on the animation generation
		 * this runnable noticeably reduced lag times on slower computers
		 * */
		Thread load = new Thread(new Runnable()
		{
			public void run()
			{
				IH.LoadAllSprites();
			}
		});
		load.start();
		Thread doallThread = new Thread(new Runnable()
		{
			public void run()
			{
				while (true)
				{
					/*
					 * This thread/Runnable keeps the image and animation previews updated
					 * Used it's own thread to increase speed
					 * */
					ImgHandler.AllSplit = IH.StackImage();
					try { 
						Thread.sleep(30); 
					} catch (Exception ex) {}
				}
			}
		});
		doallThread.start();
		Thread ms = new Thread(new Runnable()
		{
			@Override public void run()
			{
				while(true)
				{	
					IH.drawBoard();
					try {
						Thread.sleep(10);
					} catch (Exception e) {}
				}
			}
		});
		ms.start();
		Thread scaleimg = new Thread(new Runnable()
		{
			@Override public void run()
			{
				while (true)
				{
					for (int a = 3; a >= 0; a--)
					{
						try {
							//This one is confusing even though I wrote it
							/*
							 * This controls which frame is displayed in the scaled animation window
							 * */
							/*
							 * formatted like a block statement for readability on something I have to really look at to change
							 * */
							ImgHandler.ScaledAllSplit[a] = 
									IH.createResizedCopy(
											IH.MakeSmaller(
													ImgHandler.bi[NSplitFrame - a], ImageW * 2, ImageH * 2, ImageFrames, makeSmallerX, makeSmallerY
											)[1], ImageW * 4, ImageH * 4
									);
						}catch (NullPointerException ex) {
							//Ignore and continue.
						}
					}
					try { 
						Thread.sleep(30); 
					} catch (Exception ex) {}
				}
			}
		});
		scaleimg.start();
	}
	/*
	 * Terminate the thread
	 * */
	public void stop()
	{
		th = null;
	}
	public void QuickStack(String color)
	{
		MTypeBody = color;
		FTypeBody = color;
		PickSex = "Female";
		ImgHandler.AllSplit = IH.StackImage();
		PickSex = "Male";
		ImgHandler.AllSplit = IH.StackImage();
	}
	public void run()
	{
		/*
		 * This is for keeping track of the tread for later use to make sure that the thread is
		 * still running
		 * */
		Thread thisThread = Thread.currentThread();
		/*
		 * repaint is needed here to do a little drawing before everything starts.
		 * */
		for (String s : ImgHandler.cbody)
			QuickStack(s);
		MTypeBody = "White";
		FTypeBody = "White";
		repaint();
		while (!quit && th == thisThread)
		{
			/*
			 * CheckTheMouse keeps track of what Object you're clicking on in the Selection Window
			 * */
			CheckTheMouse();
			repaint();
			try {
				//Reduce CPU usage
				Thread.sleep(30);
			} catch (InterruptedException ex) {}
		}
	}
	public void destroy()
	{
		//timerHandler.displayTimer.stop();
		//timerHandler.displayTimer2.stop();
		//timerHandler.displayTimer3.stop();
		th = null;
		IH.th = null;
		quit();
	}

	
	private void CheckTheMouse()
	{
		if (HasBeenClicked && !ClickChecked)
		{	//Here is the part selection window frame
			if (M_x > PartSelectWindowX && M_x < PartSelectWindowX + 32)
				if (M_x > PartSelectWindowY - 32 && M_y < PartSelectWindowY)
				{
					makeBlank("Body", Body_L1, -1, -1, Body);
					makeBlank("Hair", Hair_L1, Hair_L2, -1, Hair);
					makeBlank("Hairop", Hairop_L1, Hairop_L2, -1, Hairop);
					makeBlank("Mantle", Mantle_L1, Mantle_L2, -1, Mantle);
					makeBlank("Acce1", Acce1_L1, Acce1_L2, -1, Acce1);
					makeBlank("Acce2", Acce2_L1, -1, -1, Acce2);
					makeBlank("Option", Option_L1, Option_L2, -1, Option);
					makeBlank("Tail", Tail_L1, Tail_L2, -1, Tail);
					makeBlank("Ears", Ears_L1, Ears_L2, Ears_L3, Ears);
					makeBlank("Bangs", Bangs_L1, -1, -1, Bangs);
					makeBlank("Face", Face_L1, -1, -1, Face);
					makeBlank("Armor", Armor_L1, -1, -1, Armor);
				}
			/*
			 * When adding new categories you must add above
			 * */
			/*
			 * sorry for this one. Automating from configuration
			 * makes adding and removing image parts VERY simple
			 * however it makes understanding this not so easy
			 * Just don't change anything here all the values should be changed
			 * else where.
			 * */
				CheckObject(adj(mbodyI), adj(mbodyI) + addone(mbodyI), 	// This one is different than the rest due to
						adj(fbodyI), adj(fbodyI) + addone(fbodyI), 		// using Image[][] instead of image[][][]
						"Body", IH.Male_Body, IH.Female_Body,
						Body_L1, Body);
				/*
				 * 32 represents the image height
				 * 2 represents having 2 control window sections
				 * 		since it is split in half
				 * */
				CheckObject(adj(mhairI), adj(mhairI) + addone(mhairI),	// Male window controls																
						adj(fhairI), adj(fhairI) + addone(fhairI), 		// Female window controls
						"Hair", IH.Male_Hair, IH.Female_Hair,					// Object Name, Object Male, Object Female
						Hair_L1, Hair_L2, -1,									// Image Objects Layer 1, Layer 2, Layer 3
						1, 2, -1, Hair);										// Int Layer order L1, L2, L3, Order
																				// Layer order is for front, back, middle
																				// separation
				CheckObject(adj(mhairopI), adj(mhairopI) + addone(mhairopI),
						adj(fhairopI), adj(fhairopI) + addone(fhairopI), 
						"Hairop", IH.Male_Hairop, IH.Female_Hairop,
						Hairop_L1, Hairop_L2, -1,
						1, 2, -1, Hairop);
				
				CheckObject(adj(mantleI), adj(mantleI) + addone(mantleI),
						adj(mantleI), adj(mantleI) + addone(mantleI), 
						"Mantle", IH.Male_Mantle, IH.Female_Mantle,
						Mantle_L1, Mantle_L2, -1,
						1, 2, -1, Mantle);
				
				CheckObject(adj(macce1I), adj(macce1I) + addone(macce1I),
						adj(facce1I), adj(facce1I) + addone(facce1I), 
						"Acce1", IH.Male_Acce1, IH.Female_Acce1,
						Acce1_L1, Acce1_L2, -1,
						1, 2, -1, Acce1);
				
				CheckObject(adj(acce2I), adj(acce2I) + addone(acce2I),
						adj(acce2I), adj(acce2I) + addone(acce2I), 
						"Acce2", IH.Male_Acce2, IH.Female_Acce2, 
						Acce2_L1, -1, -1,
						2, -1, -1, Acce2);
				
				CheckObject(adj(optionI), adj(optionI) + addone(optionI),
						adj(optionI), adj(optionI) + addone(optionI), 
						"Option", IH.Male_Option, IH.Female_Option,
						Option_L1, Option_L2, -1,
						1, 2, -1, Option);
				
				CheckObject(adj(tailI), adj(tailI) + addone(tailI), 
						adj(tailI), adj(tailI) + addone(tailI), 
						"Tail", IH.Male_Tail, IH.Female_Tail, 
						Tail_L1, Tail_L2, -1, 
						3, 1, 2, Tail);
				
				CheckObject(adj(earsI), adj(earsI) + addone(earsI), 
						adj(earsI), adj(earsI) + addone(earsI), 
						"Ears", IH.Male_Ears, IH.Female_Ears, 
						Ears_L1, Ears_L2, Ears_L3, 
						3, 1, 2, Ears);
				
				CheckObject(adj(mbangsI), adj(mbangsI) + addone(mbangsI),
						adj(fbangsI), adj(fbangsI) + addone(fbangsI), 
						"Bangs", IH.Male_Bangs, IH.Female_Bangs,
						Bangs_L1, -1, -1,
						2, -1, -1, Bangs);
				
				CheckObject(adj(faceI), adj(faceI) + addone(faceI),
						adj(faceI), adj(faceI) + addone(faceI), 
						"Face", IH.Male_Face, IH.Female_Face,
						Face_L1, -1, -1,
						2, -1, -1, Face);
				
				CheckObject(adj(armorI), adj(armorI) + addone(armorI),
						adj(armorI), adj(armorI) + addone(armorI),
						"Armor", IH.Male_Armor, IH.Female_Armor,
						Armor_L1, -1, -1,
						2, -1, -1, Armor);
				/*
				 * When adding new categories you must add above
				 * */
				/*This tells that the item you have selected was finished and everything is done
				 * it also keeps from dragging the mouse and fast changing Objects
				 * Instead you have to click and then release to change to another Object
				 */
			ClickChecked = true;
		}
	}
	private int adj(int I) { return I / 2 * ImageH; }
	/*
	 * addone is used when calculating if there needs to be an extra body draw
	 * This is only needed when the objects being used is odd. An even amount of objects
	 * does not need this function
	 * */
	public int addone(int i)
	{
		float ii = i;
		if (ii / 2 == i / 2)
			return 0;
		else
			return ImageH;
	}
	/*
	 * This controls reloading of the Male or Female iamges
	 * Everything here is reset as if you had just started the program
	 * you can choose to reload either one or both Male and Female
	 * */
	public void makeBlank(String obs, int L1, int L2, int L3, int os)
	{
		if (ObjectSelect.equals(obs))
		{
			if (PickSex.equals("Male"))
			{
				if (L1 != -1)
					Mimg[L1] = AllBlank;
				if (L2 != -1)
					Mimg[L2] = AllBlank;
				if (L3 != -1)
					Mimg[L3] = AllBlank;
				if (os != -1)
					Mtype[os] = "00_";
			}
			else
			{
				if (L1 != -1)
					Fimg[L1] = AllBlank;
				if (L2 != -1)
					Fimg[L2] = AllBlank;
				if (L3 != -1)
					Fimg[L3] = AllBlank;
				if (os != -1)
					Ftype[os] = "00_";
			}
		}
	}
	public int parts(int m, int f)
	{
		if (PickSex.equals("Male"))
			return m;
		else
			return f;
	}
	public void CheckObject(int msize1, int msize2, int fsize1, int fsize2, String obs, Image[][] mi, Image[][] fi, int L1, int os)
	{
		if (ObjectSelect.equals(obs))
		{
			if (M_x > PartSelectWindowX && M_x < PartSelectWindowX + drawAcross)
				if (M_y > PartSelectWindowY && M_y < PartSelectWindowY + parts(msize1, fsize1))
				{
					PressedX = (M_x - PartSelectWindowX) / SingleImageSize;
					PressedY = (M_y - PartSelectWindowY) / SingleImageSize;
					if (PickSex.equals("Male"))
					{
						if (L1 != -1)
							Mimg[L1] = mi[PressedX + 1][PressedY + 1];
						if (os != -1)
							Mtype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + 1) + "_";
					}
					if (PickSex.equals("Female"))
					{
						if (L1 != -1)
							Fimg[L1] = fi[PressedX + 1][PressedY + 1];
						if (os != -1)
							Ftype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + 1) + "_";
					}
				}
		
			if (M_x > PartSelectWindowX + drawAcross && M_x < PartSelectWindowX + totalAcross)
				if (M_y > PartSelectWindowY && M_y < PartSelectWindowY + parts(msize2, fsize2))
				{
					PressedX = (M_x - PartSelectWindowX - drawAcross) / SingleImageSize;
					PressedY = (M_y - PartSelectWindowY) / SingleImageSize;
					if (PickSex.equals("Male"))
					{
						if (L1 != -1)
							Mimg[L1] = mi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1];
						if (os != -1)
							Mtype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + (parts(msize1, fsize1) / ImageH) + 1) + "_";
					}
					if (PickSex.equals("Female"))
					{
						if (L1 != -1)
							Fimg[L1] = fi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1];
						if (os != -1)
							Ftype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + (parts(msize1, fsize1) / ImageH) + 1) + "_";
					}
				}
			}
	}
	public void CheckObject(int msize1, int msize2, int fsize1, int fsize2, String obs, Image[][][] mi, Image[][][] fi, int L1, int L2, int L3, int L1o, int L2o, int L3o, int os)
	{
		if (ObjectSelect.equals(obs))
		{
			if (M_x > PartSelectWindowX && M_x < PartSelectWindowX + drawAcross)
				if (M_y > PartSelectWindowY && M_y < PartSelectWindowY + parts(msize1, fsize1))
				{
					PressedX = (M_x - PartSelectWindowX) / SingleImageSize;
					PressedY = (M_y - PartSelectWindowY) / SingleImageSize;
					if (PickSex.equals("Male"))
					{
						if (L3 != -1)
							Mimg[L3] = mi[PressedX + 1][PressedY + 1][L3o];
						if (L2 != -1)
							Mimg[L2] = mi[PressedX + 1][PressedY + 1][L2o];
						if (L1 != -1)
							Mimg[L1] = mi[PressedX + 1][PressedY + 1][L1o];
						if (os != -1)
							Mtype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + 1) + "_";
					}
					if (PickSex.equals("Female"))
					{
						if (L3 != -1)
							Fimg[L3] = fi[PressedX + 1][PressedY + 1][L3o];
						if (L2 != -1)
							Fimg[L2] = fi[PressedX + 1][PressedY + 1][L2o];
						if (L1 != -1)
							Fimg[L1] = fi[PressedX + 1][PressedY + 1][L1o];
						if (os != -1)
							Ftype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + 1) + "_";
					}
				}
		
			if (M_x > PartSelectWindowX + drawAcross && M_x < PartSelectWindowX + totalAcross)
				if (M_y > PartSelectWindowY && M_y < PartSelectWindowY + parts(msize2, fsize2))
				{
					PressedX = (M_x - PartSelectWindowX - drawAcross) / SingleImageSize;
					PressedY = (M_y - PartSelectWindowY) / SingleImageSize;
					if (PickSex.equals("Male"))
					{
						if (L3 != -1)
							Mimg[L3] = mi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1][L3o];
						if (L2 != -1)
							Mimg[L2] = mi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1][L2o];
						if (L1 != -1)
							Mimg[L1] = mi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1][L1o];
						if (os != -1)
							Mtype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + (parts(msize1, fsize1) / ImageH) + 1) + "_";
					}
					if (PickSex.equals("Female"))
					{
						if (L3 != -1)
							Fimg[L3] = fi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1][L3o];
						if (L2 != -1)
							Fimg[L2] = fi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1][L2o];
						if (L1 != -1)
							Fimg[L1] = fi[PressedX + 1][PressedY + (parts(msize1, fsize1) / ImageH) + 1][L1o];
						if (os != -1)
							Ftype[os] = Integer.toString(PressedX + 1) + Integer.toString(PressedY + (parts(msize1, fsize1) / ImageH) + 1) + "_";
					}
				}
			}
	}
	
	private void ReloadAll(boolean b, String SexToReload)
	{
		if (SexToReload.equals("Male") || b)
		{
			for (int i = Cons.StackFirst; i <= Cons.StackLast; i++)
				Mimg[i] = AllBlank;
			for (int i = 0; i <= Obcount; i++)
				Mtype[i] = "00_";
			MTypeBody = "White";
			if (SprCre.PickSex.equals(SexToReload))
				ObjectSelect = "Body";
			MObjectSelect = "Body";
			MHaveBody = true;
			MisGhost = false;
			Mopacity = 0.5f;
		}
		if (SexToReload.equals("Female") || b)
		{
			for (int i = Cons.StackFirst; i <= Cons.StackLast; i++)
				Fimg[i] = AllBlank;
			for (int i = 0; i <= Obcount; i++)
				Ftype[i] = "00_";
			FTypeBody = "White";
			if (SprCre.PickSex.equals(SexToReload))
				ObjectSelect = "Body";
			FObjectSelect = "Body";
			FHaveBody = true;
			FisGhost = false;
			Fopacity = 0.5f;
		}
		if (b && SexToReload.equals(""))
			PickSex = "Male";
	}
	@Override public void mousePressed(MouseEvent e)
	{
		/*
		 * This keeps the mouse updated when you click. HasBeenClicked controls when some things 
		 * are searched through when clicked and ignores when nothing is clicked
		 * This greatly improved response times on just about every computer
		 * especially slower computers.
		 * */
		HasBeenClicked = true;
		M_x = MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x;
		M_y = MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y;
	}
	@Override public void mouseReleased(MouseEvent e)
	{
		//Make sure to reset the clicking and the check
		HasBeenClicked = false;
		ClickChecked = false;
	}
	/*
	 * Guess I could have used a MouseAdapter
	 * */
	@Override public void mouseEntered(MouseEvent e){}
	@Override public void mouseExited(MouseEvent e){}
	@Override public void mouseClicked(MouseEvent e){}
	@Override public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g; 
		super.paint(g2); 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(Back_Ground, BackgroundX, BackgroundY, 205, 43, null);
		g2.setFont(firstFont);
		g2.drawString("Choose Skin Color", ColorButtonX, ColorButtonY - 4);
		g2.setColor(Color.black);
		g2.setFont(secondFont);
		g2.drawString("Title Preview Direction", arrowsX - 73, arrowsY - 2);
		if (!MisGhost && PickSex.equals("Male") || !FisGhost && PickSex.equals("Female"))
		{
			g2.setColor(Color.gray);
			g2.drawString("Ghost Opacity", OPSliderX + 5, OPSliderY);
		}
		else
		{
			g2.setColor(Color.black);
			g2.drawString("Ghost Opacity", OPSliderX + 5, OPSliderY);
		}
		g2.drawRect(ColorButtonX + 118, ColorButtonY + 54, 97, 129);
		g2.drawImage(ImgHandler.AllSplit, ColorButtonX + 118, ColorButtonY + 54, null);
		g2.drawLine(ColorButtonX, ColorButtonY - 2, ColorButtonX + ColorButtonLineMaxX, ColorButtonY - 2);
		/*
		 * This control the placement of the scaled animation preview
		 * a controls which frame
		 * */
		for (int ScaledImageFrame = 3, x = 32, y = 54; ScaledImageFrame >= 0; ScaledImageFrame--, y += 64)
		{
			if (y > 118)
			{
				y = 54;
				x = 96;
			}
			g2.drawImage(ImgHandler.ScaledAllSplit[ScaledImageFrame], ColorButtonX + x - 39, ColorButtonY + y, null);
		}
		IH.DrawParts(g2, ObjectSelect);
	}
	public void quit()
	{
		quit = true;
		System.exit(0);
	}
	@Override public void update(Graphics g)
	{
		if (dbImage == null)
		{
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
		dbg.setColor(getForeground());
		paint(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	public int width, height;
	private int M_x, M_y, PressedX, PressedY;
	public static int NSplitFrame = 4, NSplitFrameInc = 4;
	
	private Graphics dbg;
	
	public static float Fopacity = 0.5f, Mopacity = 0.5f;
	
	public static String FTypeBody = "White";
	public static String MTypeBody = "White";
	public static String PickSex = "Female", ObjectSelect = "Body", MObjectSelect = "Body", FObjectSelect = "Body";
	public static String fileName = new String();
	public static String[] Ftype = new String[Obcount + 1];
	public static String[] Mtype = new String[Obcount + 1];
	
	private static Image dbImage, Back_Ground;
	public static Image fback, mback;
	public static Image ffront, mfront;
	public static Image fside, mside;
	public static Image AllBlank;
	public static Image Facce1, Facce2, Fbody, Fhair, Fhairop, Foption, Fmantle, Macce1, Macce2, Mbody, Mhair, Mhairop, Moption, Mmantle, Mtail, Mears, Ftail, Fears, Mbangs, Fbangs;
	public static Image Mface, Fface, Marmor, Farmor;
	public static Image[] Fimg = new Image[maxI], Mimg = new Image[maxI], Fbase = new Image[ImageBases + 1], Mbase = new Image[ImageBases + 1];
	
	private JButton MaleC, FemaleC;
	
	public static JSlider OpacitySlider;
	
	public static JCheckBox GhostC, HaveBodyC, NoOpBaseC, SaveAsXPC;
	
	public static boolean SaveAsXP = false;
	public static boolean MisGhost = false, FisGhost = false;
	private boolean ClickChecked = false;
	public static boolean MHaveBody = true, FHaveBody = true;
	public static boolean quit = false;
	private boolean HasBeenClicked = false;
	private Font firstFont, secondFont;	
}
