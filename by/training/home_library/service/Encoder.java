package by.training.home_library.service;

public class Encoder {
	public String encrypt(String unencryptedPassword) {
		
		String encryptedPassword;
		char[] set;
		
		set = unencryptedPassword.toCharArray();
		for (int i = 0; i < set.length; i++) {
			set[i] = (char) (set[i] - 10);
		}
		encryptedPassword = new String(set);
		
		return encryptedPassword;
	}

	public String decrypt(String encryptedPassword) {
		
		String decryptedPassword;
		char[] set;
		
		set = encryptedPassword.toCharArray();
		for (int i = 0; i < set.length; i++) {
			set[i] = (char) (set[i] + 10);
		}
		decryptedPassword = new String(set);
		
		return decryptedPassword;
	}
}
