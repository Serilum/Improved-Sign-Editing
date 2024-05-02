package com.natamus.improvedsignediting.data;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;

public class Constants {
    public static final Minecraft mc = Minecraft.getInstance();
    public static final Font font = mc.font;

    public static int getMaxSignWidth(Screen screen) {
        return 85;
    }
}
