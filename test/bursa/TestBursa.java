package bursa;

import bursa.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dispatch.Event;
import dispatch.EventFilter;
import dispatch.Listener;

public class TestBursa {
	Bursa bursa;	
	Cumparator cumparator = new Cumparator();
    Vanzator vanzator = new Vanzator();
	
	private class Cumparator implements Listener {
		public OfertaVanzare oferta;
		
        @Override
		public void consumeEvent(Event event) {
            if(event instanceof OfertaVanzareEvent)
            {
                OfertaVanzareEvent bevent = (OfertaVanzareEvent)event;
                this.oferta = bevent.getOferta();
            }
            if(event instanceof TranzactieFinalizataEvent)
            {
                TranzactieFinalizataEvent tevent = (TranzactieFinalizataEvent) event;
                this.oferta = tevent.getOfertaVanzare();
            }
		}
	}

    private class Vanzator implements Listener {
	public OfertaCumparare oferta;

        @Override
		public void consumeEvent(Event event) {
            if(event instanceof OfertaCumparareEvent)
            {
                OfertaCumparareEvent bevent = (OfertaCumparareEvent)event;
                this.oferta = bevent.getOferta();
            }
            if(event instanceof TranzactieFinalizataEvent)
            {
                TranzactieFinalizataEvent tevent = (TranzactieFinalizataEvent) event;
                this.oferta = tevent.getOfertaCumparare();
            }
		}
	}

    private class VanzariFilter implements EventFilter {
        @Override
        public boolean accept(Event e) {
            return (e instanceof OfertaVanzareEvent) || (e instanceof TranzactieFinalizataEvent);
        }
    }

    private class CumparariFilter implements EventFilter {
        @Override
        public boolean accept(Event e) {
            return (e instanceof OfertaCumparareEvent) || (e instanceof TranzactieFinalizataEvent);
        }
    }

	@Before
	public void setUp() throws Exception {
		bursa = new Bursa();
		bursa.inregistrareLaEveniment(cumparator, new VanzariFilter());
		bursa.inregistrareLaEveniment(vanzator, new CumparariFilter());
	}

	@Test
	public void testCreeazaOferta() {
		OfertaVanzare oferta = bursa.creeazaOfertaVanzare("SUN", 100, 14);
		assertTrue(bursa.oferte.containsKey("SUN"));
		assertEquals("SUN", oferta.getNumeCompanie());
		assertEquals(100, oferta.getNrActiuni());
		assertEquals(14, oferta.getPret());
		assertEquals(oferta, cumparator.oferta);
	}

	@Test
	public void testModificaPret() {
		bursa.creeazaOfertaVanzare("SUN", 100, 14);
		bursa.modificaPret("SUN", 15);
		assertEquals(15, bursa.oferte.get("SUN").getPret());
		assertEquals(bursa.oferte.get("SUN"), cumparator.oferta);
	}

    @Test
	public void testLanseazaOfertaCumparareNereusita() {
		OfertaVanzare o = bursa.creeazaOfertaVanzare("SUN", 100, 14);
		bursa.lanseazaOfertaCumparare("Gigi investitoru", "SUN", o, 12);
		assertNotNull(bursa.oferte.get("SUN"));
		assertEquals(bursa.oferte.get("SUN"), vanzator.oferta.getOfertaVanzare());
	}

    @Test
	public void testLanseazaOfertaCumparareReusita() {
		OfertaVanzare o = bursa.creeazaOfertaVanzare("SUN", 100, 13);
		bursa.lanseazaOfertaCumparare("Gigi investitoru", "SUN", o, 13);
		assertNull(bursa.oferte.get("SUN"));
		assertEquals(o, cumparator.oferta);
        assertEquals(o, vanzator.oferta.getOfertaVanzare());
	}

    @Test
    public void testCitesteOferta() {
        bursa.creeazaOfertaVanzare("SUN", 100, 14);
    }
}
