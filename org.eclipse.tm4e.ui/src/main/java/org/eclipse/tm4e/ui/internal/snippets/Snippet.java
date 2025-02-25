/**
 *  Copyright (c) 2015-2017 Angelo ZERR.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.tm4e.ui.internal.snippets;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.tm4e.registry.TMResource;
import org.eclipse.tm4e.registry.XMLConstants;
import org.eclipse.tm4e.ui.snippets.ISnippet;

public class Snippet extends TMResource implements ISnippet {

	private String scopeName;
	private String name;

	/**
	 * Constructor for user preferences (loaded from Json with Gson).
	 */
	public Snippet() {
		super();
	}

	/**
	 * Constructor for extension point.
	 *
	 * @param scopeName
	 */
	public Snippet(String scopeName, String path, String name) {
		super(path);
		this.scopeName = scopeName;
		this.name = name;
	}

	public Snippet(IConfigurationElement ce) {
		super(ce);
		this.scopeName = ce.getAttribute(XMLConstants.SCOPE_NAME_ATTR);
		this.name = ce.getAttribute(XMLConstants.NAME_ATTR);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getContent() {
		return getResourceContent();
	}

	@Override
	public String getScopeName() {
		return scopeName;
	}
}
