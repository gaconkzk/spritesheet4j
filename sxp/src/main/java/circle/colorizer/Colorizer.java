package circle.colorizer;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;

import circle.SprCre;
import circle.handlers.CheckOb;
@SuppressWarnings("unused")
public class Colorizer extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private static volatile Thread t1h;
	public JFrame cframe;
	
	public static Colorizer colorizer = new Colorizer();
	public static ColorBalanceHandler CBH;
	public static ColorizerUI cui;
	
	private boolean HasBeenClicked = false, ClickChecked = false;
	private int M_x, M_y;

	public int layerSelected = 2; //Starting layer to color
	public boolean showAllLayers = true;
	
	public BufferedImage[] BackupM = new BufferedImage[13];// = SprCre.mColorObject.image.clone();
	
	public BufferedImage[] BackupF = new BufferedImage[13];// = SprCre.fColorObject.image.clone();
	
	public static void main(String[] args)
	{
		colorizer = new Colorizer();
		CBH = new ColorBalanceHandler();
		colorizer.setLayout(null);
		colorizer.start();
		
		colorizer.init();
		colorizer.cframe.setVisible(true);
	}
	public boolean keepgoing = true;
	public void init()
	{
		setBackground(new Color(250, 250, 250));
		setSize(720, 420);
		setLocation(0, 0);
		setLayout(null);
		cframe = new JFrame("Colorizer");
		// frame.setIconImage(IH.load("/Data/Sprites/Background/BodyIcon.png"));
		cframe.setLayout(null);
		cframe.getContentPane().add(colorizer);
		cframe.setSize(this.getWidth() + 4, this.getHeight() + 28);
		cframe.setResizable(false);
		cframe.setLocation(50, 100);
		cframe.getContentPane().setBackground(Color.black);
		cframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		cframe.addWindowListener(new WindowAdapter()
		{
			@Override public void windowClosed(WindowEvent e)
			{
				for (int i = 0; i < 13; i ++)
				{
					SprCre.mColorObject.setLuminocity(i, 50);
					SprCre.mColorObject.setTone(i, 0);
					SprCre.mColorObject.setShade(i, 0);
					SprCre.mColorObject.setBrightness(i, 0);
					SprCre.mColorObject.setContrast(i, 50);
				
					SprCre.fColorObject.setLuminocity(i, 50);
					SprCre.fColorObject.setTone(i, 0);
					SprCre.fColorObject.setShade(i, 0);
					SprCre.fColorObject.setBrightness(i, 0);
					SprCre.fColorObject.setContrast(i, 50);
					
				}
				SprCre.CanOpenWindow = true;
				//ColorizerUI.menuBaraC.removeAll();
				keepgoing = false;
				t1h = null;
				stop();
			}
			@Override public void windowClosing(WindowEvent e)
			{
				
				//TODO
				/*
				 * Reset all values here cause if Apply is not pressed
				 * those values remain
				 * */
				if (!Colorizer.cui.applypressed)
				{
					int result = JOptionPane.showConfirmDialog(null, "Really exit?" + '\n' + "You will lose all changes if you do not press apply.", "Really Exit?", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION)
					{
						cframe.dispose();
					}
				}
				else
					cframe.dispose();
				
			}
		});
		colorizer.addMouseListener(new MouseInputAdapter()
		{
			@Override public void mousePressed(MouseEvent arg0)
			{
				HasBeenClicked = true;
				M_x = MouseInfo.getPointerInfo().getLocation().x - Colorizer.colorizer.getLocationOnScreen().x;
				M_y = MouseInfo.getPointerInfo().getLocation().y - Colorizer.colorizer.getLocationOnScreen().y;	
			}
			@Override public void mouseReleased(MouseEvent arg0)
			{
				HasBeenClicked = false;
				ClickChecked = false;
			}
		});
		
		cui = new ColorizerUI();
		
	}
	public void start()
	{
		t1h = new Thread(this);
		t1h.start();
	}
	public void stop() { t1h = null; }
	public void destroy() { quit(); }
	public void quit() { quit = true; }
	
	@Override public void run()
	{
		Thread thisThread = Thread.currentThread();
		while (!quit && t1h == thisThread)
		{
			try {
				colorizer.setSize(cframe.getWidth(), cframe.getHeight());
			} catch (NullPointerException NPE) {
			} catch (ClassCastException CCE) {
			} finally {}
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException ex) {}
		}
	}
	private int imgUSX = 256, imgUSY = 384;

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g; 
		super.paintComponents(g2);
		g2.setColor(new Color(250, 250, 250));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.fillRect(20,20,imgUSX, imgUSY);
		if (!showAllLayers)
		{
			if (SprCre.apl.PickSex.equals("Male"))
			{
				switch (layerSelected)
				{
					case 0:
					case 1:
						break;
					case 2:
						g2.drawImage(BackupM[layerSelected - 2], 20, 20, 256, 384, null);
						g2.drawImage(BackupM[layerSelected], 20, 20, 256, 384, null);
						break;
					case 9:
						g2.drawImage(BackupM[layerSelected], 20, 20, 256, 384, null);
						break;
					case 10:
						//g2.drawImage(SprCre.mColorObject.getImage(layerSelected - 9), 20, 20, 256, 384, null);
						//g2.drawImage(SprCre.mColorObject.getImage(layerSelected), 20, 20, 256, 384, null);
						g2.drawImage(BackupM[layerSelected - 9], 20, 20, 256, 384, null);
						g2.drawImage(BackupM[layerSelected], 20, 20, 256, 384, null);
						break;
					default:
						//g2.drawImage(SprCre.mColorObject.getImage(layerSelected), 20, 20, 256, 384, null);
						g2.drawImage(BackupM[layerSelected], 20, 20, 256, 384, null);
						break;
				}
			}
			else if (SprCre.apl.PickSex.equals("Female"))
			{
				switch (layerSelected)
				{
					case 0:
					case 1:
						break;
					case 2:
						g2.drawImage(BackupF[layerSelected - 2], 20, 20, 256, 384, null);
						g2.drawImage(BackupF[layerSelected], 20, 20, 256, 384, null);
						break;
					case 9:
						g2.drawImage(BackupF[layerSelected], 20, 20, 256, 384, null);
						break;
					case 10:
						g2.drawImage(BackupF[layerSelected - 9], 20, 20, 256, 384, null);
						g2.drawImage(BackupF[layerSelected], 20, 20, 256, 384, null);
						break;
					default:
						g2.drawImage(BackupF[layerSelected], 20, 20, 256, 384, null);
						break;
				}
			}
		}
		else
		{
			for (int i = 0; i < 13; i++)
			{
				if (SprCre.apl.PickSex.equals("Male"))
				{
					if (i == 3 && CheckOb.mbootFirst)
					{
						g2.drawImage(BackupM[i + 1], 20, 20, 256, 384, null);
					}
					else if (i == 4 && CheckOb.mbootFirst)
					{
						g2.drawImage(BackupM[i - 1], 20, 20, 256, 384, null);
					}
					else
						g2.drawImage(BackupM[i], 20, 20, 256, 384, null);
				}
				else if (SprCre.apl.PickSex.equals("Female"))
				{
					if (i == 3 && CheckOb.fbootFirst)
					{
						g2.drawImage(BackupF[i + 1], 20, 20, 256, 384, null);
					}
					else if (i == 4 && CheckOb.fbootFirst)
					{
						g2.drawImage(BackupF[i - 1], 20, 20, 256, 384, null);
					}
					else
						g2.drawImage(BackupF[i], 20, 20, 256, 384, null);
				}
			}
		}
		//g2.drawImage(Colorizer.colorizer.BackupM[2], 300,20,Colorizer.colorizer.cframe);
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
	public boolean quit = false, Loaded = false;
	private Graphics dbg;
	private static Image dbImage;
}
