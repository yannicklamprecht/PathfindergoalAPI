package com.github.ysl3000.bukkit.pathfinding.pathfinding

@Deprecated("")
interface ControllerMove {
    fun move(motionX: Double, motionY: Double, motionZ: Double, speed: Double)
    fun move(forward: Float, sideward: Float)
    fun update()
    fun getX(): Double
    fun getY(): Double
    fun getZ(): Double
    fun getSpeed(): Double
    fun isOperationMove(): Boolean
}
