package com.github.hanyaeger.api.engine.entities.entity.collisions;

import com.github.hanyaeger.api.engine.entities.entity.Rotatable;
import com.github.hanyaeger.api.engine.entities.entity.YaegerEntity;
import com.github.hanyaeger.api.engine.entities.entity.Bounded;
import com.github.hanyaeger.api.engine.scenes.YaegerScene;

import java.util.Set;

/**
 * A {@link AABBCollided} represents an non-rotatable {@link YaegerEntity} that can be collided with by a
 * {@link AABBCollider}. In such a case, the {@link AABBCollided} is the {@link YaegerEntity} that
 * gets notified of the collision.
 *
 * <p>There are different types of collision detection. In the case of an {@link AABBCollided} we perform so called
 * <b>Axis-Aligned Bounding Box</b> collision detection, which required the instances of {@link YaegerEntity}
 * to not be {@link Rotatable}. </p>
 *
 * <p>Each Game world Update a {@link AABBCollided} is checked against all instances of{@link AABBCollider}. If many instances
 * of {@link AABBCollider} are part of the {@link YaegerScene}, this
 * could lead to many calculations, which could slow down the game and framerate. Thus ensure only those instances of
 * {@link YaegerEntity} that really need to be part of the collision detection implement
 * the {@link AABBCollided} or {@link AABBCollider} interfaces.
 * </p>
 */
public interface AABBCollided extends Bounded {

    /**
     * This method is called if a collision has occurred.
     *
     * @param collidingObject The EntityCollection you are colliding with.
     */
    void onCollision(AABBCollider collidingObject);

    /**
     * Perform collision detection with a {@link Set} of {@link AABBCollider} instances. Only the first collision
     * is detected.
     *
     * @param AABBColliders A {@link Set} of colliders that should be checked for collisions.
     */
    default void checkForCollisions(Set<AABBCollider> AABBColliders) {
        if (AABBColliders == null || AABBColliders.isEmpty()) {
            return;
        }

        for (AABBCollider AABBCollider : AABBColliders) {
            if (collisionHasOccured(AABBCollider)) {
                onCollision(AABBCollider);
                break;
            }
        }
    }

    private boolean collisionHasOccured(AABBCollider AABBCollider) {
        return !this.equals(AABBCollider) && getTransformedBounds().intersects(AABBCollider.getTransformedBounds());
    }
}
