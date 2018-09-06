package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding

import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer
import org.bukkit.entity.Player

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer

import net.minecraft.server.v1_13_R2.EntityPlayer

/**
 * Created by ysl3000
 */
class CraftPathfinderPlayer private constructor(private val entityPlayer: EntityPlayer) : PathfinderPlayer {

    val STILL = -0.0784000015258789

    constructor(player: Player) : this((player as CraftPlayer).handle) {}

    override fun getPlayer(): Player {
        return entityPlayer.bukkitEntity
    }

    override fun getRelativeMotionX(): Double {
        return entityPlayer.motX
    }

    override fun getRelativeMotionY(): Double {
        return entityPlayer.motY
    }

    override fun getRelativeMotionZ(): Double {
        return entityPlayer.motZ
    }

    override fun getRelativeMotionYaw(): Float {
        return entityPlayer.yaw
    }

    override fun getRelativeMotionPitch(): Float {
        return entityPlayer.pitch
    }

    override fun getMotionForward(): Float {
        return entityPlayer.bh
    }

    override fun getMotionSideward(): Float {
        return entityPlayer.bj
    }

    override fun isJumping(): Boolean {
        return entityPlayer.motY > STILL
    }


}
