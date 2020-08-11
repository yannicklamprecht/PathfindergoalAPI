package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_16_R2.pathfinding

import com.github.ysl3000.bukkit.pathfinding.AbstractNavigation
import net.minecraft.server.v1_16_R2.*
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity

class CraftNavigation(private val navigationAbstract: NavigationAbstract, private val handle: EntityInsentient, private val defaultSpeed: Double) : AbstractNavigation(
        doneNavigating = { navigationAbstract.n() },
        pathSearchRange = {
            handle.getAttributeInstance(GenericAttributes.FOLLOW_RANGE)?.let { it.value.toFloat() }?: 0f
        },
        moveToPositionU = { x, y, z -> navigationAbstract.a(x, y, z, defaultSpeed) },
        moveToPositionB = { x, y, z, speed -> navigationAbstract.a(x, y, z, speed) },
        moveToEntityU = { entity -> navigationAbstract.a((entity as CraftEntity).handle, defaultSpeed) },
        moveToentityB = { entity, speed -> navigationAbstract.a((entity as CraftEntity).handle, speed) },
        speedU = navigationAbstract::a,
        clearPathEntityU = navigationAbstract::o,
        setCanPassDoors = navigationAbstract.q()::a,
        setCanOpenDoors = navigationAbstract.q()::b,
        setCanFloat = navigationAbstract.q()::c,
        canPassDoors = navigationAbstract.q()::c,
        canOpenDoors = navigationAbstract.q()::d,
        canFloat = navigationAbstract.q()::e
)