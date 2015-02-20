package ca.javajesus.game.entities.vehicles;

public class CarPhysics {

	private int tickCount;
	public double x, y;
	private double xVelocity, yVelocity;
	private double xAcceleration, yAcceleration;
	private double xJerk, yJerk;

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
		/*
		 * if (xVelocity >= 2) { xVelocity = 2; // xAcceleration = 0; xVelocity
		 * -= 2; } else if (xVelocity <= -2) { xVelocity = -2; // xAcceleration
		 * = 0; xVelocity += 2; } else
		 */
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

	public void updateYVelocity() {
		/*
		 * if (yVelocity >= 2) { yVelocity = 2; yVelocity -= 2; } else if
		 * (yVelocity <= -2) { yVelocity = -2; yVelocity += 2; } else
		 */
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

}
