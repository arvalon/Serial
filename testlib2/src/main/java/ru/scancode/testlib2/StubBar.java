package ru.scancode.testlib2;

import androidx.annotation.NonNull;

/**
 * Тестовый класс-заглушка
 * @author Vaganov
 */
public class StubBar {

	private int num;
	private String name;

	public StubBar(int num, String name) {
		this.num = num;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@NonNull
	@Override
	public String toString() {
		return "My name is "+name+", number: "+num+" END4";
	}
}
