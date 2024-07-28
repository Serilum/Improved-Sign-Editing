package com.natamus.improvedsignediting;

import com.natamus.improvedsignediting.events.SignEditEvent;

import net.fabricmc.api.ClientModInitializer;
import com.natamus.improvedsignediting.util.Reference;
import com.natamus.collective.check.ShouldLoadCheck;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		registerEvents();
	}
	
	private void registerEvents() {
		ClientTickEvents.END_CLIENT_TICK.register((Minecraft mc) -> {
			SignEditEvent.onClientTick(mc);
		});
	}
}
