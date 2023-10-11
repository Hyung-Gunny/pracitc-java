package 정보보안과제1;

import java.util.Scanner;

public class main
{
	public static void main(String[] arg)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Sentece to encrypt:");
		
		String plaintext=sc.nextLine();
		
		
		System.out.printf("Enter k:");
		
		int k=sc.nextInt();


		String ciphertext=encrypt(plaintext, k);
		System.out.println("Encrypted sentece: "+ciphertext);
	
		
		sc.close();
	}
	
	public static String encrypt(String plaintext, int k)
	{
		StringBuilder ciphertext=new StringBuilder();
		
		for(int i=0; i<plaintext.length(); i++)
		{
			char c=plaintext.charAt(i);
			if(Character.isLetter(c))
			{
				char base=Character.isLowerCase(c)?'a':'A';
				char encryptedChar=(char)(((c-base+k)%26)+base);
				ciphertext.append(encryptedChar);
			}
			else
			{
				ciphertext.append(c);
			}
		}
		return ciphertext.toString();
		
	}

}
