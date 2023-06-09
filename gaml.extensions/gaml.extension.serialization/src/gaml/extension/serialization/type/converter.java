/*******************************************************************************************************
 *
 * converter.java, in gaml.extension.serialization, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gaml.extension.serialization.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gaml.extension.serialization.type.converters.IGamaConverter;

/**
 * The Annotation converter.
 */
@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.TYPE)

public @interface converter {

	/** The class to use as converter. */
	Class<? extends IGamaConverter> value();
}
