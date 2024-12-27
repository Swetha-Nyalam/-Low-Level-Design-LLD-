import java.util.*;

// Supports three different types of parking spots: compact, small, and large.
// Provides functionality to check the availability of parking spots for each type:
// - isCompactSpotAvailable
// - isSmallSpotAvailable
// - isLargeSpotAvailable
// Allows reservation of parking spots and freeing them after use.

public class ParkingFloor {
    private Map<ParkingSpotType, Queue<ParkingSpot>> parkingQueues = new HashMap<>();
    HashMap<String, ParkingSpot> vehicleToSpot = new HashMap<>();

    public ParkingFloor(int compactSpots, int smallSpots, int largeSpots) {
        parkingQueues.put(ParkingSpotType.COMPACT, new LinkedList<>());
        parkingQueues.put(ParkingSpotType.SMALL, new LinkedList<>());
        parkingQueues.put(ParkingSpotType.LARGE, new LinkedList<>());

        for (int i = 1; i <= compactSpots; i++) {
            parkingQueues.get(ParkingSpotType.COMPACT).add(new CompactParkingSpot("C" + i, false, null));
        }
        for (int i = 1; i <= smallSpots; i++) {
            parkingQueues.get(ParkingSpotType.SMALL).add(new SmallParkingSpot("S" + i, false, null));
        }
        for (int i = 1; i <= largeSpots; i++) {
            parkingQueues.get(ParkingSpotType.LARGE).add(new LargeParkingSpot("L" + i, false, null));
        }
    }

    public boolean isSpotAvailable(ParkingSpotType type) {
        Queue<ParkingSpot> queue = parkingQueues.get(type);
        return queue != null && !queue.isEmpty();
    }

    public String reserveParkingSpot(ParkingSpotType type, Vehicle vehicle) {
        if (!isSpotAvailable(type)) {
            System.out.println("No available spots for type: " + type);
            return "null";
        }
        ParkingSpot spot = parkingQueues.get(type).poll(); // Get the next available spot

        spot.set_parked_vehicle_no_plate(vehicle.get_no_plate());
        spot.set_parking_status(true);
        vehicleToSpot.put(vehicle.get_no_plate(), spot);

        System.out.println("Reserved " + spot.get_parking_spot_ID() + " for vehicle: " + vehicle.get_no_plate());
        return spot.get_parking_spot_ID();
    }

    public boolean freeParkingSpot(Vehicle vehicle) {
        ParkingSpot spot = vehicleToSpot.get(vehicle.get_no_plate());
        if (spot == null) {
            System.out.println("No vehicle found with number plate: " + vehicle.get_no_plate());
            return false;
        }

        spot.set_parking_status(false);
        spot.set_parked_vehicle_no_plate("null");

        // Return the spot to the corresponding queue
        parkingQueues.get(spot.get_parking_spot_type()).add(spot);
        vehicleToSpot.remove(vehicle.get_no_plate());

        System.out.println(
                "Freed spot: " + spot.get_parking_spot_ID() + " for vehicle: " + vehicle.get_no_plate());
        return true;
    }
}
