package com.natamus.improvedsignediting.events;

import com.natamus.collective.functions.ScreenFunctions;
import com.natamus.improvedsignediting.config.ConfigHandler;
import com.natamus.improvedsignediting.data.Constants;
import com.natamus.improvedsignediting.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignEditEvent {
	public static void onClientTick(Minecraft mc) {
		Screen screen = mc.screen;

		if (!(screen instanceof AbstractSignEditScreen)) {
			return;
		}

		if (!ConfigHandler.enableImprovedEditing || !Util.improvedEditingEnabled) {
			return;
		}

		AbstractSignEditScreen signEditScreen = (AbstractSignEditScreen)screen;

		String[] messages = ScreenFunctions.getSignMessagesFromScreen(signEditScreen);
		List<String> messageList = new ArrayList<String>(Arrays.asList(messages));
		if (Util.messagesAreEqual(messageList)) {
			return;
		}

		Util.preMessages = messageList;

		int signLine = ScreenFunctions.getSignLineFromScreen(signEditScreen);

		String lineMessage = messages[signLine];
		int lineWidth = Constants.font.width(lineMessage);

		if (lineWidth == Util.preLineWidth) {
			return;
		}

		Util.startLine = Util.getStartLine(messageList);

		if (lineWidth >= Constants.maxSignWidth && lineWidth > Util.preLineWidth) {
			if (signLine == 3) {
				return;
			}

			if (!messages[signLine+1].isEmpty()) {
				return;
			}

			boolean endsWithSpace = lineMessage.endsWith(" ");
			String strippedLineMessage = lineMessage.strip();
			String[] lineMessageSpl = strippedLineMessage.split(" ");
			if (lineMessageSpl.length <= 1) {
				Util.preLineWidth = lineWidth;
				return;
			}

			String lineMessageWithoutLastWord = strippedLineMessage.substring(0, strippedLineMessage.lastIndexOf(" "));
			String lineMessageLastWord = lineMessageSpl[lineMessageSpl.length - 1];
			if (endsWithSpace) {
				lineMessageLastWord = lineMessageLastWord + " ";
			}

			ScreenFunctions.signSetMessage(signEditScreen, lineMessageWithoutLastWord, signLine, false);
			ScreenFunctions.signSetMessage(signEditScreen, lineMessageLastWord, signLine + 1, true);
			ScreenFunctions.getSignFieldFromScreen(signEditScreen).setCursorToEnd();

			Util.preJumpLine = signLine;
			if (!Util.linesJumped.contains(signLine)) {
				Util.linesJumped.add(signLine);
			}

			Util.preLineWidth = Constants.font.width(lineMessageLastWord);
		}
		else if (Util.preJumpLine >= 0 && signLine > 0) {
			int lastLineWidth = Constants.font.width(messages[signLine-1] + " ");
			if (lastLineWidth + lineWidth > Constants.maxSignWidth || lastLineWidth < 10 || signLine == Util.startLine) {
				Util.preLineWidth = lineWidth;
				return;
			}

			String newContent = messages[signLine-1] + " " + messages[signLine].strip();

			ScreenFunctions.signSetMessage(signEditScreen, newContent, signLine - 1, true);
			ScreenFunctions.signSetMessage(signEditScreen, "", signLine, false);
			ScreenFunctions.getSignFieldFromScreen(signEditScreen).setCursorToEnd();

			if (Util.linesJumped.contains(signLine - 1)) {
				Util.preJumpLine = signLine - 1;
			}
			else {
				signLine = -1;
			}

			Util.preLineWidth = lineWidth;
		}
	}
}
