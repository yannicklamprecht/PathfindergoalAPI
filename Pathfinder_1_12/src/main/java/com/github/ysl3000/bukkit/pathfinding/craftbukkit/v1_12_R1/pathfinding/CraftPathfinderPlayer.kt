package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding

import com.github.ysl3000.bukkit.pathfinding.AbstractPathfinderPlayer
import net.minecraft.server.v1_12_R1.EntityPlayer
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 * Created by ysl3000
 */
class CraftPathfinderPlayer private constructor(private val entityPlayer: EntityPlayer) : AbstractPathfinderPlayer(
        player = { entityPlayer.bukkitEntity },
        relativeMotionX = { entityPlayer.motX },
        relativeMotionY = { entityPlayer.motY },
        relativeMotionZ = { entityPlayer.motZ },
        relativeMotionYaw = { entityPlayer.pitch },
        relativeMotionPitch = { entityPlayer.yaw },
        relativeMotionForward = { entityPlayer.bg },
        relativeMotionSideward = { entityPlayer.be },
        jump = { entityPlayer.motY > STILL }
) {

    companion object {
        private const val STILL = -0.0784000015258789
    }

    constructor(player: Player) : this((player as CraftPlayer).handle)
}
