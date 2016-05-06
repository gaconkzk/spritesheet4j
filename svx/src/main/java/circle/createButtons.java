package circle;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;


public class createButtons 
{
	public createButtons()
	{

	}
	public void BodyButton(final String label, int x, int y, int sx, int sy)
	{
		JButton butt = new JButton(label);
		butt.setBounds(x, y, sx, sy);
		butt.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (SprCre.PickSex.equals("Male"))
					SprCre.MTypeBody = label;
				if (SprCre.PickSex.equals("Female"))
					SprCre.FTypeBody = label;
				SprCre.apl.repaint();
			}
		});
		SprCre.apl.add(butt);
	}
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
			}
		});
	}
	/*
	 * This is for creating the arrow keys that control the direction of the animated Icon in the 
	 * title bar
	 * */
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
				timerHandler.SetIconImageFrameHold = behold;
			}
		});
	}
	/*
	 * This is for controlling which Object to work with Hair, Body, Hairop ect...
	 * */
	private void ChangeLabel(String label)
	{
		if (SprCre.PickSex.equals("Male"))
			SprCre.MObjectSelect = label;
		else
			SprCre.FObjectSelect = label;
		SprCre.ObjectSelect = label;
	}
	/*
	 * This is for controlling the color buttons that change the skin color
	 * It sets the Color of the button and text and then uses the String scolor
	 * to keep track of what color is selected.
	 * */
	
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
		butt.setBounds(x, y, SprCre.ColorButtonSize, SprCre.ColorButtonSize);
		butt.setBackground(color);
		SprCre.apl.add(butt);
		butt.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				butt.setToolTipText(scolor + " Human " + SprCre.PickSex);	
			}
		});
		butt.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (SprCre.PickSex.equals("Male"))
					if (!SprCre.MTypeBody.equals(scolor))
						SprCre.MTypeBody = scolor;
				if (SprCre.PickSex.equals("Female"))
					if (!SprCre.FTypeBody.equals(scolor))
						SprCre.FTypeBody = scolor;
			//	ImgHandler.AllSplit = SprCre.IH.StackImage();
				SprCre.apl.repaint();
			}
		});
		SprCre.tempColorButtonSize += SprCre.ColorButtonSize;
	}
}
