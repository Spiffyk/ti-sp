package tisp;

import lombok.*;

/**
 * The class representing the controlled garage control automaton.
 */
@ToString
@EqualsAndHashCode
public class Garage {

	/**
	 * The current state of the automaton.
	 */
	@Getter
	private State currentState;



	/**
	 * Creates a new instance with {@link State#CLOSED_LIGHT_OFF} as the initial state.
	 */
	public Garage() {
		this(State.CLOSED_LIGHT_OFF);
	}

	/**
	 * Creates a new instance with the specified initial state.
	 *
	 * @param initialState the state that the garage should start with
	 */
	public Garage(@NonNull final State initialState) {
		this.currentState = initialState;
	}



	/**
	 * Sets a new state of the garage based on the specified input.
	 *
	 * @param input the input to use
	 * @return the newly set state
	 */
	public State signal(@NonNull final Input input) {
		return this.currentState = this.currentState.getNextByInput(input);
	}

}
