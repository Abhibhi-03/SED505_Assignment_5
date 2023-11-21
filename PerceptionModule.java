// Perception Module - processes camera, lidar and GPS data
class PerceptionModule {
    private int timer = 0; // increments once per hour
    private final int MAX_TIME = 6;

    void processCameraData(CameraData cameraData) {
        switch (timer) {
            case 0:
                cameraData.object = CameraData.ObjectType.none;
                break;
            case 1:
                cameraData.object = CameraData.ObjectType.vehicle;
                break;
            case 2:
                cameraData.object = CameraData.ObjectType.pedestrian;
                break;
            case 3:
                cameraData.object = CameraData.ObjectType.bicycle;
                break;
            case 4:
                cameraData.object = CameraData.ObjectType.stopLight;
                break;
            case 5:
                cameraData.object = CameraData.ObjectType.speedLimit;
                break;
            default:
                cameraData.object = CameraData.ObjectType.none;
        }
    }

    void processLidarData(LidarData lidarData) {
        switch (timer) {
            case 0:
            case 3:
                lidarData.object = LidarData.ObjectType.roadCurvature;
                break;
            case 1:
            case 4:
                lidarData.object = LidarData.ObjectType.smallObstruction;
                break;
            case 2:
            case 5:
                lidarData.object = LidarData.ObjectType.largeObstruction;
                break;
            default:
                lidarData.object = LidarData.ObjectType.roadCurvature;
        }
    }

    void processGPSData(GPSData gpsData, double speed, double direction) {
        gpsData.curr_longitude += 180.0 * speed * Math.sin(Math.PI / 180.0 * direction) / 40040.00;
        gpsData.curr_latitude += 180.0 * speed * Math.cos(Math.PI / 180.0 * direction) / 40040.00;
    }

    void incrementTime() {
        ++timer;
        timer = timer % MAX_TIME;
    }
}