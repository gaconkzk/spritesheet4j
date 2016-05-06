package circle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

import circle.colorizer.ColorizerObjectHandler;
import circle.handlers.CheckOb;
import circle.handlers.IOHandler;
import circle.handlers.ImgHandler;
import circle.handlers.timerHandler;
import circle.ui.MenuBar;
import circle.ui.UIControl;

public class SprCre extends JPanel implements Runnable, Cons
{
	private static final long serialVersionUID = 1L;
	private static String BuildNumber = "SprCre3XP Colorizer ALPHA 2014.3.30.04";
	
	private volatile static Thread th;
	
	public static JFrame frame = new JFrame("Sprite Creator 3 XP - " + BuildNumber);
	public final int PartSelectWindowX = 275, PartSelectWindowY = 102;
	
	private final int AppletLocationX = 0, AppletLocationY = 0;
	
	private final int AppletSizeX = 931, AppletSizeY = 611; 
	private final int FrameSizeX = AppletSizeX + 2;
	private final int FrameSizeY = AppletSizeY + 50;
	
	private final int BackgroundX = 20, BackgroundY = AppletSizeY - 45;
	public static SprCre apl = new SprCre();
	public static IOHandler io = new IOHandler("Config.cfg");
	public static ColorizerObjectHandler mColorObject= new ColorizerObjectHandler(13);
	public static ColorizerObjectHandler fColorObject= new ColorizerObjectHandler(13);
	public static CheckOb cho = new CheckOb();
	public static ImgHandler IH = new ImgHandler();
	
	public static boolean CanOpenWindow = true;

	public static timerHandler TH;
	public String getBuild() { return BuildNumber; }
	
	public static void main(String[] args)
	{
		ImageIO.setUseCache(false);
		apl.setLayout(null);
		apl.start();
		apl.init();
		frame.setJMenuBar(MenuBar.menubar);
		frame.setVisible(true);
	}	
	private final Font getsFont()
    {
        Graphics g = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).getGraphics();
        Font font = new Font(g.getFont().toString(), 0, 12);
        g.dispose();

        return font;
    }
	public void init()
	{
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(5000);
		String ImageLocation = IH.ImageLocation;
		Back_Ground = IH.load(ImageLocation + "Background/background.png");
		String[] baseNames = {"_e.png", "_ne.png", "_e_b.png", "_ne_b.png"};
		for (int i = 0; i <= ImageBases - 1; i++)
		{
			for (int r = 0; r <= 3; r++)
			{
				IH.Mbase[r][i] = IH.load(ImageLocation + "Male/Base/" + ImgHandler.cbody[i] + baseNames[r]);
				IH.Fbase[r][i] = IH.load(ImageLocation + "Female/Base/" + ImgHandler.cbody[i] + baseNames[r]);
				SprCre.IH.toBufferedImage(IH.Mbase[r][i]);
				SprCre.IH.toBufferedImage(IH.Fbase[r][i]);
			}
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
	
		cho.ReloadAll(true, ".");

		this.setBackground(new Color(250, 250, 250));
		this.setSize(AppletSizeX, AppletSizeY);
		this.setLocation(AppletLocationX, AppletLocationY);
		width = this.getWidth();
		height = this.getHeight();
		secondFont = getsFont();
		firstFont = new Font("TimesRoman", Font.BOLD, UIControl.getColorButtonSize());
		this.setFont(firstFont);
		this.setLayout(null);
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
				ImgHandler.doneWithFirstBoard = true;
				int result = JOptionPane.showConfirmDialog(frame, 
						"Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					destroy();
				}
			}
		});
		new MenuBar(frame);
		new UIControl();
		TH = new timerHandler();
		TH.StartTimers();
	}
	static long a = 0;
	static long b = 0;
	static double c = 0;

	public static void S()
	{
		a = System.nanoTime();
		System.out.println("Timer Started");
	}
	public static double X()
	{
		b = System.nanoTime();
		c = (double)(b - a) / 1000000000.0;
		System.out.println("Timer Stopped: " + c);
		return c;
	}
	public void start()
	{
		th = new Thread(this);
		th.start();
		Thread load = new Thread(new Runnable()
		{
			public void run()
			{
				IH.LoadAllSprites();
			}
		});
		load.start();
		try
		{
			load.join();
		} catch (InterruptedException e1)
		{}
		Thread ms = new Thread(new Runnable()
		{
			@Override public void run()
			{
				while (!ImgHandler.doneWithFirstBoard)
				{
					IH.drawBoard();
					try
					{
						Thread.sleep(200);
					} catch (Exception e)
					{}
				}

			}
		});
		ms.start();
		Thread scaleimg = new Thread(new Runnable()
		{
			@Override public void run()
			{
				BufferedImage ihbi;
				BufferedImage[] bia;
				BufferedImage bi;
				while (true)
				{
					for (int a = 3; a >= 0; a--)
					{
						try {
							ihbi = ImgHandler.bi[TH.NSplitFrame - a];
							bia = IH.MakeSmaller(ihbi);
							bi = bia[1];
							ImgHandler.ScaledAllSplit[a] = bi;
						}catch (NullPointerException ex) {}
					}
					try { 
						Thread.sleep(30); 
					} catch (Exception ex) {}
				}
			}
		});
		scaleimg.start();
	}
	public void stop()
	{
		th = null;
	}
	long lastLoopTime = System.nanoTime();
	final int TARGET_FPS = 30;
	final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	public void run()
	{
		Thread thisThread = Thread.currentThread();
		repaint();
		while (!quit && th == thisThread)
		{
			long now = System.nanoTime();
			lastLoopTime = now;
			cho.CheckTheMouse();
			repaint();
			try {
				//Fixed Time Rate
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000); //10ms per frame
			} catch (InterruptedException e) {
				
			} catch (IllegalArgumentException e) {
				System.out.println("Did not sleep");
			}
		}
	}
	public void destroy()
	{
		th = null;
		quit();
	}
	public void paintComponent(Graphics g)
	{
		g2 = (Graphics2D)g; 
		super.paintComponents(g2);
		g2.setColor(new Color(250, 250, 250));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(Color.black);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(Back_Ground, BackgroundX - 35, BackgroundY - 40, 400, 100, null);
		g2.setFont(firstFont);
		g2.drawString("Choose Skin Color", UIControl.ColorButtonX, UIControl.ColorButtonY - 4);
		g2.setFont(secondFont);
		
		g2.drawRect(UIControl.ColorButtonX + 126, UIControl.ColorButtonY + 53, 129, 197);
		g2.drawImage(ImgHandler.AllSplit, UIControl.ColorButtonX + 127, UIControl.ColorButtonY + 54, null);
		g2.drawLine(UIControl.ColorButtonX, UIControl.ColorButtonY - 2, UIControl.ColorButtonX + UIControl.ColorButtonLineMaxX, UIControl.ColorButtonY - 2);

		if (UIControl.getFrameScale() == 2)
		{
			for (int ScaledImageFrame = 3, x = 36, y = 64; ScaledImageFrame >= 0; ScaledImageFrame--, y += 100)
			{
				x = y > 168 ? 100 : x;
				y = y > 164 ? 64 : y;
				g2.drawImage(ImgHandler.ScaledAllSplit[ScaledImageFrame], UIControl.ColorButtonX + x - 39, UIControl.ColorButtonY + y - 10, 64, 96, null);
			}
		}
		else
			g2.drawImage(ImgHandler.ScaledAllSplit[-((timerHandler.SetIconImageFrameHold - 1) - 3)], UIControl.ColorButtonX - 7, UIControl.ColorButtonY + 54, 128, 192, null);
		IH.DrawParts(g2, cho.ObjectSelect);
	}
	public void quit()
	{
		quit = true;
		SprCre.frame.dispose();
		SprCre.apl = null;
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
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	public int width, height;
	
	private Graphics dbg;
	//TODO
	public volatile String PickSex = "Male";
	
	private Graphics2D g2;
	
	private Image dbImage, Back_Ground;
	public Image fback, mback;
	public Image ffront, mfront;
	public Image fside, mside;
	public Image AllBlank;
	public static boolean quit = false;
	private Font firstFont;
	public Font secondFont;	
	
}
