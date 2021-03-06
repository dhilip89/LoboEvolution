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
package org.loboevolution.primary.clientlets.html;

import java.awt.Component;
import java.awt.Insets;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loboevolution.clientlet.AbstractComponentContent;
import org.loboevolution.html.domimpl.HTMLDocumentImpl;
import org.loboevolution.html.gui.HtmlPanel;
import org.loboevolution.http.Cookie;
import org.loboevolution.http.Urls;
import org.loboevolution.info.MetaInfo;
import org.loboevolution.request.CookieStore;
import org.loboevolution.util.Nodes;
import org.loboevolution.util.Strings;
import org.loboevolution.util.io.BufferExceededException;
import org.loboevolution.util.io.RecordedInputStream;
import org.loboevolution.w3c.html.HTMLDocument;
import org.loboevolution.w3c.html.HTMLElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The Class HtmlContent.
 */
public class HtmlContent extends AbstractComponentContent {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(HtmlContent.class);

	/** The Constant serialVersionUID. */
	
	/** The document. */
	private final HTMLDocument document;

	/** The panel. */
	private final HtmlPanel panel;

	/** The ris. */
	private final RecordedInputStream ris;

	/** The charset. */
	private final String charset;

	/** The source code. */
	private final String sourceCode;

	/**
	 * Instantiates a new html content.
	 *
	 * @param document
	 *            the document
	 * @param panel
	 *            the panel
	 * @param ris
	 *            the ris
	 * @param charset
	 *            the charset
	 */
	public HtmlContent(final HTMLDocument document, final HtmlPanel panel, RecordedInputStream ris, String charset) {
		super();
		this.document = document;
		this.panel = panel;
		this.ris = ris;
		this.charset = charset;
		this.sourceCode = null;
	}

	/**
	 * Instantiates a new html content.
	 *
	 * @param document
	 *            the document
	 * @param panel
	 *            the panel
	 * @param sourceCode
	 *            the source code
	 */
	public HtmlContent(final HTMLDocument document, final HtmlPanel panel, String sourceCode) {
		super();
		this.document = document;
		this.panel = panel;
		this.ris = null;
		this.charset = null;
		this.sourceCode = sourceCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#canCopy()
	 */
	@Override
	public boolean canCopy() {
		return this.panel.hasSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#copy()
	 */
	@Override
	public boolean copy() {
		return this.panel.copy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#getComponent()
	 */
	@Override
	public Component getComponent() {
		return this.panel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#getSourceCode()
	 */
	@Override
	public String getSourceCode() {
		try {
			RecordedInputStream ris = this.ris;
			if (ris != null) {
				byte[] bytesSoFar = ris.getBytesRead();
				try {
					return new String(bytesSoFar, this.charset);
				} catch (UnsupportedEncodingException uee) {
					return "[Error: " + uee + "]";
				}
			} else {
				return this.sourceCode;
			}
		} catch (BufferExceededException bee) {
			return "[Error: Document content too large.]";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#getTitle()
	 */
	@Override
	public String getTitle() {
		return this.document.getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#getDescription()
	 */
	@Override
	public String getDescription() {
		List<MetaInfo> infoList = getMetaList();
		for (int i = 0; i < infoList.size(); i++) {
			MetaInfo info = infoList.get(i);
			String name = info.getName();
			if ("description".equalsIgnoreCase(name)) {
				return info.getDescription();
			}
		}
		return null;
	}
	
	@Override
	public List<MetaInfo> getMetaList() {
		List<MetaInfo> infoList = new ArrayList<MetaInfo>();
		NodeList nodeList = this.document.getElementsByTagName("meta");
		
		if (nodeList == null) {
			return null;
		}
		
		for (Node node : Nodes.iterable(nodeList)) {
			if (node instanceof HTMLElement) {
				MetaInfo info = new MetaInfo();
				HTMLElement element = (HTMLElement) node;
				info.setName(element.getAttribute("name"));
				info.setItemprop(element.getAttribute("itemprop"));
				info.setProperty(element.getAttribute("property"));
				info.setHttpEqui(element.getAttribute("http-equi"));
				info.setContent(element.getAttribute("content"));
				info.setDescription(element.getAttribute("description"));
				info.setCharset(element.getAttribute("charset"));
				infoList.add(info);
			}
		}
		return infoList;
	}
	
	@Override
	public List<MetaInfo> getMediaList(){
		List<MetaInfo> infoList = new ArrayList<MetaInfo>();
		NodeList nodeList = this.document.getElementsByTagName("img");
		
		if (nodeList == null) {
			return null;
		}
		
		for (Node node : Nodes.iterable(nodeList)) {
			if (node instanceof HTMLElement) {
				MetaInfo info = new MetaInfo();
				HTMLElement element = (HTMLElement) node;
				String src = element.getAttribute("src");
				if (Strings.isNotBlank(src)) {
					if(!Urls.isAbsolute(src)) {
						HTMLDocumentImpl doc = (HTMLDocumentImpl)document;
						src = doc.getFullURL(src).toString();
					}
					if (src.startsWith("//")) {
						src = "http:" + src;
					}							
					
					info.setName(src);
					infoList.add(info);
				}
			}
		}

		nodeList = this.document.getElementsByTagName("link");
		for (Node node : Nodes.iterable(nodeList)) {
			if (node instanceof HTMLElement) {
				MetaInfo info = new MetaInfo();
				HTMLElement element = (HTMLElement) node;
				String rel = element.getAttribute("rel");
				String href = element.getAttribute("href");
				if ("icon".equalsIgnoreCase(rel) && Strings.isNotBlank(href)) {
					if(!Urls.isAbsolute(href)) {
						HTMLDocumentImpl doc = (HTMLDocumentImpl)document;
						href = doc.getFullURL(href).toString();
					}
					if (href.startsWith("//")) {
						href = "http:" + href;
					}							
					
					info.setName(href);
					infoList.add(info);
				}
			}
		}
		return infoList;
	}
		
	@Override
	public List<MetaInfo> getStyleList() {
		List<MetaInfo> infoList = new ArrayList<MetaInfo>();
		NodeList nodeList = this.document.getElementsByTagName("link");
		
		if (nodeList == null) {
			return null;
		}
		
		for (Node node : Nodes.iterable(nodeList)) {
			if (node instanceof HTMLElement) {
				MetaInfo info = new MetaInfo();
				HTMLElement element = (HTMLElement) node;
				String rel = element.getAttribute("rel");
				String href = element.getAttribute("href");
				if ("stylesheet".equalsIgnoreCase(rel) && Strings.isNotBlank(href)) {
					if(!Urls.isAbsolute(href)) {
						HTMLDocumentImpl doc = (HTMLDocumentImpl)document;
						href = doc.getFullURL(href).toString();
					}
					if (href.startsWith("//")) {
						href = "http:" + href;
					}							
					
					info.setName(href);
					infoList.add(info);
				}
			}
		}
		return infoList;
	}

	@Override
	public List<MetaInfo> getJSList() {
		List<MetaInfo> infoList = new ArrayList<MetaInfo>();
		NodeList nodeList = this.document.getElementsByTagName("script");
		
		if (nodeList == null) {
			return null;
		}
		
		for (Node node : Nodes.iterable(nodeList)) {
			if (node instanceof HTMLElement) {
				MetaInfo info = new MetaInfo();
				HTMLElement element = (HTMLElement) node;
				String src = element.getAttribute("src");
				if (Strings.isNotBlank(src)) {
					if(!Urls.isAbsolute(src)) {
						HTMLDocumentImpl doc = (HTMLDocumentImpl)document;
						src = doc.getFullURL(src).toString();
					}
					if (src.startsWith("//")) {
						src = "http:" + src;
					}							
					
					info.setName(src);
					infoList.add(info);
				}
			}
		}
		return infoList;
	}
	
	@Override
	public List<Cookie> getCookieList(String address) {
		URL url;
		List<Cookie> cookies = new ArrayList<Cookie>();
		try {
			logger.error("address: " + address);
			url = new URL(address);
			logger.error("url.getHost(): " + url.getHost());
			String domain = url.getHost().replaceFirst("^www.*?\\.", "");
			logger.error("domain: " + domain);
			cookies = CookieStore.getCookies(domain, "/");
		} catch (Exception e) {
			logger.error(e);
		}
		logger.error("cookies: " + cookies.size());
		return cookies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#getContentObject()
	 */
	@Override
	public Object getContentObject() {
		return this.document;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.clientlet.ComponentContent#getMimeType()
	 */
	@Override
	public String getMimeType() {
		return "text/html";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.clientlet.ComponentContent#setProperty(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public void setProperty(String name, Object value) {
		if ("defaultMarginInsets".equals(name) && value instanceof Insets) {
			this.panel.setDefaultMarginInsets((Insets) value);
		} else if ("defaultOverflowX".equals(name) && value instanceof Integer) {
			this.panel.setDefaultOverflowX((Integer) value);
		} else if ("defaultOverflowY".equals(name) && value instanceof Integer) {
			this.panel.setDefaultOverflowY((Integer) value);
		}
	}
}
