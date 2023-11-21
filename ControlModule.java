// Control Module - this module brings together gps data, sensor data, and planning data
class ControlModule {
    // write a function to initialize GPS data
    // write a function to adjust speed and direction based on:
    // - the processing of sensor data
    // - the planning of your route
    // - the updating of your route
    // end your journey after 24 hours or within 25km of your destination

    private GPSData currentGPSData;
    private GPSData destinationGPSData;
    private double currentSpeed;
    private double currentDirection;

    ControlModule() {
        // Initialize control module data
        currentGPSData = new GPSData();
        destinationGPSData = new GPSData();
        currentSpeed = 0.0;
        currentDirection = 0.0;
    }

    // Function to initialize GPS data
    void initializeGpsData(double latitude1, double longitude1, double latitude2, double longitude2) {
        currentGPSData.curr_latitude = latitude1;
        currentGPSData.curr_longitude = longitude1;
        currentGPSData.dest_latitude = latitude2;
        currentGPSData.dest_longitude = longitude2;

        destinationGPSData.curr_latitude = latitude2;
        destinationGPSData.curr_longitude = longitude2;
    }

    // Function to adjust speed and direction based on sensor data and route planning
    void adjustSpeedAndDirection(PerceptionModule perceptionModule, PlanningModule planningModule) {
        // Process sensor data
        perceptionModule.processCameraData(new CameraData());
        perceptionModule.processLidarData(new LidarData());

        // Update GPS data
        perceptionModule.processGPSData(currentGPSData, currentSpeed, currentDirection);

        // Plan and update route
        planningModule.planRoute(currentGPSData.curr_latitude, currentGPSData.curr_longitude,
                                 currentGPSData.dest_latitude, currentGPSData.dest_longitude);

        planningModule.updateRoute(new CameraData(), new LidarData());

        // Adjust speed and direction (simplified for demonstration)
        double[] nextWaypoint = planningModule.getNextWaypoint();
        if (nextWaypoint != null) {
            double destLatitude = nextWaypoint[0];
            double destLongitude = nextWaypoint[1];

            double angleToWaypoint = calculateAngleToWaypoint(destLatitude, destLongitude);
            adjustSpeedAndDirectionBasedOnAngle(angleToWaypoint);
        } else {
            System.out.println("You have arrived! (close enough)");
            // End the journey
        }
    }

    // Function to calculate the angle to the next waypoint
    private double calculateAngleToWaypoint(double destLatitude, double destLongitude) {
        double deltaLongitude = destLongitude - currentGPSData.curr_longitude;
        double deltaLatitude = destLatitude - currentGPSData.curr_latitude;
        return Math.toDegrees(Math.atan2(deltaLongitude, deltaLatitude));
    }

    // Function to adjust speed and direction based on the calculated angle
    private void adjustSpeedAndDirectionBasedOnAngle(double angleToWaypoint) {
        // Adjust speed and direction (simplified for demonstration)
        currentSpeed = 60.0; // Set a constant speed of 60 km/h
        currentDirection = angleToWaypoint;
        System.out.println("Adjusting speed: " + currentSpeed + " km/h, Direction: " + currentDirection + " degrees");
    }
}