package org.gaconkzk.spritesheet;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author: tntvu
 * since : 06/05/2016
 */
@AllArgsConstructor()
public class Pose {
    private String name;
    private int row;
    private int cols;
    private String directions;

    public int rows() {
	return directions.length();
    }
}
