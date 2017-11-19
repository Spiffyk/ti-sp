package tisp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GarageApp {

	public static void main(String[] args) {
		final Garage garage = new Garage(State.CLOSED_LIGHT_OFF);
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Type '?' and <ENTER> to show help");

		while(true) {
			boolean exitRequested = false;
			Input input = null;

			System.out.println("\nCurrent state is " + garage.getCurrentState().name());
			System.out.print("> ");

			try {
				final String s = br.readLine();
				if (s.isEmpty()) {
					continue;
				}

				final char c = Character.toUpperCase(s.charAt(0));
				switch (c) {
					case 'B':
						input = Input.BUTTON;
						break;
					case 'U':
						input = Input.TRIGGER_UPPER;
						break;
					case 'L':
						input = Input.TRIGGER_LOWER;
						break;
					case 'T':
						input = Input.TIMER;
						break;
					case 'P':
						input = Input.PHOTOCELL;
						break;
					case 'X':
						exitRequested = true;
						break;
					case '?':
						printHelp();
						continue;
					default:
						System.out.println("Unknown input");
						continue;
				}
			} catch (final IOException e) {
				throw new RuntimeException("Error while reading input!", e);
			}

			if (exitRequested) {
				System.out.println("Exit requested.");
				break;
			}

			System.out.println("(Input was " + input.name() + ")\n");
			garage.signal(input);
		}
	}

	private static void printHelp() {
		System.out.println(
				"Only the first input character is used, case-insensitive.\n" +
				"\n" +
				"Possible options:\n" +
				"\n" +
				"B - BUTTON input\n" +
				"U - TRIGGER_UPPER input\n" +
				"L - TRIGGER_LOWER input\n" +
				"T - TIMER input\n" +
				"P - PHOTOCELL input\n" +
				"X - exits the application\n" +
				"? - shows help\n"
		);
	}

}
