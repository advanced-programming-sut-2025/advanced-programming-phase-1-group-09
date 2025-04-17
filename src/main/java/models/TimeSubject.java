package models;

public interface TimeSubject {
    void addObserver(TimeObserver observer);
    void removeObserver(TimeObserver observer);
    void notifyObservers();
}
