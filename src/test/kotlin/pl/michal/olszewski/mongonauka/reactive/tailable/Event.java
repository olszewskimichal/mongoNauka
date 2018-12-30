package pl.michal.olszewski.mongonauka.reactive.tailable;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
class Event {

  @Id
  private String id;
  private final EventType eventType;
  private final Date date;

  Event(EventType eventType, Date date) {
    this.eventType = eventType;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EventType getEventType() {
    return eventType;
  }

  public Date getDate() {
    return date;
  }



  @Override
  public String toString() {
    return "Event{" +
        "id='" + id + '\'' +
        ", eventType='" + eventType + '\'' +
        ", date=" + date +
        '}';
  }
}
