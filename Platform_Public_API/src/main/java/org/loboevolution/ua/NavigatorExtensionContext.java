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
package org.loboevolution.ua;

import org.loboevolution.clientlet.ClientletSelector;

/**
 * This interface gives extensions access to the platform.
 *
 * @see NavigatorExtension#init(NavigatorExtensionContext)
 */
public interface NavigatorExtensionContext {

	/**
	 * Adds a clientlet selector. This is how platform extensions register
	 * additional content handlers.
	 *
	 * @param selector
	 *            the selector
	 */
	void addClientletSelector(ClientletSelector selector);

	/**
	 * Removes the clientlet selector.
	 *
	 * @param selector
	 *            the selector
	 */
	void removeClientletSelector(ClientletSelector selector);

	/**
	 * Adds an object that can view connections made by the browser and
	 * potentially modify their headers and other data.
	 *
	 * @param processor
	 *            An connection processor.
	 */
	void addConnectionProcessor(ConnectionProcessor processor);

	/**
	 * Removes the connection processor.
	 *
	 * @param processor
	 *            the processor
	 */
	void removeConnectionProcessor(ConnectionProcessor processor);

	/**
	 * Adds a listener of navigator events.
	 *
	 * @param listener
	 *            the listener
	 */
	void addNavigatorErrorListener(NavigatorErrorListener listener);

	/**
	 * Removes a listener of navigation events.
	 *
	 * @param listener
	 *            the listener
	 */
	void removeNavigatorErrorListener(NavigatorErrorListener listener);

	/**
	 * Adds a global listener of navigation events.
	 *
	 * @param listener
	 *            The listener.
	 */
	void addNavigationListener(NavigationListener listener);

	/**
	 * Removes the navigation listener.
	 *
	 * @param listener
	 *            the listener
	 */
	void removeNavigationListener(NavigationListener listener);

	/**
	 * Gets the user agent.
	 *
	 * @return the user agent
	 */
	UserAgent getUserAgent();

	/**
	 * Registers a URL stream handler factory which may be used to implement
	 * custom protocols. Note that Java platform protocols (HTTP, HTTPS, etc.)
	 * or protocols defined by extensions with higher priority cannot be
	 * overridden. The factory must return <code>null</code> if it does not know
	 * how to handle a particular protocol.
	 *
	 * @param factory
	 *            An implementation of
	 *            <code>java.net.URLStreamHandlerFactory</code>.
	 */
	void addURLStreamHandlerFactory(java.net.URLStreamHandlerFactory factory);
}
