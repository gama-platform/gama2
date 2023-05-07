/*******************************************************************************************************
 *
 * DXFMTextHandler.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.parser.entities;

import gama.dependencies.kabeja.dxf.DXFEntity;
import gama.dependencies.kabeja.dxf.DXFMText;
import gama.dependencies.kabeja.parser.DXFValue;


/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public class DXFMTextHandler extends AbstractEntityHandler {
    public static final String ENTITY_NAME = "MTEXT";
    public static final int TEXT_VALUE_END = 1;
    public static final int TEXT_VALUE = 3;
    public static final int TEXT_HEIGHT = 40;
    public static final int TEXT_REF_WIDTH = 41;
    public static final int TEXT_REF_HEIGHT = 43;
    public static final int TEXT_DRAWING_DIRECTION_FLAG = 72;
    public static final int TEXT_ATTACHMENT_POINT = 71;
    public static final int TEXT_VALIGN = 73;
    public static final int TEXT_ALIGN_X = 11;
    public static final int TEXT_ALIGN_Y = 21;
    public static final int TEXT_ALIGN_Z = 31;
    public static final int TEXT_STYLE = 7;
    public static final int TEXT_OBLIQUEANGLE = 51;
    public static final int TEXT_ROTATION = 50;
    private DXFMText mtext;
    private StringBuffer buf = new StringBuffer();
    private int insert = 0;

    /**
     *
     */
    public DXFMTextHandler() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#endParsing()
     */
    public void endDXFEntity() {
        mtext.setText(buf.toString());
        buf.delete(0, buf.length());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#getEntity()
     */
    public DXFEntity getDXFEntity() {
        return mtext;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#getEntityName()
     */
    public String getDXFEntityName() {
        return ENTITY_NAME;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#isFollowSequence()
     */
    public boolean isFollowSequence() {
        
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#parseGroup(int,
     *      org.dxf2svg.parser.DXFValue)
     */
    public void parseGroup(int groupCode, DXFValue value) {
        switch (groupCode) {
        case TEXT_VALUE:

            String part = value.getValue();
            buf.insert(insert, part);
            insert += part.length();

            break;

        case TEXT_VALUE_END:
            buf.insert(insert, value.getValue());

            break;

        case TEXT_ATTACHMENT_POINT:
            mtext.setAttachmentPoint(value.getIntegerValue());

            break;

        case GROUPCODE_START_X:
            mtext.setX(value.getDoubleValue());

            break;

        case GROUPCODE_START_Y:
            mtext.setY(value.getDoubleValue());

            break;

        case GROUPCODE_START_Z:
            mtext.setZ(value.getDoubleValue());

            break;

        case TEXT_ALIGN_X:
            mtext.setAlignX(value.getDoubleValue());
            mtext.setRotation(0.0);

            break;

        case TEXT_ALIGN_Y:
            mtext.setAlignY(value.getDoubleValue());
            mtext.setRotation(0.0);

            break;

        case TEXT_ALIGN_Z:
            mtext.setAlignZ(value.getDoubleValue());
            mtext.setRotation(0.0);

            break;

        case TEXT_HEIGHT:
            mtext.setHeight(value.getDoubleValue());

            break;

        case TEXT_DRAWING_DIRECTION_FLAG:

            switch (value.getIntegerValue()) {
            case 2:
                mtext.setBackward(true);

                break;

            case 4:
                mtext.setUpsideDown(true);

                break;
            }

            break;

        case TEXT_STYLE:
            mtext.setTextStyle(value.getValue());

            break;

        case TEXT_ROTATION:
            mtext.setRotation(value.getDoubleValue());

            break;

        case TEXT_REF_WIDTH:
            mtext.setReferenceWidth(value.getDoubleValue());

            break;

        case TEXT_REF_HEIGHT:
            mtext.setReferenceHeight(value.getDoubleValue());

            break;

        case TEXT_OBLIQUEANGLE:
            mtext.setObliqueAngle(value.getDoubleValue());

            break;

        default:
            super.parseCommonProperty(groupCode, value, mtext);

            break;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.parser.entities.EntityHandler#startParsing()
     */
    public void startDXFEntity() {
        mtext = new DXFMText();
        insert = 0;
    }
}
