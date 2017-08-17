package org.bukkit.pathfinding.goals;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Insentient;
import org.bukkit.pathfinding.PathfinderGoal;
import org.bukkit.pathfinding.PathfinderManager;
import org.bukkit.pathfinding.PathfinderPlayer;

public class PathfinderGoalSteerEntity implements PathfinderGoal {


    private PathfinderPlayer pathfinderPlayer;
    private PathfinderManager pathfinderManager;
    private Insentient pathfinderGoalEntity;
    private Player bukkitPlayer;

    public PathfinderGoalSteerEntity(PathfinderManager pathfinderManager, Insentient pathfinderGoalEntity) {
        this.pathfinderManager = pathfinderManager;
        this.pathfinderGoalEntity = pathfinderGoalEntity;
    }


    @Override
    public boolean isInterruptible() {
        return true;
    }

    @Override
    public boolean shouldInitExecute() {
        return pathfinderPlayer == null && pathfinderGoalEntity.getBukkitEntity().getPassengers().size() > 0;
    }

    @Override
    public void initExecute() {

        org.bukkit.entity.Entity entity = pathfinderGoalEntity.getBukkitEntity().getPassengers().get(0);
        if (entity instanceof Player) {
            this.bukkitPlayer = (Player) entity;
            this.pathfinderPlayer = pathfinderManager.getPathfinderPlayer(bukkitPlayer);
        }
    }

    @Override
    public void executeUpdate() {
        this.pathfinderGoalEntity.getControllerMove().move(pathfinderPlayer.getMotionForward(), pathfinderPlayer.getMotionSideward());
        Location location = bukkitPlayer.getLocation();

        this.pathfinderGoalEntity.setRotation(location.getYaw(), location.getYaw());
    }

    @Override
    public boolean shouldExecute() {
        return pathfinderGoalEntity != null && pathfinderPlayer.getPlayer().getVehicle() != null && pathfinderGoalEntity != null;
    }

    @Override
    public void reset() {
        this.pathfinderPlayer = null;
        this.bukkitPlayer = null;
    }

    @Override
    public void move() {

        Location blockInDirection = bukkitPlayer.getLocation().add(bukkitPlayer.getLocation().getDirection().setY(0).normalize().multiply(1));

        if (blockInDirection.getBlock().getType().isSolid() && !blockInDirection.add(0, 1, 0).getBlock().getType().isSolid()) {
            pathfinderGoalEntity.getControllerJump().jump();
        }

    }
}