package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding

import com.github.ysl3000.bukkit.pathfinding.AbstractNavigation
import net.minecraft.server.v1_12_R1.NavigationAbstract
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity

class CraftNavigation(private val navigationAbstract: NavigationAbstract) :
        AbstractNavigation(
                doneNavigating = { navigationAbstract.o() },
                pathSearchRange = { navigationAbstract.i() },
                moveToPositionU = { x, y, z -> navigationAbstract.a(x, y, z) },
                moveToPositionB = { x, y, z, speed -> navigationAbstract.a(x, y, z, speed) },
                moveToEntityU = { entity -> navigationAbstract.a((entity as CraftEntity).handle) },
                moveToentityB = { entity, speed -> navigationAbstract.a((entity as CraftEntity).handle, speed) },
                speedU = { speed -> navigationAbstract.a(speed) },
                clearPathEntityU = { navigationAbstract.p() }
        )