/*******************************************************************************************************
 *
 * NavigatorFilter.java, in gama.ui.navigator, is part of the source code of the
 * GAMA modeling and simulation platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 * 
 ********************************************************************************************************/
package gama.ui.navigator;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import gama.ui.navigator.contents.ResourceManager;
import gama.ui.navigator.contents.WrappedFolder;
import gama.ui.navigator.metadata.FileMetaDataProvider;

/**
 * The Class NavigatorFilter.
 */
public class NavigatorFilter extends ViewerFilter {

	/**
	 * Instantiates a new navigator filter.
	 */
	public NavigatorFilter() {}

	@Override
	public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
		if (parentElement instanceof TreePath && ResourceManager.isFile(element)) {
			final TreePath p = (TreePath) parentElement;
			if (p.getLastSegment() instanceof WrappedFolder) {
				final IResource r = FileMetaDataProvider.shapeFileSupportedBy(ResourceManager.getFile(element));
				if (r != null) { return false; }
			}
		}
		return true;
	}

}
