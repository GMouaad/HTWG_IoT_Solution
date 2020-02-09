package de.htwg.iotstack.MqttGateway.Persistence;

/**
 * This Class is added for demonstration purpose and is not needed for the moment in the current implementation
 */
public class PersistenceThread implements Runnable {

    DBController dbController;

    @Override
    public void run() {
        while (true) {
            double rand = getRandomDouble(10) + 20.0;
            Double obj = new Double(rand);
            dbController.persist(obj);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getRandomDouble(int max) {

        double randomD = Math.random() * max + 1;

        return randomD;
    }
}
