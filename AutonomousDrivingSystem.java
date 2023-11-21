// Autonomous Driving System - starts, runs and stops the simulation
class AutonomousDrivingSystem {
    // write a function to initialize data
    // write a function to run the overall simulation

    private ControlModule controlModule;

    public AutonomousDrivingSystem() {
        this.controlModule = new ControlModule();
    }

    void initGpsData(double latitude1, double longitude1, double latitude2, double longitude2) {
        controlModule.initializeGpsData(latitude1, longitude1, latitude2, longitude2);
    }

    void runSimulation() {
        for (int hour = 0; hour < 24; hour++) {
            // Simulate once per hour
            controlModule.adjustSpeedAndDirection(new PerceptionModule(), new PlanningModule());
            // Increment time in the PerceptionModule
            new PerceptionModule().incrementTime();
        }
    }
}
