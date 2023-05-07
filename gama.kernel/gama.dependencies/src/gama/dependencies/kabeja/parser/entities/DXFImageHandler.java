/*******************************************************************************************************
 *
 * DXFImageHandler.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.parser.entities;

import gama.dependencies.kabeja.dxf.DXFConstants;
import gama.dependencies.kabeja.dxf.DXFEntity;
import gama.dependencies.kabeja.dxf.DXFImage;
import gama.dependencies.kabeja.dxf.helpers.Point;
import gama.dependencies.kabeja.parser.DXFValue;


/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public class DXFImageHandler extends AbstractEntityHandler {
    public final static int GROUPCODE_IMAGEDEF_HARDREFERENCE = 340;
    public final static int GROUPCODE_VECTOR_U_X = 11;
    public final static int GROUPCODE_VECTOR_U_Y = 21;
    public final static int GROUPCODE_VECTOR_U_Z = 31;
    public final static int GROUPCODE_VECTOR_V_X = 12;
    public final static int GROUPCODE_VECTOR_V_Y = 22;
    public final static int GROUPCODE_VECTOR_V_Z = 32;
    public final static int GROUPCODE_IAMGESIZE_U = 13;
    public final static int GROUPCODE_IAMGESIZE_V = 23;
    public final static int GROUPCODE_DISPLAY_PROPERTY = 70;
    public final static int GROUPCODE_BRIGHTNESS = 281;
    public final static int GROUPCODE_CONTRAST = 282;
    public final static int GROUPCODE_FADE = 283;
    public final static int GROUPCODE_NUMBER_CLIP_BOUNDARY = 91;
    public final static int GROUPCODE_CLIP_BOUNDARY_X = 14;
    public final static int GROUPCODE_CLIP_BOUNDARY_Y = 24;
    public final static int GROUPCODE_CLIP_BOUNDARY_TYPE = 71;
    public final static int GROUPCODE_CLIPPING_STATE = 280;
    protected DXFImage image;
    protected Point clippingPoint;

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#getDXFEntityName()
     */
    public String getDXFEntityName() {
        return DXFConstants.ENTITY_TYPE_IMAGE;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#endDXFEntity()
     */
    public void endDXFEntity() {
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#getDXFEntity()
     */
    public DXFEntity getDXFEntity() {
        
        return image;
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
        case GROUPCODE_START_X:
            image.getInsertPoint().setX(value.getDoubleValue());

            break;

        case GROUPCODE_START_Y:
            image.getInsertPoint().setY(value.getDoubleValue());

            break;

        case GROUPCODE_START_Z:
            image.getInsertPoint().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_IMAGEDEF_HARDREFERENCE:
            image.setImageDefObjectID(value.getValue());

            break;

        case GROUPCODE_VECTOR_U_X:
            image.getVectorU().setX(value.getDoubleValue());

            break;

        case GROUPCODE_VECTOR_U_Y:
            image.getVectorU().setY(value.getDoubleValue());

            break;

        case GROUPCODE_VECTOR_U_Z:
            image.getVectorU().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_VECTOR_V_X:
            image.getVectorV().setX(value.getDoubleValue());

            break;

        case GROUPCODE_VECTOR_V_Y:
            image.getVectorV().setY(value.getDoubleValue());

            break;

        case GROUPCODE_VECTOR_V_Z:
            image.getVectorV().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_IAMGESIZE_U:
            image.setImageSizeAlongU(value.getDoubleValue());

            break;

        case GROUPCODE_IAMGESIZE_V:
            image.setImageSizeAlongV(value.getDoubleValue());

            break;

        case GROUPCODE_CLIPPING_STATE:
            image.setClipping(value.getBooleanValue());

            break;

        case GROUPCODE_CLIP_BOUNDARY_X:
            clippingPoint = new Point();
            clippingPoint.setX(value.getDoubleValue());
            image.addClippingPoint(clippingPoint);

            break;

        case GROUPCODE_CLIP_BOUNDARY_Y:
            clippingPoint.setY(value.getDoubleValue());

            break;

        case GROUPCODE_BRIGHTNESS:
            image.setBrightness(value.getDoubleValue());

            break;

        case GROUPCODE_CONTRAST:
            image.setContrast(value.getDoubleValue());

            break;

        case GROUPCODE_FADE:
            image.setFade(value.getDoubleValue());

            break;

        case GROUPCODE_CLIP_BOUNDARY_TYPE:

            if (value.getIntegerValue() == 1) {
                image.setRectangularClipping(true);
            } else if (value.getIntegerValue() == 2) {
                image.setPolygonalClipping(true);
            }

            break;

        default:
            super.parseCommonProperty(groupCode, value, image);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#startDXFEntity()
     */
    public void startDXFEntity() {
        image = new DXFImage();
        image.setDXFDocument(doc);
    }
}
