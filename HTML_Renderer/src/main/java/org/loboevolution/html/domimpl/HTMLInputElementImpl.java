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
package org.loboevolution.html.domimpl;

import java.io.File;


import org.loboevolution.html.FormInput;
import org.loboevolution.html.dombl.InputContext;
import org.loboevolution.util.Strings;
import org.loboevolution.w3c.html.HTMLInputElement;

/**
 * The Class HTMLInputElementImpl.
 */
public class HTMLInputElementImpl extends HTMLBaseInputElement implements HTMLInputElement {

	/** The image. */
	private static final String IMAGE = "image";

	/** The submit. */
	private static final String SUBMIT = "submit";

	/** The text. */
	private static final String TEXT = "tetx";

	/** The password. */
	private static final String PASSWORD = "password";

	/** The hidden. */
	private static final String HIDDEN = "hidden";

	/** The radio. */
	private static final String RADIO = "radio";

	/** The checkbox. */
	private static final String CHECKBOX = "checkbox";

	/** The reset. */
	private static final String RESET = "reset";

	/** The file. */
	private static final String FILE = "file";
	
	/** The default checked. */
	private static boolean defaultChecked;

	/**
	 * Instantiates a new HTML input element impl.
	 *
	 * @param name
	 *            the name
	 */
	public HTMLInputElementImpl(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getDefaultChecked()
	 */
	@Override
	public boolean getDefaultChecked() {
		return this.defaultChecked;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setDefaultChecked(boolean)
	 */
	@Override
	public void setDefaultChecked(boolean defaultChecked) {
		this.defaultChecked = defaultChecked;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.html.domimpl.HTMLBaseInputElement#getChecked()
	 */
	@Override
	public boolean getChecked() {
		InputContext ic = this.inputContext;
		if (ic == null) {
			return this.getAttributeAsBoolean(CHECKED);
		} else {
			return ic.getChecked();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.html.domimpl.HTMLBaseInputElement#setChecked(boolean)
	 */
	@Override
	public void setChecked(boolean checked) {
		InputContext ic = this.inputContext;
		if (ic != null) {
			ic.setChecked(checked);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getMaxLength()
	 */
	@Override
	public int getMaxLength() {
		InputContext ic = this.inputContext;
		return ic == null ? 0 : ic.getMaxLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setMaxLength(int)
	 */
	@Override
	public void setMaxLength(int maxLength) {
		InputContext ic = this.inputContext;
		if (ic != null) {
			ic.setMaxLength(maxLength);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getSize()
	 */
	@Override
	public int getSize() {
		InputContext ic = this.inputContext;
		return ic == null ? 0 : ic.getControlSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setSize(int)
	 */
	@Override
	public void setSize(int size) {
		InputContext ic = this.inputContext;
		if (ic != null) {
			ic.setControlSize(size);
		}
	}

	/**
	 * Gets input type in lowercase.
	 *
	 * @return the type
	 */
	@Override
	public String getType() {
		String type = this.getAttribute(TYPE);
		return type == null ? null : type.toLowerCase();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.setAttribute(TYPE, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getUseMap()
	 */
	@Override
	public String getUseMap() {
		return this.getAttribute(USEMAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.w3c.html.HTMLInputElement#setUseMap(java.lang.String)
	 */
	@Override
	public void setUseMap(String useMap) {
		this.setAttribute(USEMAP, useMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.html.domimpl.HTMLElementImpl#click()
	 */
	@Override
	public void click() {
		InputContext ic = this.inputContext;
		if (ic != null) {
			ic.click();
		}
	}

	/**
	 * Checks if is submittable with enter key.
	 *
	 * @return true, if is submittable with enter key
	 */
	public boolean isSubmittableWithEnterKey() {
		String type = this.getType();
		return type == null || "".equals(type) || TEXT.equals(type) || PASSWORD.equals(type);
	}

	/**
	 * Checks if is submittable with press.
	 *
	 * @return true, if is submittable with press
	 */
	public boolean isSubmittableWithPress() {
		String type = this.getType();
		return SUBMIT.equals(type) || IMAGE.equals(type);
	}

	/**
	 * Checks if is submit input.
	 *
	 * @return true, if is submit input
	 */
	public boolean isSubmitInput() {
		String type = this.getType();
		return SUBMIT.equals(type);
	}

	/**
	 * Checks if is image input.
	 *
	 * @return true, if is image input
	 */
	public boolean isImageInput() {
		String type = this.getType();
		return IMAGE.equals(type);
	}

	/**
	 * Checks if is reset input.
	 *
	 * @return true, if is reset input
	 */
	public boolean isResetInput() {
		String type = this.getType();
		return RESET.equals(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.html.domimpl.HTMLBaseInputElement#resetInput()
	 */
	@Override
	public void resetInput() {
		InputContext ic = this.inputContext;
		if (ic != null) {
			ic.resetInput();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.html.domimpl.HTMLElementImpl#getFormInputs()
	 */
	@Override
	protected FormInput[] getFormInputs() {
		String type = this.getType();
		String name = this.getName();
		if (name == null) {
			return null;
		}
		if (type == null) {
			return new FormInput[] { new FormInput(name, this.getValue()) };
		} else {
			if (TEXT.equals(type) || PASSWORD.equals(type) || HIDDEN.equals(type) || "".equals(type)) {
				return new FormInput[] { new FormInput(name, this.getValue()) };
			} else if (SUBMIT.equals(type)) {
				// It's done as an "extra" form input
				return null;
			} else if (RADIO.equals(type) || CHECKBOX.equals(type)) {
				if (this.getChecked()) {
					String value = this.getValue();
					if (Strings.isBlank(value)) {
						value = "on";
					}
					return new FormInput[] { new FormInput(name, value) };
				} else {
					return null;
				}
			} else if (IMAGE.equals(type)) {
				// It's done as an "extra" form input
				return null;
			} else if (FILE.equals(type)) {
				File[] files = this.getFileValue();
				if (files == null) {
					return null;
				} else {
					return new FormInput[] { new FormInput(name, files) };
				}
			} else {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getAutocomplete()
	 */
	@Override
	public boolean getAutocomplete() {
		String autocomplete = this.getAttribute(AUTOCOMPLETE);
		return MUTED.equalsIgnoreCase(autocomplete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setAutocomplete(boolean)
	 */
	@Override
	public void setAutocomplete(boolean autocomplete) {
		this.setAttribute(AUTOCOMPLETE, autocomplete ? AUTOCOMPLETE : null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getAutofocus()
	 */
	@Override
	public boolean getAutofocus() {
		String focus = this.getAttribute(AUTOFOCUS);
		return AUTOFOCUS.equalsIgnoreCase(focus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setAutofocus(boolean)
	 */
	@Override
	public void setAutofocus(boolean autofocus) {
		this.setAttribute(AUTOFOCUS, autofocus ? AUTOFOCUS : null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getFiles()
	 */
	@Override
	public File[] getFiles() {
		InputContext ic = this.inputContext;
		return ic.getFileValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getFormAction()
	 */
	@Override
	public String getFormAction() {
		return this.getAttribute(FORMACTION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.w3c.html.HTMLInputElement#setFormAction(java.lang.String)
	 */
	@Override
	public void setFormAction(String formAction) {
		this.setAttribute(FORMACTION, formAction);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getFormEnctype()
	 */
	@Override
	public String getFormEnctype() {
		return this.getAttribute(FORMENCTYPE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setFormEnctype(java.lang.
	 * String)
	 */
	@Override
	public void setFormEnctype(String formEnctype) {
		this.setAttribute(FORMENCTYPE, formEnctype);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getFormMethod()
	 */
	@Override
	public String getFormMethod() {
		return this.getAttribute(FORMMETHOD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.w3c.html.HTMLInputElement#setFormMethod(java.lang.String)
	 */
	@Override
	public void setFormMethod(String formMethod) {
		this.setAttribute(FORMMETHOD, formMethod);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getFormNoValidate()
	 */
	@Override
	public boolean getFormNoValidate() {
		String formNoValidate = this.getAttribute(FORMNOVALIDATE);
		return FORMNOVALIDATE.equalsIgnoreCase(formNoValidate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setFormNoValidate(boolean)
	 */
	@Override
	public void setFormNoValidate(boolean formNoValidate) {
		this.setAttribute(FORMNOVALIDATE,
				formNoValidate ? FORMNOVALIDATE : null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getFormTarget()
	 */
	@Override
	public String getFormTarget() {
		return this.getAttribute(FORMTARGET);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.w3c.html.HTMLInputElement#setFormTarget(java.lang.String)
	 */
	@Override
	public void setFormTarget(String formTarget) {
		this.setAttribute(FORMTARGET, formTarget);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getHeight()
	 */
	@Override
	public String getHeight() {
		return this.getAttribute(HEIGHT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.w3c.html.HTMLInputElement#setHeight(java.lang.String)
	 */
	@Override
	public void setHeight(String height) {
		this.setAttribute(HEIGHT, height);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getMax()
	 */
	@Override
	public String getMax() {
		return this.getAttribute(MAX);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setMax(java.lang.String)
	 */
	@Override
	public void setMax(String max) {
		this.setAttribute(MAX, max);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getMin()
	 */
	@Override
	public String getMin() {
		return this.getAttribute(MIN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setMin(java.lang.String)
	 */
	@Override
	public void setMin(String min) {
		this.setAttribute(MIN, min);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getMultiple()
	 */
	@Override
	public boolean getMultiple() {
		String multiple = this.getAttribute(MULTIPLE);
		return MULTIPLE.equalsIgnoreCase(multiple);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setMultiple(boolean)
	 */
	@Override
	public void setMultiple(boolean multiple) {
		this.setAttribute(MULTIPLE, multiple ? MULTIPLE : null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getPattern()
	 */
	@Override
	public String getPattern() {
		return this.getAttribute(PATTERN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.loboevolution.w3c.html.HTMLInputElement#setPattern(java.lang.String)
	 */
	@Override
	public void setPattern(String pattern) {
		this.setAttribute(PATTERN, pattern);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getRequired()
	 */
	@Override
	public boolean getRequired() {
		String required = this.getAttribute(REQUIRED);
		return REQUIRED.equalsIgnoreCase(required);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setRequired(boolean)
	 */
	@Override
	public void setRequired(boolean required) {
		this.setAttribute(REQUIRED, required ? REQUIRED : null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getStep()
	 */
	@Override
	public String getStep() {
		return this.getAttribute(STEP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setStep(java.lang.String)
	 */
	@Override
	public void setStep(String step) {
		this.setAttribute(STEP, step);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#getWidth()
	 */
	@Override
	public String getWidth() {
		if (getType().equalsIgnoreCase(IMAGE)) {
			return this.getAttribute(WIDTH);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.loboevolution.w3c.html.HTMLInputElement#setWidth(java.lang.String)
	 */
	@Override
	public void setWidth(String width) {
		if (getType().equalsIgnoreCase(IMAGE)) {
			this.setAttribute(WIDTH, width);
		}
	}

	@Override
	public boolean getWillValidate() {
		return true;
	}
}
