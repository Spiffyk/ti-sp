package tisp;

/**
 * The inputs for the garage door control automaton.
 */
public enum Input {

	/**
	 * The control button input. Triggered when a user pushes the button.
	 */
	BTN,

	/**
	 * The light timeout timer input. Triggered when the timer runs out.
	 */
	TMR,

	/**
	 * The upper trigger input. Triggered when the door is fully open.
	 */
	T_UP,

	/**
	 * The lower trigger input. Triggered when the door is fully closed.
	 */
	T_BOT,

	/**
	 * The photocell input. Triggered by the photocell (e.g. when a car drives through while the door is closing).
	 */
	PTC

}
