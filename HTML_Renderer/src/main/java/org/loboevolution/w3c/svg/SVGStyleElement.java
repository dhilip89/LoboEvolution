/*
    GNU GENERAL LICENSE
    Copyright (C) 2014 - 2018 Lobo Evolution

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
    

    Contact info: ivan.difrancesco@yahoo.it
 */
package org.loboevolution.w3c.svg;

import org.w3c.dom.DOMException;

public interface SVGStyleElement extends SVGElement {
	public String getXMLspace();

	public void setXMLspace(String xmlspace) throws DOMException;

	public String getType();

	public void setType(String type) throws DOMException;

	public String getMedia();

	public void setMedia(String media) throws DOMException;

	public String getTitle();

	public void setTitle(String title) throws DOMException;
}
