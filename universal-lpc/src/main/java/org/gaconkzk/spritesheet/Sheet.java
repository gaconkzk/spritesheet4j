package org.gaconkzk.spritesheet;

import lombok.Data;

/**
 * author: tntvu
 * since : 06/05/2016
 */
@Data
public class Sheet {
    public static final Pose[] POSES = {
		    new Pose("spellcast", 0, 7, "nwse"),
		    new Pose("thrust", 4, 8, "nwse"),
		    new Pose("walkcycle", 8, 9, "nwse"),
		    new Pose("slash", 12, 6, "nwse"),
		    new Pose("shoot", 16, 13, "nwse"),
		    new Pose("hurt", 20, 6, "s")
    };
    private String path;
    private Sheet referencePointsSheet;
    private Frame[] referenceFrames;
    private Frame[] frames;

    public void initialize(String path, Sheet referencePointsSheet) {
	this.path = path;
	this.referencePointsSheet = referencePointsSheet;
	this.referenceFrames = referencePointsSheet==null?new Frame[]{}:referencePointsSheet.getFrames();
    }
}
