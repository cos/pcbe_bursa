package dispatch;

public interface Dispatcher {
	public void registerListener(String eventType, Listener listener);
	public void dispatch(Event event);
}
