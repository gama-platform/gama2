/*******************************************************************************************************
 *
 * DXFHandler.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.parser.dxf;

import gama.dependencies.kabeja.parser.DXFValue;
import gama.dependencies.kabeja.parser.ParseException;


public interface DXFHandler {
    public void parseGroup(int groupCode, DXFValue value)
        throws ParseException;
}
