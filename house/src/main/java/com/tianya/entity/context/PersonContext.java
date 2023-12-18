package com.tianya.entity.context;

import com.tianya.entity.request.PersonRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author changwenbo
 * @date 2023/3/10 15:41
 */
public class PersonContext {
	private PersonRequest person;
	private HttpServletRequest request;
	//


	public PersonContext() {
	}

	public PersonContext(PersonRequest person, HttpServletRequest request) {
		this.person = person;
		this.request = request;
	}

	public PersonRequest getPerson() {
		return person;
	}

	public void setPerson(PersonRequest person) {
		this.person = person;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
