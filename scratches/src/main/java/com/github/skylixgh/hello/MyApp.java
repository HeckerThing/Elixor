package com.github.skylixgh.hello;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import net.skylix.elixor.desktop.Desktop;
import net.skylix.elixor.desktop.DesktopFrameType;
import net.skylix.elixor.desktop.DesktopSettings;
import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.theme.ThemeColor;
import net.skylix.elixor.desktop.theme.presets.ThemeDark;
import net.skylix.elixor.desktop.ux.uxButton.UXButton;
import net.skylix.elixor.desktop.ux.uxButton.UXButtonSettings;
import net.skylix.elixor.desktop.ux.uxButton.UXButtonType;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class MyApp {
    public static class DemoTree {
        public ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
    }

    public static class TreeNode {
        public UXComponent el;

        public TreeNode(UXComponent elt) {
            el = elt;
        }

        public static TreeNode button(String text, Consumer<UXButtonSettings> mod) throws InvalidHexCode {
            UXButtonSettings settings = new UXButtonSettings();
            mod.accept(settings);
            return new TreeNode(new UXButton(text, settings));
        }
    }

    public static void main(String[] args) throws URISyntaxException, WindowAlreadyRunning, InvalidHexCode {
        Desktop window = new Desktop(new DesktopSettings() {{
            theme = new ThemeDark() {
                {
                    setThemeAttributes(new HashMap() {{
                        put("highlight4", new ThemeColor("#ff00ff"));
                    }});
                }
            };

            frameType = DesktopFrameType.GENERIC;
        }});

        Desktop dialog = new Desktop();

        DemoTree app = new DemoTree() {{
            nodes = new ArrayList<TreeNode>() {{
                add(TreeNode.button("Hello", (m) -> {}));
                add(
                        TreeNode.button("World", (m) -> {
                            m.type = UXButtonType.HIGHLIGHTED;
                        })
                );

                add(
                        TreeNode.button("Delete Account", (m) -> {
                            m.type = UXButtonType.CRITICAL;
                            m.onMouseClick = () -> {
                                try {
                                    dialog.run();
                                } catch (WindowAlreadyRunning e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            };
                        })
                );
            }};
        }};

        UXButtonSettings btSettings = new UXButtonSettings() {
            {
                type = UXButtonType.HIGHLIGHTED;
            }
        };

        btSettings.onMouseClick = () -> {
            System.out.println("Hello World!");
        };

        UXComponent ro = new UXComponent();
        JPanel jp = new JPanel();
        JSlider js = new JSlider();

        for (TreeNode node : app.nodes) {
            jp.add(node.el.getSwingComponent());
        }

        jp.setBackground(new ThemeDark().getThemeAttribute("layerSolid1").getAwtColor());
        js.setBackground(new ThemeDark().getThemeAttribute("layerSolid1").getAwtColor());
//        jp.add(js);

        ro.setElement(jp);

        window.setRootElement(ro);
        window.run();
    }
}
