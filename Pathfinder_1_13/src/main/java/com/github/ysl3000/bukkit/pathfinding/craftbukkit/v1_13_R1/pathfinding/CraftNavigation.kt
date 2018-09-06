package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding

import org.bukkit.Location
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftEntity
import org.bukkit.entity.Entity

import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation

import net.minecraft.server.v1_13_R1.NavigationAbstract

/**
 * Created by ysl3000
 */
class CraftNavigation(private val navigationAbstract: NavigationAbstract) : Navigation {

    override fun isDoneNavigating(): Boolean = navigationAbstract.p()


    /**
     * The maximal distance the pathfinder search
     *
     * @return distance
     */
    override fun getPathSearchRange(): Float = navigationAbstract.j()

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


    /**
     * sets active PathEntity to null
     */
    override fun clearPathEntity() {
        navigationAbstract.p()
    }

}
