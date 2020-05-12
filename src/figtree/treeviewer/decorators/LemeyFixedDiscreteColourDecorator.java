/*
 * FixedDiscreteColourDecorator.java
 *
 * Copyright (C) 2006-2014 Andrew Rambaut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package figtree.treeviewer.decorators;

import jebl.util.Attributable;

import java.awt.*;
import java.util.Set;

/**
 * @author Andrew Rambaut
 * @version $Id$
 *
 * $HeadURL$
 *
 * $LastChangedBy$
 * $LastChangedDate$
 * $LastChangedRevision$
 */
public class LemeyFixedDiscreteColourDecorator extends DiscreteColourDecorator {

    private static String COLOR_STRING = "{#A6CEE3,#83B8D6,#ECD87E,#FBFA99,#D8AD61,#DA9A87,#F88E8D,#F26D6D,#EC4C4D,#E89459,#E62D25,#EC573A,#E62B2D,#F38150,#F9AB65,#FDB65F,#FDA543,#FE9526,#FE840A,#F68722,#60A2CA,#3E8CBE,#227AB3,#DBA190,#CDAEC7,#4894A8,#B89CCA,#6DAF9D,#C48344,#9F7EBB,#93C992,#749E4F,#A79C6B,#ACDC85,#8760AC,#6E429C,#8BCB6D,#6BBB55,#4AAB3D,#896599,#AF9799,#409F33,#B15928,#D5C899}";

    public LemeyFixedDiscreteColourDecorator(String attributeName) {
        super(attributeName);
        setupColours();
    }

    public LemeyFixedDiscreteColourDecorator(String attributeName, String settings) {
        super(attributeName);
        setup(settings);
    }

//    public LemeyFixedDiscreteColourDecorator(String attributeName, String settings, Set<? extends Attributable> items) {
//        super(attributeName, items);
//        setup(settings);
//    }

    private Color getColor(String rgb) {
        return Color.decode(rgb);
    }

    /**
     * Set up from a settings string
     * @param settings
     */
    public void setup(String settings) {
        if (!settings.startsWith("{") || !settings.endsWith("}")) {
            throw new IllegalArgumentException("LemeyFixedDiscreteColourDecorator settings string not in correct format");
        }

        String[] colorStrings = settings.substring(1, settings.length() - 1).split("[, ]+");

        colors = new Color[colorStrings.length];
        for (int i = 0; i < colorStrings.length; ++i) {
            colors[i] = getColor(colorStrings[i]);
        }

        setColourMap(getValues(), colors);
    }

    protected void setupColours() {
        setup(COLOR_STRING);
    }

    private Color[] colors;

    private String getString(Color color) {
        return Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
    }

    /**
     * Create a string representation suitable for writing to a text file
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("#").append(getString(colors[0]));
        for (int i = 1; i < colors.length; ++i) {
            sb.append(",#").append(getString(colors[i]));
        }
        sb.append("}");
        return sb.toString();
    }
}
