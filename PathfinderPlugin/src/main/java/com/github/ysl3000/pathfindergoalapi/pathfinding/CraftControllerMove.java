package com.github.ysl3000.pathfindergoalapi.pathfinding;

/**
 * Created by ysl3000
 */
public class CraftControllerMove implements com.github.ysl3000.pathfindergoalapi.pathfinding.ControllerMove{


    private net.minecraft.server.v1_12_R1.ControllerMove controllerMove;

    public CraftControllerMove(net.minecraft.server.v1_12_R1.ControllerMove controllerMove) {
        this.controllerMove = controllerMove;
    }


    @Override
    public boolean isOperationMove() {
        return controllerMove.b();
    }

    @Override
    public void move(double motionX, double motionY, double motionZ, double speed) {
        controllerMove.a(motionX,motionY,motionZ,speed);
    }

    @Override
    public void move(float forward, float sideward) {
        controllerMove.a(forward,sideward);
    }

    @Override
    public void update() {
        controllerMove.a();
    }

    @Override
    public double getX() {
        return controllerMove.d();
    }

    @Override
    public double getY() {
        return controllerMove.e();
    }

    @Override
    public double getZ() {
        return controllerMove.f();
    }

    @Override
    public double getSpeed() {
        return controllerMove.c();
    }

}
