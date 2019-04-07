package observer;

public class FieldObservable {
    private boolean changed = false;
    private FieldObserver obs;

    public FieldObservable() {

    }

    public synchronized void setObserver(FieldObserver o) {
        this.obs = o;
    }

    public void notifyObserver() {
        if (changed) {
            clearChanged();
            this.obs.update();
        }
    }

    public void setChanged() {
        changed = true;
    }

    private void clearChanged() {
        changed = false;
    }
}
