package circle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class timerHandler 
{
	public Timer displayTimer = null;
	public Timer displayTimer2 = null;
	public Timer displayTimer3 = null;
	public int SetIconImageFrame = 5, SetIconImageFrameInc = 4;
	public static int SetIconImageFrameHold = 5;
	public timerHandler()
	{
		ActionListener listener2 = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				/*
				 * This controls what is enabled with the Opacity slider
				 * depending on if you are currently selecting male or female
				 * */
				if (SprCre.MisGhost && SprCre.PickSex.equals("Male") || SprCre.FisGhost && SprCre.PickSex.equals("Female"))
				{
					SprCre.GhostC.setSelected(true);
					SprCre.OpacitySlider.setEnabled(true);
					if (SprCre.PickSex.equals("Female"))
						SprCre.OpacitySlider.setValue((int)(SprCre.Fopacity * 100));
					else if (SprCre.PickSex.equals("Male"))
						SprCre.OpacitySlider.setValue((int)(SprCre.Mopacity * 100));
				}
				/*
				 * This keeps the ghost check box status (Selected or Not) when you select between
				 * Male and Female
				 * */
				if (!SprCre.MisGhost && SprCre.PickSex.equals("Male") || !SprCre.FisGhost && SprCre.PickSex.equals("Female"))
				{
					SprCre.GhostC.setSelected(false);
					SprCre.OpacitySlider.setEnabled(false);
				}
				/*
				 * This keeps the NoOpBody check box status (Selected or Not) when you select between
				 * Male and Female
				 * */
				if (SprCre.MHaveBody && SprCre.PickSex.equals("Male") || SprCre.FHaveBody && SprCre.PickSex.equals("Female"))
					SprCre.HaveBodyC.setSelected(true);
				if (!SprCre.MHaveBody && SprCre.PickSex.equals("Male") || !SprCre.FHaveBody && SprCre.PickSex.equals("Female"))
					SprCre.HaveBodyC.setSelected(false);
			}
		};
		displayTimer2 = new Timer(50, listener2);
		displayTimer2.start();
		ActionListener listener = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				/*
				 * This controls the main animation window
				 * */
				if (SprCre.NSplitFrame == 8)
					SprCre.NSplitFrame += SprCre.NSplitFrameInc;
				else
				{
					SprCre.NSplitFrame = 8;
					SprCre.NSplitFrameInc = -SprCre.NSplitFrameInc;
				}
			}
		};
		displayTimer = new Timer(200, listener);
		displayTimer.start();
		ActionListener listener3 = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				/*
				 * This controls the window Icon
				 * */
				if (SetIconImageFrame == SetIconImageFrameHold)
					SetIconImageFrame += SetIconImageFrameInc;
				else
				{
					SetIconImageFrame = SetIconImageFrameHold;
					SetIconImageFrameInc = -SetIconImageFrameInc;
				}
				/*
				 * Try to set the icon. This only fails on program start
				 * requires a try catch to avoid any problems
				 */
				try {
					SprCre.frame.setIconImage(ImgHandler.bi[SetIconImageFrame]);
				} catch (NullPointerException NPE){
					//What?? Ignore and continue
				}
			}
		};
		displayTimer3 = new Timer(200, listener3);
		displayTimer3.start();
	}
}
