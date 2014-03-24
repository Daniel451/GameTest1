package main.fachwerte;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class EnumVertexColorsTest
{
	@Test
	public void ZufallsbuchstabeIstImmerKorrekterBuchstabeTest()
	{
		assertEquals(EnumVertexColors.ColorStandard.getColor(), Color.CYAN);
	}
}
