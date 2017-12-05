package tisp;

/**
 * The input signals for the garage door control automaton.
 */
public enum Input {

	/**
	 * The control button input. Triggered when a user pushes the button.
	 */
	BUTTON,

	/**
	 * The light timeout timer input. Triggered when the timer runs out.
	 */
	TIMER,

	/**
	 * The upper trigger input. Triggered when the door is fully open.
	 */
	TRIGGER_UPPER,

	/**
	 * The lower trigger input. Triggered when the door is fully closed.
	 */
	TRIGGER_LOWER,

	/**
	 * The photocell input. Triggered by the photocell (e.g. when a car drives through while the door is closing).
	 */
	PHOTOCELL

}
