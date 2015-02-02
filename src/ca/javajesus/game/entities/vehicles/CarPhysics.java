package ca.javajesus.game.entities.vehicles;

public class CarPhysics
{
    int tickCount;
    int xa = 0;
    int ya = 0;
    double x, y;
    double myXVelocity, myYVelocity;
    double myXAcceleration, myYAcceleration;
    double myXJerk, myYJerk;
    
    public CarPhysics()
    {
        x = 0;
        y = 0;
        myXVelocity = 0;
        myXAcceleration = 0;
        myXJerk = 0;
        myYVelocity = 0;
        myYAcceleration = 0;
        myYJerk = 0;
    }
    
    public CarPhysics(double hor, double vert, double v, double a, double j)
    {
        x = hor;
        y = vert;
        myXVelocity = v;
        myXAcceleration = a;
        myXJerk = j;
        myYVelocity = v;
        myYAcceleration = a;
        myYJerk = j;
    }
    
    public CarPhysics(double hor, double vert, double v, double a)
    {
        x = hor;
        y = vert;
        myXVelocity = v;
        myXAcceleration = a;
        myXJerk = 0;
        myYVelocity = v;
        myYAcceleration = a;
        myYJerk = 0;
    }
    
    public void setTick(int t)
    {
        tickCount = t;
    }
    
    public void setXJerk(double j)
    {
        myXJerk = j;
    }
    
    public void setXAcceleration(double a)
    {
        myXAcceleration = a;
    }
    
    public void setXVelocity(double v)
    {
        myXVelocity = v;
    }
    
    public double xAcceleration()
    {
        return myXJerk*tickCount;
    }
    
    public double xVelocity()
    {
        if (myXJerk != 0)
        {
            return xAcceleration()*tickCount;
        }
        else
        {
            return myXAcceleration*tickCount;   
        }
    }
    
    public void setYJerk(double j)
    {
        myYJerk = j;
    }
    
    public void setYAcceleration(double a)
    {
        myYAcceleration = a;
    }
    
    public void setYVelocity(double v)
    {
        myYVelocity = v;
    }
    
    public double yAcceleration()
    {
        return myYJerk*tickCount;
    }
    
    public double yVelocity()
    {
        if (myYJerk != 0)
        {
            return yAcceleration()*tickCount;
        }
        else
        {
            return myYAcceleration*tickCount;   
        }
    }
    
    public void position()
    {
        x = 0.5*(xVelocity()*tickCount + 0.5*xAcceleration()*tickCount*tickCount);
        y = 0.5*(yVelocity()*tickCount + 0.5*yAcceleration()*tickCount*tickCount);
    }
    
}
