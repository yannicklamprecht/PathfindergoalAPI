package org.bukkit.craftbukkit.v1_12_R1.pathfinding;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftInsentient;
import org.bukkit.entity.*;
import org.bukkit.entity.Insentient;
import org.bukkit.pathfinding.PathfinderManager;
import org.bukkit.pathfinding.PathfinderPlayer;

/**
 * Created by ysl3000
 */
public class CraftPathfinderManager implements PathfinderManager {
    @Override
    public Insentient getPathfindeGoalEntity(Creature creature) {
        return null;
    }

    @Override
    public Insentient getPathfinderGoalEntity(Flying flying) {
        return new CraftInsentient(flying);
    }

    @Override
    public Insentient getPathfinderGoalEntity(Ambient ambient) {
        return new CraftInsentient(ambient);
    }

    @Override
    public Insentient getPathfinderGoalEntity(Slime slime) {
        return new CraftInsentient(slime);
    }

    @Override
    public Insentient getPathfinderGoalEntity(EnderDragon enderDragon) {
        return new CraftInsentient(enderDragon);
    }

    @Override
    public PathfinderPlayer getPathfinderPlayer(Player player) {
        return new CraftPathfinderPlayer(player);
    }
}
