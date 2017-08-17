package org.bukkit.pathfinding.goals;

import org.bukkit.entity.Insentient;
import org.bukkit.pathfinding.PathfinderGoal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;

import java.util.Comparator;
import java.util.Optional;

/**
 * Created by ysl3000 on 09.12.16.
 */
public class PathfinderGoalWalkToSteakAndEat implements PathfinderGoal {

    private Entity target;
    private Insentient pathfinderGoalEntity;
    private double distance;

    private DistanceComperator distanceComperator;

    public PathfinderGoalWalkToSteakAndEat(Insentient pathfinderGoalEntity, double distance) {
        this.pathfinderGoalEntity = pathfinderGoalEntity;
        this.distance = distance;
        this.distanceComperator = new DistanceComperator();
    }


    @Override
    public boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldExecute() {
        return target != null && !target.isDead() && distanceComperator.getDistance(target) > 0;
    }

    @Override
    public boolean shouldInitExecute() {
        return target == null;
    }

    @Override
    public void initExecute() {
        Optional<Entity> nearestStack = pathfinderGoalEntity.getBukkitEntity().getNearbyEntities(distance, distance, distance).stream().filter(e -> e.getType() == EntityType.DROPPED_ITEM).sorted(distanceComperator).findFirst();


        if (nearestStack.isPresent()) {
            target = nearestStack.get();
        }
        executeUpdate();
    }

    @Override
    public void executeUpdate() {
        pathfinderGoalEntity.getNavigation().moveTo(target, 2);
    }

    @Override
    public void reset() {

        this.pathfinderGoalEntity.getNavigation().clearPathEntity();

        if (this.target != null) {
            if (this.target instanceof Item) {
                Entity buEntity = this.pathfinderGoalEntity.getBukkitEntity();
                if (buEntity instanceof LivingEntity)

                    ((LivingEntity) buEntity).getEquipment().setItemInMainHand(((Item) this.target).getItemStack());
                this.target.remove();
                this.target = null;
            }
        }
    }

    @Override
    public void move() {
        pathfinderGoalEntity.getControllerJump().jump();
    }

    private class DistanceComperator implements Comparator<Entity> {


        @Override
        public int compare(Entity o1, Entity o2) {
            if (getDistance(o1) > getDistance(o2)) {
                return 1;
            } else if (getDistance(o1) == getDistance(o2)) {
                return 0;
            } else {
                return -1;
            }
        }

        private double getDistance(Entity entity) {
            return entity.getLocation().distanceSquared(pathfinderGoalEntity.getBukkitEntity().getLocation());
        }

    }

}