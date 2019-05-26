package com.github.ysl3000.bukkit.pathfinding

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer
import org.bukkit.entity.Player

open class AbstractPathfinderPlayer(
        private val player: () -> Player,
        private val relativeMotionX: () -> Double,
        private val relativeMotionY: () -> Double,
        private val relativeMotionZ: () -> Double,
        private val relativeMotionYaw: () -> Float,
        private val relativeMotionPitch: () -> Float,
        private val relativeMotionForward: () -> Float,
        private val relativeMotionSideward: () -> Float,
        private val jump: () -> Boolean
) : PathfinderPlayer {

    override fun getPlayer(): Player = player.invoke()

    override fun getRelativeMotionX(): Double = relativeMotionX.invoke()


    override fun getRelativeMotionY(): Double = relativeMotionY.invoke()

    override fun getRelativeMotionZ(): Double = relativeMotionZ.invoke()

    override fun getRelativeMotionYaw(): Float = relativeMotionYaw.invoke()

    override fun getRelativeMotionPitch(): Float = relativeMotionPitch.invoke()

    override fun getMotionForward(): Float = relativeMotionForward.invoke()

    override fun getMotionSideward(): Float = relativeMotionSideward.invoke()

    override fun isJumping(): Boolean = jump.invoke()


}