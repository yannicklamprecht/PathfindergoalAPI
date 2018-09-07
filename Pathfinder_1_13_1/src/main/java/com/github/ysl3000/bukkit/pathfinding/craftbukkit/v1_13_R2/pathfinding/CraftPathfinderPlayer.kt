package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding

import com.github.ysl3000.bukkit.pathfinding.AbstractPathfinderPlayer
import net.minecraft.server.v1_13_R2.EntityPlayer
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 * Created by ysl3000
 */
class CraftPathfinderPlayer private constructor(ep: EntityPlayer) :
        AbstractPathfinderPlayer(
                player = { ep.bukkitEntity },
                relativeMotionX = { ep.motX },
                relativeMotionY = { ep.motY },
                relativeMotionZ = { ep.motZ },
                relativeMotionYaw = { ep.yaw },
                relativeMotionPitch = { ep.pitch },
                relativeMotionForward = { ep.bh },
                relativeMotionSideward = { ep.bj },
                jump = { ep.motY > STILL }
        ) {
    constructor(player: Player) : this((player as CraftPlayer).handle)

    companion object {
        private const val STILL = -0.0784000015258789
    }
}
