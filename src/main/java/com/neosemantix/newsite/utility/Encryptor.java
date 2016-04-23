// Copyright 2014; All rights reserved with NeoSemantix, Inc.

package com.neosemantix.newsite.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.neosemantix.newsite.Registry;

/**
 * @author upatil
 *
 */
public class Encryptor {

    public static String encrypt(String value) {
	String result = null;
	try {
	    result = encrypt(Registry.getInstance().getEncryptionKeyOne(),
		    Registry.getInstance().getEncryptionKeyTwo(), value);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return result;
    }

    public static String encrypt(String key, String initVector, String value) {
	try {
	    IvParameterSpec iv = new IvParameterSpec(
		    initVector.getBytes("UTF-8"));
	    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"),
		    "AES");

	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

	    byte[] encrypted = cipher.doFinal(value.getBytes());
	    // System.out.println("encrypted string: " +
	    // Base64.encodeBase64String(encrypted));

	    return Base64.encodeBase64String(encrypted);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return null;
    }

    public static String decrypt(String encrypted) {
	String result = null;
	try {
	    result = decrypt(Registry.getInstance().getEncryptionKeyOne(),
		    Registry.getInstance().getEncryptionKeyTwo(), encrypted);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return result;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
	try {
	    IvParameterSpec iv = new IvParameterSpec(
		    initVector.getBytes("UTF-8"));
	    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"),
		    "AES");

	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	    cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

	    byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

	    return new String(original);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	return null;
    }

    static String convertUnicodeToString(String input) {
	/**
	 * The following method converts a string containing unicode content
	 * into a pure string with the respective characters represented by the
	 * unicode. e.g \u003etr will become <tr
	 **/

	StringBuilder sb = new StringBuilder(input);
	/*
	 * startIndex is the point where the backslash is found, endIndex is the
	 * point where the unicode section ends; e.g \u003e -
	 * endIndex=startIndex+6
	 */
	int startIndex = 0, endIndex = 0, val = 0;

	for (int i = 0; i < sb.length() - 1; i++) {

	    if (sb.charAt(i) == '\\') { /* check if the char is a backslash */
		startIndex = i; /*
				 * save the index as a starting point for
				 * replace later
				 */
		endIndex = startIndex + 6;
		if (sb.charAt(++i) == 'u') {/*
					     * check if the next char is a 'u'
					     * which indicates a unicode section
					     * is found
					     */

		    /*
		     * extract the unicode section withouth \ u and convert to
		     * an integer which is then used to convert to its
		     * respective character
		     */
		    val = Integer.parseInt(sb.substring(++i, i + 4), 16);

		    sb.replace(startIndex, endIndex,
			    String.valueOf((char) (val)));
		    i = startIndex;
		}
	    }

	}
	return sb.toString();
    }
    

    public static void main(String[] args) {

	try {
	    
	    File file = new File("C:\\nsx\\newsite\\NewsSite\\src\\main\\java\\test_js.txt");
	    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	    // we know the file has only one line
	    String entireContent = reader.readLine();
	    reader.close();
	    String convertedString = convertUnicodeToString(entireContent);
	    
	    File htmlFile = new File("C:\\nsx\\newsite\\NewsSite\\src\\main\\java\\test.html");
	    FileWriter fw = new FileWriter(htmlFile);
	    fw.write(convertedString);
	    fw.flush();
	    fw.close();
	    
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
    
    private static void test1(){
	try {

	    // String key = "Bar12345Bar12345"; // 128 bit key
	    // String initVector = "RandomInitVector"; // 16 bytes IV

	    String key = Registry.getInstance().getEncryptionKeyOne();
	    String initVector = Registry.getInstance().getEncryptionKeyTwo();

	    String es = encrypt(key, initVector, "Hello World");

	    String ds = decrypt(key, initVector, "2cNvqVXUFwZR29BYtQK1dw==");

	    System.out.println("es: " + es);

	    System.out.println("ds: " + ds);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}