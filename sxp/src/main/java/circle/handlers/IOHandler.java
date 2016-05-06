package circle.handlers;

import circle.Cons;
import circle.SprCre;
import circle.ui.UIControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOHandler implements Cons {
    /*
     * If you added a category you must also add it here
     * */
    public final int fhairCFG = CFGinc(); //0
    public final int mhairCFG = CFGinc();
    public final int mchestCFG = CFGinc();
    public final int fchestCFG = CFGinc();
    public final int mshoulderCFG = CFGinc();
    public final int fshoulderCFG = CFGinc();
    public final int marmsCFG = CFGinc();
    public final int farmsCFG = CFGinc();
    public final int mpantsCFG = CFGinc();
    public final int fpantsCFG = CFGinc();
    public final int mwaistCFG = CFGinc();
    public final int fwaistCFG = CFGinc();
    public final int mbootsCFG = CFGinc();
    public final int fbootsCFG = CFGinc();
    public final int mhatCFG = CFGinc();
    public final int fhatCFG = CFGinc();
    public final int mshirtCFG = CFGinc();
    public final int fshirtCFG = CFGinc();
    public final int mlongshirtCFG = CFGinc();
    public final int flongshirtCFG = CFGinc();// 20
    public final int categories = getCFGinc(); // should match last in variables labeled XXXCFG below

    //This order is the object amounts when default. These values are not used unless the config file is not found or deleted.
    private final int[] na = { 15, 15, 3, 3, 13, 7, 8, 7, 5, 10, 4, 2, 3, 1, 7, 8, 11, 17, 9, 9, 1, 1 };

    public volatile int[] cfg = new int[categories + 1];
    //+10??? Not sure. I think I was being safe and this was never corrected. Not changing but pointing out.
    public int[][] overridesCFG = new int[categories + 10][maxI + 10];
    public int[][] multilayer = new int[categories + 10][maxI + 10];

    public static boolean SaveBoth = false;
    public static boolean normalSave = true;
    public String fileName = "";
    /*
     * *****************************HERE
     * */
    protected String[] cfgtext = { "# <---------These lines are ignored. Blank lines are ignored as well", "# This config file is for those who want to add custom images to Sprite Creator 3", "# -o Overrides the sprite ROW facing direction of the selection window.", "# -m declares which Images have more than 1 layer the layer number is set", "# internally by Sprite Creator 3", "# Programmed by 0circle0", "# www.facebook.com/SpriteCreator3" };
    protected String[] overrides1 = { "-m", "7", "-" };
    protected String[] overrides2 = { "-m", "3", "7", "13", "-" };

    /*
     * these values are only used if the config file is not found or deleted. Used for creating the config file
     * ******************************To HERE
     * */
    public IOHandler( String CtL ) {
	populateuse();
	LoadCFG( CtL );
    }

    public int[] getCFG() {
	return cfg;
    }

    public int getCFG( int i ) {
	return cfg[i];
    }

    public int CFGi = 0;

    public int CFGinc() {
	return CFGi++;
    }

    public int getCFGinc() {
	return (int) (CFGi - 1);
    }

    private void populateuse() {
	for ( int x = 0; x <= categories; x++ ) {
	    for ( int y = 0; y <= maxI; y++ ) {
		overridesCFG[x][y] = -1;
		multilayer[x][y] = -1;
	    }
	}
    }

    private void populateDefault() {
	System.arraycopy( na, 0, cfg, 0, na.length );
    }

    private void makeFile( String configFile ) {
	BufferedWriter writer = null;
	try {
	    File logFile;
	    Path path = Paths.get( configFile );
	    if ( Files.exists( path ) ) {
		logFile = path.toFile();
	    } else {
		// use default config
		logFile = new File( Thread.currentThread().getContextClassLoader().getResource( "Config.cfg" ).getFile() );
	    }
	    populateDefault();
	    writer = new BufferedWriter( new BufferedWriter( new FileWriter( logFile ) ) );
	    for ( int i = 0; i <= categories; i++ ) {
		if ( i == 0 )
		    for ( String g : cfgtext ) {
			writer.write( g );
			writer.write( '\n' );
		    }
		writer.write( cfglist[i] );
		writer.write( '\n' );
		if ( i == 0 ) {
		    writer.write( "-o" );
		    writer.write( '\n' );
		    for ( int x = 1; x <= 15; x++ ) {
			writer.write( Integer.toString( x ) );
			writer.write( '\n' );
		    }
		    writer.write( "-" );
		    writer.write( '\n' );
		    for ( String g : overrides2 ) {
			writer.write( g );
			writer.write( '\n' );
		    }
		}
		if ( i == 1 ) {
		    writer.write( "-o" );
		    writer.write( '\n' );
		    for ( int x = 1; x <= 15; x++ ) {
			writer.write( Integer.toString( x ) );
			writer.write( '\n' );
		    }
		    writer.write( "-" );
		    writer.write( '\n' );
		    for ( String g : overrides1 ) {
			writer.write( g );
			writer.write( '\n' );
		    }
		}
		writer.write( Integer.toString( cfg[i] ) );
		for ( int k = 0; k <= 1; k++ )
		    writer.write( '\n' );
	    }
	} catch ( IOException ignored ) {

	} finally {
	    try {
		if ( writer != null ) {
		    writer.close();
		}
		//LoadCFG("Config.cfg");
	    } catch ( Exception ignored ) {
	    }
	}

    }

    private void LoadCFG( String configFile ) {
	File fileCFG;
	Path path = Paths.get( configFile );
	if ( Files.exists( path ) ) {
	    fileCFG = path.toFile();
	} else {
	    URL defaultConfig = Thread.currentThread().getContextClassLoader().getResource( "Config.cfg" );
	    // use default config
	    if (defaultConfig!=null) {
		fileCFG = new File( defaultConfig.getFile() );
	    } else {
		// This just for filenotfound exception
		fileCFG = new File( "Config.cfg" );
	    }
	}

	BufferedReader reader = null;
	boolean stillneed;
	int cfginc = 0;
	int count = 0;
	int arrayc;
	try {
	    reader = new BufferedReader( new FileReader( fileCFG ) );
	    String text;
	    while ( (text = reader.readLine()) != null ) {
		if ( text.trim().length() != 0 ) {
		    if ( text.replace( " ", "" ).charAt( 0 ) != '#' && text.replace( " ", "" ).charAt( 0 ) != '-' ) {
			cfg[cfginc++] = Integer.parseInt( text.replace( " ", "" ) );
			count++;
		    } else if ( text.replace( " ", "" ).charAt( 0 ) == '-' ) {
			if ( text.replace( " ", "" ).charAt( 1 ) == 'm' ) {
			    stillneed = true;
			    while ( stillneed && (text = reader.readLine()) != null ) {
				if ( text.trim().length() != 0 ) {
				    if ( text.replace( " ", "" ).charAt( 0 ) != '#' && text.replace( " ", "" ).charAt( 0 ) != '-' ) {
					multilayer[count][Integer.parseInt( text.replace( " ", "" ) )] = Integer.parseInt( text.replace( " ", "" ) );
				    } else if ( text.replace( " ", "" ).charAt( 0 ) != '#' )
					stillneed = false;
				}
			    }
			} else if ( text.replace( " ", "" ).charAt( 1 ) == 'o' ) {
			    stillneed = true;
			    arrayc = 0;
			    while ( stillneed && (text = reader.readLine()) != null ) {
				if ( text.trim().length() != 0 ) {
				    if ( text.replace( " ", "" ).charAt( 0 ) != '#' && text.replace( " ", "" ).charAt( 0 ) != '-' ) {
					if ( arrayc <= maxI )
					    overridesCFG[count][arrayc++] = Integer.parseInt( text.replace( " ", "" ) );
				    } else if ( text.replace( " ", "" ).charAt( 0 ) != '#' )
					stillneed = false;
				}
			    }
			}
		    }
		}
	    }
	} catch ( FileNotFoundException FNFe ) {
	    try {
		if ( reader != null )
		    reader.close();
	    } catch ( IOException ignored ) {
	    }
	    makeFile( "Config.cfg" );
	} catch ( IOException ignored ) {

	} finally {
	    try {
		if ( reader != null )
		    reader.close();
	    } catch ( IOException ignored ) {
	    }
	}

	if ( count != categories + 1 ) {
	    populateDefault();
	    makeFile( "Config.cfg" );
	}
    }

    public void save( boolean d, String SexToSave ) {
	SaveBoth = d;
	String SexHold;
	SexHold = SprCre.apl.PickSex;
	if ( (SexToSave.equals( "Male" ) || SaveBoth) ) {
	    SprCre.apl.PickSex = "Male";
	    fileName = "Saved/";
	    if ( CheckOb.MisGhost )
		fileName = fileName + "Ghost_";
	    if ( CheckOb.MisGhost && !CheckOb.MHaveBody )
		fileName = fileName + "_";
	    if ( !CheckOb.MHaveBody )
		fileName = fileName + "NoBody";
	    else
		fileName = fileName + SprCre.cho.MTypeBody;
	    fileName = fileName + "_" + SprCre.apl.PickSex + "_" + returnType( SprCre.Obcount, CheckOb.Mtype ) + (int) (SprCre.cho.Mopacity * 100);
	    SavePoint( fileName );
	}
	if ( (!SexToSave.equals( "Male" ) || SaveBoth) && !normalSave )
	    SavePoint( fileName );
	if ( (SexToSave.equals( "Female" ) || SaveBoth) ) {
	    SprCre.apl.PickSex = "Female";
	    fileName = "Saved/";
	    if ( CheckOb.FisGhost )
		fileName = fileName + "Ghost_";
	    if ( CheckOb.FisGhost && !CheckOb.FHaveBody )
		fileName = fileName + "_";
	    if ( !CheckOb.FHaveBody )
		fileName = fileName + "NoBody";
	    else
		fileName = fileName + SprCre.cho.FTypeBody;
	    fileName = fileName + "_" + SprCre.apl.PickSex + "_" + returnType( SprCre.Obcount, CheckOb.Ftype ) + (int) (SprCre.cho.Fopacity * 100);
	    SavePoint( fileName );
	}
	if ( (!SexToSave.equals( "Female" ) || SaveBoth) && !normalSave )
	    SavePoint( fileName );

	SprCre.apl.PickSex = SexHold;
	SaveBoth = false;
    }

    private String returnType( int t, String[] s ) {
	String arrayed = s[t];
	if ( t == 0 )
	    return " " + arrayed;
	else
	    return arrayed + returnType( t - 1, s );
    }

    private void SavePoint( String fileName ) {
	File folder = new File( "Saved" );
	if ( !folder.exists() )
	    if ( !folder.mkdir() )
		JOptionPane.showMessageDialog( SprCre.apl, "Cannot create folder \"Saved\". Create the folder yourself to save.", "Error in SavePoint(String ext, String fileName)", JOptionPane.ERROR_MESSAGE );
	try {
	    if ( !UIControl.SaveAsVX ) {
		fileName = fileName + "_XP";
		ImageIO.write( ImgHandler.AllSplit, "png", new File( fileName + "." + "png" ) );
	    } else {
		fileName = fileName + "_VX";
		ImageIO.write( SprCre.IH.SaveASXP(), "png", new File( fileName + "." + "png" ) );
	    }
	    if ( !normalSave ) {
		File folderunc = new File( fileName );

		if ( !folderunc.exists() )
		    if ( !folderunc.mkdir() )
			JOptionPane.showMessageDialog( SprCre.apl, "Cannot create folder for uncompiled images.", "Error in SavePoint", JOptionPane.ERROR_MESSAGE );
		for ( int k = Cons.StackFirst; k <= Cons.ObjectValues.length - 1; k++ ) {
		    if ( SprCre.apl.PickSex.equals( "Male" ) ) {

			if ( SprCre.mColorObject.getImage( k ) != SprCre.apl.AllBlank ) {
			    if ( !CheckOb.MisGhost )
				ImageIO.write( SprCre.mColorObject.getImage( k ), "png", new File( fileName + "/" + "10" + k + "_" + Cons.ObjectValues[k] + "." + "png" ) );
			    else
				ImageIO.write( SprCre.IH.Ghostify( SprCre.mColorObject.getImage( k ) ), "png", new File( fileName + "/" + "10" + k + "_" + Cons.ObjectValues[k] + "." + "png" ) );
			}
		    }
		    if ( SprCre.apl.PickSex.equals( "Female" ) )
			if ( SprCre.fColorObject.getImage( k ) != SprCre.apl.AllBlank ) {
			    if ( !CheckOb.FisGhost )
				ImageIO.write( SprCre.fColorObject.getImage( k ), "png", new File( fileName + "/" + "10" + k + "_" + Cons.ObjectValues[k] + "." + "png" ) );
			    else
				ImageIO.write( SprCre.IH.Ghostify( SprCre.fColorObject.getImage( k ) ), "png", new File( fileName + "/" + "10" + k + "_" + Cons.ObjectValues[k] + "." + "png" ) );
			}
		}
		if ( SprCre.apl.PickSex.equals( "Male" ) ) {
		    if ( !CheckOb.MisGhost )
			ImageIO.write( SprCre.IH.ReturnBody( SprCre.cho.MTypeBody, "Male" ), "png", new File( fileName + "/" + "107_Base.png" ) );
		    else
			ImageIO.write( SprCre.IH.Ghostify( SprCre.IH.ReturnBody( SprCre.cho.MTypeBody, "Male" ) ), "png", new File( fileName + "/" + "107_Base.png" ) );
		} else {
		    if ( !CheckOb.FisGhost )
			ImageIO.write( SprCre.IH.ReturnBody( SprCre.cho.FTypeBody, "Female" ), "png", new File( fileName + "/" + "107_Base.png" ) );
		    else
			ImageIO.write( SprCre.IH.Ghostify( SprCre.IH.ReturnBody( SprCre.cho.FTypeBody, "Female" ) ), "png", new File( fileName + "/" + "107_Base.png" ) );
		}

	    }
	} catch ( IOException e ) {
	    JOptionPane.showMessageDialog( SprCre.apl, "Unable to save image(s). This error is usually due to the folder not having been created.", "Error in SavePoint(String ext, String fileName)", JOptionPane.ERROR_MESSAGE );
	}
    }
}