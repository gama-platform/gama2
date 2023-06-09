/*******************************************************************************************************
 *
 * ParametricLine.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.dxf.helpers;

import gama.dependencies.kabeja.math.MathUtils;


public class ParametricLine {
    protected Point startPoint;
    protected Vector direction;

    public ParametricLine(Point startPoint, Vector direction) {
        this.startPoint = startPoint;
        this.direction = direction;
    }

    public ParametricLine(Point start, Point end) {
        this.startPoint = start;
        this.direction = MathUtils.getVector(start, end);
    }

    public ParametricLine() {
        this(new Point(), new Point());
    }

    public double getIntersectionParameter(ParametricLine line) {
        Vector n = MathUtils.crossProduct(this.direction,
                line.getDirectionVector());

        if (MathUtils.absoluteValue(n) == 0.0) {
            //System.out.println("parallel");
            return Double.POSITIVE_INFINITY;
        }

        Vector m = MathUtils.crossProduct(MathUtils.getVector(this.startPoint,
                    line.getStartPoint()), line.getDirectionVector());
        double s = 0;

        if (n.getX() != 0.0) {
            s = m.getX() / n.getX();
        } else if (n.getY() != 0.0) {
            s = m.getY() / n.getY();
        } else if (n.getZ() != 0.0) {
            s = m.getZ() / n.getZ();
        }

        return s;
    }

    public Point getStartPoint() {
        return this.startPoint;
    }

    public void setStartPoint(Point start) {
        this.startPoint = start;
    }

    public Vector getDirectionVector() {
        return this.direction;
    }

    public void setDirectionVector(Vector v) {
        this.direction = v;
    }

    public Point getPointAt(double para) {
        return MathUtils.getPointOfStraightLine(this.startPoint,
            this.direction, para);
    }

    public double getParameter(Point p) {
        double t = 0;

        if (this.direction.getX() != 0) {
            t = (p.getX() - this.startPoint.getX()) / this.direction.getX();
        } else if (this.direction.getY() != 0.0) {
            t = (p.getY() - this.startPoint.getY()) / this.direction.getY();
        } else if (this.direction.getZ() != 0.0) {
            t = (p.getZ() - this.startPoint.getZ()) / this.direction.getZ();
        }

        return t;
    }
}
