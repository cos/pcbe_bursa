package bursa;

import java.util.HashMap;

import dispatch.Dispatcher;
import dispatch.EventDispatcher;
import dispatch.Listener;

public class Bursa {
	HashMap<String, Oferta> oferte = new HashMap<String, Oferta>();
	Dispatcher dispatcher = new EventDispatcher();
	
	public void creeazaOferta(String numeCompanie, int nrActiuni, int pret) {
		if (!oferte.containsKey(numeCompanie)) {
			Oferta oferta = new Oferta(numeCompanie, nrActiuni, pret);
			oferte.put(numeCompanie, oferta);
			dispatcher.dispatch(new BursaEvent(BursaEventType.AparitieOferta.toString(), oferta));
		}		
	}
	
	public void modificaPret(String numeCompanie, int pretNou) {
		if (oferte.containsKey(numeCompanie)) {
			oferte.get(numeCompanie).setPret(pretNou);
			dispatcher.dispatch(new BursaEvent(BursaEventType.ModificareOferta.toString(), oferte.get(numeCompanie)));			
		}
	}
	
	// nrActiuni este un increment cu care sa se modifice
	// nr de actiuni; poate fi si negativ
	public void modificaActiuni(String numeCompanie, int nrActiuni) {
		if (oferte.containsKey(numeCompanie)) {
			Oferta oferta = oferte.get(numeCompanie);
			oferta.setNrActiuni(oferta.getNrActiuni() + nrActiuni);
			dispatcher.dispatch(new BursaEvent(BursaEventType.ModificareOferta.toString(), oferta));
		}
	}
	
	public void inregistrareLaEveniment(BursaEventType tipEveniment, Listener listener) {
		dispatcher.registerListener(tipEveniment.toString(), listener);
	}
}
