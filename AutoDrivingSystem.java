import java.util.Scanner;

public class AutoDrivingSystem {
    public static void main(String[] args) {
        // Create an autonomous driving system
        AutonomousDrivingSystem autonomousDrivingSystem = new AutonomousDrivingSystem();
        double latitude1, latitude2;
        double longitude1, longitude2;

        Scanner scanner = new Scanner(System.in);

        System.out.print("What is your initial location (latitude): ");
        latitude1 = scanner.nextDouble();
        System.out.print("                             (longitude): ");
        longitude1 = scanner.nextDouble();

        System.out.print("What is your destination location (latitude): ");
        latitude2 = scanner.nextDouble();
        System.out.print("                                 (longitude): ");
        longitude2 = scanner.nextDouble();

        scanner.close();

        autonomousDrivingSystem.initGpsData(latitude1, longitude1, latitude2, longitude2);

        // Process the sensor data
        autonomousDrivingSystem.runSimulation();

        System.out.println("You have arrived! (close enough)");
    }
}