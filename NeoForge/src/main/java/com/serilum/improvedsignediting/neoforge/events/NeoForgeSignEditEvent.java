package com.serilum.improvedsignediting.neoforge.events;

import com.serilum.improvedsignediting.data.Constants;
import com.serilum.improvedsignediting.events.SignEditEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class NeoForgeSignEditEvent {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Post e) {
		SignEditEvent.onClientTick(Constants.mc);
	}
}
