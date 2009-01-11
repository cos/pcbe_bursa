package bursa;

import dispatch.Event;


public class TranzactieFinalizataEvent implements Event
{
    private OfertaVanzare ofertaVanzare;
    private OfertaCumparare ofertaCumparare;
    public TranzactieFinalizataEvent(OfertaVanzare ov, OfertaCumparare oc) {
        this.ofertaVanzare = ov;
        this.ofertaCumparare = oc;
    }

    public OfertaCumparare getOfertaCumparare() {
        return ofertaCumparare;
    }

    public OfertaVanzare getOfertaVanzare() {
        return ofertaVanzare;
    }
}
