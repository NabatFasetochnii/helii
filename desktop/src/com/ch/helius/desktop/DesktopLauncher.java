package com.ch.helius.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ch.helius.HeliusGameClass;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "HELIUS";
        config.width = 400;
        config.height = 800;

        new LwjglApplication(new HeliusGameClass(), config);
    }
}
