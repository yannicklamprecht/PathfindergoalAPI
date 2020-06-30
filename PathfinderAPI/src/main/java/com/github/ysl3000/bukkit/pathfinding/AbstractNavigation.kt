package com.github.ysl3000.bukkit.pathfinding

import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation
import org.bukkit.Location
import org.bukkit.entity.Entity

open class AbstractNavigation(
        private val doneNavigating: () -> Boolean,
        private val pathSearchRange: () -> Float,
        private val moveToPositionU: (x: Double, y: Double, z: Double) -> Unit,
        private val moveToPositionB: (x: Double, y: Double, z: Double, speed: Double) -> Boolean,
        private val moveToEntityU: (entity: Entity) -> Unit,
        private val moveToentityB: (entity: Entity, speed: Double) -> Boolean,
        private val speedU: (speed: Double) -> Unit,
        private val clearPathEntityU: () -> Unit,
        private val setCanPassDoors: (canPassDoors: Boolean) -> Unit,
        private val setCanOpenDoors: (canOpenDoors: Boolean) -> Unit,
        private val setCanFloat: (canFloat: Boolean) -> Unit,
        private val canPassDoors: () -> Boolean,
        private val canOpenDoors: () -> Boolean,
        private val canFloat: () -> Boolean

) : Navigation {

    override fun isDoneNavigating(): Boolean = doneNavigating.invoke()

    override fun getPathSearchRange(): Float = pathSearchRange.invoke()

    override fun moveTo(location: Location) = moveToPositionU.invoke(location.x, location.y, location.z)

    override fun moveTo(entity: Entity) = moveToEntityU.invoke(entity)

    override fun moveTo(location: Location, speed: Double): Boolean = moveToPositionB.invoke(location.x, location.y, location.z, speed)

    override fun moveTo(entity: Entity, speed: Double): Boolean = moveToentityB.invoke(entity, speed)

    override fun setSpeed(speed: Double) = speedU.invoke(speed)

    override fun clearPathEntity() = clearPathEntityU.invoke()

    override fun setCanPassDoors(canPassDoors: Boolean) = setCanPassDoors.invoke(canPassDoors)

    override fun setCanOpenDoors(canOpenDoors: Boolean) = setCanOpenDoors.invoke(canOpenDoors)

    override fun setCanFloat(canFloat: Boolean) = setCanFloat.invoke(canFloat)

    override fun canPassDoors(): Boolean = canPassDoors.invoke()

    override fun canOpenDoors(): Boolean = canOpenDoors.invoke()

    override fun canFloat(): Boolean = canFloat.invoke()

}