package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R2.pathfinding;

import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderPlayer;

import net.minecraft.server.v1_13_R2.EntityPlayer;

/**
 * Created by ysl3000
 */
public class CraftPathfinderPlayer implements PathfinderPlayer {

  public final double STILL = -0.0784000015258789;

  private EntityPlayer entityPlayer;

  public CraftPathfinderPlayer(Player player) {
    this(((CraftPlayer) player).getHandle());
  }

  private CraftPathfinderPlayer(EntityPlayer entityPlayer) {
    this.entityPlayer = entityPlayer;
  }

  @Override
  public Player getPlayer() {
    return entityPlayer.getBukkitEntity();
  }

  @Override
  public double getRelativeMotionX() {
    return entityPlayer.motX;
  }

  @Override
  public double getRelativeMotionY() {
    return entityPlayer.motY;
  }

  @Override
  public double getRelativeMotionZ() {
    return entityPlayer.motZ;
  }

  @Override
  public float getRelativeMotionYaw() {
    return entityPlayer.yaw;
  }

  @Override
  public float getRelativeMotionPitch() {
    return entityPlayer.pitch;
  }

  @Override
  public float getMotionForward() {
    return entityPlayer.bh;
  }

  @Override
  public float getMotionSideward() {
    return entityPlayer.bj;
  }

  @Override
  public boolean isJumping() {
    return entityPlayer.motY > STILL;
  }


}
