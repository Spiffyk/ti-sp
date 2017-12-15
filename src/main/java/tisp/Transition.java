package tisp;

import lombok.Data;
import lombok.NonNull;

/**
 * Data class containing a target state and output signals provided for the given {@link Input}
 * in {@link State#transitionMap}.
 */
@Data
public class Transition {

	/**
	 * The target {@link State}.
	 */
	private final State stateTo;

	/**
	 * The output {@link Signal}s.
	 */
	private final Signal[] signals;

	/**
	 * Creates a new instance of {@link Transition} with the provided target {@link State} and output {@link Signal}s.
	 *
	 * @param stateTo the target state
	 * @param signals the output signals
	 */
	public Transition(@NonNull final State stateTo, @NonNull final Signal... signals) {
		this.stateTo = stateTo;
		this.signals = signals;
	}

}
