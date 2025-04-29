package copyandwritearraylist;

//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class EventSource {
//    private final CopyOnWriteArrayList<EventListener> listeners = new CopyOnWriteArrayList<>();
//
//    public void addListener(EventListener l) { listeners.addIfAbsent(l); }
//    public void removeListener(EventListener l) { listeners.remove(l); }
//
//    public void fireEvent(Event e) {
//        for (EventListener l : listeners) {
//            l.onEvent(e);  // безопасно, даже если в процессе кто-то добавляет/удаляет
//        }
//    }
//}
