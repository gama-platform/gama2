/*******************************************************************************************************
 *
 * DXFVertex.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.dxf;

import java.util.Map;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import gama.dependencies.kabeja.dxf.helpers.Point;
import gama.dependencies.kabeja.math.TransformContext;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 */
public class DXFVertex extends DXFPoint {
    private double startWidth = 0.0;
    private double endWidth = 0.0;
    private double bulge = 0;
    private int polyFaceMeshVertex0;
    private int polyFaceMeshVertex1;
    private int polyFaceMeshVertex2;
    private int polyFaceMeshVertex3;

    /**
     *
     */
    public DXFVertex() {
        super();
    }

    public DXFVertex(Point p) {
        super(p);
    }

    /**
     * @return Returns the endWidth.
     */
    public double getEndWidth() {
        return endWidth;
    }

    /**
     * @param endWidth
     *            The endWidth to set.
     */
    public void setEndWidth(double endWidth) {
        this.endWidth = endWidth;
    }

    /**
     * @return Returns the startWidth.
     */
    public double getStartWidth() {
        return startWidth;
    }

    /**
     * @param startWidth
     *            The startWidth to set.
     */
    public void setStartWidth(double startWidth) {
        this.startWidth = startWidth;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.dxf2svg.dxf.DXFEntity#toSAX(org.xml.sax.ContentHandler)
     */
    public void toSAX(ContentHandler handler, Map svgContext, DXFEntity entity,
        TransformContext transformContext) throws SAXException {
    }

    public Bounds getBounds() {
        return super.getBounds();
    }

    /**
     * @return Returns the bulge.
     */
    public double getBulge() {
        return bulge;
    }

    /**
     * @param bulge
     *            The bulge to set.
     */
    public void setBulge(double bulge) {
        this.bulge = bulge;
    }

    /* (non-Javadoc)
     * @see de.miethxml.kabeja.dxf.DXFEntity#getType()
     */
    public String getType() {
        return DXFConstants.ENTITY_TYPE_VERTEX;
    }

    public boolean isConstantWidth() {
        return endWidth == startWidth;
    }

    public boolean isCurveFitVertex() {
        return (this.flags & 1) == 1;
    }

    public boolean isTagentUsed() {
        return (this.flags & 2) == 2;
    }

    public boolean is2DSplineControlVertex() {
        return (this.flags & 16) == 16;
    }

    public boolean is2DSplineApproximationVertex() {
        return (this.flags & 8) == 8;
    }

    public boolean isPolyFaceMeshVertex() {
        //bit 7 and 8 are set
        return (((this.flags & 64) == 64) && ((this.flags & 128) == 128));
    }

    public boolean isFaceRecord() {
        return this.flags == 128;
    }

    public boolean isMeshApproximationVertex() {
        return ((this.flags & 64) == 64) && ((this.flags & 8) == 8);
    }

    /**
     * @return Returns the polyFaceMeshVertex0.
     */
    public int getPolyFaceMeshVertex0() {
        return Math.abs(polyFaceMeshVertex0);
    }

    /**
     * @param polyFaceMeshVertex0 The polyFaceMeshVertex0 to set.
     */
    public void setPolyFaceMeshVertex0(int polyFaceMeshVertex0) {
        this.polyFaceMeshVertex0 = polyFaceMeshVertex0;
    }

    /**
     * @return Returns the polyFaceMeshVertex1.
     */
    public int getPolyFaceMeshVertex1() {
        return Math.abs(polyFaceMeshVertex1);
    }

    /**
     * @param polyFaceMeshVertex1 The polyFaceMeshVertex1 to set.
     */
    public void setPolyFaceMeshVertex1(int polyFaceMeshVertex1) {
        this.polyFaceMeshVertex1 = polyFaceMeshVertex1;
    }

    /**
     * @return Returns the polyFaceMeshVertex2.
     */
    public int getPolyFaceMeshVertex2() {
        return Math.abs(polyFaceMeshVertex2);
    }

    /**
     * @param polyFaceMeshVertex2 The polyFaceMeshVertex2 to set.
     */
    public void setPolyFaceMeshVertex2(int polyFaceMeshVertex2) {
        this.polyFaceMeshVertex2 = polyFaceMeshVertex2;
    }

    /**
     * @return Returns the polyFaceMeshVertex3.
     */
    public int getPolyFaceMeshVertex3() {
        return Math.abs(polyFaceMeshVertex3);
    }

    /**
     * @param polyFaceMeshVertex3 The polyFaceMeshVertex3 to set.
     */
    public void setPolyFaceMeshVertex3(int polyFaceMeshVertex3) {
        this.polyFaceMeshVertex3 = polyFaceMeshVertex3;
    }

    public boolean isPolyFaceEdge0Visible() {
        return this.polyFaceMeshVertex0 > 0;
    }

    public boolean isPolyFaceEdge1Visible() {
        return this.polyFaceMeshVertex1 > 0;
    }

    public boolean isPolyFaceEdge2Visible() {
        return this.polyFaceMeshVertex2 > 0;
    }

    public boolean isPolyFaceEdge3Visible() {
        return this.polyFaceMeshVertex3 > 0;
    }
}
