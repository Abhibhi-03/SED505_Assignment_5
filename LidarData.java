// Sensor Data Structures
class LidarData {
    enum ObjectType {
        roadCurvature,
        smallObstruction,
        largeObstruction,
    }

    ObjectType object;
}
