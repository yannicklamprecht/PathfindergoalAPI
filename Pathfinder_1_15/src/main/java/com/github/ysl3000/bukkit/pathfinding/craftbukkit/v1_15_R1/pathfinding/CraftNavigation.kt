package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_15_R1.pathfinding

import com.github.ysl3000.bukkit.pathfinding.AbstractNavigation
import net.minecraft.server.v1_15_R1.EntityInsentient
import net.minecraft.server.v1_15_R1.GenericAttributes
import net.minecraft.server.v1_15_R1.NavigationAbstract
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity

class CraftNavigation(private val navigationAbstract: NavigationAbstract, private val handle: EntityInsentient, private val defaultSpeed: Double) : AbstractNavigation(
        doneNavigating = { navigationAbstract.n() },
        pathSearchRange = { handle.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).value.toFloat() },
        moveToPositionU = { x, y, z -> navigationAbstract.a(x, y, z, defaultSpeed) },
        moveToPositionB = { x, y, z, speed -> navigationAbstract.a(x, y, z, speed) },
        moveToEntityU = { entity -> navigationAbstract.a((entity as CraftEntity).handle, defaultSpeed) },
        moveToentityB = { entity, speed -> navigationAbstract.a((entity as CraftEntity).handle, speed) },
        speedU = { speed -> navigationAbstract.a(speed) },
        clearPathEntityU = { navigationAbstract.o() }
)