/*
    GNU GENERAL LICENSE
    Copyright (C) 2006 The Lobo Project. Copyright (C) 2014 - 2017 Lobo Evolution

    This program is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    verion 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    General License for more details.

    You should have received a copy of the GNU General Public
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    

    Contact info: lobochief@users.sourceforge.net; ivan.difrancesco@yahoo.it
 */

package org.lobobrowser.w3c.html;


/**
 * The Interface HTMLTableColElement.
 */
public interface HTMLTableColElement extends HTMLElement {
	
	/**
	 * Gets the span.
	 *
	 * @return the span
	 */
	// HTMLTableColElement
	public int getSpan();

	/**
	 * Sets the span.
	 *
	 * @param span
	 *            the new span
	 */
	public void setSpan(int span);

	/**
	 * Gets the align.
	 *
	 * @return the align
	 */
	// HTMLTableColElement-8
	public String getAlign();

	/**
	 * Sets the align.
	 *
	 * @param align
	 *            the new align
	 */
	public void setAlign(String align);

	/**
	 * Gets the ch.
	 *
	 * @return the ch
	 */
	public String getCh();

	/**
	 * Sets the ch.
	 *
	 * @param ch
	 *            the new ch
	 */
	public void setCh(String ch);

	/**
	 * Gets the ch off.
	 *
	 * @return the ch off
	 */
	public String getChOff();

	/**
	 * Sets the ch off.
	 *
	 * @param chOff
	 *            the new ch off
	 */
	public void setChOff(String chOff);

	/**
	 * Gets the v align.
	 *
	 * @return the v align
	 */
	public String getVAlign();

	/**
	 * Sets the v align.
	 *
	 * @param vAlign
	 *            the new v align
	 */
	public void setVAlign(String vAlign);

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public String getWidth();

	/**
	 * Sets the width.
	 *
	 * @param width
	 *            the new width
	 */
	public void setWidth(String width);
}
