package dispatch;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher implements Dispatcher {
	private HashMap<String, ArrayList<Listener>> hashListeners = new HashMap<String, ArrayList<Listener>>();
	
	public void dispatch(Event event) {
		if (hashListeners.containsKey(event.getType())) {
			for (Listener listener : hashListeners.get(event.getType())) {
				listener.consumeEvent(event);
			}
		}
	}

	public void registerListener(String eventType, Listener listener) {
		if (!hashListeners.containsKey(eventType)) {
			hashListeners.put(eventType, new ArrayList<Listener>());
		}
		hashListeners.get(eventType).add(listener);
	}
}
