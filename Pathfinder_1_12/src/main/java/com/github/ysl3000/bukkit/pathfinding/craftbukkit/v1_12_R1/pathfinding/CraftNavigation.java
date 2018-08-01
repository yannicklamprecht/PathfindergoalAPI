package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

import com.github.ysl3000.bukkit.pathfinding.pathfinding.Navigation;

import net.minecraft.server.v1_12_R1.NavigationAbstract;

/**
 * Created by ysl3000
 */
public class CraftNavigation implements Navigation {

  private NavigationAbstract navigationAbstract;

  public CraftNavigation(NavigationAbstract navigationAbstract) {
    this.navigationAbstract = navigationAbstract;
  }

  @Override
  public void moveTo(Location location) {
    navigationAbstract.a(location.getX(), location.getY(), location.getZ());
  }

  @Override
  public void moveTo(Entity entity) {
    navigationAbstract.a(((CraftEntity) entity).getHandle());
  }

  @Override
  public boolean moveTo(Location location, double speed) {
    return navigationAbstract.a(location.getX(), location.getY(), location.getZ(), speed);
  }

  @Override
  public boolean moveTo(Entity entity, double speed) {
    return navigationAbstract.a(((CraftEntity) entity).getHandle(), speed);
  }

  @Override
  public void setSpeed(double speed) {
    navigationAbstract.a(speed);
  }

  @Override
  public boolean isDoneNavigating() {
    return navigationAbstract.o();
  }


  /**
   * sets active PathEntity to null
   */
  @Override
  public void clearPathEntity() {
    navigationAbstract.p();
  }


  /**
   * The maximal distance the pathfinder search
   *
   * @return distance
   */
  @Override
  public float getPathSearchRange() {
    return navigationAbstract.i();
  }

}
