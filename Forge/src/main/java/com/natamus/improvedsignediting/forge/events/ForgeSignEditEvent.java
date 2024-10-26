package com.natamus.improvedsignediting.forge.events;

import com.natamus.improvedsignediting.data.Constants;
import com.natamus.improvedsignediting.events.SignEditEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class ForgeSignEditEvent {
	@SubscribeEvent
	public void onClientTick(ClientTickEvent e) {
		if (!e.phase.equals(Phase.END)) {
			return;
		}

		SignEditEvent.onClientTick(Constants.mc);
	}
}
