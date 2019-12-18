package nl.meron.yaeger.engine.entities.entity.text;

import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.geometry.Point2D;
import nl.meron.yaeger.engine.Configurable;
import nl.meron.yaeger.engine.entities.entity.motion.DefaultMotionApplier;
import nl.meron.yaeger.engine.entities.entity.motion.Moveable;
import nl.meron.yaeger.engine.entities.entity.UpdateDelegator;
import nl.meron.yaeger.engine.entities.entity.Updater;
import nl.meron.yaeger.engine.entities.entity.motion.MotionApplier;

public abstract class DynamicTextEntity extends TextEntity implements UpdateDelegator, Moveable {

    private DefaultMotionApplier motionApplier;
    private Updater updater;

    /**
     * Instantiate a new {@link DynamicTextEntity}.
     */
    public DynamicTextEntity() {
        this(new Point2D(0, 0));
    }

    /**
     * Instantiate a new {@code DynamicTextEntity} for the given {@link Point2D}.
     *
     * @param initialPosition the initial {@link Point2D} of this {@link DynamicTextEntity}
     */
    public DynamicTextEntity(final Point2D initialPosition) {
        this(initialPosition, "");
    }

    /**
     * Instantiate a new {@link DynamicTextEntity} for the given {@link Point2D} and textDelegate.
     *
     * @param initialPosition the initial {@link Point2D} of this {@link DynamicTextEntity}
     * @param text            a {@link String} containing the initial textDelegate to be displayed
     */
    public DynamicTextEntity(final Point2D initialPosition, final String text) {
        super(initialPosition, text);
    }


    @Override
    public MotionApplier getMotionApplier() {
        return motionApplier;
    }

    @Override
    public void init(Injector injector) {
        super.init(injector);

        this.configure();
    }

    @Override
    public Updater getUpdater() {
        return updater;
    }

    @Inject
    @Override
    public void setMotionApplier(DefaultMotionApplier motionApplier) {
        this.motionApplier = motionApplier;
    }

    @Inject
    public void setUpdater(Updater updater) {
        this.updater = updater;
    }
}
