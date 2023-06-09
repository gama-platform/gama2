/*******************************************************************************************************
 *
 * ISaveDelegate.java, in gama.core, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.core.common.interfaces;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import gama.core.runtime.IScope;
import gaml.core.expressions.IExpression;
import gaml.core.types.IType;
import gaml.core.types.Types;

/**
 * The Interface ISaveDelegate.
 */
public interface ISaveDelegate {

	/**
	 * Save an item to disk.
	 *
	 * @param scope
	 *            the runtime scope
	 * @param item
	 *            the item to save
	 * @param file
	 *            the file to save it to
	 * @param code
	 *            the code (epsg code)
	 * @param addHeader
	 *            wether to add a header or not when it makes sense
	 * @param type
	 *            the type of saved data we target (e.g. 'image', etc.)
	 * @param attributesToSave
	 *            the attributes to save. Can be an instance of Arguments or an expression containing a map<string,
	 *            value> or a list<string>.
	 * @throws IOException
	 */
	void save(IScope scope, IExpression item, File file, String code, boolean addHeader, String type,
			Object attributesToSave) throws IOException;

	/**
	 * The type of the item. Returns the gaml type required for triggering this save delegate. If no type is declared
	 * (by default), then only the type of the file to produce (see {@link #getFileTypes()} is used to determine which
	 * save delegate to run. If a gaml type is declared, the type of the file needs also to match (this allows two
	 * delegates to save the same objects, but with different file types).
	 *
	 * @return the i type
	 */
	default IType getDataType() { return Types.NO_TYPE; }

	/**
	 * Returns the types of file this ISaveDelegate is able to produce (e.g. "image", "shp", etc.). This is used to
	 * determine which ISaveDelegate to choose when 'save' is invoked in addition to the gaml type provided (see
	 * {@link #getDataType()}
	 *
	 * @return the type
	 */
	Set<String> getFileTypes();

}