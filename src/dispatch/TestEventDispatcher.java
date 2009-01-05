package dispatch;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEventDispatcher {
	private class CountListener implements Listener {
		public int count = 0;
		
		public void consumeEvent(Event event) {
			count++;
		}
	}

	EventDispatcher dispatcher;
	
	@Before
	public void setUp() throws Exception {
		dispatcher = new EventDispatcher();
	}
	
	@Test
	public void testDispatch() {
		CountListener listener = new CountListener();
		dispatcher.registerListener("aparitie oferta", listener);
		dispatcher.dispatch(new Event("aparitie oferta"));
		assertEquals(1, listener.count);
	}
	
	@Test
	public void testDispatchTwice() {
		CountListener listener = new CountListener();
		dispatcher.registerListener("aparitie oferta", listener);
		dispatcher.dispatch(new Event("aparitie oferta"));
		dispatcher.dispatch(new Event("aparitie oferta"));
		assertEquals(2, listener.count);
	}
	
	@Test
	public void testDispatch2() {
		CountListener listener1 = new CountListener();
		CountListener listener2 = new CountListener();
		dispatcher.registerListener("aparitie oferta", listener1);
		dispatcher.registerListener("aparitie oferta", listener2);
		dispatcher.dispatch(new Event("aparitie oferta"));
		assertEquals(1, listener1.count);
		assertEquals(1, listener2.count);
	}
	
	@Test
	public void testDispatch2Different() {
		CountListener listener1 = new CountListener();
		CountListener listener2 = new CountListener();
		dispatcher.registerListener("aparitie oferta", listener1);
		dispatcher.registerListener("altceva", listener2);
		dispatcher.dispatch(new Event("aparitie oferta"));
		assertEquals(1, listener1.count);
		assertEquals(0, listener2.count);
	}
	
	@Test
	public void testDispatchSameListener2EventTypes() {
		CountListener listener = new CountListener();
		dispatcher.registerListener("aparitie oferta", listener);
		dispatcher.registerListener("modificare oferta", listener);
		dispatcher.dispatch(new Event("aparitie oferta"));		
		dispatcher.dispatch(new Event("modificare oferta"));		
		assertEquals(2, listener.count);
	}
}
