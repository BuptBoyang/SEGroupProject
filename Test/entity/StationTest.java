package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StationTest {

	@Test
	void testCreateStation() {
		Station station = new Station();
		assertEquals(station.getSlot(0).isHasScooter(),true);
		assertEquals(station.getSlot(0).isLocked(),true);
		assertEquals(station.getSlot(1).isHasScooter(),true);
		assertEquals(station.getSlot(2).isHasScooter(),true);
		assertEquals(station.getSlot(3).isHasScooter(),true);
		assertEquals(station.getSlot(4).isHasScooter(),false);
		assertEquals(station.getSlot(5).isHasScooter(),false);
		assertEquals(station.getSlot(6).isHasScooter(),false);
		assertEquals(station.getSlot(7).isHasScooter(),false);
	}
	
	@Test
	void testSlot() {
		Station station = new Station();
		station.openSlot(0);
		assertEquals(station.getSlot(0).isLocked(),false);
		station.closeSlot(0);
		assertEquals(station.getSlot(0).isLocked(),true);
	}
	
	@Test
	void testReset() {
		Station station = new Station();
		station.reset(0, true);
		assertEquals(station.getSlot(0).isHasScooter(),true);
		station.reset(0, false);
		assertEquals(station.getSlot(0).isHasScooter(),false);
	}

}
