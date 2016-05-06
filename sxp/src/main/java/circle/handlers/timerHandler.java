package circle.handlers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import circle.SprCre;
import circle.ui.UIControl;

public class timerHandler 
{
	public int SetIconImageFrame = 5, SetIconImageFrameInc = 4;
	public static int SetIconImageFrameHold = 1;
	public int NSplitFrame = 4, NSplitFrameInc = 4;
	public Timer displayTimer1, displayTimer2, displayTimer3;
	public timerHandler()
	{
		ActionListener listener2 = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (SprCre.apl.PickSex.equals("Male"))
					SprCre.cho.obSelected = SprCre.cho.MobSelected;
				if (SprCre.apl.PickSex.equals("Female"))
					SprCre.cho.obSelected = SprCre.cho.FobSelected;
				if (CheckOb.MisGhost && SprCre.apl.PickSex.equals("Male") || CheckOb.FisGhost && SprCre.apl.PickSex.equals("Female"))
				{
					UIControl.GhostC.setSelected(true);
					UIControl.OpacitySlider.setEnabled(true);
					if (SprCre.apl.PickSex.equals("Female"))
						UIControl.OpacitySlider.setValue((int)(SprCre.cho.Fopacity * 100));
					else if (SprCre.apl.PickSex.equals("Male"))
						UIControl.OpacitySlider.setValue((int)(SprCre.cho.Mopacity * 100));
				}
				if (!CheckOb.MisGhost && SprCre.apl.PickSex.equals("Male") || !CheckOb.FisGhost && SprCre.apl.PickSex.equals("Female"))
				{
					UIControl.GhostC.setSelected(false);
					UIControl.OpacitySlider.setEnabled(false);
				}
				if (SprCre.apl.PickSex.equals("Male"))
					UIControl.SwapBootC.setSelected(CheckOb.mbootFirst);
				if (SprCre.apl.PickSex.equals("Female"))
					UIControl.SwapBootC.setSelected(CheckOb.fbootFirst);
				
				if (SprCre.IH.MhasEars && SprCre.apl.PickSex.equals("Male") || SprCre.IH.FhasEars && SprCre.apl.PickSex.equals("Female"))
					UIControl.ShowEarsC.setSelected(true);
				if (!SprCre.IH.MhasEars && SprCre.apl.PickSex.equals("Male") || !SprCre.IH.FhasEars && SprCre.apl.PickSex.equals("Female"))
					UIControl.ShowEarsC.setSelected(false);
				if (CheckOb.MHaveBody && SprCre.apl.PickSex.equals("Male") || CheckOb.FHaveBody && SprCre.apl.PickSex.equals("Female"))
					UIControl.HaveBodyC.setSelected(true);
				if (!CheckOb.MHaveBody && SprCre.apl.PickSex.equals("Male") || !CheckOb.FHaveBody && SprCre.apl.PickSex.equals("Female"))
					UIControl.HaveBodyC.setSelected(false);
			}
		};
		displayTimer2 = new Timer(50, listener2);
		ActionListener listener = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if(!UIControl.pauseanim)
				{
					if (NSplitFrame >= 4 && NSplitFrame <= 12)
						NSplitFrame += NSplitFrameInc;
					else
						NSplitFrame = 4;
				}
				else
					NSplitFrame = 4;
			}
		};
		displayTimer1 = new Timer(200, listener);
		ActionListener listener3 = new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (!UIControl.pauseanim)
				{
					if (SetIconImageFrame >= SetIconImageFrameHold && SetIconImageFrame <= 12)
						SetIconImageFrame += SetIconImageFrameInc;
					else
						SetIconImageFrame = SetIconImageFrameHold;
				}
				SprCre.frame.setIconImage(SprCre.IH.MakeSmaller(ImgHandler.AllSplit)[SetIconImageFrame]);
			}
		};
		displayTimer3 = new Timer(200, listener3);
	}
	public void StartTimers()
	{
		SprCre.cho.MTypeBody = "White";
		SprCre.cho.FTypeBody = "White";
		displayTimer1.start();
		displayTimer2.start();
		displayTimer3.start();
	}
}
