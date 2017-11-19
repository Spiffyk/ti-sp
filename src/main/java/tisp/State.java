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
	CLOSED_LIGHT_OFF,
	ERROR;

	static {
		OPEN_LIGHT_ON.transitionMap.put(Input.BUTTON, State.CLOSING);
		OPEN_LIGHT_ON.transitionMap.put(Input.TIMER, State.OPEN_LIGHT_OFF);

		OPEN_LIGHT_OFF.transitionMap.put(Input.BUTTON, State.CLOSING);

		OPENING.transitionMap.put(Input.BUTTON, State.STOPPED_WHILE_OPENING);
		OPENING.transitionMap.put(Input.TRIGGER_UPPER, State.OPEN_LIGHT_ON);

		STOPPED_WHILE_OPENING.transitionMap.put(Input.BUTTON, State.CLOSING);
		STOPPED_WHILE_OPENING.transitionMap.put(Input.PHOTOCELL, State.OPENING);

		CLOSING.transitionMap.put(Input.BUTTON, State.STOPPED_WHILE_CLOSING);
		CLOSING.transitionMap.put(Input.TRIGGER_LOWER, State.CLOSED_LIGHT_ON);
		CLOSING.transitionMap.put(Input.PHOTOCELL, State.OPENING);

		STOPPED_WHILE_CLOSING.transitionMap.put(Input.BUTTON, State.OPENING);
		STOPPED_WHILE_CLOSING.transitionMap.put(Input.PHOTOCELL, State.OPENING);

		CLOSED_LIGHT_ON.transitionMap.put(Input.BUTTON, State.OPENING);
		CLOSED_LIGHT_ON.transitionMap.put(Input.TIMER, State.CLOSED_LIGHT_OFF);

		CLOSED_LIGHT_OFF.transitionMap.put(Input.BUTTON, State.OPENING);
	}

	/**
	 * A map of transitions for the state.
	 */
	private HashMap<Input, State> transitionMap = new HashMap<>();

	/**
	 * Gets the next state for the specified {@link Input}. If no following state is defined for the {@code Input},
	 * the {@link State#ERROR} state is returned.
	 *
	 * @param input the input for the state
	 * @return The defined state or {@link State#ERROR} if undefined.
	 */
	public State getNextByInput(@NonNull final Input input) {
		return transitionMap.computeIfAbsent(input, i -> State.ERROR);
	}

}
