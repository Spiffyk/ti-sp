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

	OPEN_LIGHT_ON,
	OPEN_LIGHT_OFF,
	OPENING,
	STOPPED_WHILE_OPENING,
	CLOSING,
	STOPPED_WHILE_CLOSING,
	CLOSED_LIGHT_ON,
	CLOSED_LIGHT_OFF;



	static {
		OPEN_LIGHT_ON.transitionMap.put(Input.BUTTON, new Transition(State.CLOSING, Signal.MOTOR_START_CLOSING));
		OPEN_LIGHT_ON.transitionMap.put(Input.TIMER, new Transition(State.OPEN_LIGHT_OFF, Signal.LIGHT_OFF));

		OPEN_LIGHT_OFF.transitionMap.put(Input.BUTTON, new Transition(State.CLOSING, Signal.MOTOR_START_CLOSING, Signal.LIGHT_ON));

		OPENING.transitionMap.put(Input.BUTTON, new Transition(State.STOPPED_WHILE_OPENING, Signal.MOTOR_STOP));
		OPENING.transitionMap.put(Input.TRIGGER_UPPER, new Transition(State.OPEN_LIGHT_ON, Signal.MOTOR_STOP));

		STOPPED_WHILE_OPENING.transitionMap.put(Input.BUTTON, new Transition(State.CLOSING, Signal.MOTOR_START_CLOSING));
		STOPPED_WHILE_OPENING.transitionMap.put(Input.PHOTOCELL, new Transition(State.OPENING, Signal.MOTOR_START_OPENING));

		CLOSING.transitionMap.put(Input.BUTTON, new Transition(State.STOPPED_WHILE_CLOSING, Signal.MOTOR_STOP));
		CLOSING.transitionMap.put(Input.TRIGGER_LOWER, new Transition(State.CLOSED_LIGHT_ON, Signal.MOTOR_STOP));
		CLOSING.transitionMap.put(Input.PHOTOCELL, new Transition(State.OPENING, Signal.MOTOR_START_OPENING));

		STOPPED_WHILE_CLOSING.transitionMap.put(Input.BUTTON, new Transition(State.OPENING, Signal.MOTOR_START_OPENING));
		STOPPED_WHILE_CLOSING.transitionMap.put(Input.PHOTOCELL, new Transition(State.OPENING, Signal.MOTOR_START_OPENING));

		CLOSED_LIGHT_ON.transitionMap.put(Input.BUTTON, new Transition(State.OPENING, Signal.MOTOR_START_OPENING));
		CLOSED_LIGHT_ON.transitionMap.put(Input.TIMER, new Transition(State.CLOSED_LIGHT_OFF, Signal.LIGHT_OFF));

		CLOSED_LIGHT_OFF.transitionMap.put(Input.BUTTON, new Transition(State.OPENING, Signal.MOTOR_START_OPENING, Signal.LIGHT_ON));
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
