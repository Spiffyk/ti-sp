package tisp;

import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;

/**
 * The state enum of the garage door control automaton. Each enum has a table of transitions for {@link Input}s defined.
 * There is a light in the garage: unless the name of the state explicitly specifies it, the light is on.
 */
@ToString
public enum State {

	/**
	 * State where the garage is open and the light inside is on.
	 */
	OPEN_LIGHT_ON,

	/**
	 * State where the garage is open and the light inside is of.
	 */
	OPEN_LIGHT_OFF,

	/**
	 * State where the garage is in the process of being opened.
	 */
	OPENING,

	/**
	 * State where the garage was stopped while opening.
	 */
	STOPPED_WHILE_OPENING,

	/**
	 * State where the garage is in the process of being closed.
	 */
	CLOSING,

	/**
	 * State where the garage was stopped while closing.
	 */
	STOPPED_WHILE_CLOSING,

	/**
	 * State where the garage is closed and the light inside is on.
	 */
	CLOSED_LIGHT_ON,

	/**
	 * State where the garage is closed and the light inside is of.
	 */
	CLOSED_LIGHT_OFF;



	// Initialization block of the transition table.
	static {
		OPEN_LIGHT_ON.transitionMap.put(Input.BTN, new Transition(State.CLOSING, Signal.MOT_CL));
		OPEN_LIGHT_ON.transitionMap.put(Input.TMR, new Transition(State.OPEN_LIGHT_OFF, Signal.LIGHT_OFF));

		OPEN_LIGHT_OFF.transitionMap.put(Input.BTN, new Transition(State.CLOSING, Signal.MOT_CL, Signal.LIGHT_ON));

		OPENING.transitionMap.put(Input.BTN, new Transition(State.STOPPED_WHILE_OPENING, Signal.MOT_STOP));
		OPENING.transitionMap.put(Input.T_UP, new Transition(State.OPEN_LIGHT_ON, Signal.MOT_STOP, Signal.TIMER));

		STOPPED_WHILE_OPENING.transitionMap.put(Input.BTN, new Transition(State.CLOSING, Signal.MOT_CL));
		STOPPED_WHILE_OPENING.transitionMap.put(Input.PTC, new Transition(State.OPENING, Signal.MOT_OP));

		CLOSING.transitionMap.put(Input.BTN, new Transition(State.STOPPED_WHILE_CLOSING, Signal.MOT_STOP));
		CLOSING.transitionMap.put(Input.T_BOT, new Transition(State.CLOSED_LIGHT_ON, Signal.MOT_STOP, Signal.TIMER));
		CLOSING.transitionMap.put(Input.PTC, new Transition(State.OPENING, Signal.MOT_OP));

		STOPPED_WHILE_CLOSING.transitionMap.put(Input.BTN, new Transition(State.OPENING, Signal.MOT_OP));
		STOPPED_WHILE_CLOSING.transitionMap.put(Input.PTC, new Transition(State.OPENING, Signal.MOT_OP));

		CLOSED_LIGHT_ON.transitionMap.put(Input.BTN, new Transition(State.OPENING, Signal.MOT_OP));
		CLOSED_LIGHT_ON.transitionMap.put(Input.TMR, new Transition(State.CLOSED_LIGHT_OFF, Signal.LIGHT_OFF));

		CLOSED_LIGHT_OFF.transitionMap.put(Input.BTN, new Transition(State.OPENING, Signal.MOT_OP, Signal.LIGHT_ON));
	}



	/**
	 * A map of transitions for the state.
	 */
	private HashMap<Input, Transition> transitionMap = new HashMap<>();

	/**
	 * Gets the next transition for the specified {@link Input}. If no following transition is defined for
	 * the {@code Input}, a transition with no signals and {@code this} state is returned.
	 *
	 * @param input the input for the state
	 * @return The defined {@link Transition} or an empty one if undefined.
	 */
	public Transition getNextByInput(@NonNull final Input input) {
		if (transitionMap.containsKey(input)) {
			return transitionMap.get(input);
		} else {
			return new Transition(this);
		}
	}

}
