package tisp;

/**
 * The input signals for the garage door control automaton.
 */
public enum Input {

	/**
	 * The control button signal. Triggered when a user pushes the button.
	 */
	BUTTON,

	/**
	 * The light timeout timer signal. Triggered when the timer runs out.
	 */
	TIMER,

	/**
	 * The upper trigger signal. Triggered when the door is fully open.
	 */
	TRIGGER_UPPER,

	/**
	 * The lower trigger signal. Triggered when the door is fully closed.
	 */
	TRIGGER_LOWER,

	/**
	 * The photocell signal. Triggered by the photocell (e.g. when a car drives through while the door is closing).
	 */
	PHOTOCELL

}
