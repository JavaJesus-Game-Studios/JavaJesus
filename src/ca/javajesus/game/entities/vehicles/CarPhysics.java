package ca.javajesus.game.entities.vehicles;

public class CarPhysics {

	private int tickCount = 0;
	public double x, y;
	private double xVelocity, yVelocity;
	private double xAcceleration, yAcceleration;
	private double xJerk, yJerk;
	private double xFriction, yFriction;

	public CarPhysics() {
		x = 0;
		y = 0;
		xVelocity = 0;
		xAcceleration = 0;
		xJerk = 0;
		yVelocity = 0;
		yAcceleration = 0;
		yJerk = 0;
	}

	public CarPhysics(double hor, double vert, double v, double a, double j) {
		x = hor;
		y = vert;
		xVelocity = v;
		xAcceleration = a;
		xJerk = j;
		yVelocity = v;
		yAcceleration = a;
		yJerk = j;
	}

	public CarPhysics(double hor, double vert, double v, double a) {
		x = hor;
		y = vert;
		xVelocity = v;
		xAcceleration = a;
		xJerk = 0;
		yVelocity = v;
		yAcceleration = a;
		yJerk = 0;
	}

	public void setXJerk(double j) {
		xJerk = j;
	}

	public void setXAcceleration(double a) {
		xAcceleration = a;
	}

	public void setXVelocity(double v) {
		xVelocity = v;
	}

	public void xAccelerate() {
		xAcceleration += xJerk;
	}

	public void updateXVelocity() {
		
		if (xVelocity >= 2) 
		{ xVelocity = 2; 
		// xAcceleration = 0; 
		xVelocity-= 2; 
		} 
		else if (xVelocity <= -2) 
		{ xVelocity = -2; 
		// xAcceleration = 0; 
		xVelocity += 2; 
		} 
		else 
		xVelocity += xAcceleration;
	}

	public void updateXPosition() {
		x += xVelocity;
	}

	public void setYJerk(double j) {
		yJerk = j;
	}

	public void setYAcceleration(double a) {
		yAcceleration = a;
	}

	public void setYVelocity(double v) {
		yVelocity = v;
	}

	public void yAccelerate() {
		yAcceleration += yJerk;
	}
	
	public void setXFriction(double f)
	{
	    xFriction = f;
	}
	
	public void setYFriction(double f)
    {
        yFriction = f;
    }

	public void updateYVelocity() {
        
        if (yVelocity >= 2) 
        { yVelocity = 2; 
        // yAcceleration = 0; 
        yVelocity-= 2; 
        } 
        else if (yVelocity <= -2) 
        { yVelocity = -2; 
        // yAcceleration = 0; 
        yVelocity += 2; 
        } 
        else 
        yVelocity += yAcceleration;
    }

	public double getXVelocity() {
		return xVelocity;
	}

	public double getYVelocity() {
		return yVelocity;
	}

	public double getXAcceleration() {
		return xAcceleration;
	}

	public double getYAcceleration() {
		return yAcceleration;
	}

	public void updatePosition() {
		xAccelerate();
		yAccelerate();
		updateXVelocity();
		updateYVelocity();
		x += xVelocity;
		x += xAcceleration * tickCount;
		y += yVelocity;
		y += yAcceleration * tickCount;
	}
	
	public void incrementTick()
	{
	    if(!(tickCount > 60))        
	    tickCount++;
	}
	
	public void reset() {
	    xAcceleration = 0;
	    yAcceleration = 0;
	    xVelocity = 0;
	    yVelocity = 0;
	    x = 0;
	    y = 0;
	    //tickCount = 0;
	}
	
	public void xReset()
	{
	    xAcceleration = 0;
        xVelocity = 0;
        x = 0;   
	}
	
	public void yReset()
	{
	    yAcceleration = 0;
        yVelocity = 0;
        y = 0;  
	}
	
	public void xFriction(int movingDir)
	{ 
	    //if(Math.abs(xVelocity) > 1)
	    //{
            if (movingDir == 2)
            {
                xAcceleration+=xFriction;
            }
            if (movingDir == 3)
            {
                xAcceleration-=xFriction;
            }
	    //}
	}

	public void yFriction(int movingDir)
    { 
        //if(Math.abs(yVelocity) > 1)
        //{
            if (movingDir == 0)
            {
                yAcceleration+=yFriction;
            }
            if (movingDir == 1)
            {
                yAcceleration-=yFriction;
            }
        //}
    }

}
