package com.roryharford.decorator;

public abstract class UserDecorator implements TypeOfUser {
	protected TypeOfUser decoratedUser;

	public UserDecorator(TypeOfUser decoratedUser) {
		this.decoratedUser = decoratedUser;
	}

	public String login() {
		return decoratedUser.login();
	}
}
