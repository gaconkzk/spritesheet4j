package circle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

public class RandButton 
{
	public static JButton randButt;
	private int mbody, mhair, mhairop, mmantle, macce1, macce2, moption, mtail, mears, mface, mbangs, marmor;
	private int mbodycolor, mhaircolor, mhairopcolor, mmantlecolor, macce1color, macce2color, moptioncolor, mtailcolor, mearscolor, mfacecolor, mbangscolor, marmorcolor;
	private int fbody, fhair, fhairop, fmantle, facce1, facce2, foption, ftail, fears, fface, fbangs, farmor;
	private int fbodycolor, fhaircolor, fhairopcolor, fmantlecolor, facce1color, facce2color, foptioncolor, ftailcolor, fearscolor, ffacecolor, fbangscolor, farmorcolor;
	
	/*
	 * This button became so large that I put it into it's own file
	 * This will randomly generate a character using all available parts
	 * Now each item has a probability of being used rather than just
	 * mass populating each template. randInt() explains the probabilities.
	 * */
	public RandButton()
	{
		randButt = new JButton("Random");
		randButt.setBounds(SprCre.ColorButtonX + 20, SprCre.ColorButtonY + 250, 90, 20);
		randButt.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent e)
			{
				if (SprCre.PickSex.equals("Male"))
				{
					mbody = 0;
					mhair = 0;
					mhairop = 0; 
					mmantle = 0;
					macce1 = 0;
					macce2 = 0;
					moption = 0;
					mtail = 0;
					mears = 0;
					mface = 0;
					mbangs = 0;
					marmor = 0;
					mbodycolor = 0;
					mhaircolor = 0;
					mhairopcolor = 0;
					mmantlecolor = 0;
					macce1color = 0;
					macce2color = 0;
					moptioncolor = 0;
					mtailcolor = 0;
					mearscolor = 0;
					mfacecolor = 0;
					mbangscolor = 0;
					marmorcolor = 0;
					SprCre.MTypeBody = ImgHandler.cbody[randInt(0, 100) >= 10 ? randInt(0, 7) : randInt(0, Cons.ImageBases - 1)];
					mbodycolor = randInt(1, Cons.usedColors);
					mbody = randInt(1, SprCre.mbodyI);
					SprCre.Mimg[SprCre.Body_L1] = SprCre.IH.Male_Body[mbodycolor][mbody];
					SprCre.Mtype[SprCre.Body] = Integer.toString(mbodycolor) + Integer.toString(mbody) + "_";
					mhaircolor = randInt(1, Cons.usedColors);
					mhair = randInt(1, SprCre.mhairI);
					SprCre.Mimg[SprCre.Hair_L2] = SprCre.IH.Male_Hair[mhaircolor][mhair][2];
					SprCre.Mimg[SprCre.Hair_L1] = SprCre.IH.Male_Hair[mhaircolor][mhair][1];
					SprCre.Mtype[SprCre.Hair] = Integer.toString(mhaircolor) + Integer.toString(mhair) + "_";
					if (randInt(1, 100) >= 60) //40% chance
					{
						if (randInt(1, 100) >= 15) //85% chance that if used it will be the same color as the hair
							mhairopcolor = mhaircolor;
						else
							mhairopcolor = randInt(1, Cons.usedColors);
						mhairop = randInt(1, SprCre.mhairopI);
						SprCre.Mimg[SprCre.Hairop_L2] = SprCre.IH.Male_Hairop[mhairopcolor][mhairop][2];
						SprCre.Mimg[SprCre.Hairop_L1] = SprCre.IH.Male_Hairop[mhairopcolor][mhairop][1];
						SprCre.Mtype[SprCre.Hairop] = Integer.toString(mhairopcolor) + Integer.toString(mhairop) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Hairop_L2] = SprCre.AllBlank;
						SprCre.Mimg[SprCre.Hairop_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Hairop] = "00_";
					}
					if (randInt(1, 100) >= 80) //20% chance
					{
						if (randInt(1, 100) >= 75) //25% chance when selected it will be the same color as the body
							mmantlecolor = mbodycolor;
						else
							if (randInt(1, 100) >= 85) //15% chance to match hair color if matching body fails
								mmantlecolor = mhaircolor;
							else
								mmantlecolor = randInt(1, Cons.usedColors);
						mmantle = randInt(1, SprCre.mantleI);
						SprCre.Mimg[SprCre.Mantle_L2] = SprCre.IH.Male_Mantle[mmantlecolor][mmantle][2];
						SprCre.Mimg[SprCre.Mantle_L1] = SprCre.IH.Male_Mantle[mmantlecolor][mmantle][1];
						SprCre.Mtype[SprCre.Mantle] = Integer.toString(mmantlecolor) + Integer.toString(mmantle) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Mantle_L2] = SprCre.AllBlank;
						SprCre.Mimg[SprCre.Mantle_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Mantle] = "00_";
					}
					if (randInt(1, 100) >= 80) //20% chance
					{
						if (randInt(1, 100) >= 30) //70% chance for acce1 to match hair color
							macce1color = mhaircolor;
						else
							if (randInt(1, 100) >= 50) //50% chance to match body if hair match fails
								macce1color = mbodycolor;
							else
								macce1color = randInt(1, Cons.usedColors);
						macce1 = randInt(1, SprCre.macce1I);
						SprCre.Mimg[SprCre.Acce1_L2] = SprCre.IH.Male_Acce1[macce1color][macce1][2];
						SprCre.Mimg[SprCre.Acce1_L1] = SprCre.IH.Male_Acce1[macce1color][macce1][1];
						SprCre.Mtype[SprCre.Acce1] = Integer.toString(macce1color) + Integer.toString(macce1) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Acce1_L2] = SprCre.AllBlank;
						SprCre.Mimg[SprCre.Acce1_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Acce1] = "00_";
					}
					if (randInt(1, 100) >= 80) //20% chance
					{
						macce2color = randInt(1, Cons.usedColors);
						macce2 = randInt(1, SprCre.acce2I);
						SprCre.Mimg[SprCre.Acce2_L1] = SprCre.IH.Male_Acce2[macce2color][macce2][2];
						SprCre.Mtype[SprCre.Acce2] = Integer.toString(macce2color) + Integer.toString(macce2) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Acce2_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Acce2] = "00_";
					}
					if (randInt(1, 100) >= 90) //10% chance
					{
						moptioncolor = randInt(1, Cons.usedColors);
						moption = randInt(1, SprCre.optionI);
						SprCre.Mimg[SprCre.Option_L2] = SprCre.IH.Male_Option[moptioncolor][moption][2];
						SprCre.Mimg[SprCre.Option_L1] = SprCre.IH.Male_Option[moptioncolor][moption][1];
						SprCre.Mtype[SprCre.Option] = Integer.toString(moptioncolor) + Integer.toString(moption) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Option_L2] = SprCre.AllBlank;
						SprCre.Mimg[SprCre.Option_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Option] = "00_";
					}
					if (randInt(1, 100) >= 93) //7% chance
					{
						mtailcolor = randInt(1, Cons.usedColors);
						mtail = randInt(1, SprCre.tailI);
						SprCre.Mimg[SprCre.Tail_L2] = SprCre.IH.Male_Tail[mtailcolor][mtail][1];
						SprCre.Mimg[SprCre.Tail_L1] = SprCre.IH.Male_Tail[mtailcolor][mtail][3];
						SprCre.Mtype[SprCre.Tail] = Integer.toString(mtailcolor) + Integer.toString(mtail) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Tail_L2] = SprCre.AllBlank;
						SprCre.Mimg[SprCre.Tail_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Tail] = "00_";
					}
					if (randInt(1, 100) >= 93) //7% chance
					{
						mearscolor = randInt(1, Cons.usedColors);
						mears = randInt(1, SprCre.earsI);
						SprCre.Mimg[SprCre.Ears_L3] = SprCre.IH.Male_Ears[mearscolor][mears][2];
						SprCre.Mimg[SprCre.Ears_L2] = SprCre.IH.Male_Ears[mearscolor][mears][1];
						SprCre.Mimg[SprCre.Ears_L1] = SprCre.IH.Male_Ears[mearscolor][mears][3];
						SprCre.Mtype[SprCre.Ears] = Integer.toString(mearscolor) + Integer.toString(mears) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Ears_L3] = SprCre.AllBlank;
						SprCre.Mimg[SprCre.Ears_L2] = SprCre.AllBlank;
						SprCre.Mimg[SprCre.Ears_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Ears] = "00_";
					}
					if (randInt(1, 100) >= 70) //30% chance
					{
						if (randInt(1, 100) >= 20) //80% chance to match hairop. if hairop doesn't exist match hair instead
						{
							if (mhairopcolor != 0)
								mbangscolor = mhairopcolor;
							else
								mbangscolor = mhaircolor;
						}
						else
							mbangscolor = randInt(1, Cons.usedColors);
						mbangs = randInt(1, SprCre.mbangsI);
						SprCre.Mimg[SprCre.Bangs_L1] = SprCre.IH.Male_Bangs[mbangscolor][mbangs][2];
						SprCre.Mtype[SprCre.Bangs] = Integer.toString(mbangscolor) + Integer.toString(mbangs) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Bangs_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Bangs] = "00_";
					}
					if (randInt(1, 100) >= 95) //5% chance
					{
						mfacecolor = randInt(1, Cons.usedColors);
						mface = randInt(1, SprCre.faceI);
						SprCre.Mimg[SprCre.Face_L1] = SprCre.IH.Male_Face[mfacecolor][mface][2];
						SprCre.Mtype[SprCre.Face] = Integer.toString(mfacecolor) + Integer.toString(mface) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Face_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Face] = "00_";
					}
					if (randInt(1, 100) >= 90)
					{
						marmorcolor = randInt(1, Cons.usedColors);
						marmor = randInt(1, SprCre.armorI);
						SprCre.Mimg[SprCre.Armor_L1] = SprCre.IH.Male_Armor[marmorcolor][marmor][2];
						SprCre.Mtype[SprCre.Armor] = Integer.toString(marmorcolor) + Integer.toString(marmor) + "_";
					}
					else
					{
						SprCre.Mimg[SprCre.Armor_L1] = SprCre.AllBlank;
						SprCre.Mtype[SprCre.Armor] = "00_";
					}
				}
				else if (SprCre.PickSex.equals("Female"))
				{
					fbody = 0;
					fhair = 0;
					fhairop = 0; 
					fmantle = 0;
					facce1 = 0;
					facce2 = 0;
					foption = 0;
					ftail = 0;
					fears = 0;
					fface = 0;
					fbangs = 0;
					farmor = 0;
					fbodycolor = 0;
					fhaircolor = 0;
					fhairopcolor = 0;
					fmantlecolor = 0;
					facce1color = 0;
					facce2color = 0;
					foptioncolor = 0;
					ftailcolor = 0;
					fearscolor = 0;
					ffacecolor = 0;
					fbangscolor = 0;
					farmorcolor = 0;
					SprCre.FTypeBody = ImgHandler.cbody[randInt(0, 100) >= 10 ? randInt(0, 7) : randInt(0, Cons.ImageBases - 1)];
					fbodycolor = randInt(1, Cons.usedColors);
					fbody = randInt(1, SprCre.fbodyI);
					SprCre.Fimg[SprCre.Body_L1] = SprCre.IH.Female_Body[fbodycolor][fbody];
					SprCre.Ftype[SprCre.Body] = Integer.toString(fbodycolor) + Integer.toString(fbody) + "_";
					fhaircolor = randInt(1, Cons.usedColors);
					fhair = randInt(1, SprCre.fhairI);
					SprCre.Fimg[SprCre.Hair_L2] = SprCre.IH.Female_Hair[fhaircolor][fhair][2];
					SprCre.Fimg[SprCre.Hair_L1] = SprCre.IH.Female_Hair[fhaircolor][fhair][1];
					SprCre.Ftype[SprCre.Hair] = Integer.toString(fhaircolor) + Integer.toString(fhair) + "_";
					if (randInt(1, 100) >= 40)
					{
						if (randInt(1, 100) >= 15)
							fhairopcolor = fhaircolor;
						else
							fhairopcolor = randInt(1, Cons.usedColors);
						fhairop = randInt(1, SprCre.fhairopI);
						SprCre.Fimg[SprCre.Hairop_L2] = SprCre.IH.Female_Hairop[fhairopcolor][fhairop][2];
						SprCre.Fimg[SprCre.Hairop_L1] = SprCre.IH.Female_Hairop[fhairopcolor][fhairop][1];
						SprCre.Ftype[SprCre.Hairop] = Integer.toString(fhairopcolor) + Integer.toString(fhairop) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Hairop_L2] = SprCre.AllBlank;
						SprCre.Fimg[SprCre.Hairop_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Hairop] = "00_";
					}
					if (randInt(1, 100) >= 80)
					{
						if (randInt(1, 100) >= 65)
							fmantlecolor = fbodycolor;
						else
							if (randInt(1, 100) >= 85) //15% chance to match hair color if matching body fails
								fmantlecolor = fhaircolor;
							else
								fmantlecolor = randInt(1, Cons.usedColors);
						fmantle = randInt(1, SprCre.mantleI);
						SprCre.Fimg[SprCre.Mantle_L2] = SprCre.IH.Female_Mantle[fmantlecolor][fmantle][2];
						SprCre.Fimg[SprCre.Mantle_L1] = SprCre.IH.Female_Mantle[fmantlecolor][fmantle][1];
						SprCre.Ftype[SprCre.Mantle] = Integer.toString(fmantlecolor) + Integer.toString(fmantle) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Mantle_L2] = SprCre.AllBlank;
						SprCre.Fimg[SprCre.Mantle_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Mantle] = "00_";
					}
					if (randInt(1, 100) >= 80)
					{
						if (randInt(1, 100) >= 30)
							facce1color = fhaircolor;
						else
							if (randInt(1, 100) >= 50) //50% chance to match body if hair match fails
								facce1color = fbodycolor;
							else
								facce1color = randInt(1, Cons.usedColors);
						facce1 = randInt(1, SprCre.facce1I);
						SprCre.Fimg[SprCre.Acce1_L2] = SprCre.IH.Female_Acce1[facce1color][facce1][2];
						SprCre.Fimg[SprCre.Acce1_L1] = SprCre.IH.Female_Acce1[facce1color][facce1][1];
						SprCre.Ftype[SprCre.Acce1] = Integer.toString(facce1color) + Integer.toString(facce1) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Acce1_L2] = SprCre.AllBlank;
						SprCre.Fimg[SprCre.Acce1_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Acce1] = "00_";
					}
					if (randInt(1, 100) >= 80)
					{
						facce2color = randInt(1, Cons.usedColors);
						facce2 = randInt(1, SprCre.acce2I);
						SprCre.Fimg[SprCre.Acce2_L1] = SprCre.IH.Female_Acce2[facce2color][facce2][2];
						SprCre.Ftype[SprCre.Acce2] = Integer.toString(facce2color) + Integer.toString(facce2) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Acce2_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Acce2] = "00_";
					}
					if (randInt(1, 100) >= 90)
					{
						foptioncolor = randInt(1, Cons.usedColors);
						foption = randInt(1, SprCre.optionI);
						SprCre.Fimg[SprCre.Option_L2] = SprCre.IH.Female_Option[foptioncolor][foption][2];
						SprCre.Fimg[SprCre.Option_L1] = SprCre.IH.Female_Option[foptioncolor][foption][1];
						SprCre.Ftype[SprCre.Option] = Integer.toString(foptioncolor) + Integer.toString(foption) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Option_L2] = SprCre.AllBlank;
						SprCre.Fimg[SprCre.Option_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Option] = "00_";
					}
					if (randInt(1, 100) >= 93)
					{
						ftailcolor = randInt(1, Cons.usedColors);
						ftail = randInt(1, SprCre.tailI);
						SprCre.Fimg[SprCre.Tail_L2] = SprCre.IH.Female_Tail[ftailcolor][ftail][1];
						SprCre.Fimg[SprCre.Tail_L1] = SprCre.IH.Female_Tail[ftailcolor][ftail][3];
						SprCre.Ftype[SprCre.Tail] = Integer.toString(ftailcolor) + Integer.toString(ftail) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Tail_L2] = SprCre.AllBlank;
						SprCre.Fimg[SprCre.Tail_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Tail] = "00_";
					}
					if (randInt(1, 100) >= 93)
					{
						fearscolor = randInt(1, Cons.usedColors);
						fears = randInt(1, SprCre.earsI);
						SprCre.Fimg[SprCre.Ears_L3] = SprCre.IH.Female_Ears[fearscolor][fears][2];
						SprCre.Fimg[SprCre.Ears_L2] = SprCre.IH.Female_Ears[fearscolor][fears][1];
						SprCre.Fimg[SprCre.Ears_L1] = SprCre.IH.Female_Ears[fearscolor][fears][3];
						SprCre.Ftype[SprCre.Ears] = Integer.toString(fearscolor) + Integer.toString(fears) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Ears_L3] = SprCre.AllBlank;
						SprCre.Fimg[SprCre.Ears_L2] = SprCre.AllBlank;
						SprCre.Fimg[SprCre.Ears_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Ears] = "00_";
					}
					if (randInt(1, 100) >= 70)
					{
						if (randInt(1, 100) >= 20)
						{
							if (fhairopcolor != 0)
								fbangscolor = fhairopcolor;
							else
								fbangscolor = fhaircolor;
						}
						else
							fbangscolor = randInt(1, Cons.usedColors);
						fbangs = randInt(1, SprCre.fbangsI);
						SprCre.Fimg[SprCre.Bangs_L1] = SprCre.IH.Female_Bangs[fbangscolor][fbangs][2];
						SprCre.Ftype[SprCre.Bangs] = Integer.toString(fbangscolor) + Integer.toString(fbangs) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Bangs_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Bangs] = "00_";
					}
					if (randInt(1, 100) >= 95)
					{
						ffacecolor = randInt(1, Cons.usedColors);
						fface = randInt(1, SprCre.faceI);
						SprCre.Fimg[SprCre.Face_L1] = SprCre.IH.Female_Face[ffacecolor][fface][2];
						SprCre.Ftype[SprCre.Face] = Integer.toString(ffacecolor) + Integer.toString(fface) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Face_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Face] = "00_";
					}
					if (randInt(1, 100) >= 90)
					{
						farmorcolor = randInt(1, Cons.usedColors);
						farmor = randInt(1, SprCre.armorI);
						SprCre.Fimg[SprCre.Armor_L1] = SprCre.IH.Female_Armor[farmorcolor][farmor][2];
						SprCre.Ftype[SprCre.Armor] = Integer.toString(farmorcolor) + Integer.toString(farmor) + "_";
					}
					else
					{
						SprCre.Fimg[SprCre.Armor_L1] = SprCre.AllBlank;
						SprCre.Ftype[SprCre.Armor] = "00_";
					}
				}
			}
		});
		SprCre.apl.add(randButt);
	}
	public static int randInt(int min, int max) { return new Random().nextInt((max - min) + 1) + min; }
}
