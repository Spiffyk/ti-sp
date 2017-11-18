package tisp;

import lombok.Getter;
import lombok.ToString;

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
		OPEN_LIGHT_ON.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.CLOSING),
				Transition.get(Input.TIMER, State.OPEN_LIGHT_OFF)
		};

		OPEN_LIGHT_OFF.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.CLOSING)
		};

		OPENING.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.STOPPED_WHILE_OPENING),
				Transition.get(Input.TRIGGER_UPPER, State.OPEN_LIGHT_ON)
		};

		STOPPED_WHILE_OPENING.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.CLOSING)
		};

		CLOSING.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.STOPPED_WHILE_CLOSING),
				Transition.get(Input.TRIGGER_LOWER, State.CLOSED_LIGHT_ON),
				Transition.get(Input.PHOTOCELL, State.OPENING)
		};

		STOPPED_WHILE_CLOSING.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.OPENING)
		};

		CLOSED_LIGHT_ON.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.OPENING),
				Transition.get(Input.TIMER, State.CLOSED_LIGHT_OFF)
		};

		CLOSED_LIGHT_OFF.transitions = new Transition[] {
				Transition.get(Input.BUTTON, State.OPENING)
		};
	}

	// TODO - maybe use a map rather than an array of transitions?

	@Getter private Transition[] transitions;

}
