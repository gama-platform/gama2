/*******************************************************************************************************
 *
 * IElement.java, in gama.processor, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.processor.precompiler.doc;

/**
 * The Interface IElement.
 */
public interface IElement {
	
	/**
	 * Gets the element DOM.
	 *
	 * @return the element DOM
	 */
	public org.w3c.dom.Element getElementDOM();
}
