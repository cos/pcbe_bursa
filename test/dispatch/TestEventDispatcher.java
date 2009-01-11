package dispatch;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEventDispatcher {
	private class CountListener implements Listener {
		public int count = 0;
		
        @Override
		public void consumeEvent(Event event) {
			count++;
		}
	}

	EventDispatcher dispatcher;
	
    private class AllEventFilter implements EventFilter
    {
        @Override
        public boolean accept(Event e) {
            return true;
        } 
    }

    private class MessageEventFilter implements EventFilter
    {
        public String m;
        public MessageEventFilter(String m)
        {
            this.m = m;
        }
        @Override
        public boolean accept(Event e) {
            return (e instanceof MessageEvent) && ((MessageEvent)e).m.equals(this.m);
        }
    }

    private class MessageEvent implements Event
    {
        public String m;
        public MessageEvent(String m)
        {
            this.m = m;
        }
    }

	@Before
	public void setUp() throws Exception {
		dispatcher = new EventDispatcher();
	}
	
	@Test
	public void testDispatch() {
		CountListener listener = new CountListener();
		dispatcher.registerListener(new AllEventFilter(), listener);
		dispatcher.dispatch(new MessageEvent("aparitie oferta"));
		assertEquals(1, listener.count);
	}
	
	@Test
	public void testDispatchTwice() {
		CountListener listener = new CountListener();
		dispatcher.registerListener(new AllEventFilter(), listener);
		dispatcher.dispatch(new MessageEvent("aparitie oferta"));
		dispatcher.dispatch(new MessageEvent("aparitie oferta"));
		assertEquals(2, listener.count);
	}
	
	@Test
	public void testDispatch2() {
		CountListener listener1 = new CountListener();
		CountListener listener2 = new CountListener();
		dispatcher.registerListener(new AllEventFilter(),  listener1);
		dispatcher.registerListener(new AllEventFilter(),  listener2);
		dispatcher.dispatch(new MessageEvent("aparitie oferta"));
		assertEquals(1, listener1.count);
		assertEquals(1, listener2.count);
	}
	
	@Test
	public void testDispatch2Different() {
		CountListener listener1 = new CountListener();
		CountListener listener2 = new CountListener();
		dispatcher.registerListener(new MessageEventFilter("of 1"), listener1);
		dispatcher.registerListener(new MessageEventFilter("of 2"), listener2);
		dispatcher.dispatch(new MessageEvent("of 1"));
		assertEquals(1, listener1.count);
		assertEquals(0, listener2.count);
	}
	
	@Test
	public void testDispatchSameListener2EventTypes() {
		CountListener listener = new CountListener();
		dispatcher.registerListener(new MessageEventFilter("of 1"), listener);
		dispatcher.registerListener(new MessageEventFilter("of 2"), listener);
		dispatcher.dispatch(new MessageEvent("of 1"));
		dispatcher.dispatch(new MessageEvent("of 2"));
		assertEquals(2, listener.count);
	}
}
