/*******************************************************************************************************
 *
 * ModelsLibraryFolder.java, in gama.ui.navigator, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.ui.navigator.contents;

import gama.ui.shared.utils.WorkbenchHelper;

/**
 * The Class ModelsLibraryFolder.
 */
public class ModelsLibraryFolder extends TopLevelFolder {

	/**
	 * Instantiates a new models library folder.
	 *
	 * @param root
	 *            the root
	 * @param name
	 *            the name
	 */
	public ModelsLibraryFolder(final NavigatorRoot root, final String name) {
		super(root, name, FOLDER_BUILTIN, "Models shipped with GAMA", BLUE, WorkbenchHelper.BUILTIN_NATURE,
				Location.CoreModels);
	}

}
