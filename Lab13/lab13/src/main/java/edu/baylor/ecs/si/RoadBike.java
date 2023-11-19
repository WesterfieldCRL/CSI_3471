package edu.baylor.ecs.si;

public class RoadBike extends Bicycle {
    private int tireWidth;

    public RoadBike(int startCadence, int startSpeed, int startGear, int tireWidth) {
        super(startCadence, startSpeed, startGear);
        this.tireWidth = tireWidth;
    }

    @Override
    public void printDescription(){
        System.out.println("\nRoadBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + ". ");
    }

    public void visit(RoadBikeService basicService)
    {
        basicService.accept(this);
    }

    public int getTireWidth() {
        return tireWidth;
    }

    public void setTireWidth(int tireWidth) {
        this.tireWidth = tireWidth;
    }
}
