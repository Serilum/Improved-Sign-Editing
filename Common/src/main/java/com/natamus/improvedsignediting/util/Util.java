package com.natamus.improvedsignediting.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {
	public static boolean improvedEditingEnabled = true;
	public static boolean shouldResetButton = false;

	public static List<Integer> linesJumped = new ArrayList<Integer>();
	public static List<String> preMessages = Arrays.asList("", "", "", "");
	public static int preJumpLine = -1;
	public static int preLineWidth = -1;
	public static int startLine = -1;

	public static void clearEditVariables() {
		linesJumped = new ArrayList<Integer>();
		preMessages = Arrays.asList("", "", "", "");
		preJumpLine = -1;
		preLineWidth = -1;
	}

	public static boolean messagesAreEqual(List<String> messageList) {
		for (int i = 0; i < messageList.size(); i++) {
			if (!messageList.get(i).equals(preMessages.get(i))) {
				return false;
			}
		}
		return true;
	}

	public static int getStartLine(List<String> messageList) {
		int i = 0;
		for (String message : messageList) {
			if (message.strip().equals("")) {
				i += 1;
				continue;
			}
			break;
		}
		return i;
	}
}
