package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding;

import net.minecraft.server.v1_13_R1.ControllerLook;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

/**
 * Created by ysl3000
 */
public class CraftControllerLook implements com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerLook {

    private ControllerLook controllerLook;

    public CraftControllerLook(ControllerLook controllerLook) {
        this.controllerLook = controllerLook;
    }

    @Override
    public void lookAt(double x, double y, double z, float pitch, float yaw) {
        controllerLook.a(x, y, z, pitch, yaw);
    }

    @Override
    public void lookAt(Location location) {
        controllerLook.a(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
    }

    @Override
    public void lookAt(Entity entity, float yaw, float pitch) {
        controllerLook.a(((CraftEntity) entity).getHandle(), yaw, pitch);
    }

    @Override
    public void reset() {
        controllerLook.a();
    }

    @Override
    public boolean isReset() {
        return controllerLook.b();
    }

    @Override
    public double getLocationX() {
        return controllerLook.e();
    }

    @Override
    public double getLocationY() {
        return controllerLook.f();
    }

    @Override
    public double getLocationZ() {
        return controllerLook.g();
    }

}
