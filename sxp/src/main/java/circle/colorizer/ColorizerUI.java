package circle.colorizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import circle.SprCre;
import circle.handlers.ImgHandler;

public class ColorizerUI
{
	public static JTextField	lumTF, briTF, conTF, toneTF, shadeTF;

	private JLabel				lumL, briL, conL, toneL, shadeL;

	private JButton				DefaultCBC, DefaultHSBC;

	private JCheckBox			EnableHSBJ;

	public static JSlider		lumJ, briJ, conJ, toneJ, shadeJ;

	private JComboBox<String>	sublayerListJ, layerListJ;

	private int					SlidersX		= 430, SlidersY = 225;
	String						bPickSex		= SprCre.apl.PickSex;

	public boolean				applypressed	= true;

	private void setSwitch(String s)
	{
		if (SprCre.apl.PickSex.equals("Male"))
		{
			if (s.equals("lum") || s.equals("all"))
			{
				lumTF.setText(String.valueOf((int) SprCre.mColorObject.getBrightness(Colorizer.colorizer.layerSelected)));
				lumJ.setValue((int) SprCre.mColorObject.getBrightness(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("bri") || s.equals("all"))
			{
				briTF.setText(String.valueOf((int) SprCre.mColorObject.getBrightness(Colorizer.colorizer.layerSelected)));
				briJ.setValue((int) SprCre.mColorObject.getBrightness(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("con") || s.equals("all"))
			{
				conTF.setText(String.valueOf((int) SprCre.mColorObject.getContrast(Colorizer.colorizer.layerSelected)));
				conJ.setValue((int) SprCre.mColorObject.getContrast(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("tone") || s.equals("all"))
			{
				toneTF.setText(String.valueOf((int) SprCre.mColorObject.getTone(Colorizer.colorizer.layerSelected)));
				toneJ.setValue((int) SprCre.mColorObject.getTone(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("sha") || s.equals("all"))
			{
				shadeTF.setText(String.valueOf((int) SprCre.mColorObject.getShade(Colorizer.colorizer.layerSelected)));
				shadeJ.setValue((int) SprCre.mColorObject.getShade(Colorizer.colorizer.layerSelected));
			}
		}
		else if (SprCre.apl.PickSex.equals("Female"))
		{
			if (s.equals("lum") || s.equals("all"))
			{
				lumTF.setText(String.valueOf((int) SprCre.fColorObject.getBrightness(Colorizer.colorizer.layerSelected)));
				lumJ.setValue((int) SprCre.fColorObject.getBrightness(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("bri") || s.equals("all"))
			{
				briTF.setText(String.valueOf((int) SprCre.fColorObject.getBrightness(Colorizer.colorizer.layerSelected)));
				briJ.setValue((int) SprCre.fColorObject.getBrightness(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("con") || s.equals("all"))
			{
				conTF.setText(String.valueOf((int) SprCre.fColorObject.getContrast(Colorizer.colorizer.layerSelected)));
				conJ.setValue((int) SprCre.fColorObject.getContrast(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("tone") || s.equals("all"))
			{
				toneTF.setText(String.valueOf((int) SprCre.fColorObject.getTone(Colorizer.colorizer.layerSelected)));
				toneJ.setValue((int) SprCre.fColorObject.getTone(Colorizer.colorizer.layerSelected));
			}
			if (s.equals("sha") || s.equals("all"))
			{
				shadeTF.setText(String.valueOf((int) SprCre.fColorObject.getShade(Colorizer.colorizer.layerSelected)));
				shadeJ.setValue((int) SprCre.fColorObject.getShade(Colorizer.colorizer.layerSelected));
			}
		}
	}

	public static void doLuminocity(int amount, int over)
	{
		int i = over;
		if (i == 2)
		{
			if (SprCre.apl.PickSex.equals("Male"))
				ImgHandler.MneedsBody = false;
			else if (SprCre.apl.PickSex.equals("Female"))
				ImgHandler.FneedsBody = false;
		}
		String s = SprCre.apl.PickSex;

		BufferedImage bi = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
		BufferedImage bi2 = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));

		if (s.equals("Male"))
		{
			Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];
			SprCre.mColorObject.setLuminocity(i, amount);
			bi2 = Colorizer.colorizer.BackupM[i];

		}
		else if (s.equals("Female"))
		{
			Colorizer.colorizer.BackupF[i] = SprCre.fColorObject.image[i];
			SprCre.fColorObject.setLuminocity(i, amount);
			bi2 = Colorizer.colorizer.BackupF[i];

		}

		bi = Colorizer.CBH.changeLuminocity(bi2, amount);

		if (s.equals("Male"))
		{
			bi2 = SprCre.mColorObject.preColor(i, bi, "t");
			bi = SprCre.mColorObject.preColor(i, bi2, "s");
			bi2 = SprCre.mColorObject.preColor(i, bi, "b");
			bi = SprCre.mColorObject.preColor(i, bi2, "c");
			Colorizer.colorizer.BackupM[i] = deepCopy(bi);
			SprCre.mColorObject.setneedStack(i, false);
		}
		else if (s.equals("Female"))
		{
			bi2 = SprCre.mColorObject.preColor(i, bi, "t");
			bi = SprCre.fColorObject.preColor(i, bi2, "s");
			bi2 = SprCre.fColorObject.preColor(i, bi, "b");
			bi = SprCre.fColorObject.preColor(i, bi2, "c");
			Colorizer.colorizer.BackupF[i] = deepCopy(bi);
			SprCre.fColorObject.setneedStack(i, false);
		}
	}

	public static void doTone(int amount, int over)
	{
		int i = over;
		if (i == 2)
		{
			if (SprCre.apl.PickSex.equals("Male"))
				ImgHandler.MneedsBody = false;
			else if (SprCre.apl.PickSex.equals("Female"))
				ImgHandler.FneedsBody = false;
		}
		String s = SprCre.apl.PickSex;

		BufferedImage bi = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
		BufferedImage bi2 = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));

		if (s.equals("Male"))
		{
			Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];
			SprCre.mColorObject.setTone(i, amount);
			bi = Colorizer.colorizer.BackupM[i];

			bi2 = SprCre.mColorObject.preColor(i, bi, "l");

		}
		else if (s.equals("Female"))
		{
			Colorizer.colorizer.BackupF[i] = SprCre.fColorObject.image[i];
			SprCre.fColorObject.setTone(i, amount);
			bi = Colorizer.colorizer.BackupF[i];

			bi2 = SprCre.fColorObject.preColor(i, bi, "l");
		}

		bi = Colorizer.CBH.changeTone(bi2, amount);

		if (s.equals("Male"))
		{
			bi2 = SprCre.mColorObject.preColor(i, bi, "s");
			bi = SprCre.mColorObject.preColor(i, bi2, "b");
			bi2 = SprCre.mColorObject.preColor(i, bi, "c");
			Colorizer.colorizer.BackupM[i] = deepCopy(bi2);
			SprCre.mColorObject.setneedStack(i, false);
		}
		else if (s.equals("Female"))
		{
			bi2 = SprCre.fColorObject.preColor(i, bi, "s");
			bi = SprCre.fColorObject.preColor(i, bi2, "b");
			bi2 = SprCre.fColorObject.preColor(i, bi, "c");
			Colorizer.colorizer.BackupF[i] = deepCopy(bi2);
			SprCre.fColorObject.setneedStack(i, false);
		}
	}

	public static void doShade(int amount, int over)
	{
		int i = over;
		if (i == 2)
		{
			if (SprCre.apl.PickSex.equals("Male"))
				ImgHandler.MneedsBody = false;
			else if (SprCre.apl.PickSex.equals("Female"))
				ImgHandler.FneedsBody = false;
		}
		String s = SprCre.apl.PickSex;

		BufferedImage bi = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
		BufferedImage bi2 = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));

		if (s.equals("Male"))
		{
			Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];
			SprCre.mColorObject.setShade(i, amount);
			bi2 = Colorizer.colorizer.BackupM[i];
			bi = SprCre.mColorObject.preColor(i, bi2, "sat");
			bi2 = SprCre.mColorObject.preColor(i, bi, "l");
			bi = SprCre.mColorObject.preColor(i, bi2, "t");

		}
		else if (s.equals("Female"))
		{
			Colorizer.colorizer.BackupF[i] = SprCre.fColorObject.image[i];
			SprCre.fColorObject.setShade(i, amount);
			bi2 = Colorizer.colorizer.BackupF[i];
			bi = SprCre.fColorObject.preColor(i, bi2, "sat");
			bi2 = SprCre.fColorObject.preColor(i, bi, "l");
			bi = SprCre.fColorObject.preColor(i, bi2, "t");
		}

		bi2 = Colorizer.CBH.changeShade(bi, amount);
		if (s.equals("Male"))
		{
			bi = SprCre.mColorObject.preColor(i, bi2, "b");
			bi2 = SprCre.mColorObject.preColor(i, bi, "c");
			Colorizer.colorizer.BackupM[i] = deepCopy(bi2);
			SprCre.mColorObject.setneedStack(i, false);
		}
		else if (s.equals("Female"))
		{
			bi = SprCre.fColorObject.preColor(i, bi2, "b");
			bi2 = SprCre.fColorObject.preColor(i, bi, "c");
			Colorizer.colorizer.BackupF[i] = deepCopy(bi2);
			SprCre.fColorObject.setneedStack(i, false);
		}
	}

	public static void doBright(int amount, int over)
	{
		int i = over;
		if (i == 2)
		{
			if (SprCre.apl.PickSex.equals("Male"))
				ImgHandler.MneedsBody = false;
			else if (SprCre.apl.PickSex.equals("Female"))
				ImgHandler.FneedsBody = false;
		}
		String s = SprCre.apl.PickSex;
		BufferedImage bi = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
		BufferedImage bi2 = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));

		if (s.equals("Male"))
		{
			Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];
			SprCre.mColorObject.setBrightness(i, amount);
			bi2 = Colorizer.colorizer.BackupM[i];// SprCre.mColorObject.image[i];

			bi = SprCre.mColorObject.preColor(i, bi2, "l");
			bi2 = SprCre.mColorObject.preColor(i, bi, "t");
			bi = SprCre.mColorObject.preColor(i, bi2, "s");
		}
		else if (s.equals("Female"))
		{
			Colorizer.colorizer.BackupF[i] = SprCre.fColorObject.image[i];
			SprCre.fColorObject.setBrightness(i, amount);
			bi2 = Colorizer.colorizer.BackupF[i];// SprCre.mColorObject.image[i];

			bi = SprCre.fColorObject.preColor(i, bi2, "l");
			bi2 = SprCre.fColorObject.preColor(i, bi, "t");
			bi = SprCre.fColorObject.preColor(i, bi2, "s");
		}

		bi2 = Colorizer.CBH.changeBrightness(bi, amount);

		if (s.equals("Male"))
		{
			bi = SprCre.mColorObject.preColor(i, bi2, "c");
			Colorizer.colorizer.BackupM[i] = deepCopy(bi);
			SprCre.mColorObject.setneedStack(i, false);
		}
		else if (s.equals("Female"))
		{
			bi = SprCre.fColorObject.preColor(i, bi2, "c");
			Colorizer.colorizer.BackupF[i] = deepCopy(bi);
			SprCre.fColorObject.setneedStack(i, false);
		}
	}

	public static void doContrast(int amount, int over)
	{

		int i = over;
		if (i == 2)
		{
			if (SprCre.apl.PickSex.equals("Male"))
				ImgHandler.MneedsBody = false;
			else if (SprCre.apl.PickSex.equals("Female"))
				ImgHandler.FneedsBody = false;
		}
		String s = SprCre.apl.PickSex;

		BufferedImage bi = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
		BufferedImage bi2 = deepCopy(SprCre.IH.toBufferedImage(SprCre.apl.AllBlank));
		if (s.equals("Male"))
		{
			Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];
			bi = Colorizer.colorizer.BackupM[i];
			bi2 = SprCre.mColorObject.preColor(i, bi, "l");
			bi = SprCre.mColorObject.preColor(i, bi2, "t");
			bi2 = deepCopy(SprCre.mColorObject.preColor(i, bi, "s"));
			bi = deepCopy(SprCre.mColorObject.preColor(i, bi2, "b"));
			Colorizer.colorizer.BackupM[i] = bi;// Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];

			SprCre.mColorObject.setContrast(i, amount);

			bi2 = Colorizer.colorizer.BackupM[i];// SprCre.mColorObject.image[i];

			bi = Colorizer.CBH.changeContrast(bi2, amount);

			Colorizer.colorizer.BackupM[i] = deepCopy(bi);
		}
		else if (s.equals("Female"))
		{
			Colorizer.colorizer.BackupF[i] = SprCre.fColorObject.image[i];
			bi = Colorizer.colorizer.BackupF[i];
			bi2 = SprCre.fColorObject.preColor(i, bi, "l");
			bi = SprCre.fColorObject.preColor(i, bi2, "t");
			bi2 = deepCopy(SprCre.fColorObject.preColor(i, bi, "s"));
			bi = deepCopy(SprCre.fColorObject.preColor(i, bi2, "b"));
			Colorizer.colorizer.BackupF[i] = bi;// Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];

			SprCre.fColorObject.setContrast(i, amount);

			bi2 = Colorizer.colorizer.BackupF[i];// SprCre.mColorObject.image[i];

			bi = Colorizer.CBH.changeContrast(bi2, amount);

			Colorizer.colorizer.BackupF[i] = deepCopy(bi);
		}
	}

	//public static boolean bodychanged = false;

	private Thread	t;

	public ColorizerUI()
	{
		t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (Colorizer.colorizer.keepgoing)
				{
					if (!bPickSex.equals(SprCre.apl.PickSex))
					{

						if (SprCre.apl.PickSex.equals("Male"))
						{
							if (SprCre.mColorObject.getHSBFlag(Colorizer.colorizer.layerSelected))
								enableHSB();
							else
								disableHSB();
							bPickSex = "Male";
						}
						else if (SprCre.apl.PickSex.equals("Female"))
						{
							if (SprCre.fColorObject.getHSBFlag(Colorizer.colorizer.layerSelected))
								enableHSB();
							else
								disableHSB();
							bPickSex = "Female";
						}
						setSwitch("all");
					}
					try
					{
						Thread.sleep(20);
					} catch (InterruptedException e)
					{

						e.printStackTrace();
					}
				}
				Colorizer.colorizer = null;
				Colorizer.CBH = null;
				Colorizer.cui = null;

			}

		});
		t.start();
		JButton apply = new JButton("Apply");
		apply.setBounds(SlidersX + 50, SlidersY - 170, 90, 20);
		apply.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SprCre.mColorObject.image = Colorizer.colorizer.BackupM.clone();

				SprCre.fColorObject.image = Colorizer.colorizer.BackupF.clone();

				ImgHandler.AllSplit = SprCre.IH.StackImage();
				ImgHandler.bi = SprCre.IH.MakeSmaller(ImgHandler.AllSplit);

				lumJ.setValue(0);
				lumTF.setText("0");
				toneJ.setValue(0);
				toneTF.setText("0");
				shadeJ.setValue(0);
				shadeTF.setText("0");
				briJ.setValue(0);
				briTF.setText("0");
				conJ.setValue(50);
				conTF.setText("50");
				applypressed = true;
				if (!Colorizer.cui.applypressed)
				{
					int result = JOptionPane.showConfirmDialog(null, "Really exit?" + '\n' + "You will lose all changes if you do not press apply.", "Really Exit?", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION)
					{
						Colorizer.colorizer.cframe.dispose();
					}
				}
				else
					Colorizer.colorizer.cframe.dispose();
			}
		});
		Colorizer.colorizer.add(apply);

		lumJ = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		lumJ.setBounds(SlidersX, SlidersY + 40, 180, 20);
		lumJ.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				lumTF.setText(String.valueOf(source.getValue()));
				if (SprCre.apl.PickSex.equals("Male"))
				{
					doLuminocity((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doLuminocity((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doLuminocity((int) source.getValue(), 1);
				}
				else if (SprCre.apl.PickSex.equals("Female"))
				{
					doLuminocity((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doLuminocity((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doLuminocity((int) source.getValue(), 1);
				}
				applypressed = false;
			}

		});
		Colorizer.colorizer.add(lumJ);

		toneJ = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		toneJ.setBounds(SlidersX, SlidersY + 60, 180, 20);
		toneJ.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				toneTF.setText(String.valueOf(source.getValue()));
				if (SprCre.apl.PickSex.equals("Male"))
				{
					doTone((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doTone((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doTone((int) source.getValue(), 1);
				}
				else if (SprCre.apl.PickSex.equals("Female"))
				{
					doTone((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doTone((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doTone((int) source.getValue(), 1);
				}
				applypressed = false;
			}

		});
		Colorizer.colorizer.add(toneJ);
		shadeJ = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		shadeJ.setBounds(SlidersX, SlidersY + 80, 180, 20);
		shadeJ.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				shadeTF.setText(String.valueOf(source.getValue()));
				if (SprCre.apl.PickSex.equals("Male"))
				{
					doShade((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doShade((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doShade((int) source.getValue(), 1);
				}
				else if (SprCre.apl.PickSex.equals("Female"))
				{
					doShade((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doShade((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doShade((int) source.getValue(), 1);
				}
				applypressed = false;
			}

		});
		Colorizer.colorizer.add(shadeJ);
		briJ = new JSlider(JSlider.HORIZONTAL, -128, 127, 0);
		briJ.setBounds(SlidersX, SlidersY + 100, 180, 20);
		briJ.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{

				JSlider source = (JSlider) e.getSource();
				briTF.setText(String.valueOf(source.getValue()));
				if (SprCre.apl.PickSex.equals("Male"))
				{
					doBright((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doBright((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doBright((int) source.getValue(), 1);
				}
				else if (SprCre.apl.PickSex.equals("Female"))
				{
					doBright((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doBright((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doBright((int) source.getValue(), 1);
				}
				applypressed = false;
			}
		});
		Colorizer.colorizer.add(briJ);
		conJ = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		conJ.setBounds(SlidersX, SlidersY + 120, 180, 20);
		conJ.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				conTF.setText(String.valueOf(source.getValue()));
				if (SprCre.apl.PickSex.equals("Male"))
				{
					doContrast((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doContrast((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doContrast((int) source.getValue(), 1);
				}
				else if (SprCre.apl.PickSex.equals("Female"))
				{
					doContrast((int) source.getValue(), Colorizer.colorizer.layerSelected);

					if (Colorizer.colorizer.layerSelected == 2)
						doContrast((int) source.getValue(), 0);
					if (Colorizer.colorizer.layerSelected == 10)
						doContrast((int) source.getValue(), 1);
				}
				applypressed = false;
			}
		});
		Colorizer.colorizer.add(conJ);
		EnableHSBJ = new JCheckBox("Colorize");
		EnableHSBJ.setBounds(SlidersX, SlidersY - 20, 90, 20);
		EnableHSBJ.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				boolean toDo = e.getStateChange() == ItemEvent.SELECTED ? true : false;

				if (toDo)
					enableHSB();
				else
					disableHSB();
				if (SprCre.apl.PickSex.equals("Male"))
					SprCre.mColorObject.setHSBFlag(Colorizer.colorizer.layerSelected, toDo);
				else if (SprCre.apl.PickSex.equals("Female"))
					SprCre.fColorObject.setHSBFlag(Colorizer.colorizer.layerSelected, toDo);
				applypressed = false;
			}
		});
		//Colorizer.colorizer.add(EnableHSBJ);
		EnableHSBJ.setEnabled(false);
		DefaultHSBC = new JButton("Defaults");
		DefaultHSBC.setBounds(SlidersX + 185, SlidersY, 90, 20);
		DefaultHSBC.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				applypressed = false;
			}

		});
		//Colorizer.colorizer.add(DefaultHSBC);
		DefaultCBC = new JButton("Defaults");
		DefaultCBC.setBounds(SlidersX + 185, SlidersY + 100, 90, 20);
		DefaultCBC.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//TODO
				/*
				 * maybe add shade and tone here with luminocity and saturation.
				 * */
				briTF.setText("0");
				briJ.setValue(0);
				conTF.setText("50");
				conJ.setValue(50);
				applypressed = false;
			}

		});
		Colorizer.colorizer.add(DefaultCBC);
		lumTF = new JTextField("50");
		lumTF.setBounds(SlidersX - 50, SlidersY + 40, 40, 20);
		lumTF.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int i = Integer.parseInt(lumTF.getText());
					if (i < 0 || i > 100)
					{
						i = 50;
						lumTF.setText("50");
						lumJ.setValue(i);
					}
					else
						lumJ.setValue(i);
				}
				catch (Exception ex)
				{
					lumJ.setValue(0);
					lumTF.setText("50");
				}
				applypressed = false;
			}

		});
		Colorizer.colorizer.add(lumTF);

		lumL = new JLabel("Saturation: ");
		lumL.setBounds(SlidersX - 116, SlidersY + 40, 90, 20);
		Colorizer.colorizer.add(lumL);

		toneTF = new JTextField("0");
		toneTF.setBounds(SlidersX - 50, SlidersY + 60, 40, 20);
		toneTF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int i = Integer.parseInt(toneTF.getText());
					if (i < 0 || i > 100)
					{
						i = 0;
						toneTF.setText("0");
						toneJ.setValue(i);
					}
					else
						toneJ.setValue(i);
				}
				catch (Exception ex)
				{
					toneJ.setValue(0);
					toneTF.setText("0");
				}
				applypressed = false;
			}
		});
		Colorizer.colorizer.add(toneTF);

		toneL = new JLabel("Tone:");
		toneL.setBounds(SlidersX - 85, SlidersY + 60, 90, 20);
		Colorizer.colorizer.add(toneL);

		shadeTF = new JTextField("0");
		shadeTF.setBounds(SlidersX - 50, SlidersY + 80, 40, 20);
		shadeTF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int i = Integer.parseInt(shadeTF.getText());
					if (i < 0 || i > 100)
					{
						i = 0;
						shadeTF.setText("0");
						shadeJ.setValue(i);
					}
					else
						shadeJ.setValue(i);
				}
				catch (Exception ex)
				{
					shadeJ.setValue(0);
					shadeTF.setText("0");
				}
				applypressed = false;
			}
		});
		Colorizer.colorizer.add(shadeTF);

		shadeL = new JLabel("Shade:");
		shadeL.setBounds(SlidersX - 93, SlidersY + 80, 90, 20);
		Colorizer.colorizer.add(shadeL);

		briTF = new JTextField("0");
		briTF.setBounds(SlidersX - 50, SlidersY + 100, 40, 20);
		briTF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int i = Integer.parseInt(briTF.getText());
					if (i < -256 || i > 256)
					{
						i = 0;
						briTF.setText("0");
						briJ.setValue(i);
					}
					else
						briJ.setValue(i);
				}
				catch (Exception ex)
				{
					briJ.setValue(0);
					briTF.setText("0");
				}
				applypressed = false;
			}
		});
		Colorizer.colorizer.add(briTF);

		briL = new JLabel("Brightness:");
		briL.setBounds(SlidersX - 119, SlidersY + 100, 90, 20);
		Colorizer.colorizer.add(briL);

		conTF = new JTextField("50");
		conTF.setBounds(SlidersX - 50, SlidersY + 120, 40, 20);
		conTF.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int i = Integer.parseInt(conTF.getText());
					if (i < 0 || i > 100)
					{
						i = 50;
						conTF.setText("50");
						conJ.setValue(i);
					}
					else
						conJ.setValue(i);
				}
				catch (Exception ex)
				{
					conJ.setValue(50);
					conTF.setText("50");
				}
				applypressed = false;
			}
		});
		Colorizer.colorizer.add(conTF);

		conL = new JLabel("Contrast:");
		conL.setBounds(SlidersX - 106, SlidersY + 120, 90, 20);
		Colorizer.colorizer.add(conL);

		String[] layers = { "Layer", "_______", "Base", "Boots", "Pants", "Shirt", "Chest", "LongShirt", "Arms", "Waist", "Shoulder", "Hair", "Hat" }; //INDEX STARTS AT 2
		//layers = layers + Cons.ObjectsUseable;
		JLabel layerListLabel = new JLabel("Edit Layer");
		layerListLabel.setBounds(SlidersX - 50, SlidersY - 120, 100, 20);
		Colorizer.colorizer.add(layerListLabel);
		layerListJ = new JComboBox<String>(layers);
		layerListJ.setBounds(SlidersX - 50, SlidersY - 100, 120, 30);
		layerListJ.setSelectedIndex(2);
		layerListJ.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Colorizer.colorizer.layerSelected = layerListJ.getSelectedIndex();
				if (SprCre.apl.PickSex.equals("Male"))
				{
					lumTF.setText(String.valueOf((int) SprCre.mColorObject.getLuminocity(Colorizer.colorizer.layerSelected)));
					lumJ.setValue((int) SprCre.mColorObject.getLuminocity(Colorizer.colorizer.layerSelected));

					toneTF.setText(String.valueOf((int) SprCre.mColorObject.getTone(Colorizer.colorizer.layerSelected)));
					toneJ.setValue((int) SprCre.mColorObject.getTone(Colorizer.colorizer.layerSelected));

					shadeTF.setText(String.valueOf((int) SprCre.mColorObject.getShade(Colorizer.colorizer.layerSelected)));
					shadeJ.setValue((int) SprCre.mColorObject.getShade(Colorizer.colorizer.layerSelected));

					briTF.setText(String.valueOf((int) SprCre.mColorObject.getBrightness(Colorizer.colorizer.layerSelected)));
					briJ.setValue((int) SprCre.mColorObject.getBrightness(Colorizer.colorizer.layerSelected));

					conTF.setText(String.valueOf((int) SprCre.mColorObject.getContrast(Colorizer.colorizer.layerSelected)));
					conJ.setValue((int) SprCre.mColorObject.getContrast(Colorizer.colorizer.layerSelected));

					//					if (SprCre.mColorObject.getHSBFlag(Colorizer.colorizer.layerSelected))
					//						enableHSB();
					//					else
					//						disableHSB();
				}
				else if (SprCre.apl.PickSex.equals("Female"))
				{
					lumTF.setText(String.valueOf((int) SprCre.fColorObject.getLuminocity(Colorizer.colorizer.layerSelected)));
					lumJ.setValue((int) SprCre.fColorObject.getLuminocity(Colorizer.colorizer.layerSelected));

					toneTF.setText(String.valueOf((int) SprCre.fColorObject.getTone(Colorizer.colorizer.layerSelected)));
					toneJ.setValue((int) SprCre.fColorObject.getTone(Colorizer.colorizer.layerSelected));

					shadeTF.setText(String.valueOf((int) SprCre.fColorObject.getShade(Colorizer.colorizer.layerSelected)));
					shadeJ.setValue((int) SprCre.fColorObject.getShade(Colorizer.colorizer.layerSelected));

					briTF.setText(String.valueOf((int) SprCre.fColorObject.getBrightness(Colorizer.colorizer.layerSelected)));
					briJ.setValue((int) SprCre.fColorObject.getBrightness(Colorizer.colorizer.layerSelected));

					conTF.setText(String.valueOf((int) SprCre.fColorObject.getContrast(Colorizer.colorizer.layerSelected)));
					conJ.setValue((int) SprCre.fColorObject.getContrast(Colorizer.colorizer.layerSelected));

					//					if (SprCre.fColorObject.getHSBFlag(Colorizer.colorizer.layerSelected))
					//						enableHSB();
					//					else
					//						disableHSB();
				}
			}
		});
		Colorizer.colorizer.add(layerListJ);

		String[] sublayers = { "All Layers", "Current Layer" };

		JLabel showLayers = new JLabel("Show");
		showLayers.setBounds(SlidersX + 125, SlidersY - 125, 100, 30);
		Colorizer.colorizer.add(showLayers);
		sublayerListJ = new JComboBox<String>(sublayers);
		sublayerListJ.setBounds(SlidersX + 125, SlidersY - 100, 120, 30);
		sublayerListJ.setSelectedIndex(0);
		sublayerListJ.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				//SaveAsVX = e.getStateChange() == ItemEvent.SELECTED ? true : false;
				Colorizer.colorizer.showAllLayers = sublayerListJ.getSelectedIndex() == 0 ? true : false;
				//Colorizer.colorizer.BackupM = SprCre.mColorObject.image.clone();
				//Colorizer.colorizer.BackupF = SprCre.fColorObject.image.clone();
			}

		});
		Colorizer.colorizer.add(sublayerListJ);
		for (int i = 0; i < 13; i++)
		{

			if (SprCre.mColorObject.getneedStack(i))
			{
				Colorizer.colorizer.BackupM[i] = SprCre.mColorObject.image[i];

				SprCre.mColorObject.setLuminocity(i, 50);
				SprCre.mColorObject.setTone(i, 0);
				SprCre.mColorObject.setShade(i, 0);
				SprCre.mColorObject.setBrightness(i, 0);
				SprCre.mColorObject.setContrast(i, 50);

				SprCre.mColorObject.setneedStack(i, false);
			}
			if (SprCre.fColorObject.getneedStack(i))
			{
				Colorizer.colorizer.BackupF[i] = SprCre.fColorObject.image[i];

				SprCre.fColorObject.setLuminocity(i, 50);
				SprCre.fColorObject.setTone(i, 0);
				SprCre.fColorObject.setShade(i, 0);
				SprCre.fColorObject.setBrightness(i, 0);
				SprCre.fColorObject.setContrast(i, 50);

				SprCre.fColorObject.setneedStack(i, false);
			}
		}
		Colorizer.colorizer.BackupM = SprCre.mColorObject.getImage().clone();
		Colorizer.colorizer.BackupF = SprCre.fColorObject.getImage().clone();
		disableButtons();
	}

	private void disableHSB()
	{
		DefaultHSBC.setEnabled(false);

		EnableHSBJ.setSelected(false);
	}

	private void enableHSB()
	{
		DefaultHSBC.setEnabled(true);

		EnableHSBJ.setSelected(true);
	}

	private void disableButtons()
	{
		DefaultHSBC.setEnabled(false);

		EnableHSBJ.setSelected(false);
	}

	static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
}
