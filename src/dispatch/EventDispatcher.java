package dispatch;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher implements Dispatcher {
    private class Entry
    {
        public Entry(Listener l, EventFilter f)
        {
            this.l = l;
            this.f = f;
        }
        public Listener l;
        public EventFilter f;
    }

	private ArrayList<Entry> entries = new ArrayList<Entry>();
	
	public void dispatch(Event event) {
        for (Entry entry : entries) {
            if(entry.f.accept(event))
                entry.l.consumeEvent(event);
        }
	}

	public void registerListener(EventFilter ev, Listener listener) {
		entries.add(new Entry(listener, ev));
	}
}
