package net.skylix.elixor.desktop;

import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.errors.WindowCannotReinitialize;
import net.skylix.elixor.desktop.errors.WindowNotRunning;
import net.skylix.elixor.desktop.local.ModJFrame;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.desktop.ux.uxLabel.UXLabel;
import net.skylix.elixor.desktop.ux.uxLabel.UXLabelSettings;
import net.skylix.elixor.desktop.ux.uxPanel.*;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Provider;
import java.util.function.Function;

public class Desktop {
    private final ModJFrame frame;
    private final DesktopSettings settings;
    private boolean running = false;
    private final JPanel root;
    private boolean initiallyPowered = false;

    public Desktop(DesktopSettings settings) throws InvalidHexCode {
        this.settings = settings;

        frame = new ModJFrame(
                "Elixor [Single Service]",
                settings.frameType != DesktopFrameType.SYSTEM,
                (settings.frameType == DesktopFrameType.HIDDEN || settings.frameType == DesktopFrameType.NONE) ? 0 : 20
        );

        frame.setAlwaysOnTop(settings.alwaysOnTop);

        if (settings.frameType == DesktopFrameType.NONE) {
            frame.setUndecorated(true);
        }

        ThemeColor backgroundColor = settings.theme.getThemeAttribute("layerSolid2");

        frame.setSize(1000, 600);
        Desktop self = this;

        JPanel innerFrame = new JPanel();
        UXPanel titleBar = new UXPanel(new UXPanelSettings() {{
            color = settings.theme.getThemeAttribute("layerSolid1");
            dragRole = UXPanelWindowDragRole.DRAG;
            width = frame.getWidth();
            height = 32;
            rowAlignment = UXPanelRowAlignment.SPACE_BETWEEN;
            columnAlignment = UXPanelColumnAlignment.CENTER;
            spacingX = 10;
        }});

        frame.setOnMaximizeRunnable(() -> {
            if (!System.getProperty("os.name").toLowerCase().contains("windows")) return;
            innerFrame.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        });

        frame.setOnUnMaximizeRunnable(() -> {
            if (!System.getProperty("os.name").toLowerCase().contains("windows")) return;
            innerFrame.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        });

        frame.setOnClosingRunnable(() -> {
            running = false;
            frame.setVisible(false);
            frame.dispose();
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                innerFrame.setSize(frame.getWidth(), frame.getHeight());
                titleBar.setSize(frame.getWidth() - (frame.hasTriggeredMaximized() ? 16 : 0), 32);
                root.setSize(frame.getWidth(), frame.getHeight() - titleBar.getHeight());
                settings.onResize.accept(self);
            }
        });

        root = new JPanel();

        innerFrame.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
        innerFrame.setLayout(new BorderLayout());
        innerFrame.setBackground(new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha()));

        if (settings.frameType == DesktopFrameType.GENERIC) {
            enum FrameButtonType {
                CLOSE,
                MINIMIZE,
                MAXIMIZE
            }

            Function<FrameButtonType, UXComponent> createFrameButton = (FrameButtonType type) -> {
                final UXPanel button;
                UXLabel label = null;

                try {
                    label = new UXLabel("-", new UXLabelSettings() {{
                        fontSize = 20;
                    }});
                } catch (InvalidHexCode e) {
                    e.printStackTrace();
                }

                try {
                    final UXLabel labelCopy = label;
                    assert false;

                    button = new UXPanel(new UXPanelSettings() {{
                        dragRole = UXPanelWindowDragRole.EXCLUDE;

                        onMouseEnter = (panel) -> {
                            if (type == FrameButtonType.CLOSE) {
                                panel.setColor(this.theme.getThemeAttribute("critical4"));
                                labelCopy.setColor(this.theme.getThemeAttribute("criticalText4"));
                            } else {
                                panel.setColor(this.theme.getThemeAttribute("component1"));
                            }
                        };

                        onMouseExit = (panel) -> {
                            panel.setColor(new ThemeColor(0, 0, 0, 0), 300);
                            labelCopy.setColor(settings.theme.getThemeAttribute("text4"), 300);
                        };

                        onMouseClick = (panel) -> {
                            switch (type) {
                                case CLOSE -> {
                                    try {
                                        stop();
                                    } catch (WindowNotRunning e) {
                                        e.printStackTrace();
                                    }
                                }

                                case MINIMIZE -> {
                                    frame.minimizeNative();
                                }

                                case MAXIMIZE -> {
                                    frame.maximizeNative();
                                }
                            }
                        };

                        rowAlignment = UXPanelRowAlignment.CENTER;
                        columnAlignment = UXPanelColumnAlignment.CENTER;
                    }});

                    assert false;
                    switch (type) {
                        case CLOSE -> {
                            label.setText("x");
                        }

                        case MINIMIZE -> {
                            label.setText("-");
                        }

                        case MAXIMIZE -> {
                            label.setText("o");
                        }
                    };

                    button.add(new UXComponent().setElement(label));
                    button.setSize(45, 32);

                    return button;
                } catch (InvalidHexCode e) {
                    throw new RuntimeException(e);
                }
            };

            UXPanel buttons = new UXPanel(new UXPanelSettings() {{
                // We need to add 2 pixels due to the horizontal spacing
                width = (45 * 3) + 2;
                height = 32;
                spacingX = 1;
            }});

            buttons.add(createFrameButton.apply(FrameButtonType.MINIMIZE));
            buttons.add(createFrameButton.apply(FrameButtonType.MAXIMIZE));
            buttons.add(createFrameButton.apply(FrameButtonType.CLOSE));

            titleBar.add(buttons);
        }

        int titleBarHeight = settings.frameType == DesktopFrameType.GENERIC ? titleBar.getHeight() : 0;

        root.setLayout(null);
        root.setSize(new Dimension(frame.getWidth(), frame.getHeight() - titleBarHeight));
        root.setBackground(backgroundColor.getAwtColor());

        UXLabel label = new UXLabel("No Root Element Set", new UXLabelSettings() {{
            color = settings.theme.getThemeAttribute("text2");
            fontSize = 20;
        }});

        UXComponent defaultComp = new UXComponent(settings.theme, settings.accessibility);
        defaultComp.setElement(label);

        if (settings.frameType == DesktopFrameType.GENERIC) {
            UXLabel title = new UXLabel("No Title Set", new UXLabelSettings() {{
                color = settings.theme.getThemeAttribute("text2");
            }});

            titleBar.add(title);
            innerFrame.add(titleBar.getSwingComponent(), BorderLayout.NORTH);
        }

        innerFrame.add(root, BorderLayout.CENTER);

        setRootElement(defaultComp);
        frame.setContentPane(innerFrame);
    }

    public final int getWidth() {
        return frame.getWidth();
    }

    public final int getHeight() {
        return frame.getHeight();
    }

    public final Dimension getSize() {
        return frame.getSize();
    }

    public Desktop() throws InvalidHexCode {
        this(new DesktopSettings());
    }

    public void setWidth(int width) {
        frame.setSize(width, frame.getHeight());
    }

    public void setHeight(int height) {
        frame.setSize(frame.getWidth(), height);
    }

    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    public void setPositionX(int x) {
        frame.setLocation(x, frame.getY());
    }

    public void setPositionY(int y) {
        frame.setLocation(frame.getX(), y);
    }

    public void setPosition(int x, int y) {
        frame.setLocation(x, y);
    }

    public int getPositionX() {
        return frame.getX();
    }

    public int getPositionY() {
        return frame.getY();
    }

    public Point getPosition() {
        return frame.getLocation();
    }

    public final void setAlwaysOnTop(boolean alwaysOnTop) {
        frame.setAlwaysOnTop(alwaysOnTop);
    }

    public final void setRootElement(UXComponent rootElement) {
        root.removeAll();
        root.add(rootElement.getSwingComponent());
    }

    public final void run() throws WindowAlreadyRunning, WindowCannotReinitialize {
        if (running || frame.isVisible()) {
            throw new WindowAlreadyRunning("The window is already running");
        }

        if (initiallyPowered) {
            throw new WindowCannotReinitialize("The window cannot be reinitialized after run() was called once");
        }

        initiallyPowered = true;
        running = true;
        frame.setVisible(true);
    }

    public final void stop() throws WindowNotRunning {
        if (!running || !frame.isVisible()) {
            throw new WindowNotRunning("The window is not running");
        }

        running = false;
        frame.setVisible(false);
        frame.dispose();
    }
}
