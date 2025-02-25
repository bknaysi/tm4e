/**
 *  Copyright (c) 2015-2017 Angelo ZERR.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Initial code from https://github.com/chjj/marked/
 * Initial copyright Copyright (c) 2011-2014, Christopher Jeffrey and others
 * Initial license: MIT
 *
 * Contributors:
 *  - Christopher Jeffrey and others: Initial code, written in JavaScript, licensed under MIT license
 *  - Angelo Zerr <angelo.zerr@gmail.com> - translation and adaptation to Java
 */
package org.eclipse.tm4e.markdown.marked;

import static org.eclipse.tm4e.markdown.marked.Helpers.isEmpty;

import java.util.regex.Matcher;

public class Lexer {

	private BlockRules rules;
	private final Tokens tokens;
	private final Options options;

	public Lexer(Options options) {
		this.tokens = new Tokens();
		this.options = options != null ? options : Options.DEFAULTS;
		this.rules = BlockRules.normal;

		if (this.options.isGfm()) {
			if (this.options.isTables()) {
				this.rules = BlockRules.tables;
			} else {
				this.rules = BlockRules.gfm;
			}
		}
	}

	public static Tokens lex(String src, Options options) {
		Lexer lexer = new Lexer(options);
		return lexer.lex(src);
	}

	private Tokens lex(String src) {
		src = src.replaceAll("\r\n|\r", "\n").replaceAll("\t", "    ").replaceAll("\u00a0", " ").replaceAll("\u2424",
				"\n");
		return this.token(src, true);
	}

	private Tokens token(String src, boolean top) {
		return token(src, top, null);
	}

	private Tokens token(String src, boolean top, Object bq) {
		src = src.replaceAll("^ +$", "");
		Matcher cap;
		while (!isEmpty(src)) {

			// newline
			if ((cap = this.rules.newline.exec(src)) != null) {
				src = src.substring(cap.group(0).length());
				if (cap.group(0).length() > 1) {
					this.tokens.add(new Token(TokenType.space));
				}
			}

			// code
			// if ((cap = this.rules.code.exec(src)) != null) {
			// src = src.substring(cap.group(0).length());
			// cap = cap.group(0).matches("^ {4}", "");
			// String text = !this.options.pedantic
			// ? cap.replace(/\n+$/, '')
			// : cap;
			// this.tokens.add(new Token(TokenType.type));
			// continue;
			// }

			// fences (gfm)
			if ((cap = this.rules.fences.exec(src)) != null) {
				src = src.substring(cap.group(0).length());
				String lang = cap.group(2);
				String text = !isEmpty(cap.group(3)) ? cap.group(3) : "";
				this.tokens.add(new Token(TokenType.code, lang, text));
				continue;
			}

			// heading
			if ((cap = this.rules.heading.exec(src)) != null) {
				src = src.substring(cap.group(0).length());
				String text = cap.group(2);
				int depth = cap.group(1).length();
				this.tokens.add(new Token(TokenType.heading, text, depth));
				continue;
			}

			// table no leading pipe (gfm)
			// TODO

			// lheading
			if ((cap = this.rules.lheading.exec(src)) != null) {
				src = src.substring(cap.group(0).length());
				String text = cap.group(1);
				int depth = cap.group(2).equals("=") ? 1 : 2;
				this.tokens.add(new Token(TokenType.heading, text, depth));
				continue;
			}

			// hr
			if ((cap = this.rules.hr.exec(src)) != null) {
				src = src.substring(cap.group(0).length());
				this.tokens.add(new Token(TokenType.hr));
				continue;
			}

			// top-level paragraph
			if (top && ((cap = this.rules.paragraph.exec(src)) != null)) {
				src = src.substring(cap.group(0).length());
				String text = cap.group(1).charAt(cap.group(1).length() - 1) == '\n' ? cap.group(1) : cap.group(1);
				this.tokens.add(new Token(TokenType.paragraph, text));
				continue;
			}
		}
		return this.tokens;
	}

}
