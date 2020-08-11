package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_16_R2.pathfinding

import com.github.ysl3000.bukkit.pathfinding.AbstractPathfinderPlayer
import net.minecraft.server.v1_16_R2.EntityPlayer
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer
import org.bukkit.entity.Player

class CraftPathfinderPlayer private constructor(ep: EntityPlayer) :
        AbstractPathfinderPlayer(
                player = { ep.bukkitEntity },
                relativeMotionX = { ep.mot.x },
                relativeMotionY = { ep.mot.y },
                relativeMotionZ = { ep.mot.z },
                relativeMotionYaw = { ep.yaw },
                relativeMotionPitch = { ep.pitch },
                relativeMotionForward = { ep.aR },
                relativeMotionSideward = { ep.aT },
                jump = { ep.mot.y > STILL }
        ) {
    constructor(player: Player) : this((player as CraftPlayer).handle)

    companion object {
        private const val STILL = -0.0784000015258789
    }
}