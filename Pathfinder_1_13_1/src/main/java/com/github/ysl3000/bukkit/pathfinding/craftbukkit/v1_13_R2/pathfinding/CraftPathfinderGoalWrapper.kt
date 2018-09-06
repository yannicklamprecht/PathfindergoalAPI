package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding


import net.minecraft.server.v1_13_R2.PathfinderGoal


/**
 * Created by Yannick on 30.11.2016.
 */
class CraftPathfinderGoalWrapper(
        private val pathfinderGoal: com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal) : PathfinderGoal() {

    override fun a(): Boolean {
        return pathfinderGoal.shouldExecute()
    }

    override fun b(): Boolean {
        return pathfinderGoal.shouldTerminate()
    }

    override fun c() {
        // setup
        pathfinderGoal.init()
    }

    override fun d() {
        // onFinish
        pathfinderGoal.reset()
    }

    override fun e() {
        // executeUpdate
        pathfinderGoal.execute()
    }
}





