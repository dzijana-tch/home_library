package by.training.home_library.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import by.training.home_library.bean.Account;
import by.training.home_library.bean.Role;
import by.training.home_library.dao.AccountDAO;
import by.training.home_library.dao.DAOException;

public class FileAccountDAO implements AccountDAO {
	
	//Example:
	//role=USER
	//name=Stas Novik
	//login=novik.stas@mail.ru
	//password=example
	//------------------------------
	

	public static final String SOURCE = "e:\\Diana\\eclipse-workspace\\home-library\\src\\by\\training\\home_library\\resources\\ListOfAccounts.txt";

	@Override
	public boolean registration(String name, String login, String password) throws DAOException {
		
		if (search(login)) {
			return false;
		}
		
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SOURCE, true))) {
			
			if (new File(SOURCE).length() == 0) {
				bufferedWriter.write("role=ADMIN\n");
			} else {
				bufferedWriter.write("role=USER\n");
			}
			
			bufferedWriter.write("name=" + name + "\n");
			bufferedWriter.write("login=" + login + "\n");
			bufferedWriter.write("password=" + password + "\n");
			bufferedWriter.write("------------------------------\n");

		} catch (IOException ex) {
			throw new DAOException(ex);
		}
		
		return true;
	}
	
	private boolean search(String login) throws DAOException {
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SOURCE))) {
			
			String line;
			String substring;
			
			while ((line = bufferedReader.readLine()) != null) {
				
				substring = line.substring(0, 6);
				
				if (substring.equals("login=")) {
					
					if (line.split("=")[1].equals(login)) {
						return true;
					}
				}
			}
		} catch (IOException ex) {
			throw new DAOException(ex);
		}
		return false;
	}

	@Override
	public Account authorization(String login, String password) throws DAOException {
		
		Set<Account> allAccounts = getAll();
		
		for (Account a : allAccounts) {
			
			if (a.getLogin().equals(login) && a.getPassword().equals(password)) {
				return a;
			}
		}
		
		return new Account(Role.NONE, "", "", "");
	}
	
	private Set<Account> getAll() throws DAOException {

		Set<Account> allAccounts = new HashSet<Account>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(SOURCE))) {
			
			String line;
			String[] lines = new String[4];
			Account account;
			
			while ((line = bufferedReader.readLine()) != null) {
				lines[0] = line;
				for (int i = 1; i < 4; i++) {
					lines[i] = bufferedReader.readLine();
				}
				bufferedReader.readLine();
				
				account = makeAccount(lines);
				allAccounts.add(account);
			}
			
		} catch (IOException ex) {
			throw new DAOException(ex);
		}
		
		return allAccounts;
	}

	private Account makeAccount(String[] lines) {
		Account account;

		String role;
		String name;
		String login;
		String password;
		
		role = lines[0].split("=")[1];
		
		name = lines[1].split("=")[1];
		
		login = lines[2].split("=")[1];
		
		password = lines[3].split("=")[1];

		
		account = new Account(Role.valueOf(role), name, login, password);
		
		return account;
	}

}
