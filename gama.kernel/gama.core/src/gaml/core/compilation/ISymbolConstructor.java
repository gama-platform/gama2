/*******************************************************************************************************
 *
 * ISymbolConstructor.java, in gama.core, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.core.compilation;

import gaml.core.descriptions.IDescription;

/**
 * Written by drogoul Modified on 29 ao�t 2010
 * 
 * @todo Description
 * 
 */
@FunctionalInterface
public interface ISymbolConstructor {

	/**
	 * Creates the.
	 *
	 * @param description the description
	 * @return the i symbol
	 */
	public ISymbol create(IDescription description);

}
