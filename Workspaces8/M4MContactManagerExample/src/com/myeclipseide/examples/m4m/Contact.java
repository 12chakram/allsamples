package com.myeclipseide.examples.m4m;

public class Contact {
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String notes;

	public Contact() {
		this(null);
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	public Contact(String propertyValue) {
		populateInstance(propertyValue);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFormattedValue() {
		StringBuilder builder = new StringBuilder();
		builder.append(getFirstName()).append(',').append(getLastName())
				.append(',').append(getEmail()).append(',').append(getPhone())
				.append(',').append(getNotes());

		return builder.toString();
	}

	private void populateInstance(String propertyValue) {
		if (propertyValue == null || propertyValue.length() == 0)
			return;

		String[] values = propertyValue.split(",");

		/*
		 * No validation here for simplicity sake, we just assign the values
		 */

		setFirstName(values[0]);
		setLastName(values[1]);
		setEmail(values[2]);
		setPhone(values[3]);
		setNotes(values[4]);
	}
}