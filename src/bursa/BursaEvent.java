package bursa;

import dispatch.Event;

public class BursaEvent extends Event {
	private Oferta oferta;
	
	public BursaEvent(String type, Oferta oferta) {
		super(type);
		this.oferta = oferta;
	}

	public Oferta getOferta() {
		return oferta;
	}
}
