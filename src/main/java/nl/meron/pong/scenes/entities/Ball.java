package nl.meron.pong.scenes.entities;

import nl.meron.pong.scenes.ScoreKeeper;
import nl.meron.yaeger.engine.entities.entity.collisions.Collided;
import nl.meron.yaeger.engine.entities.entity.collisions.Collider;
import nl.meron.yaeger.engine.entities.entity.collisions.CollisionSide;
import nl.meron.yaeger.engine.entities.entity.Point;
import nl.meron.yaeger.engine.Size;
import nl.meron.yaeger.engine.entities.entity.SceneBorderTouchingWatcher;
import nl.meron.yaeger.engine.entities.entity.sprite.DynamicSpriteEntity;
import nl.meron.yaeger.engine.entities.entity.SceneBorderCrossingWatcher;
import nl.meron.yaeger.engine.scenes.SceneBorder;

public class Ball extends DynamicSpriteEntity implements SceneBorderTouchingWatcher, SceneBorderCrossingWatcher, Collided {

    private static final double INITIAL_SPEED = 4;
    private ScoreKeeper scoreKeeper;

    public Ball(ScoreKeeper scoreKeeper) {
        super("pong/ball.png", new Point(200, 200), new Size(20, 20));
        this.scoreKeeper = scoreKeeper;
    }

    private void start() {
        placeOnPosition(getSceneWidth() / 2, getSceneHeight() / 2);
    }

    @Override
    public void configure() {
        setMotionTo(INITIAL_SPEED, 80);
    }

    @Override
    public void onCollision(Collider collidingObject, CollisionSide collisionSide) {
        var direction = getDirection();

        if (direction > 0 && direction < 180) {
            setDirectionTo(360 - direction);
        } else if (direction > 180 && direction < 360) {
            setDirectionTo(180 - (direction - 180));
        } else {
            setDirectionTo(direction + 180);
        }
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder border) {
        if (border.equals(SceneBorder.LEFT) || border.equals(SceneBorder.RIGHT)) {
            scoreKeeper.playerScores(border);
            start();
        }
    }

    @Override
    public void notifyBoundaryTouching(SceneBorder border) {
        if (border.equals(SceneBorder.TOP)) {
            var direction = getDirection();

            if (direction > 90 && direction < 180) {
                setDirectionTo(180 - direction);
            } else {
                setDirectionTo(180 - (direction - 180));
            }
        } else if (border.equals(SceneBorder.BOTTOM)) {
            var direction = getDirection();

            if (direction > 0 && direction < 90) {
                setDirectionTo(180 - direction);
            } else {
                setDirectionTo(270 - (direction - 270));
            }
        }
    }
}