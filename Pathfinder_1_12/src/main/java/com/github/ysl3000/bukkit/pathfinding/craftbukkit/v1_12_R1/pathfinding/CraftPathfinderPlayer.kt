package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.entity.Player

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer

import net.minecraft.server.v1_12_R1.EntityPlayer

/**
 * Created by ysl3000
 */
class CraftPathfinderPlayer private constructor(private val entityPlayer: EntityPlayer) : PathfinderPlayer {

    private val STILL = -0.0784000015258789

    override fun getPlayer(): Player = entityPlayer.bukkitEntity

    override fun getRelativeMotionX(): Double = entityPlayer.motX

    override fun getRelativeMotionY(): Double = entityPlayer.motY

    override fun getRelativeMotionZ(): Double = entityPlayer.motZ

    override fun getRelativeMotionYaw(): Float = entityPlayer.yaw

    override fun getRelativeMotionPitch(): Float = entityPlayer.pitch

    override fun getMotionForward(): Float = entityPlayer.bg

    override fun getMotionSideward(): Float = entityPlayer.be

    override fun isJumping(): Boolean = entityPlayer.motY > STILL

    constructor(player: Player) : this((player as CraftPlayer).handle)


}
