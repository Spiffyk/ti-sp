package tisp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Transition {

	private final Input input;

	private final State targetState;

	public static Transition get(@NonNull final Input input, @NonNull final State targetState) {
		// TODO - Optimize?
		return new Transition(input, targetState);
	}
}
