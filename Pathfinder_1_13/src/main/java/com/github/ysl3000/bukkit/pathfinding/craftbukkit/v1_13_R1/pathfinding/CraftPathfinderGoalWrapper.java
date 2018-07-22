package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding;


import net.minecraft.server.v1_13_R1.PathfinderGoal;


/**
 * Created by Yannick on 30.11.2016.
 */
public class CraftPathfinderGoalWrapper extends PathfinderGoal {


    /*

        goal setup
        1. shouldInitExecute and -> initExecute -> reset
        2. should not execute -> reset
        goal tick
        3. move



     */


    private com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal;

    public CraftPathfinderGoalWrapper(com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal pathfinderGoal) {
        this.pathfinderGoal = pathfinderGoal;
    }

    @Override
    public boolean a() {
        return pathfinderGoal.shouldTerminate();

    }

    @Override
    public boolean b() {
        return pathfinderGoal.shouldExecute();
    }

    @Override
    public void c() {
        // setup
        pathfinderGoal.init();
    }

    @Override
    public void d() {
        // reset
        pathfinderGoal.reset();
    }

    @Override
    public void e() {
        // executeUpdate
        pathfinderGoal.execute();
    }
}





