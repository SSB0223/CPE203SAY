import processing.core.PImage;

import java.util.*;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public abstract class Entity {
    private final String id;
    private Point position;
    private List<PImage> images;

    private int imageIndex;


    public Entity(String id, Point position, List<PImage> images) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }

    String getId()
    {
        return id;
    }

    Point getPosition()
    {
        return position;
    }

    List<PImage> getImages() {return images; }

    void setImages(List<PImage> images) { this.images = images;}

    void setPosition(Point position)
    {
        this.position = position;
    }

    void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    PImage getCurrentImage()
    {
        return images.get((this).imageIndex);
    }


//    public int getAnimationPeriod() {
//        switch (this.kind) {
//            case DUDE_FULL:
//            case DUDE_NOT_FULL:
//            case OBSTACLE:
//            case FAIRY:
//            case SAPLING:
//            case TREE:
//                return this.animationPeriod;
//            default:
//                throw new UnsupportedOperationException(
//                        String.format("getAnimationPeriod not supported for %s",
//                                this.kind));
//        }
//    }
//
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }
//
//    public void executeSaplingActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        this.health++;
//        if (!transformPlant(world, scheduler, imageStore)) {
//            scheduler.scheduleEvent(this,
//                    Action.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
//
//    public void executeTreeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        if (!transformPlant(world, scheduler, imageStore)) {
//
//            scheduler.scheduleEvent(this,
//                    Action.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
//
//    public void executeFairyActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> fairyTarget =
//                world.findNearest(this.position, new ArrayList<>(Arrays.asList(EntityKind.STUMP)));
//
//        if (fairyTarget.isPresent()) {
//            Point tgtPos = fairyTarget.get().position;
//
//            if (moveToFairy(world, fairyTarget.get(), scheduler)) {
//                Entity sapling = tgtPos.createSapling("sapling_" + this.id,
//                        imageStore.getImageList(Functions.SAPLING_KEY));
//
//                world.addEntity(sapling);
//                sapling.scheduleActions(scheduler, world, imageStore);
//            }
//        }
//
//        scheduler.scheduleEvent(this,
//                Action.createActivityAction(this, world, imageStore),
//                this.actionPeriod);
//    }
//
//    public void executeDudeNotFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> target =
//                world.findNearest(this.position, new ArrayList<>(Arrays.asList(EntityKind.TREE, EntityKind.SAPLING)));
//
//        if (!target.isPresent() || !moveToNotFull(world,
//                target.get(),
//                scheduler)
//                || !transformNotFull(world, scheduler, imageStore)) {
//            scheduler.scheduleEvent(this,
//                    Action.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
//
//    public void executeDudeFullActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Entity> fullTarget =
//                world.findNearest(this.position, new ArrayList<>(Arrays.asList(EntityKind.HOUSE)));
//
//        if (fullTarget.isPresent() && moveToFull(world,
//                fullTarget.get(), scheduler)) {
//            transformFull(world, scheduler, imageStore);
//        } else {
//            scheduler.scheduleEvent(this,
//                    Action.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
//
//    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.resourceCount >= this.resourceLimit) {
//            Entity miner = this.position.createDudeFull(this.id, this.actionPeriod,
//                    this.animationPeriod,
//                    this.resourceLimit,
//                    this.images);
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(miner);
//            miner.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }
//
//    public void transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        Entity miner = this.position.createDudeNotFull(this.id, this.actionPeriod,
//                this.animationPeriod,
//                this.resourceLimit,
//                this.images);
//
//        world.removeEntity(this);
//        scheduler.unscheduleAllEvents(this);
//
//        world.addEntity(miner);
//        miner.scheduleActions(scheduler, world, imageStore);
//    }
//
//    public boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.kind == EntityKind.TREE) {
//            return transformTree(world, scheduler, imageStore);
//        } else if (this.kind == EntityKind.SAPLING) {
//            return transformSapling(world, scheduler, imageStore);
//        } else {
//            throw new UnsupportedOperationException(
//                    String.format("transformPlant not supported for %s", this));
//        }
//    }
//
//    public boolean transformTree(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.health <= 0) {
//            Entity stump = this.position.createStump(this.id,
//                    imageStore.getImageList(Functions.STUMP_KEY));
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(stump);
//
//            return true;
//        }
//
//        return false;
//    }
//
//    public boolean transformSapling(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
//        if (this.health <= 0) {
//            Entity stump = this.position.createStump(this.id,
//                    imageStore.getImageList(Functions.STUMP_KEY));
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(stump);
//
//            return true;
//        } else if (this.health >= this.healthLimit) {
//            Entity tree = this.position.createTree("tree_" + this.id,
//                    Functions.getNumFromRange(Functions.TREE_ACTION_MAX, Functions.TREE_ACTION_MIN),
//                    Functions.getNumFromRange(Functions.TREE_ANIMATION_MAX, Functions.TREE_ANIMATION_MIN),
//                    Functions.getNumFromRange(Functions.TREE_HEALTH_MAX, Functions.TREE_HEALTH_MIN),
//                    imageStore.getImageList(Functions.TREE_KEY));
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(tree);
//            tree.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }
//
//    public boolean moveToFairy(WorldModel world, Entity target, EventScheduler scheduler) {
//        if (this.position.adjacent(target.position)) {
//            world.removeEntity(target);
//            scheduler.unscheduleAllEvents(target);
//            return true;
//        } else {
//            Point nextPos = nextPositionFairy(world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant(nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents(occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }
//
//    public boolean moveToNotFull(WorldModel world, Entity target, EventScheduler scheduler) {
//        if (this.position.adjacent(target.position)) {
//            this.resourceCount += 1;
//            target.health--;
//            return true;
//        } else {
//            Point nextPos = nextPositionDude(world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant(nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents(occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }
//
//    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler) {
//        if (this.position.adjacent(target.position)) {
//            return true;
//        } else {
//            Point nextPos = nextPositionDude(world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant(nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents(occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }
//
//    public Point nextPositionFairy(WorldModel world, Point destPos) {
//        int horiz = Integer.signum(destPos.getX() - this.position.getX());
//        Point newPos = new Point(this.position.getX() + horiz, this.position.getY());
//
//        if (horiz == 0 || world.isOccupied(newPos)) {
//            int vert = Integer.signum(destPos.getY() - this.position.getY());
//            newPos = new Point(this.position.getX(), this.position.getY() + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }
//
//    public Point nextPositionDude(WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.getX() - this.position.getX());
//        Point newPos = new Point(this.position.getX() + horiz, this.position.getY());
//
//        if (horiz == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
//            int vert = Integer.signum(destPos.getY() - this.position.getY());
//            newPos = new Point(this.position.getX(), this.position.getY() + vert);
//
//            if (vert == 0 || world.isOccupied(newPos) &&  world.getOccupancyCell(newPos).kind != EntityKind.STUMP) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }
//
//    public static PImage getCurrentImage(Object entity) {
//        if (entity instanceof Background) {
//            return ((Background)entity).getImages().get(
//                    ((Background)entity).getImageIndex());
//        }
//        else if (entity instanceof Entity) {
//            return ((Entity)entity).getImages().get(((Entity)entity).getImageIndex());
//        }
//        else {
//            throw new UnsupportedOperationException(
//                    String.format("getCurrentImage not supported for %s",
//                            entity));
//        }
//    }
//
//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
//        switch (this.kind) {
//            case DUDE_FULL:
//                scheduler.scheduleEvent(this,
//                        Action.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Action.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case DUDE_NOT_FULL:
//                scheduler.scheduleEvent(this,
//                        Action.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Action.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case OBSTACLE:
//                scheduler.scheduleEvent(this,
//                        Action.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case FAIRY:
//                scheduler.scheduleEvent(this,
//                        Action.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Action.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case SAPLING:
//                scheduler.scheduleEvent(this,
//                        Action.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Action.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case TREE:
//                scheduler.scheduleEvent(this,
//                        Action.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Action.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            default:
//        }
//    }
//
//    public EntityKind getKind() {
//        return kind;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public Point getPosition() {
//        return position;
//    }
//
//    public void setPosition(Point position) {
//        this.position = position;
//    }
//
//    public List<PImage> getImages() {
//        return images;
//    }
//
//    public int getImageIndex() {
//        return imageIndex;
//    }
//
//    public int getActionPeriod() {
//        return actionPeriod;
//    }
//
//    public int getHealth() {
//        return health;
//    }
}










