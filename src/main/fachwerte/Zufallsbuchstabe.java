package main.fachwerte;

public class Zufallsbuchstabe
{
	public static char gibGrossBuchstaben()
	{
		int index = (int)'A';
		
		int zufallszahl = ((int)(Math.random() * 10)) % 25;
		
		return (char)(index + zufallszahl);
	}
}