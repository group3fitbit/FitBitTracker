package tempreture.android.csulb.edu.fitbitapp;


public class Dummy {
    String name;
    int circleImage;
    int boxImage;
    int steps;
    int elevation;
    int calories;
    double active;
    double distance;
    double progress;

    public Dummy(String name, int circleImage,int boxImage){
        this.name = name;
        this.circleImage = circleImage;
        this.boxImage = boxImage;
        calories = 0;
        steps = 0;
        elevation = 0;
        active = 0.0;
        distance = 0.0;
        progress = 0;
    }
    public double getProgress(){
        return progress;
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


    public int getSteps(){
        return steps;
    }
    public int getElevation(){
        return elevation;
    }

    public int getCalories(){
        return calories;
    }

    public double getActiveHours(){
        return active;
    }

    public double getDistance(){
        return distance;
    }

    public void setProgress(String type){
        if(type.equalsIgnoreCase("steps")){
            progress = steps;
        }else if(type.equalsIgnoreCase("calories burned")){
            progress = calories;
        }else if(type.equalsIgnoreCase("floors climbed")){
            progress = elevation;
        }else if(type.equalsIgnoreCase("distance traveled")){
            progress = distance;
        }else if(type.equalsIgnoreCase("hours active")){
            progress = active;
        }
    }
    public void setCalories(int calories){
        this.calories = calories;
    }
    public void setSteps(int steps){
        this.steps = steps;
    }
    public void setElevation(int elevation){
        this.elevation = elevation;
    }
    public void setActiveHours(double active){
        this.active = active;
    }
    public void setDistance(double distance){
        this.distance = distance;
    }
    public void setStats(int calories,int steps, int elevation, double active, double distance){
        setCalories(calories);
        setSteps(steps);
        setElevation(elevation);
        setActiveHours(active);
        setDistance(distance);
    }
}