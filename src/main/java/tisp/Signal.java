package tisp;

/**
 * The output signals of the garage control automaton.
 */
public enum Signal {

	/**
	 * Signal to stop the motor.
	 */
	MOT_STOP,

	/**
	 * Signal to start the motor in the door opening direction.
	 */
	MOT_OP,

	/**
	 * Signal to start the motor in the door closing direction.
	 */
	MOT_CL,

	/**
	 * Signal to turn on the light inside of the garage.
	 */
	LIGHT_ON,

	/**
	 * Signal to turn off the light inside of the garage.
	 */
	LIGHT_OFF,

	/**
	 * Signal to start the timer for turning off the light while the garage is fully open or closed.
	 */
	TIMER;
}
