/*******************************************************************************************************
 *
 * DXFToleranceHandler.java, in gama.dependencies, is part of the source code of the
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
import gama.dependencies.kabeja.dxf.DXFTolerance;
import gama.dependencies.kabeja.parser.DXFValue;


/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public class DXFToleranceHandler extends AbstractEntityHandler {
    public final static int GROUPCODE_X_AXIS_DIRECTOPN_X = 11;
    public final static int GROUPCODE_X_AXIS_DIRECTOPN_Y = 21;
    public final static int GROUPCODE_X_AXIS_DIRECTOPN_Z = 31;
    protected DXFTolerance tolerance;

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.parser.entities.DXFEntityHandler#getDXFEntityName()
     */
    public String getDXFEntityName() {
        return DXFConstants.ENTITY_TYPE_TOLERANCE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.parser.entities.DXFEntityHandler#endDXFEntity()
     */
    public void endDXFEntity() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.parser.entities.DXFEntityHandler#getDXFEntity()
     */
    public DXFEntity getDXFEntity() {
        return tolerance;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.parser.entities.DXFEntityHandler#isFollowSequence()
     */
    public boolean isFollowSequence() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.parser.entities.DXFEntityHandler#parseGroup(int,
     *      org.kabeja.parser.DXFValue)
     */
    public void parseGroup(int groupCode, DXFValue value) {
        switch (groupCode) {
        case GROUPCODE_START_X:
            tolerance.getInsertionPoint().setX(value.getDoubleValue());

            break;

        case GROUPCODE_START_Y:
            tolerance.getInsertionPoint().setY(value.getDoubleValue());

            break;

        case GROUPCODE_START_Z:
            tolerance.getInsertionPoint().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_X_AXIS_DIRECTOPN_X:
            tolerance.getXaxisDirection().setX(value.getDoubleValue());

            break;

        case GROUPCODE_X_AXIS_DIRECTOPN_Y:
            tolerance.getXaxisDirection().setY(value.getDoubleValue());

            break;

        case GROUPCODE_X_AXIS_DIRECTOPN_Z:
            tolerance.getXaxisDirection().setZ(value.getDoubleValue());

            break;

        case GROUPCODE_TEXT:
            tolerance.setText(value.getValue());

            break;

        case GROUPCODE_STYLENAME:
            tolerance.setStyleID(value.getValue());

            break;

        default:
            super.parseCommonProperty(groupCode, value, tolerance);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kabeja.parser.entities.DXFEntityHandler#startDXFEntity()
     */
    public void startDXFEntity() {
        tolerance = new DXFTolerance();
    }
}
