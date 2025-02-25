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
package org.eclipse.tm4e.core.internal.grammar;

/**
 * Standard TextMate token type.
 *
 */
public class StandardTokenType {

	/**
	 * Content should be accessed statically
	 */
	private StandardTokenType() {
	}

	public static final int Other = 0;
	public static final int Comment = 1;
	public static final int String = 2;
	public static final int RegEx = 4;

}
