package com.github.ysl3000.bukkit.pathfinding.goals;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by 2008Choco
 */
public class PathfinderGoalGimmiCookie implements PathfinderGoal {

    private static final ItemStack COOKIE = new ItemStack(Material.COOKIE);
    private final Creature creature;
    private final Insentient pathfinderGoalEntity;
    private boolean isGenerous = true;
    private boolean hasGivenCookie = false;
    private List<Entity> nearbyEntities;
    private Player nearestPlayer;

    public PathfinderGoalGimmiCookie(Insentient pathfinderGoalEntity, Creature creature) {
        this.pathfinderGoalEntity = pathfinderGoalEntity;
        this.creature = creature;
    }

    @Override
    public boolean shouldExecute() {
        if (!isGenerous) return false;

        List<Entity> nearbyEntities = creature.getNearbyEntities(5, 5, 5);
        if (nearbyEntities.isEmpty()) return false;

        this.nearbyEntities = nearbyEntities;
        return nearbyEntities.stream().anyMatch(Player.class::isInstance);
    }

    @Override
    public boolean shouldTerminate() {
        return !hasGivenCookie;
    }

    @Override
    public void init() {
        this.nearestPlayer = getNearestPlayerFrom(nearbyEntities).orElse(null);
        this.nearbyEntities = null;
        this.creature.getEquipment().setItemInMainHand(COOKIE);
        this.creature.setTarget(nearestPlayer);
        this.pathfinderGoalEntity.getNavigation().moveTo(nearestPlayer);


    }

    @Override
    public void execute() {
        this.pathfinderGoalEntity.getControllerJump().jump();
        if (creature.getLocation().distanceSquared(nearestPlayer.getLocation()) <= 1) {
            this.creature.getWorld().dropItem(nearestPlayer.getLocation(), COOKIE);
            this.creature.getEquipment().setItemInMainHand(null);
            this.isGenerous = false;
            this.hasGivenCookie = true;
        }
    }

    @Override
    public void reset() {
        this.nearestPlayer = null;
        this.hasGivenCookie = false;
    }

    private Optional<Player> getNearestPlayerFrom(List<Entity> entities) {
        return entities.stream()
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .min(Comparator.comparingDouble(p -> creature.getLocation().distanceSquared(p.getLocation())));
    }

}