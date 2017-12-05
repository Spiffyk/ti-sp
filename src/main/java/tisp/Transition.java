package tisp;

import lombok.Data;
import lombok.NonNull;

@Data
public class Transition {

	private final State stateTo;

	private final Signal[] signals;

	public Transition(@NonNull final State stateTo, @NonNull final Signal... signals) {
		this.stateTo = stateTo;
		this.signals = signals;
	}

}
