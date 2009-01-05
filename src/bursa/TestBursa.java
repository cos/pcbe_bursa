package bursa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dispatch.Event;
import dispatch.Listener;

public class TestBursa {
	Bursa bursa;	
	ReportListener listener = new ReportListener();
	
	private class ReportListener implements Listener {
		public String report;
		
		public void consumeEvent(Event event) {
			BursaEvent bevent = (BursaEvent)event;
			report = event.getType() + ": ";
			report += bevent.getOferta().getNumeCompanie() + " ";
			report += bevent.getOferta().getNrActiuni() + " ";
			report += bevent.getOferta().getPret();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		bursa = new Bursa();
		bursa.inregistrareLaEveniment(BursaEventType.AparitieOferta, listener);
		bursa.inregistrareLaEveniment(BursaEventType.ModificareOferta, listener);
	}

	@Test
	public void testCreeazaOferta() {
		bursa.creeazaOferta("SUN", 100, 14);
		assertTrue(bursa.oferte.containsKey("SUN"));
		Oferta oferta = bursa.oferte.get("SUN");
		assertEquals("SUN", oferta.getNumeCompanie());
		assertEquals(100, oferta.getNrActiuni());
		assertEquals(14, oferta.getPret());
		assertEquals("AparitieOferta: SUN 100 14", listener.report);
	}

	@Test
	public void testModificaPret() {
		bursa.creeazaOferta("SUN", 100, 14);
		bursa.modificaPret("SUN", 15);
		assertEquals(15, bursa.oferte.get("SUN").getPret());
		assertEquals("ModificareOferta: SUN 100 15", listener.report);
	}

	@Test
	public void testModificaActiuni() {
		bursa.creeazaOferta("SUN", 100, 14);
		bursa.modificaActiuni("SUN", 25);
		assertEquals(125, bursa.oferte.get("SUN").getNrActiuni());
		assertEquals("ModificareOferta: SUN 125 14", listener.report);
		bursa.modificaActiuni("SUN", -20);
		assertEquals(105, bursa.oferte.get("SUN").getNrActiuni());
		assertEquals("ModificareOferta: SUN 105 14", listener.report);
	}	
}
