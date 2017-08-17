package com.github.ysl3000.pathfindergoalapi.pathfinding;


import net.minecraft.server.v1_12_R1.PathfinderGoal;

import java.util.HashMap;


/**
 * Created by ysl3000
 */
public class CraftPathfinderGoalWrapper extends PathfinderGoal {


    private com.github.ysl3000.pathfindergoalapi.pathfinding.PathfinderGoal pathfinderGoal;

    public CraftPathfinderGoalWrapper(com.github.ysl3000.pathfindergoalapi.pathfinding.PathfinderGoal pathfinderGoal) {
        this.pathfinderGoal = pathfinderGoal;
        //this.a(1);
    }

    @Override
    public boolean a() {
        return pathfinderGoal.shouldInitExecute();
    }

    @Override
    public boolean b() {
        boolean shouldExecute = pathfinderGoal.shouldExecute();
        if (shouldExecute)
            pathfinderGoal.executeUpdate();

        return shouldExecute;
    }

    @Override
    public boolean g() {
        return pathfinderGoal.isInterruptible();
    }

    @Override
    public void c() {
        // setup
        pathfinderGoal.initExecute();
    }

    @Override
    public void d() {
        // onFinish
        pathfinderGoal.reset();
    }

    @Override
    public void e() {
        // move
        pathfinderGoal.move();
    }

    @Override
    public void a(int i) {
        // todo find name
        super.a(i);
    }

    @Override
    public int h() {
        // todo find name
        return super.h();
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link Object#equals(Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return pathfinderGoal.hashCode();
    }


}
