/*******************************************************************************************************
 *
 * TransformContext.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.math;

import gama.dependencies.kabeja.dxf.helpers.Point;
import gama.dependencies.kabeja.dxf.helpers.Vector;


public class TransformContext {
    private double[][] transformMatrix;
    private double rotationAngle = 0.0;
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    private double scaleZ = 1.0;
    private Vector translation = new Vector(1.0, 1.0, 1.0);

    public TransformContext() {
        transformMatrix = new double[][] {
                { 1.0, 0.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0, 0.0 },
                { 0.0, 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 0.0, 1.0 }
            };
    }

    public TransformContext(double[][] transformMatrix)
        throws IllegalArgumentException {
        if ((transformMatrix.length != 4) && (transformMatrix[0].length != 4)) {
            throw new IllegalArgumentException(
                "Not a valid tranformation matrix.");
        }

        this.transformMatrix = transformMatrix;
    }

    public double getRotationZAxis() {
        return rotationAngle;
    }

    public void setRotationZAxis(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public void setScale(double s) {
        this.setScale(s, s, s);
    }

    public void setScale(double x, double y, double z) {
        this.scaleX = x;
        this.scaleY = y;
        this.scaleZ = z;

        double[][] m = new double[][] {
                { x, 0.0, 0.0, 0.0 },
                { 0.0, y, 0.0, 0.0 },
                { 0.0, 0.0, z, 0.0 },
                { 0.0, 0.0, 0.0, 1.0 }
            };
        this.transformMatrix = MathUtils.multiplyMatrixByMatrix(this.transformMatrix,
                m);
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.setScale(scaleX, 1.0, 1.0);
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.setScale(1.0, scaleY, 1.0);
    }

    public double getScaleZ() {
        return scaleZ;
    }

    public void setScaleZ(double scaleZ) {
        this.setScale(1.0, 1.0, scaleZ);
    }

    public double[][] getTransformMatrix() {
        return transformMatrix;
    }

    public void setTransformMatrix(double[][] transformMatrix) {
        this.transformMatrix = transformMatrix;
    }

    public Vector getTranslation() {
        return translation;
    }

    public void setTranslation(Vector translation) {
        this.translation = translation;

        double[][] m = new double[][] {
                { 1.0, 0.0, 0.0, translation.getX() },
                { 0.0, 1.0, 0.0, translation.getY() },
                { 0.0, 0.0, 1.0, translation.getZ() },
                { 0.0, 0.0, 0.0, 1.0 }
            };
        this.transformMatrix = MathUtils.multiplyMatrixByMatrix(this.transformMatrix,
                m);
    }

    public Point transform(Point a) {
        double[] v = new double[] { a.getX(), a.getY(), a.getZ(), 1.0 };
        v = MathUtils.multiplyMatrixByVector(this.transformMatrix, v);

        Point p = new Point(v[0], v[1], v[2]);

        return p;
    }
}
