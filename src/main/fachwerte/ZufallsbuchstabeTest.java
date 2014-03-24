package main.fachwerte;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ZufallsbuchstabeTest
{
	@Test
	public void ZufallsbuchstabeIstImmerKorrekterBuchstabeTest()
	{
		ArrayList<Integer> liste = new ArrayList<Integer>();
		
		for(int i = 0; i < 1000000; i++)
		{
			Integer buchstabenindex = new Integer((int)Zufallsbuchstabe.gibGrossBuchstaben());
			liste.add(i, buchstabenindex);
		}
		
		int indexA = (int)'A';
		int indexZ = (int)'Z';
		
		for(Integer i : liste)
		{
			assertTrue( indexA <= i.intValue() && indexZ >= i.intValue() );
		}
	}
}
