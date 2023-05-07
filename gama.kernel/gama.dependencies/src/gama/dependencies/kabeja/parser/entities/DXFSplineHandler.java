/*******************************************************************************************************
 *
 * DXFSplineHandler.java, in gama.dependencies, is part of the source code of the
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
import gama.dependencies.kabeja.dxf.DXFSpline;
import gama.dependencies.kabeja.dxf.helpers.SplinePoint;
import gama.dependencies.kabeja.parser.DXFValue;


/**
 * @author <a href="mailto:simon.mieth@gmx.de">Simon Mieth</a>
 *
 */
public class DXFSplineHandler extends AbstractEntityHandler {
    public static final int CONTROL_POINT_X = 10;
    public static final int CONTROL_POINT_Y = 20;
    public static final int CONTROL_POINT_Z = 30;
    public static final int FIT_POINT_X = 11;
    public static final int FIT_POINT_Y = 21;
    public static final int FIT_POINT_Z = 31;
    public static final int START_TANGENT_X = 12;
    public static final int START_TANGENT_Y = 22;
    public static final int START_TANGENT_Z = 32;
    public static final int END_TANGENT_X = 13;
    public static final int END_TANGENT_Y = 23;
    public static final int END_TANGENT_Z = 33;
    public static final int FIT_TOLERANCE = 44;
    public static final int KNOTS = 40;
    public static final int WEIGHTS = 41;
    public static final int CONTROLPOINT_TOLERANCE = 42;
    public static final int KNOT_TOLERANCE = 43;
    public static final int NUMBER_OF_FIT_POINTS = 74;
    public static final int NUMBER_OF_CONTROL_POINTS = 73;
    public static final int NUMBER_OF_CONTROL_POINTS2 = 96;
    public static final int NUMBER_OF_NODES = 72;
    public static final int NUMBER_OF_NODES2 = 95;
    public static final int DEGREE = 71;
    private DXFSpline spline;
    private SplinePoint point;
    private double[] knots;
    private double[] weights;
    private int knotsCount;
    private int controlPointCount;

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#getDXFEntityName()
     */
    public String getDXFEntityName() {
        return DXFConstants.ENTITY_TYPE_SPLINE;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#endDXFEntity()
     */
    public void endDXFEntity() {
        spline.setKnots(knots);
        spline.setWeights(weights);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#getDXFEntity()
     */
    public DXFEntity getDXFEntity() {
        return spline;
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
        case DEGREE:
            spline.setDegree(value.getIntegerValue());

            break;

        case NUMBER_OF_CONTROL_POINTS:
            weights = new double[value.getIntegerValue()];
            controlPointCount = 0;
            spline.setControlPointSize(value.getIntegerValue());

            break;

        case NUMBER_OF_FIT_POINTS:
            spline.setFitPointSize(value.getIntegerValue());

            break;

        case NUMBER_OF_NODES:
            knots = new double[value.getIntegerValue()];
            knotsCount = 0;
            spline.setNodePointsSize(value.getIntegerValue());

            break;

        case NUMBER_OF_NODES2:
            knots = new double[value.getIntegerValue()];
            knotsCount = 0;
            spline.setNodePointsSize(value.getIntegerValue());

            break;

        case FIT_TOLERANCE:
            spline.setFitTolerance(value.getDoubleValue());

            break;

        case KNOTS:
            knots[knotsCount] = value.getDoubleValue();
            knotsCount++;

            break;

        case KNOT_TOLERANCE:
            spline.setKnotsTolerance(value.getDoubleValue());

            break;

        case WEIGHTS:
            weights[controlPointCount] = value.getDoubleValue();
            controlPointCount++;

            break;

        case CONTROLPOINT_TOLERANCE:
            spline.setControlPointTolerance(value.getDoubleValue());

            break;

        case FIT_POINT_X:
            point = new SplinePoint();
            point.setType(SplinePoint.TYPE_FITPOINT);
            point.setX(value.getDoubleValue());
            spline.addSplinePoint(point);

            break;

        case FIT_POINT_Y:
            point.setY(value.getDoubleValue());

            break;

        case FIT_POINT_Z:
            point.setZ(value.getDoubleValue());

            break;

        case CONTROL_POINT_X:
            point = new SplinePoint();
            point.setType(SplinePoint.TYPE_CONTROLPOINT);
            point.setX(value.getDoubleValue());
            spline.addSplinePoint(point);

            break;

        case CONTROL_POINT_Y:
            point.setY(value.getDoubleValue());

            break;

        case CONTROL_POINT_Z:
            point.setZ(value.getDoubleValue());

            break;

        case START_TANGENT_X:
            point = new SplinePoint();
            point.setType(SplinePoint.TYPE_STARTTANGENT);
            point.setX(value.getDoubleValue());
            spline.addSplinePoint(point);

            break;

        case START_TANGENT_Y:
            point.setY(value.getDoubleValue());

            break;

        case START_TANGENT_Z:
            point.setZ(value.getDoubleValue());

            break;

        case END_TANGENT_X:
            point = new SplinePoint();
            point.setType(SplinePoint.TYPE_ENDTANGENT);
            point.setX(value.getDoubleValue());
            spline.addSplinePoint(point);

            break;

        case END_TANGENT_Y:
            point.setY(value.getDoubleValue());

            break;

        case END_TANGENT_Z:
            point.setZ(value.getDoubleValue());

            break;

        default:
            super.parseCommonProperty(groupCode, value, spline);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.miethxml.kabeja.parser.entities.DXFEntityHandler#startDXFEntity()
     */
    public void startDXFEntity() {
        spline = new DXFSpline();
    }
}
