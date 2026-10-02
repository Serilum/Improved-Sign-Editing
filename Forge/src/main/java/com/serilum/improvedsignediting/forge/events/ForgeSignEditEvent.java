package com.serilum.improvedsignediting.forge.events;

import com.serilum.improvedsignediting.data.Constants;
import com.serilum.improvedsignediting.events.SignEditEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeSignEditEvent {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent e) {
		if (!e.phase.equals(Phase.END)) {
			return;
		}

		SignEditEvent.onClientTick(Constants.mc);
	}
}
