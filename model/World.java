package asteroids.model;

import java.util.*;
import java.util.stream.Collectors;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of Worlds, involving size, evolvement and interactions between entities within each world
 * @invar	No entities within the world are significantly overlapping.
 * 			| for (Entity entity: this.getEntitySet())
 * 				no other entity significantly overlaps entity.
 * @invar	The sizes of the World are greater than 0 
 * 			|this.getSizeX() >=0 && this.getSizeY() >= 0
 * @invar 	|this.getSizeX() <= Double.MAX_VALUE && this.getSizeY() <= Double.MAX_VALUE
 * @author Elisabeth Tu (2eBach CW-WTK) & Tim Lebailly (2eBach CW-ELT)
 * @repository https://elisabethtu@bitbucket.org/springtimeli/ogp.git
 *
 */

public class World {
	/**
	 * @param sizeX
	 * @param sizeY
	 * @effect setSize(sizeX, sizeY)
	 */
	public World(double sizeX, double sizeY) {
		setSize(sizeX, sizeY);
	}
	
	
	/**
	 * The horizontal size of the world.
	 */
	double sizeX;
	
	/**
	 * The vertical size of the world.
	 */
	double sizeY;
	
	/**
	 * @return @seeImplementation
	 */
	public double getSizeX() {
		return this.sizeX;
	}
	
	/**
	 * @return @seeImplementation
	 */
	public double getSizeY() {
		return this.sizeY;
	}
	
	/**
	 * @param	sizeX
	 * @param	sizeY
	 * @post	|if (Double.MAX_VALUE >= sizeX >= 0)
	 * 		 	|then new.sizeX == sizeX
	 * 			|else new.sizeX == 0
	 * @post	|if (Double.MAX_VALUE >= sizeY >= 0)
	 * 		 	|then new.sizeY == sizeY
	 * 			|else new.sizeY == 0
	 */
	public void setSize(double sizeX, double sizeY) {
		if (sizeX >= 0 && sizeX <= Double.MAX_VALUE)
			setSizeX(sizeX);
		else
			setSizeX(0);
		if (sizeY >= 0 && sizeY <= Double.MAX_VALUE)
			setSizeY(sizeY);
		else
			setSizeY(0);
	}

	/**
	 * 
	 * @param sizeX
	 * @post  @seeImplementation
	 */
	@Raw
	private void setSizeX(double sizeX) {
		this.sizeX = sizeX;
	}
	
	/**
	 * 
	 * @param sizeY
	 * @post  @seeImplementation
	 */
	@Raw
	private void setSizeY(double sizeY) {
		this.sizeY = sizeY;
	}
	
	/**
	 * A variable referencing the state of the world.
	 */
	private boolean isTerminated = false;

	/**
	 * Method that terminates the world and all entities within.
	 *@post	for entity in entitySet
	 *			entity.getWorld() == null
	 *@post	new.isTerminated() == true
	 */
	public void terminateWorld() throws IllegalStateException {
		if (! entityMap.isEmpty()) {
			Set<Entity> entitySet = this.getEntitySet();
			for (Entity entity : entitySet) {
				entity.removeWorld(this);
			}
		}
		this.entityMap.clear();
		this.isTerminated = true;
	}
	
	/**
	 * @return @seeImplementation
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * A variable hold all of the entities present in the world with the position as key.
	 */
	private HashMap<Vector2, Entity> entityMap = new HashMap<Vector2, Entity>();
	
	
	/**
	 * Adds an entity to the world
	 * @param	entity
	 * @post	|this.getEntityAt(entity.getPosition()) == entity
	 * @throws	IllegalStateException
	 * 			|!entity.canGoToWorld(this)
	 * @throws	NullPointerException
	 */
	public void addEntity(Entity entity) throws IllegalStateException,
			NullPointerException {
		if (!entity.canGoToWorld(this))
			throw new IllegalStateException();
		
		this.entityMap.put(entity.getPosition(), entity);
		entity.setWorld(this);
	}
	
	/**
	 * Updates the keys of the entityMap.
	 * @param 	newPos
	 * @param	oldPos
	 * @param	entity
	 * @post	| getEntityAt(newPos) = entity
	 * 
	 */
	protected void updateEntityMap(Vector2 newPos , Entity entity) {
		this.entityMap.remove(entity.getPosition());
		this.entityMap.put(newPos, entity);
		//this.printEntities();
		
	}
	
	/**
	 * Remove an entity from the map of entities in the world.
	 * @param	entity
	 * @throws	IllegalStateException
	 * 			|!entity.isPartOfWorld(this)
	 * @throws	NullPointerException
	 * @post	|!entity.isPartOfWorld(this)
	 */
	public void removeEntity(Entity entity) throws IllegalStateException,
			NullPointerException {
		if (!entity.isPartOfWorld(this))
			throw new IllegalStateException();
		else {
			entity.removeWorld(this);
			this.entityMap.remove(entity.getPosition());
			}
	}
	
	/**
	 * Return the map of entities present in the world.
	 * @return @seeImplementation
	 */
	public HashMap<Vector2, Entity> getEntityMap() {
		return this.entityMap;
	}
	
	/**
	 * Return the entity at the given position.
	 * @param position
	 * @return @seeImplementation
	 */
	public Entity getEntityAt(Vector2 position) {
		return this.entityMap.get(position);
	}
	
	/**
	 * Add a set of entities to the world.
	 * @param	entitySet
	 * @post 	@seeImplementation
	 */
	public void setSetInWorld(Set<Entity> entitySet) throws IllegalStateException {
		if (entitySet != null) {
			for (Entity entity: entitySet) {
				this.addEntity(entity);
			}
		}
	}
	
	/**
	 * Add a given set of ships and a set of bullets to the world.
	 * @param shipSet
	 * @param bulletSet
	 * @post 	@seeImplementation
	 */
	public void setSetInWorld(Set<Ship> shipSet, Set<Bullet> bulletSet) {
		for (Ship ship: shipSet) {
			this.addEntity(ship);
		}
		for (Bullet bullet: bulletSet) {
			this.addEntity(bullet);
		}
	}
	
	
	/**
	 * Return a set of all the ships present in the world.
	 * @return | forall (ships in getEntityMap() )
	 * 				set.add(ship)
	 * 			return set
	 * 				
	 */
	public Set<Ship> getShipSetInWorld() {
		return this.getEntityMap().values().stream().filter(x -> x instanceof Ship).map(x -> ((Ship)x))
				.collect(Collectors.toSet());
	}
	
	/**
	 Return a set of all the asteroids present in the world.
	 * @return | forall (asteroids in getEntityMap() )
	 * 				set.add(asterois)
	 * 			return set
	 */
	public Set<Asteroid> getAsteroidSetInWorld() {
		return this.getEntityMap().values().stream().filter(x -> x instanceof Asteroid).map(x -> ((Asteroid)x))
				.collect(Collectors.toSet());
	}
	
	/**
	 * Return a set of all the planetoid present in the world.
	 * @return | forall (planetoids in getEntityMap() )
	 * 				set.add(planetoid)
	 * 			return set
	 */
	public Set<Planetoid> getPlanetoidSetInWorld() {
		return this.getEntityMap().values().stream().filter(x -> x instanceof Planetoid).map(x -> ((Planetoid)x))
				.collect(Collectors.toSet());
	}
	
	/**
	 * Return a set of all the minorPlanets present in the world.
	 * @return | forall (minorPlanets in getEntityMap() )
	 * 				set.add(minorPlanet)
	 * 			return set
	 */
	public Set<MinorPlanet> getMinorPlanetSetInWorld() {
		return this.getEntityMap().values().stream().filter(x -> x instanceof MinorPlanet).map(x -> ((MinorPlanet)x))
				.collect(Collectors.toSet());
	}
	
	/**
	 *  Return a set of all the bullets present in the world.
	 * @return | forall (bullets in getEntityMap() )
	 * 				set.add(bullet)
	 * 			return set
	 */
	public Set<Bullet> getBulletSetInWorld() {
		Set<Bullet> bulletSet = new HashSet<Bullet>();
		for (Entity entity: entityMap.values()) {
			if (entity instanceof Bullet)
				bulletSet.add((Bullet) entity);
		}
		return bulletSet;
	}
	
	/**
	 * Returns a set of all the entities present in the world
	 * @return @seeImplementation
	 */
	public Set<Entity> getEntitySet() {
		return new HashSet<Entity>(this.entityMap.values());
	}
	
	/**
	 * Returns the position where the next collision in this world will take place
	 * @param	collision
	 * @return 	@seeImplementation
	 */
	public double[] getPositionNextCollision(Collision collision) {
		Entity entity = this.getEntityMap().get(collision.getEntity1().getPosition());
		if (collision.getBoundary() != 4) {
			return entity.getCollisionPositionBoundary(collision);
		}
		return entity.getCollisionPosition(collision);
	}
	
	/**
	 * GEEN DOCUMENTATIE GEVRAAGD
	 * A method that calculates the next collision that will occur.
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	public Collision getNextCollision() throws NullPointerException, 
			IllegalStateException, IllegalArgumentException {
		Set<Entity> entitySet = this.getEntitySet();
		Set<Entity> entitySetSeen = new HashSet<Entity>();
		Collision collision = new Collision();
		
		for (Entity entity : entitySet) {
			Collision currentCollision = entity.getCollisionBoundary();
			if (collision.getTime() > currentCollision.getTime())
				collision = currentCollision;
		}
		Iterator<Entity> iter = entitySet.iterator();
		
		while (iter.hasNext()) {
			Entity entity = iter.next();
			if (!entitySetSeen.isEmpty()) {
				for (Entity entityInSeen : entitySetSeen) {
					Collision currentCollision = entity.getCollisionWithEntity(entityInSeen);
					if (collision.getTime() >= currentCollision.getTime())
						collision = currentCollision;
				}
			}
			entitySetSeen.add(entity);
		}
		return collision;	
	}
	
	
	/**
	 * GEEN DOCUMENTATIE GEVRAGD
	 *  Evolves the world with the given time, without collisions.
	 * @param 	time
	 * @param 	collisionListener
	 * @throws 	IllegalPositionException
	 * @throws 	NullPointerException
	 * @post	|if ship.isThrusterOn()
	 * 				ship.newSpeed = ship.thrust(ship.getAcceleration())
	 * @post 	| for entity in this.getEntitySet()
	 * 				new.entity.getPosition() = entity.getOldPostion() + time* entity.getNewspeed()
	 */
	private void evolveWithoutCollision(double time) throws IllegalPositionException, 
			NullPointerException {
		Set<Entity> entitySet = this.getEntitySet();
		for (Entity entity : entitySet) {
			entity.evolve(time);
		}
	}
	
	
	/**
	 * GEEN DOCUMENTATIE GEVRAAGD
	 * Evolves the world with a given time.
	 * @param time
	 * @param collisionListener
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * @throws IllegalPositionException
	 */
	public void evolve(double time, CollisionListener collisionListener) throws IllegalArgumentException, 
			NullPointerException, IllegalPositionException {
		if (Double.isNaN(time) || Double.isInfinite(time) || time < 0)
			throw new IllegalArgumentException();
		
		Collision nextCollision = this.getNextCollision();
		double timeNextCollision = nextCollision.getTime();
		if (Double.isInfinite(timeNextCollision))
			return;
		
		if (time < timeNextCollision)
			this.evolveWithoutCollision(time);

		else {
			this.evolveWithoutCollision(timeNextCollision);
			nextCollision = this.getNextCollision();
			double[] pos = this.getPositionNextCollision(nextCollision);
			
			Entity entityThatCollides1 = nextCollision.getEntity1();
			if (nextCollision.getBoundary() != 4) {
				entityThatCollides1.resolve(nextCollision.getBoundary());
				if (collisionListener != null)
					collisionListener.boundaryCollision(entityThatCollides1, pos[0], pos[1]);
			}
			
			else {
				Entity entityThatCollides2 = nextCollision.getEntity2();
				entityThatCollides1.resolve(entityThatCollides2);
				if (collisionListener != null)
					collisionListener.objectCollision(entityThatCollides1, entityThatCollides2, pos[0], pos[1]);
			}
			
			this.evolve(time - timeNextCollision, collisionListener);
		}
	}
	
	@Override
	public String toString() {
		if (this.getEntitySet().isEmpty())
			return "Empty world";
		String text = "(";
		Iterator<Entity> iter = this.getEntitySet().iterator();
		while (iter.hasNext()) {
			text += iter.next().toString();
			if (!iter.hasNext())
				break;
			text += ", ";
		}
		text += ")";
		return text;
	}
		
	
	
}
