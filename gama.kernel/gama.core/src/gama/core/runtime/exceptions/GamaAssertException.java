/*******************************************************************************************************
 *
 * GamaAssertException.java, in gama.core, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.core.runtime.exceptions;

import gama.core.runtime.IScope;

/**
 * The Class GamaAssertException.
 */
public class GamaAssertException extends GamaRuntimeException {

	/**
	 * Instantiates a new gama assert exception.
	 *
	 * @param scope the scope
	 * @param s the s
	 * @param warning the warning
	 */
	public GamaAssertException(final IScope scope, final String s, final boolean warning) {
		super(scope, s, warning);
	}

}
