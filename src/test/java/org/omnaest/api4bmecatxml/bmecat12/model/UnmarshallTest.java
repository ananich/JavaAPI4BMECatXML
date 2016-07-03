package org.omnaest.api4bmecatxml.bmecat12.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omnaest.api4bmecatxml.bmecat12.manager.BMECat12Manager;

public class UnmarshallTest {

	@Test
	public void smokeTest() {
		BMECat12 xml = BMECat12Manager.loadFrom(getClass().getResourceAsStream("bmecat.xml"));
		assertEquals("Supplier 1", xml.getHeader().getSupplier().getSupplierName());
	}

}
