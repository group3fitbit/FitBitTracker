package tempreture.android.csulb.edu.fitbitapp;


public class Dummy {
    private String name;
    private int circleImage;
    private int boxImage;
    private int heartbeat;
    private int steps;
    private int elevation;
    private double active;
    private double distance;
    private int progress;

    public Dummy(String name, int circleImage, int boxImage){
        this.name = name;
        this.circleImage = circleImage;
        this.boxImage = boxImage;
        heartbeat = 0;
        steps = 0;
        elevation = 0;
        active = 0.0;
        distance = 0.0;
    }

    public int getProgress(){
        return progress;
    }

    public void setProgress(int progress){
        progress = this.progress;
    }

    public int getCircleImage(){
        return circleImage;
    }

    public int getBoxImage(){
        return boxImage;
    }

    public String getName(){
        return name;
    }

    public int getHeartbeat(){
        return heartbeat;
    }

    public int getSteps(){
        return steps;
    }

    public int getElevation(){
        return elevation;
    }

    public double active(){
        return active;
    }

    public double distance(){
        return distance;
    }
}
