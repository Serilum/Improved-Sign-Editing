package com.natamus.improvedsignediting.neoforge.events;

import com.natamus.improvedsignediting.data.Constants;
import com.natamus.improvedsignediting.events.SignEditEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class NeoForgeSignEditEvent {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Post e) {
		SignEditEvent.onClientTick(Constants.mc);
	}
}
