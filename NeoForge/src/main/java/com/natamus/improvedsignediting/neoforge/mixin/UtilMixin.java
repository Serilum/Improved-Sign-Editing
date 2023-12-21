package com.natamus.improvedsignediting.neoforge.mixin;

import net.minecraft.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Util.class, priority = 999)
public class UtilMixin {
	/*
	 *  Catches and ignores IndexOutOfBoundsExceptions due to the mod editing sign lines.
	 */
	@Inject(method = "offsetByCodepoints(Ljava/lang/String;II)I", at = @At(value = "HEAD"), cancellable = true)
	private static void offsetByCodepoints(String string, int i, int j, CallbackInfoReturnable<Integer> cir) {
        int k = string.length();
        int l;
        if (j >= 0) {
            for(l = 0; i < k && l < j; ++l) {
				try {
					if (Character.isHighSurrogate(string.charAt(i++)) && i < k && Character.isLowSurrogate(string.charAt(i))) {
						++i;
					}
				}
				catch (IndexOutOfBoundsException ignored) { }
            }
        } else {
            for(l = j; i > 0 && l < 0; ++l) {
                --i;
				try {
					if (Character.isLowSurrogate(string.charAt(i)) && i > 0 && Character.isHighSurrogate(string.charAt(i - 1))) {
						--i;
					}
				}
				catch (IndexOutOfBoundsException ignored) { }
            }
        }

        cir.setReturnValue(i);
    }
}
