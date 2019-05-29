package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StationTest {

	@Test
	void test() {
		Station station = new Station();
		assertEquals(station.getSlot(0).isHasScooter(),true);
	}

}
