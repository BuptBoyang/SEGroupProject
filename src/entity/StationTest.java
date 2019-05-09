package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StationTest {

	@Test
	void findFreeSlotTest() {
		Station stationTest = new Station();
		int empty = stationTest.findFreeSlot(false);
		int notEmpty = stationTest.findFreeSlot(true);
		assertEquals(4,empty);
		assertEquals(0,notEmpty);
	}
	
	@Test
	void checkToBorrowTest() {
		Station stationTest = new Station();
		
		int slotNo = stationTest.findFreeSlot(true);
		stationTest.releaseSlot(slotNo);
		stationTest.pressSimulator(slotNo);
		stationTest.timeout(slotNo);
		
		boolean borrowResult = stationTest.checkToBorrow(slotNo);
		assertEquals(true,borrowResult);
	}
	
	@Test
	void checkToReturnTest() {
		Station stationTest = new Station();
		
		int slotNo = stationTest.findFreeSlot(false);
		stationTest.releaseSlot(slotNo);
		stationTest.pressSimulator(slotNo);
		stationTest.timeout(slotNo);
		
		boolean returnResult = stationTest.checkToReturn(slotNo);
		assertEquals(true,returnResult);
	}

}
