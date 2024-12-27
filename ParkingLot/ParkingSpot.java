// CompactParkingSpot, SmallParkingSpot, LargeParkingSpot
abstract class ParkingSpot {
    private String parking_spot_ID;
    private boolean parking_status;
    private Vehicle parked_vehicle;
    protected ParkingSpotType parking_spot_type;

    ParkingSpot(String parking_spot_ID, boolean parking_status, Vehicle parked_vehicle) {
        this.parking_spot_ID = parking_spot_ID;
        this.parking_status = parking_status;
        this.parked_vehicle = parked_vehicle;
    }

    public void set_parking_status(boolean status) {
        this.parking_status = status;
    }

    public void set_parked_vehicle_no_plate(String id) {
        this.parking_spot_ID = id;
    }

    public void parked_vehicle_no_plate(boolean status) {
        this.parking_status = status;
    }

    public String get_parking_spot_ID() {
        return this.parking_spot_ID;
    }

    public boolean get_parking_status() {
        return this.parking_status;
    }

    public Vehicle get_parked_vehicle_no_plate() {
        if (this.parking_status == false) {
            // error message
        }
        return this.parked_vehicle;
    }

    public abstract void set_parking_spot_type();

    public ParkingSpotType get_parking_spot_type() {
        return this.parking_spot_type;
    }
}

class CompactParkingSpot extends ParkingSpot {
    public CompactParkingSpot(String parking_spot_ID, boolean parking_status, Vehicle parked_vehicle) {
        super(parking_spot_ID, parking_status, parked_vehicle);
    }

    public void set_parking_spot_type() {
        super.parking_spot_type = ParkingSpotType.COMPACT;
    }
}

class SmallParkingSpot extends ParkingSpot {
    public SmallParkingSpot(String parking_spot_ID, boolean parking_status, Vehicle parked_vehicle) {
        super(parking_spot_ID, parking_status, parked_vehicle);
    }

    public void set_parking_spot_type() {
        super.parking_spot_type = ParkingSpotType.SMALL;
    }
}

class LargeParkingSpot extends ParkingSpot {
    public LargeParkingSpot(String parking_spot_ID, boolean parking_status, Vehicle parked_vehicle) {
        super(parking_spot_ID, parking_status, parked_vehicle);
    }

    public void set_parking_spot_type() {
        super.parking_spot_type = ParkingSpotType.LARGE;
    }
}