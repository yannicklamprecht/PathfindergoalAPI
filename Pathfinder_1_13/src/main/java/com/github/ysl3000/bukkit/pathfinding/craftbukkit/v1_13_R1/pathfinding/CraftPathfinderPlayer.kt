package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer
import net.minecraft.server.v1_13_R1.EntityPlayer
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 * Created by ysl3000
 */
class CraftPathfinderPlayer private constructor(private val entityPlayer: EntityPlayer) : PathfinderPlayer {

    val STILL = -0.0784000015258789

    override fun getPlayer(): Player = entityPlayer.bukkitEntity

    override fun getRelativeMotionX(): Double = entityPlayer.motX

    override fun getRelativeMotionY(): Double = entityPlayer.motY

    override fun getRelativeMotionZ(): Double = entityPlayer.motZ

    override fun getRelativeMotionYaw(): Float = entityPlayer.yaw

    override fun getRelativeMotionPitch(): Float = entityPlayer.pitch

    override fun getMotionForward(): Float = entityPlayer.bh

    override fun getMotionSideward(): Float = entityPlayer.bj

    override fun isJumping(): Boolean = entityPlayer.motY > STILL

    constructor(player: Player) : this((player as CraftPlayer).handle)


}
