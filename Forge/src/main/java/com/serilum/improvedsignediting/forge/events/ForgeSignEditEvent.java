package com.serilum.improvedsignediting.forge.events;

import com.serilum.improvedsignediting.data.Constants;
import com.serilum.improvedsignediting.events.SignEditEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeSignEditEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeSignEditEvent.class);

		ClientTickEvent.Pre.BUS.addListener(ForgeSignEditEvent::onClientTick);
	}

	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Pre e) {
		SignEditEvent.onClientTick(Constants.mc);
	}
}
