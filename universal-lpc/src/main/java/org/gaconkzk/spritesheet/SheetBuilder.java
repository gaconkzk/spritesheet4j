package org.gaconkzk.spritesheet;

/**
 * author: tntvu
 * since : 06/05/2016
 */
public class SheetBuilder {
    private String[] args;
    private int layersAdded;
    private String outpath;

    public void initialize(String outpath) {

	this.outpath = outpath;
	this.layersAdded = 0;
	this.args = new String[] { "(", "-background", "none", ")" };

    }

//    public void addLayer(sourcePrefix, gender) {
//	String layer = Sheet.layerName(this.outpath);
//	String name = FilenameUtils.getBaseName( this.outpath );
//
//    }
}
