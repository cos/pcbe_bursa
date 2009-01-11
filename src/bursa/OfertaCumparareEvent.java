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
public class OfertaCumparareEvent implements Event
{
    private OfertaCumparare o;
    public OfertaCumparareEvent(OfertaCumparare o)
    {
        this.o = o;
    }
    public OfertaCumparare getOferta()
    {
        return this.o;
    }
}
