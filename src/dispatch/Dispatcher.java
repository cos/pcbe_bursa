package dispatch;

public interface Dispatcher {
	public void registerListener(EventFilter filter, Listener listener);
	public void dispatch(Event event);
}