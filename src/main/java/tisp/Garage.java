package tisp;

import lombok.*;

@ToString
@EqualsAndHashCode
public class Garage {

	@Getter
	private State currentState;

	public Garage() {
		this(State.CLOSED_LIGHT_OFF);
	}

	public Garage(@NonNull final State initialState) {
		this.currentState = initialState;
	}

	public State signal(@NonNull final Input input) {
		return this.currentState = this.currentState.getNextByInput(input);
	}

}
