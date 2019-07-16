package pl.michal.olszewski.mongonauka.transactions;

import org.springframework.data.annotation.Id;

public class Process {

  @Id
  Integer id;
  State state;
  int transitionCount;

  public Process() {
  }

  public Process(Integer id, State state, int transitionCount) {
    this.id = id;
    this.state = state;
    this.transitionCount = transitionCount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public int getTransitionCount() {
    return transitionCount;
  }

  public void setTransitionCount(int transitionCount) {
    this.transitionCount = transitionCount;
  }

  @Override
  public String toString() {
    return "Process{" +
        "id=" + id +
        ", state=" + state +
        ", transitionCount=" + transitionCount +
        '}';
  }
}