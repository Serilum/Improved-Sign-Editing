package com.natamus.improvedsignediting.neoforge.events;

import com.natamus.improvedsignediting.data.Constants;
import com.natamus.improvedsignediting.events.SignEditEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.event.TickEvent.ClientTickEvent;
import net.neoforged.neoforge.event.TickEvent.Phase;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class NeoForgeSignEditEvent {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent e) {
		if (!e.phase.equals(Phase.END)) {
			return;
		}

		SignEditEvent.onClientTick(Constants.mc);
	}
}
