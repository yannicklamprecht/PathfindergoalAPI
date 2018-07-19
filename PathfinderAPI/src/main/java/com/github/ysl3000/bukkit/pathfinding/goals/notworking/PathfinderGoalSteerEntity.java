package com.github.ysl3000.bukkit.pathfinding.goals.notworking;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

public class PathfinderGoalSteerEntity implements PathfinderGoal {


    private PathfinderPlayer pathfinderPlayer;
    private PathfinderManager pathfinderManager;
    private Insentient pathfinderGoalEntity;

    public PathfinderGoalSteerEntity(PathfinderManager pathfinderManager, Insentient pathfinderGoalEntity) {
        this.pathfinderManager = pathfinderManager;
        this.pathfinderGoalEntity = pathfinderGoalEntity;
    }


    @Override
    public boolean shouldExecute() {
        return pathfinderPlayer == null || getSteeringPlayer().isPresent();
    }

    @Override
    public boolean shouldTerminate() {
        return getSteeringPlayer().isPresent();
    }

    @Override
    public void init() {

        if (pathfinderPlayer == null) {
            getSteeringPlayer().ifPresent(player -> this.pathfinderPlayer = pathfinderManager.getPathfinderPlayer(player));
        }

    }

    private Optional<Player> getSteeringPlayer() {
        return pathfinderGoalEntity.getBukkitEntity().getPassengers().stream().filter(Player.class::isInstance).map(Player.class::cast).findAny();
    }


    @Override
    public void execute() {

        if (pathfinderPlayer == null) return;

        Player player = pathfinderPlayer.getPlayer();

        Location blockInDirection = player.getLocation().add(player.getLocation().getDirection().setY(0).normalize().multiply(1));

        if (blockInDirection.getBlock().getType().isSolid() && !blockInDirection.add(0, 1, 0).getBlock().getType().isSolid()) {
            pathfinderGoalEntity.getControllerJump().jump();
        }

        this.pathfinderGoalEntity.getControllerMove().move(pathfinderPlayer.getMotionForward(), pathfinderPlayer.getMotionSideward());
        Location location = player.getLocation();

        this.pathfinderGoalEntity.setRotation(location.getYaw(), location.getYaw());
    }

    @Override
    public void reset() {
        this.pathfinderPlayer = null;
    }
}