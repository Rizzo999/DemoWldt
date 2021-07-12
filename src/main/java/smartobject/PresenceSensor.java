package smartobject;

import java.util.Random;

public class PresenceSensor {

    private Random rnd;

    private boolean presenceValue;

    public PresenceSensor() {
        this.rnd = new Random();
        this.presenceValue = false;
    }

    private void generatePresenceValue() {
        presenceValue = rnd.nextBoolean();
    }

    public boolean getPresenceValue() {
        generatePresenceValue();
        return presenceValue;
    }

    public void setPresenceValue(boolean presenceValue) {
        this.presenceValue = presenceValue;
    }

    @Override
    public String toString() {
        return "PresenceSensor{" +
                "rnd=" + rnd +
                ", presenceValue=" + presenceValue +
                '}';
    }
}
