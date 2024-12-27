import java.util.*;
import java.time.*;

// Handles a single entry and exit point for vehicles (can be extended with a priority queue if needed).
// Creates a new floor, ensuring it does not already exist in the system.
// Assign the nearest available parking spot(reserveNearestParkingSpot --> nearest based on floor) to the vehicle based on its type and availability.
// Frees up a parking spot when a vehicle exits, using the vehicle's number plate and floor location mapping.
// Calculates the payment details for the vehicle upon exiting the parking lot.

public class parkingLot {
    HashMap<Integer, ParkingFloor> floorMap = new HashMap<>();
    HashMap<String, Integer> vehicleToFloor = new HashMap<>();
    HashMap<String, LocalTime> vehicleEntryTime = new HashMap<>();
    long HOURLY_RATE = 20;// hard coded can be changed accrodingly

    public boolean addNewFloor(int floorNumber, int compactSpots, int smallSpots, int largeSpots) {
        if (floorMap.containsKey(floorNumber)) {
            System.out.println("floor with " + floorNumber + " already exists");
            return false;
        }
        ParkingFloor floor = new ParkingFloor(compactSpots, smallSpots, largeSpots);
        floorMap.put(floorNumber, floor);
        System.out.println("floor with " + floorNumber + " created sucessfully!");
        return true;
    }

    public boolean reserveNearestParkingSpot(Vehicle newVehicle) {
        ParkingSpotType type = newVehicle.get_parking_spot_type();
        String vehicleNumberPlate = newVehicle.get_no_plate();
        for (int i = 1; i < floorMap.size(); i++) {
            if (floorMap.get(i).isSpotAvailable(type)) {
                String spotID = floorMap.get(i).reserveParkingSpot(type, newVehicle);
                vehicleToFloor.put(vehicleNumberPlate, i);
                vehicleEntryTime.put(vehicleNumberPlate, LocalTime.now());
                System.out.println("Succefully reserved parking spot " + spotID + " in floor " + i);
                return true;
            }
        }
        return false;
    }

    public boolean freeparkingSpot(Vehicle exitVehicle) {
        ParkingFloor parkedFloor = floorMap.get(vehicleToFloor.get(exitVehicle.get_no_plate()));
        parkedFloor.freeParkingSpot(exitVehicle);
        vehicleToFloor.remove(exitVehicle.get_no_plate());
        calculatePayAndPrint(vehicleEntryTime.get(exitVehicle.get_no_plate()), LocalTime.now());
        vehicleEntryTime.remove(exitVehicle.get_no_plate());
        System.out.println(exitVehicle.get_no_plate() + " has exited sucessfully!");
        return true;
    }

    public void calculatePayAndPrint(LocalTime entryTime, LocalTime exitTime) {
        Duration duration = Duration.between(entryTime, exitTime);

        long hours = duration.toHours();
        if (duration.toMinutesPart() > 0) {
            hours++;
        }
        long totalPayment = hours * HOURLY_RATE;
        System.out.println("total payment for vehicle : " + totalPayment);
    }
}
