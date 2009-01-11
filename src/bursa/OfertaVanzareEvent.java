/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bursa;
import dispatch.Event;
/**
 *
 * @author Cosmin
 */
public class OfertaVanzareEvent implements Event
{
    private OfertaVanzare o;
    public OfertaVanzareEvent(OfertaVanzare o)
    {
        this.o = o;
    }
    public OfertaVanzare getOferta()
    {
        return this.o;
    }
}
