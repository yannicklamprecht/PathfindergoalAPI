package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding

import com.github.ysl3000.bukkit.pathfinding.AbstractNavigation
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity
import org.bukkit.entity.Entity

import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation

import net.minecraft.server.v1_13_R2.NavigationAbstract

class CraftNavigation(private val navigationAbstract: NavigationAbstract) : AbstractNavigation(
        doneNavigating={navigationAbstract.p()},
        pathSearchRange={navigationAbstract.j()},
        moveToPositionU={x, y, z -> navigationAbstract.a(x,y,z) },
        moveToPositionB={x, y, z, speed -> navigationAbstract.a(x,y,z,speed) },
        moveToEntityU={entity -> navigationAbstract.a((entity as CraftEntity).handle) },
        moveToentityB={entity, speed -> navigationAbstract.a((entity as CraftEntity).handle,speed) },
        speedU={speed -> navigationAbstract.a(speed) },
        clearPathEntityU={navigationAbstract.q()}
)
