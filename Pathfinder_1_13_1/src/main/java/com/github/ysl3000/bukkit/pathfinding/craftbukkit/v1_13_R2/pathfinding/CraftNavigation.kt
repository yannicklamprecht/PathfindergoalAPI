package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding

import org.bukkit.Location
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity
import org.bukkit.entity.Entity

import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation

import net.minecraft.server.v1_13_R2.NavigationAbstract

/**
 * Created by ysl3000
 */
class CraftNavigation(private val navigationAbstract: NavigationAbstract) : Navigation {

    override fun moveTo(location: Location) {
        navigationAbstract.a(location.x, location.y, location.z)
    }

    override fun moveTo(entity: Entity) {
        navigationAbstract.a((entity as CraftEntity).handle)
    }

    override fun moveTo(location: Location, speed: Double): Boolean {
        return navigationAbstract.a(location.x, location.y, location.z, speed)
    }

    override fun moveTo(entity: Entity, speed: Double): Boolean {
        return navigationAbstract.a((entity as CraftEntity).handle, speed)
    }

    override fun setSpeed(speed: Double) {
        navigationAbstract.a(speed)
    }

    override fun isDoneNavigating(): Boolean {
        return navigationAbstract.p()
    }


    /**
     * sets active PathEntity to null
     */
    override fun clearPathEntity() {
        navigationAbstract.p()
    }


    /**
     * The maximal distance the pathfinder search
     *
     * @return distance
     */
    override fun getPathSearchRange(): Float {
        return navigationAbstract.j()
    }

}
