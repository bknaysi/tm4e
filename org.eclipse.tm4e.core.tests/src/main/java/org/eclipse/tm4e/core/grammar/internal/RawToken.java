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
package org.eclipse.tm4e.core.grammar.internal;

import java.util.List;

public class RawToken {

	private String value;
	private List<String> scopes;

	public RawToken() {
	}

	public RawToken(String value, List<String> scopes) {
		this.value = value;
		this.scopes = scopes;
	}

	public String getValue() {
		return value;
	}

	public List<String> getScopes() {
		return scopes;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RawToken)) {
			return false;
		}
		RawToken other = (RawToken) obj;
		if (!(this.value.equals(other.value))) {
			return false;
		}
		return this.scopes.equals(other.scopes);
	}
}
