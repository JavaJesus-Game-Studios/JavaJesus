package ca.javajesus.game.entities.vehicles;

public class CarPhysics {
	
	private int tickCount;
	private int xa = 0;
	private int ya = 0;
	public double x, y;
	private double myXVelocity, myYVelocity;
	private double myXAcceleration, myYAcceleration;
	private double myXJerk, myYJerk;

	public CarPhysics() {
		x = 0;
		y = 0;
		myXVelocity = 0;
		myXAcceleration = 0;
		myXJerk = 0;
		myYVelocity = 0;
		myYAcceleration = 0;
		myYJerk = 0;
	}

	public CarPhysics(double hor, double vert, double v, double a, double j) {
		x = hor;
		y = vert;
		myXVelocity = v;
		myXAcceleration = a;
		myXJerk = j;
		myYVelocity = v;
		myYAcceleration = a;
		myYJerk = j;
	}

	public CarPhysics(double hor, double vert, double v, double a) {
		x = hor;
		y = vert;
		myXVelocity = v;
		myXAcceleration = a;
		myXJerk = 0;
		myYVelocity = v;
		myYAcceleration = a;
		myYJerk = 0;
	}

	public void setTick(int t) {
		tickCount = t;
	}

	public void setXJerk(double j) {
		myXJerk = j;
	}

	public void setXAcceleration(double a) {
		myXAcceleration = a;
	}

	public void setXVelocity(double v) {
		myXVelocity = v;
	}

	public void xAcceleration() {
		myXAcceleration += myXJerk;
	}

	public void xVelocity() {
        if (myXVelocity >= 2)
        {
            myXVelocity = 2;
            //myXAcceleration = 0;
            myXVelocity--;
        }
        else if (myXVelocity <= -2)
        {
            myXVelocity = -2;
            //myXAcceleration = 0;
            myXVelocity++;
        }
        else
        myXVelocity += myXAcceleration;
    }
	
	public void xPosition(){
	    x += myXVelocity;
	}

	public void setYJerk(double j) {
		myYJerk = j;
	}

	public void setYAcceleration(double a) {
		myYAcceleration = a;
	}

	public void setYVelocity(double v) {
		myYVelocity = v;
	}

	public void yAcceleration() {
		myYAcceleration += myYJerk;
	}

	public void yVelocity() {
        if (myYVelocity >= 2)
        {
            myYVelocity = 2;
            //myYAcceleration = 0;
            //myYVelocity--;
        }
        else if (myYVelocity <= -2)
        {
            myYVelocity = -2;
            //myYAcceleration = 0;
            //myYVelocity++;
        }
        else
        myYVelocity += myYAcceleration;
    }
	
	public double getXVelocity()
	{
	    return myXVelocity;
	}
	
	public double getYVelocity()
	{
	    return myYVelocity;
	}
	
	public double getXAcceleration()
	{
	    return myXAcceleration;
	}
	
	public double getYAcceleration()
    {
        return myYAcceleration;
    }

    public void position() {
        xAcceleration();
        yAcceleration();
        xVelocity();
        yVelocity();
        x += myXVelocity;
        x += myXAcceleration * tickCount;
        y += myYVelocity;
        y += myYAcceleration * tickCount;
    } 

}
