package bursa;

import java.util.HashMap;

import dispatch.*;

public class Bursa {
	HashMap<String, OfertaVanzare> oferte = new HashMap<String, OfertaVanzare>();
	Dispatcher dispatcher = new EventDispatcher();
	
	public OfertaVanzare creeazaOfertaVanzare(String numeCompanie, int nrActiuni, int pret) {
        OfertaVanzare oferta = new OfertaVanzare(numeCompanie, nrActiuni, pret);
        oferte.put(numeCompanie,oferta);
        dispatcher.dispatch(new OfertaVanzareEvent(oferta));
        return oferta;
	}
	
	public void modificaPret(String numeCompanie, int pretNou) {
        oferte.get(numeCompanie).setPret(pretNou);
        dispatcher.dispatch(new OfertaVanzareEvent(oferte.get(numeCompanie)));		
	}

	public void lanseazaOfertaCumparare(String numeCumparator, String numeVanzator, OfertaVanzare o,int pret) {
        OfertaCumparare oc = new OfertaCumparare(numeCumparator, o, pret);
        if(o.getPret() <= pret)
        {
            this.oferte.remove(numeVanzator);
            this.dispatcher.dispatch(new TranzactieFinalizataEvent(o, oc));
        } else
        {
            this.dispatcher.dispatch(new OfertaCumparareEvent(oc));
        }
	}

	public void inregistrareLaEveniment(Listener listener, EventFilter f) {
		dispatcher.registerListener(f, listener);
	}
}
