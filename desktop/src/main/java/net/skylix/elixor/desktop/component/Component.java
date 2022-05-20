package net.skylix.elixor.desktop.component;

import net.skylix.elixor.desktop.engines.HierarchyTree;
import net.skylix.elixor.desktop.engines.Layout;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

import java.awt.*;

/**
 * A component that has some basics required properties.
 */
public abstract class Component {
    /**
     * The element render method.
     *
     * @param g2d The graphics output.
     * @param window The window.
     * @param parent The parent element.
     */
    public abstract void render(Graphics2D g2d, Window window, Component parent);

    /**
     * Child nodes.
     */
    private final HierarchyTree tree = new HierarchyTree(this);

    /**
     * Add an element to the 3D tree.
     *
     * @param element The element to add.
     */
    public void add(Component element) {
        tree.add(element);
    }

    /**
     * Get hierarchy tree.
     *
     * @return Hierarchy tree.
     */
    public HierarchyTree getTree() {
        return tree;
    }

    /**
     * Get the size.
     * 
     * @return The size.
     */
    public abstract Size getSize();

    /**
     * Get the minimum size.
     *
     * @return The minimum size.
     */
    public abstract Size getMinimumSize();

    /**
     * Get the maximum size.
     *
     * @return The maximum size.
     */
    public abstract Size getMaximumSize();

    /**
     * Get minimum width.
     *
     * @return The minimum width.
     */
    public int getMinimumWidth() {
        return getMinimumSize().getWidth();
    }

    /**
     * Get minimum height.
     *
     * @return The minimum height.
     */
    public int getMinimumHeight() {
        return getMinimumSize().getHeight();
    }

    /**
     * Get maximum width.
     *
     * @return The maximum width.
     */
    public int getMaximumWidth() {
        return getMaximumSize().getWidth();
    }

    /**
     * Get maximum height.
     *
     * @return The maximum height.
     */
    public int getMaximumHeight() {
        return getMaximumSize().getHeight();
    }

    /**
     * Get the parent component.
     *
     * @return The parent component.
     */
    public abstract Component getParent();

    /**
     * Get the window.
     *
     * @return The window.
     */
    public abstract Window getWindow();

    /**
     * Get the width.
     * 
     * @return The width.
     */
    public int getWidth() {
        return getSize().getWidth();
    }

    /**
     * Get the height.
     * 
     * @return The height.
     */
    public int getHeight() {
        return getSize().getHeight();
    }

    /**
     * Set the size.
     * 
     * @param size The size.
     */
    public void setSize(Size size) {
        Size originalSize = getSize();

        originalSize.setWidth(size.getWidth());
        originalSize.setHeight(size.getHeight());
    }


    /**
     * Set the width.
     *
     * @param width The width.
     */
    public void setWidth(int width) {
        getSize().setWidth(width);
    }

    /**
     * Set the height.
     *
     * @param height The height.
     */
    public void setHeight(int height) {
        getSize().setHeight(height);
    }

    /**
     * Refresh the component.
     */
    protected void refresh() {
        Window window = getWindow();

        if (window != null)
            window.refresh();
    }

    /**
     * Get the shape of the component.
     * 
     * @return The shape of the component.
     */
    public abstract Shape getShape();

    /**
     * Get the mouse position.
     *
     * @return The mouse position.
     */
    public abstract Position getMousePosition();

    /**
     * Get the mouse X position.
     *
     * @return The mouse X position.
     */
    public int getMouseX() {
        return getMousePosition().getX(); // !! TODO: This is not correct.
    }

    /**
     * Get the mouse Y position.
     *
     * @return The mouse Y position.
     */
    public int getMouseY() {
        return getMousePosition().getY(); // !! TODO: This is not correct.
    }

    /**
     * Set the position.
     * 
     * @param position The position.
     */
    public abstract void setPosition(Position position);

    /**
     * Set the X position.
     * 
     * @param x The X position.
     */
    public void setX(int x) {
        setPosition(new Position(x, getYPosition()));
    }

    /**
     * Set the Y position.
     * 
     * @param y The Y position.
     */
    public void setY(int y) {
        setPosition(new Position(getXPosition(), y));
    }

    /**
     * Get the X position.
     * 
     * @return The X position.
     */
    public int getXPosition() {
        return getPosition().getX();
    }

    /**
     * Get the Y position.
     * 
     * @return The Y position.
     */
    public int getYPosition() {
        return getPosition().getY();
    }

    /**
     * Get the position.
     * 
     * @return The position.
     */
    public abstract Position getPosition();

    /**
     * Set the layout engine.
     * 
     * @param layoutEngine The layout engine.
     */
    public abstract void setLayoutEngine(Layout layoutEngine);

    /**
     * Get the layout engine.
     * 
     * @return The layout engine.
     */
    public abstract Layout getLayoutEngine();

    /**
     * Check to see if the mouse is over the element.
     * 
     * @return True if the mouse is over the element.
     */
    public abstract boolean isMouseOver();
}
