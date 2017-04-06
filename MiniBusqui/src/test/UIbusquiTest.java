package test;

import static org.junit.Assert.*;

import org.junit.Test;

import control.ParaUIBusqui;
import vista.UIbusqui;

public class UIbusquiTest {

	@Test
	public void testGetBrightIncrement() {
		UIbusqui ui = new ParaUIBusqui();
		assertEquals(3, ui.getBrightIncrement(((20+1)/2), 20), 1f);
	}

}
