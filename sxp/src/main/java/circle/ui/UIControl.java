package circle.ui;


import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import circle.SprCre;
import circle.handlers.CheckOb;
import circle.handlers.IOHandler;
import circle.handlers.ImgHandler;
import circle.handlers.timerHandler;

public class UIControl
{
	public static boolean SaveAsVX = false;
	public static boolean pauseanim = false;
	private final int PartButtonX = SprCre.apl.PartSelectWindowX;
	private final int PartButtonY = SprCre.apl.PartSelectWindowY - 92;
	private final int PartButtonSizeX = 106, PartButtonSizeY = 20;
	public static int ColorButtonSize = 27; //27
	public static final int ColorButtonX = 5, ColorButtonY = 32; //32
	public static final int ColorButtonLineMaxX = (ColorButtonSize * 8);
	public static final int OPSliderX = ColorButtonX + 60;
	public static final int OPSliderY = ColorButtonY + 350;
	public final int arrowsX = 95;
	public final int arrowsY = 285;
	public createButtons CB = new createButtons();
	private Image animpause = SprCre.IH.load(SprCre.IH.ImageLocation + "Images/pause.png");
	public static JButton MaleC, FemaleC;
	
	public static JSlider OpacitySlider;
	
	public static JCheckBox GhostC, HaveBodyC, NoOpBaseC;
	public static JCheckBox ShowEarsC, SaveAllLayersC, SwapBootC, SaveAsVXC, LrgPreview;

	public static int tempColorButtonSize;
	public static int frameScale = 2;
	
	public static int getFrameScale() { return frameScale; }
	public static void setFrameScale(int fs) { frameScale = fs; }
	
	public UIControl()
	{
		int k = 0;
		int addPart = 0;
		for (int i = 0; i < SprCre.ObjectsUseable.length; i++)
		{
			CB.PartButtons(PartButtonX + (PartButtonSizeX * k++), PartButtonY + addPart, PartButtonSizeX, PartButtonSizeY, SprCre.ObjectsUseable[i]);
			if (k > 5)
			{
				addPart += 20;
				k = 0;
			}
		}
		tempColorButtonSize = 0;
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(255, 217, 173), new Color(0, 0, 0), "White");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(40, 254, 4), new Color(0, 0, 0), "Green");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(89, 167, 255), new Color(0, 0, 0), "Blue");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(254, 132, 135), new Color(0, 0, 0), "Red");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(109, 60, 36), new Color(255, 255, 255), "Black");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(255, 231, 67), new Color(0, 0, 0), "Yellow");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(215, 110, 254), new Color(0, 0, 0), "Purple");
		CB.addColorJButton(ColorButtonX + tempColorButtonSize, ColorButtonY, new Color(255, 178, 109), new Color(0, 0, 0), "Orange");

		CB.ArrowKeys(arrowsX + 34, arrowsY, 42, 20, 2, SprCre.IH.load(SprCre.IH.ImageLocation + "Images/larrow.png"));
		CB.ArrowKeys(arrowsX + 76, arrowsY, 42, 20, 3, SprCre.IH.load(SprCre.IH.ImageLocation + "Images/rarrow.png"));
		CB.ArrowKeys(arrowsX - 50, arrowsY, 42, 20, 4, SprCre.IH.load(SprCre.IH.ImageLocation + "Images/uarrow.png"));
		CB.ArrowKeys(arrowsX - 8, arrowsY, 42, 20, 1, SprCre.IH.load(SprCre.IH.ImageLocation + "Images/darrow.png"));
		
		OpacitySlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
		OpacitySlider.setBounds(OPSliderX - 50, OPSliderY + 3, 180, 20);
		OpacitySlider.addChangeListener(new ChangeListener()
		{
			@Override public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider)e.getSource();
				float opacity = (float)source.getValue() / 100;
				if (SprCre.apl.PickSex.equals("Male"))
					SprCre.cho.Mopacity = opacity;
				if (SprCre.apl.PickSex.equals("Female"))
					SprCre.cho.Fopacity = opacity;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(OpacitySlider);
		SaveAsVXC = new JCheckBox("Save As VX");
		SaveAsVXC.setFont(SprCre.apl.secondFont);
		SaveAsVXC.setBounds(15, 425, 95, 20);
		SaveAsVXC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				SaveAsVX = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(SaveAsVXC);
		SwapBootC = new JCheckBox("Boots Over Pants");
		SwapBootC.setFont(SprCre.apl.secondFont);
		SwapBootC.setBounds(15, 345, 130, 20);
		SwapBootC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				if (SprCre.apl.PickSex.equals("Male"))
					CheckOb.mbootFirst = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				if (SprCre.apl.PickSex.equals("Female"))
					CheckOb.fbootFirst = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(SwapBootC);
		ShowEarsC = new JCheckBox("Show Ears");
		ShowEarsC.setFont(SprCre.apl.secondFont);
		ShowEarsC.setBounds(15, 325, 90, 20);
		ShowEarsC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				if (SprCre.apl.PickSex.equals("Male"))
					SprCre.IH.MhasEars = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				if (SprCre.apl.PickSex.equals("Female"))
					SprCre.IH.FhasEars = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(ShowEarsC);
		HaveBodyC = new JCheckBox("Show Base");
		HaveBodyC.setFont(SprCre.apl.secondFont);
		HaveBodyC.setBounds(ColorButtonX + 10, ColorButtonY + 275, 90, 20);
		HaveBodyC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				if (SprCre.apl.PickSex.equals("Male"))
					CheckOb.MHaveBody = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				if (SprCre.apl.PickSex.equals("Female"))
					CheckOb.FHaveBody = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(HaveBodyC);
		SaveAllLayersC = new JCheckBox("Save All Layers");
		SaveAllLayersC.setFont(SprCre.apl.secondFont);
		SaveAllLayersC.setBounds(15, 405, 125, 20);
		SaveAllLayersC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				IOHandler.normalSave = e.getStateChange() == ItemEvent.SELECTED ? false : true;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(SaveAllLayersC);
		GhostC = new JCheckBox("Enable Opacity");
		GhostC.setFont(SprCre.apl.secondFont);
		GhostC.setBounds(15, 365, 120, 20);
		GhostC.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{	
				if (SprCre.apl.PickSex.equals("Male"))
					CheckOb.MisGhost = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				if (SprCre.apl.PickSex.equals("Female"))
					CheckOb.FisGhost = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(GhostC);
		LrgPreview = new JCheckBox("");
		LrgPreview.setFont(SprCre.apl.secondFont);
		LrgPreview.setBounds(15, arrowsY, 20, 20);
		LrgPreview.addItemListener(new ItemListener()
		{
			@Override public void itemStateChanged(ItemEvent e)
			{
				setFrameScale(e.getStateChange() == ItemEvent.SELECTED ? 1 : 2);
				timerHandler.SetIconImageFrameHold = 1;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(LrgPreview);
		JButton t = new JButton("");
		t.setIcon(new ImageIcon(animpause));
		t.setBounds(225, arrowsY, 20, 20);
		t.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				pauseanim = !pauseanim;
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		SprCre.apl.add(t);
		MaleC = new JButton("Male");
		MaleC.setBounds(ColorButtonX, ColorButtonY + 30, 120, 20);
		SprCre.apl.add(MaleC);
		MaleC.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{	
				SprCre.cho.ObjectSelect = SprCre.cho.MObjectSelect;
				SprCre.apl.PickSex = MaleC.getText();
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
		FemaleC = new JButton("Female");
		FemaleC.setBounds(ColorButtonX + 136, ColorButtonY + 30, 120, 20);
		SprCre.apl.add(FemaleC);
		FemaleC.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				SprCre.cho.ObjectSelect = SprCre.cho.FObjectSelect;
				SprCre.apl.PickSex = FemaleC.getText();
				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);
			}
		});
	}
	public static int getColorButtonSize()
	{
		return ColorButtonSize;
	}
}
