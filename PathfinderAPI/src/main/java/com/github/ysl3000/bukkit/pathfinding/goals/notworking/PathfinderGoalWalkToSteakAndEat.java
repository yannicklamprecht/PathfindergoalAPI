package com.github.ysl3000.bukkit.pathfinding.goals.notworking;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;

import java.util.Comparator;
import java.util.Optional;

/**
 * Created by ysl3000 on 09.12.16.
 * todo check if working else update
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
    public boolean shouldExecute() {
        return target != null && !target.isDead() && distanceComperator.getDistance(target) > 0;
    }

    /**
     * Whether the goal should Terminate
     *
     * @return true if should terminate
     */
    @Override
    public boolean shouldTerminate() {
        return target == null;
    }

    /**
     * Runs initially and should be used to setUp goalEnvironment
     * Condition needs to be defined thus your code in it isn't called
     */
    @Override
    public void init() {
        Optional<Entity> nearestStack = pathfinderGoalEntity.getBukkitEntity().getNearbyEntities(distance, distance, distance).stream().filter(e -> e.getType() == EntityType.DROPPED_ITEM).min(distanceComperator);

        nearestStack.ifPresent(entity -> target = entity);
        execute();
    }

    /**
     * Is called when {@link #shouldExecute()} returns true
     */
    @Override
    public void execute() {
        pathfinderGoalEntity.getNavigation().moveTo(target, 2);
        pathfinderGoalEntity.getControllerJump().jump();
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

    private class DistanceComperator implements Comparator<Entity> {

        @Override
        public int compare(Entity o1, Entity o2) {
            return Double.compare(getDistance(o1), getDistance(o2));
        }

        private double getDistance(Entity entity) {
            return entity.getLocation().distanceSquared(pathfinderGoalEntity.getBukkitEntity().getLocation());
        }

    }

}