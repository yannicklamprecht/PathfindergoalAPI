package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding;


import net.minecraft.server.v1_12_R1.PathfinderGoal;


/**
 * Created by Yannick on 30.11.2016.
 */
public class CraftPathfinderGoalWrapper extends PathfinderGoal {

    private com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal;

    public CraftPathfinderGoalWrapper(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        this.pathfinderGoal = pathfinderGoal;
    }

    @Override
    public boolean a() {
        return pathfinderGoal.shouldExecute();
    }

    @Override
    public boolean b() {
        return pathfinderGoal.shouldTerminate();
    }

    @Override
    public void c() {
        // setup
        pathfinderGoal.init();
    }

    @Override
    public void d() {
        // onFinish
        pathfinderGoal.reset();
    }

    @Override
    public void e() {
        // executeUpdate
        pathfinderGoal.execute();
    }
}





