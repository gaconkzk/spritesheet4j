package circle.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import circle.SprCre;
import circle.colorizer.Colorizer;
import circle.handlers.ImgHandler;

public class MenuBar
{
	public static JMenuBar menubar = new JMenuBar();
	private void newSeperator(JMenu menuToSeparate)
	{
		JMenuItem BlankSpot = new JMenuItem();
		BlankSpot.setEnabled(false);
		menuToSeparate.add(BlankSpot);
	}
	public MenuBar(final JFrame frame)
	{
		menubar.setOpaque(true);
		JMenu HelpMenu = new JMenu("Help");
		JMenuItem AboutWin = new JMenuItem("About");
		AboutWin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(frame, 
						"Sprite Creator 3 XP\n"
						+ "Programmed by Circle.\n"
						+ "\n"
						+ "Jensen_305@yahoo.com\n"
						+ "facebook.com/SpriteCreator3\n"
						+ "\n"
						+ "Special Thanks to:\n"
						+ "Axerax\n"
						+ "Mack\n"
						+ "Looseleaf\n"
						+ "\n"
						+ "build " + SprCre.apl.getBuild(), "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		HelpMenu.add(AboutWin);
		
		
		JMenu EditMenu = new JMenu("Edit");
		JMenuItem ImageEdit = new JMenuItem("Image");
		ImageEdit.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (SprCre.CanOpenWindow)
				{
					Colorizer.main(null);
					//Colorizer.colorizer.cui = new ColorizerUI();
					SprCre.CanOpenWindow = false;
				}
			}
		});
		EditMenu.add(ImageEdit);
		JMenu FileMenu = new JMenu("File");
		JMenu Saveone = new JMenu("Save");
		FileMenu.add(Saveone);
		JMenuItem SaveMale = new JMenuItem("Male");
		SaveMale.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SprCre.io.save(false, "Male");
			}
		});
		Saveone.add(SaveMale);
		JMenuItem SaveFemale = new JMenuItem("Female");
		SaveFemale.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SprCre.io.save(false, "Female");
			}
		});
		Saveone.add(SaveFemale);
		JMenuItem SaveAllItem = new JMenuItem("Save All");
		SaveAllItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SprCre.io.save(true, "");
			}
		});
		FileMenu.add(SaveAllItem);
		newSeperator(FileMenu);
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
					SprCre.cho.ReloadAll(false, "Male");
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
					SprCre.cho.ReloadAll(false, "Female");
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
					SprCre.cho.ReloadAll(true, "");
					ImgHandler.doneWithFirstBoard = false;
				}
			}
		});
		FileMenu.add(ReloadAllItem);
		newSeperator(FileMenu);
		JMenuItem ExitItem = new JMenuItem("Exit");
		ExitItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ImgHandler.doneWithFirstBoard = true;
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					SprCre.apl.destroy();
				}
			}
		});
		FileMenu.add(ExitItem);
		menubar.setVisible(true);
		menubar.add(FileMenu);
		menubar.add(HelpMenu);
		menubar.add(EditMenu);
	}
}
