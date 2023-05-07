/*******************************************************************************************************
 *
 * ParseException.java, in gama.dependencies, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.dependencies.kabeja.parser;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 *
 */
public class ParseException extends Exception {
    public ParseException(String s) {
        super(s);
    }

    public ParseException(String s, Exception e) {
        super(s, e);
    }

    public ParseException(Exception e) {
        super(e);
    }
}
