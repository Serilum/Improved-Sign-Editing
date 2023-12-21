package com.natamus.improvedsignediting.fabric.mixin;

import com.natamus.improvedsignediting.config.ConfigHandler;
import com.natamus.improvedsignediting.data.Buttons;
import com.natamus.improvedsignediting.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AbstractSignEditScreen.class, priority = 1001)
public abstract class AbstractSignEditScreenMixin extends Screen {
	protected AbstractSignEditScreenMixin(Component component) {
		super(component);
	}

	@Inject(method = "init()V", at = @At(value = "HEAD"))
	protected void init(CallbackInfo ci) {
		if (!ConfigHandler.enableImprovedEditing || !ConfigHandler.showImprovedEditingButton) {
			return;
		}

		Buttons.improvedEditingButton = Button.builder(Component.literal(Util.improvedEditingEnabled ? "Improved Editing" : "Normal Editing").withStyle(Util.improvedEditingEnabled ? ChatFormatting.DARK_GREEN : ChatFormatting.GRAY), ($$0) -> {
			Component buttonText = Buttons.improvedEditingButton.getMessage();
			if (buttonText.getString().contains("Improved")) {
				Util.improvedEditingEnabled = false;
				Buttons.improvedEditingButton.setMessage(Component.literal("Normal Editing").withStyle(ChatFormatting.GRAY));
			} else {
				Util.improvedEditingEnabled = true;
				Buttons.improvedEditingButton.setMessage(Component.literal("Improved Editing").withStyle(ChatFormatting.DARK_GREEN));
			}

			Util.shouldResetButton = true;
		}).bounds(this.width / 2 - 50, this.height / 4 + 144 - 24, 100, 20).build();

		this.addRenderableWidget(Buttons.improvedEditingButton);
	}

	/*
	 *  Rebuild widget after button press, so it's not selected and triggered on spacebar.
	 */
	@Inject(method = "tick()V", at = @At(value = "TAIL"))
	public void tick(CallbackInfo ci) {
		if (Util.shouldResetButton) {
			this.rebuildWidgets();

			Util.shouldResetButton = false;
		}
	}

	@Inject(method = "onDone()V", at = @At(value = "TAIL"))
	private void onDone(CallbackInfo ci) {
		Util.clearEditVariables();
	}
}
