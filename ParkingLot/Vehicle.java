// vehicle has name : car/bike/truck, color : red/blue/... , no_plate : 1234XYZ
abstract class Vehicle {
    private String vehicle_name;
    private String vehicle_color;
    private String no_plate;

    Vehicle(String vehicle_name, String vehicle_color, String no_plate) {
        this.vehicle_name = vehicle_name;
        this.vehicle_color = vehicle_color;
        this.no_plate = no_plate;
    }

    public String get_vehicle_name() {
        return this.vehicle_name;
    }

    public String get_vehicle_color() {
        return this.vehicle_color;
    }

    public String get_no_plate() {
        return this.no_plate;
    }

    public abstract ParkingSpotType get_parking_spot_type();
}

class Car extends Vehicle {

    public Car(String vehicle_name, String vehicle_color, String no_plate) {
        super(vehicle_name, vehicle_color, no_plate);
    }

    @Override
    public ParkingSpotType get_parking_spot_type() {
        return ParkingSpotType.SMALL;
    }
}

class bike extends Vehicle {

    public bike(String vehicle_name, String vehicle_color, String no_plate) {
        super(vehicle_name, vehicle_color, no_plate);
    }

    @Override
    public ParkingSpotType get_parking_spot_type() {
        return ParkingSpotType.COMPACT;
    }
}

class truck extends Vehicle {

    public truck(String vehicle_name, String vehicle_color, String no_plate) {
        super(vehicle_name, vehicle_color, no_plate);
    }

    @Override
    public ParkingSpotType get_parking_spot_type() {
        return ParkingSpotType.LARGE;
    }
}
