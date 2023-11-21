// Sensor Data Structures
class CameraData {
    enum ObjectType {
        none,
        vehicle,
        pedestrian,
        bicycle,
        stopLight,
        speedLimit
    }

    ObjectType object;
}