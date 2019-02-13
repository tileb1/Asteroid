package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.*;
import asteroids.part2.CollisionListener;
import asteroids.part3.programs.IProgramFactory;
import asteroids.program.Program;
import asteroids.util.ModelException;

public class Facade implements asteroids.part3.facade.IFacade {
	

	@Override
	public Ship createShip() throws ModelException {
		return new Ship();
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass)
			throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, orientation, radius, mass);
		}
		catch (IllegalRadiusException exc) {
			throw new ModelException("Illegal radius");
		}
		catch (IllegalPositionException exc) {
			throw new ModelException("Illegal position");
		}
		catch (AssertionError exc) {
			throw new ModelException("Assertion Fail");
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return new double[]{ship.getPosition().getX(),ship.getPosition().getY()};
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return new double[]{ship.getSpeed().getX(),ship.getSpeed().getY()};
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		try {
			ship.move(dt);
		}
		catch (IllegalDurationException exc) {
			throw new ModelException("Illegal Duration");
		}
		catch (IllegalPositionException exc) {
			throw new ModelException("Illegal Position");
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
		
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
		
		
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getCollisionWithEntity(ship2).getTime();
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Overlap");
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			Collision collision = ship1.getCollisionWithEntity(ship2);
			return ship1.getCollisionPosition(collision);
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Overlap");
		}
	}


	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getTotalMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.isThrusterOn();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if (active)
			ship.thrustOn();
		else
			ship.thrustOff();
		
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Bullet(x, y, xVelocity, yVelocity, radius);
		}
		catch (IllegalRadiusException exc) {
			throw new ModelException("IllegalRadius");
		}
		catch (IllegalPositionException exc) {
			throw new ModelException("Illegal Position");
		}
		
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return new double[] {bullet.getPosition().getX(), bullet.getPosition().getY()};
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return new double[] {bullet.getSpeed().getX(), bullet.getSpeed().getY()};
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getOwnerShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getFiredBy();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		return new World(width, height);
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminateWorld();
		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return new double[] {world.getSizeX(), world.getSizeY()};
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getShipSetInWorld();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getBulletSetInWorld();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addEntity(ship);
		}
		catch(NullPointerException exc) {
			throw new ModelException("Null");
		}
		catch(IllegalStateException exc) {
			throw new ModelException("State");
		}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {
			world.removeEntity(ship);
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
		
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.addEntity(bullet);
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.removeEntity(bullet);
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbOfBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.addBulletToShip(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException("Illegal Argument");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.addCollectionBulletsToShip(bullets);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException("Illegal Argument");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBullet(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException("Illegal Argument");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity)object).getCollisionBoundary().getTime();
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity)object).getCollisionPositionBoundary(((Entity)object).getCollisionBoundary());
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity)entity1).getCollisionWithEntity(((Entity)entity2)).getTime();
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			Collision collision = ((Entity)entity1).getCollisionWithEntity(((Entity)entity2));
			return ((Entity)entity1).getCollisionPosition(collision);
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		try {
			return world.getNextCollision().getTime();
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException("Illegal Argument");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getPositionNextCollision(world.getNextCollision());
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt, collisionListener);	
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException("Illegal Argument");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
		catch (IllegalPositionException exc) {
			throw new ModelException("Illegal Position");
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(new Vector2(x, y));
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntitySet();
	}

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return world.getAsteroidSetInWorld();
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
		world.addEntity(asteroid);
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		world.removeEntity(asteroid);
		
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return world.getPlanetoidSetInWorld();
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		try {
		world.addEntity(planetoid);
		}
		catch (IllegalStateException exc) {
			throw new ModelException("Illegal State");
		}
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		world.removeEntity(planetoid);
		
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		return new Asteroid(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
		
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		Vector2 position = asteroid.getPosition();
		return new double[] {position.getX(), position.getY()};
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		Vector2 speed = asteroid.getSpeed();
		return new double[] {speed.getX(), speed.getY()};
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		return new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.terminate();
		
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isTerminated();
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		Vector2 position = planetoid.getPosition();
		return new double[] {position.getX(), position.getY()};
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		Vector2 speed = planetoid.getSpeed();
		return new double[] {speed.getX(), speed.getY()};
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getTravelledDistance();
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getWorld();
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		try {
			ship.setProgram(program);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException("Illegal Argument");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
		
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		try {
			return ship.getProgram().executeProgram(dt);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException("Illegal Argument");
		}
		catch (NullPointerException exc) {
			throw new ModelException("Null");
		}
		catch (ClassCastException exc) {
			throw new ModelException("Illegal Casting");
		}
		catch (IndexOutOfBoundsException exc) {
			throw new ModelException("Index Out of Bound");
		}
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		return new ProgramFactory();
	}
}
