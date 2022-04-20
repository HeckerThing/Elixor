package me.skylixdev.superack;

import net.skylix.elixor.apiSocket.APISocket;
import net.skylix.elixor.apiSocket.controller.Controller;
import net.skylix.elixor.apiSocket.controller.request.ControllerRequest;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;
import net.skylix.elixor.apiSocket.errors.ServerAlreadyRunning;
import net.skylix.elixor.terminal.ansiChain.AnsiChain;
import net.skylix.elixor.terminal.color.ColorConversion;
import net.skylix.elixor.terminal.color.ColorNamesGeneric;
import net.skylix.elixor.terminal.color.ColorUtil;
import net.skylix.elixor.terminal.color.ColorsCLI256;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;
import net.skylix.elixor.terminal.logger.Logger;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

class UwU extends Controller {
    @Override
    public void onActivate(ControllerSocket socket) {
        Logger.infoBase("UwU activated");
    }

    @Override
    public void onDeactivate(ControllerSocket socket) {
        Logger.warningBase("UwU die");
    }

    @Override
    public void onRequest(ControllerSocket socket, ControllerRequest request) {
        Logger.infoBase("UwU msg");
    }
}

public class X {
    public static void main(String[] args) throws InvalidHexCode, InvalidRGBAlpha, InvalidRGBValues, ServerAlreadyRunning {
        Logger lg = new Logger();

        lg.info("Loading server TCP...");

        APISocket service = new APISocket();
        UwU hello = new UwU();

        service.connectController(hello);

        service.run();
        Logger.infoBase("Server started");
    }
}
