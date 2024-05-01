package com.natamus.improvedsignediting.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.improvedsignediting.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enableImprovedEditing = true;
	@Entry public static boolean showImprovedEditingButton = true;

	public static void initConfig() {
		configMetaData.put("enableImprovedEditing", Arrays.asList(
			"A global toggle whether the improved edtiting functionality should be enabled."
		));
		configMetaData.put("showImprovedEditingButton", Arrays.asList(
			"Whether the Improved/Normal Editing button should be visible on the sign edit screen."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}