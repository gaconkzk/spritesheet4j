package circle.ui;


import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import circle.Cons;
import circle.SprCre;
import circle.handlers.ImgHandler;
import circle.handlers.timerHandler;

public class createButtons implements Cons
{
	public createButtons(){}
	public void PartButtons (int x, int y, int sx, int sy, final String label)
	{
		JButton butt = new JButton(label);
		butt.setBounds(x, y, sx, sy);
		SprCre.apl.add(butt);
		butt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ChangeLabel(label);
				ImgHandler.doneWithFirstBoard = true;
				int i = 0;
				for (String g : ObjectsUseable)
				{
					if (g.equals(label))
					{
						if (SprCre.apl.PickSex.equals("Male"))
						{
							SprCre.cho.MobSelected = i;
							SprCre.cho.obSelected = SprCre.cho.MobSelected;
							break;
						}
						if (SprCre.apl.PickSex.equals("Female"))
						{
							SprCre.cho.FobSelected = i;
							SprCre.cho.obSelected = SprCre.cho.FobSelected;
							break;
						}
						ImgHandler.AllSplit = SprCre.IH.StackImage();
						ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
					}
					else
						i++;
				}
			}
		});
	}
	public void ArrowKeys(int x, int y, int sx, int sy, final int behold, Image iconImage)
	{
		JButton butt = new JButton();
		butt.setBounds(x, y, sx, sy);
		butt.setIcon(new ImageIcon(iconImage));
		SprCre.apl.add(butt);
		butt.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
				timerHandler.SetIconImageFrameHold = behold;
			}
		});
	}
	private void ChangeLabel(String label)
	{
		if (SprCre.apl.PickSex.equals("Male"))
			SprCre.cho.MObjectSelect = label;
		else
			SprCre.cho.FObjectSelect = label;
		SprCre.cho.ObjectSelect = label;
	}
	public void addColorJButton(int x, int y, final Color color, final Color TextColor, final String scolor)
	{
		final JButton butt = new JButton()
		{
			private static final long serialVersionUID = 1L;
			public JToolTip createToolTip()
			{
				JToolTip tip = super.createToolTip();
				tip.setBackground(color);
		        tip.setForeground(TextColor);
				return tip;
			}
		};
		butt.setBounds(x, y, UIControl.ColorButtonSize, UIControl.ColorButtonSize);
		butt.setBackground(color);
		SprCre.apl.add(butt);
		butt.addMouseListener(new MouseAdapter()
		{
			@Override public void mouseEntered(MouseEvent arg0) 
			{
				butt.setToolTipText(scolor + " Human " + SprCre.apl.PickSex);
			}
		});
		butt.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (SprCre.apl.PickSex.equals("Male"))
					if (!SprCre.cho.MTypeBody.equals(scolor))
						SprCre.cho.MTypeBody = scolor;
				if (SprCre.apl.PickSex.equals("Female"))
					if (!SprCre.cho.FTypeBody.equals(scolor))
						SprCre.cho.FTypeBody = scolor;
				if (SprCre.apl.PickSex.equals("Male"))
		        	ImgHandler.MneedsBody = true;
		        else if (SprCre.apl.PickSex.equals("Female"))
		        	ImgHandler.FneedsBody = true;
				UIControl.HaveBodyC.setEnabled(true);
				UIControl.ShowEarsC.setEnabled(true);
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
				SprCre.apl.repaint();
			}
		});
		UIControl.tempColorButtonSize += UIControl.ColorButtonSize;
	}
}
