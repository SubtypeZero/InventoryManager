package me.subtypezero.store.util;

public class Logger {

	/**
	 * Log an error in a pre-determined format.
	 * @param name   the name of the object that had an error
	 * @param action the action that was attempted
	 * @param reason the reason the action failed
	 */
	public static void logError(String name, String action, String reason) {
		System.out.println(String.format("[ERROR] %s tried to %s and failed, %s.", name, action, reason));
	}
}
