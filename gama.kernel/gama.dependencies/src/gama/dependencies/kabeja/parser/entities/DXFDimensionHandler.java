/*******************************************************************************************************
 *
 * DXFDimensionHandler.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.parser.entities;

import gama.dependencies.kabeja.dxf.DXFDimension;
import gama.dependencies.kabeja.dxf.DXFEntity;
import gama.dependencies.kabeja.parser.DXFValue;


/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public class DXFDimensionHandler extends AbstractEntityHandler {
    protected final static int GROUPCODE_REFERENCE_POINT_X = 10;
    protected final static int GROUPCODE_REFERENCE_POINT_Y = 20;
    protected final static int GROUPCODE_REFERENCE_POINT_Z = 30;
    protected final static int GROUPCODE_TEXT_POINT_X = 11;
    protected final static int GROUPCODE_TEXT_POINT_Y = 21;
    protected final static int GROUPCODE_TEXT_POINT_Z = 31;
    protected final static int GROUPCODE_INSERT_POINT_X = 12;
    protected final static int GROUPCODE_INSERT_POINT_Y = 22;
    protected final static int GROUPCODE_INSERT_POINT_Z = 32;
    protected final static int GROUPCODE_REFERENCE_POINT3_X = 13;
    protected final static int GROUPCODE_REFERENCE_POINT3_Y = 23;
    protected final static int GROUPCODE_REFERENCE_POINT3_Z = 33;
    protected final static int GROUPCODE_REFERENCE_POINT4_X = 14;
    protected final static int GROUPCODE_REFERENCE_POINT4_Y = 24;
    protected final static int GROUPCODE_REFERENCE_POINT4_Z = 34;
    protected final static int GROUPCODE_REFERENCE_POINT5_X = 15;
    protected final static int GROUPCODE_REFERENCE_POINT5_Y = 25;
    protected final static int GROUPCODE_REFERENCE_POINT5_Z = 35;
    protected final static int GROUPCODE_REFERENCE_POINT6_X = 16;
    protected final static int GROUPCODE_REFERENCE_POINT6_Y = 26;
    protected final static int GROUPCODE_REFERENCE_POINT6_Z = 36;
    protected final static int GROUPCODE_DIMENSION_STYLE = 3;
    protected final static int GROUPCODE_DIMENSION_BLOCK = 2;
    protected final static int GROUPCODE_DIMENSION_AREA = 67;
    protected final static int GROUPCODE_DIMENSION_TEXT = 1;
    protected final static int GROUPCODE_LEADINGLINE_LENGTH = 40;
    protected final static int GROUPCODE_DIMENSION_ROTATE = 50;
    protected final static int GROUPCODE_HORIZONTAL_ALIGNMENT = 51;
    protected final static int GROUPCODE_INCLINATION_HELPLINE = 52;
    protected final static int GROUPCODE_TEXT_ROTATION = 53;
    protected final static int GROUPCODE_DIMENSION_TYPE = 70;
    protected String ENTITY_NAME = "DIMENSION";
    protected DXFDimension dimension;

    public void endDXFEntity() {
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#getDXFEntity()
     */
    public DXFEntity getDXFEntity() {
        return dimension;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#getDXFEntityName()
     */
    public String getDXFEntityName() {
        
        return ENTITY_NAME;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#isFollowSequence()
     */
    public boolean isFollowSequence() {
        
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#parseGroup(int,
     *      de.miethxml.kabeja.parser.DXFValue)
     */
    public void parseGroup(int groupCode, DXFValue value) {
        switch (groupCode) {
        case GROUPCODE_TEXT_POINT_X:
            dimension.getTextPoint().setX(value.getDoubleValue());

            break;

        case GROUPCODE_TEXT_POINT_Y:
            dimension.getTextPoint().setY(value.getDoubleValue());

            break;

        case GROUPCODE_TEXT_POINT_Z:
            dimension.getTextPoint().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_INSERT_POINT_X:
            dimension.getInsertPoint().setX(value.getDoubleValue());

            break;

        case GROUPCODE_INSERT_POINT_Y:
            dimension.getInsertPoint().setY(value.getDoubleValue());

            break;

        case GROUPCODE_INSERT_POINT_Z:
            dimension.getInsertPoint().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT_X:
            dimension.getReferencePoint().setX(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT_Y:
            dimension.getReferencePoint().setY(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT_Z:
            dimension.getReferencePoint().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT3_X:
            dimension.getReferencePoint3().setX(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT3_Y:
            dimension.getReferencePoint3().setY(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT3_Z:
            dimension.getReferencePoint3().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT4_X:
            dimension.getReferencePoint4().setX(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT4_Y:
            dimension.getReferencePoint4().setY(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT4_Z:
            dimension.getReferencePoint4().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT5_X:
            dimension.getReferencePoint5().setX(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT5_Y:
            dimension.getReferencePoint5().setY(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT5_Z:
            dimension.getReferencePoint5().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT6_X:
            dimension.getReferencePoint6().setX(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT6_Y:
            dimension.getReferencePoint6().setY(value.getDoubleValue());

            break;

        case GROUPCODE_REFERENCE_POINT6_Z:
            dimension.getReferencePoint6().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_DIMENSION_BLOCK:
            dimension.setDimensionBlock(value.getValue());

            break;

        case GROUPCODE_DIMENSION_STYLE:
            dimension.setDimensionStyleID(value.getValue());

            break;

        case GROUPCODE_DIMENSION_TYPE:
            dimension.setDimensionType(value.getIntegerValue());

            break;

        case GROUPCODE_DIMENSION_TEXT:
            dimension.setDimensionText(value.getValue());

            break;

        case GROUPCODE_HORIZONTAL_ALIGNMENT:
            dimension.setHorizontalAlign(value.getDoubleValue());

            break;

        case GROUPCODE_INCLINATION_HELPLINE:
            dimension.setInclinationHelpLine(value.getDoubleValue());

            break;

        case GROUPCODE_LEADINGLINE_LENGTH:
            dimension.setLeadingLineLength(value.getDoubleValue());

            break;

        case GROUPCODE_TEXT_ROTATION:
            dimension.setTextRotation(value.getDoubleValue());

            break;

        case GROUPCODE_DIMENSION_ROTATE:
            dimension.setDimensionRotation(value.getDoubleValue());

            break;

        case GROUPCODE_DIMENSION_AREA:
            dimension.setDimensionArea(value.getIntegerValue());

            break;

        default:
            super.parseCommonProperty(groupCode, value, dimension);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#startDXFEntity()
     */
    public void startDXFEntity() {
        dimension = new DXFDimension();
    }
}
