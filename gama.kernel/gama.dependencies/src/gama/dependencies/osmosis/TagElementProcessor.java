// This software is released into the Public Domain. See copying.txt for details.
package gama.dependencies.osmosis;

import org.xml.sax.Attributes;

/**
 * Provides an element processor implementation for a tag.
 *
 * @author Brett Henderson
 */
public class TagElementProcessor extends BaseElementProcessor {
	private static final String ATTRIBUTE_NAME_KEY = "k";
	private static final String ATTRIBUTE_NAME_VALUE = "v";

	private final TagListener tagListener;
	private Tag tag;

	/**
	 * Creates a new instance.
	 *
	 * @param parentProcessor
	 *            The parent element processor.
	 * @param tagListener
	 *            The tag listener for receiving created tags.
	 */
	public TagElementProcessor(final BaseElementProcessor parentProcessor, final TagListener tagListener) {
		super(parentProcessor, true);

		this.tagListener = tagListener;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void begin(final Attributes attributes) {
		String key;
		String value;

		key = attributes.getValue(ATTRIBUTE_NAME_KEY);
		value = attributes.getValue(ATTRIBUTE_NAME_VALUE);

		tag = new Tag(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void end() {
		tagListener.processTag(tag);
		tag = null;
	}
}
