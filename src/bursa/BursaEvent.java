package bursa;

import dispatch.Event;

public class BursaEvent implements Event {
	private OfertaVanzare oferta;
	
	public BursaEvent(String type, OfertaVanzare oferta) {
		this.oferta = oferta;
	}

	public OfertaVanzare getOferta() {
		return oferta;
	}
}
