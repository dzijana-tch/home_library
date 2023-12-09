package by.training.home_library.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {

	private static final ConsoleInput instance = new ConsoleInput();
	
	private ConsoleInput() {}
	
	public static ConsoleInput getInstance() {
		return instance;
	}
	
	public int getInt(String text) {
		int value = 0;
		String valStr;
		BufferedReader br;
		
		System.out.print(text);
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			valStr = br.readLine();
			value = Integer.parseInt(valStr);
			
		} catch (IOException iOE) {
			System.out.println("Упс! Что-то пошло не так.");
			
		} catch (NumberFormatException nFE) {
			System.out.println("Неверное значение, повторите ввод.");
			value = getInt(text);
		}
		
		return value;
	}
	
	public String getString(String text) {
		String string = "";
		BufferedReader br;
		
		System.out.print(">>");
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			string = br.readLine();
		} catch (IOException iOE) {
			System.out.println("Упс! Что-то пошло не так.");
		}
		
		return string;		
	}

}
