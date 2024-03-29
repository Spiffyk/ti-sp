package tisp.app;

import tisp.Garage;
import tisp.Input;
import tisp.Transition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Shell-based application class.
 */
public class ShellApp {

	/**
	 * Application entry point.
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {
		Garage garage = new Garage();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Type '?' or 'H' and press <ENTER> to show help");

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
						input = Input.BTN;
						break;
					case 'U':
						input = Input.T_UP;
						break;
					case 'L':
						input = Input.T_BOT;
						break;
					case 'T':
						input = Input.TMR;
						break;
					case 'P':
						input = Input.PTC;
						break;
					case 'R':
						System.out.println("Resetting the garage.");
						garage = new Garage();
						continue;
					case 'X':
					case 'Q':
						exitRequested = true;
						break;
					case '?':
					case 'H':
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

			System.out.println("(Input was " + input.name() + ")");
			final Transition transition = garage.input(input);
			if (transition.getSignals().length > 0) {
				System.out.format("(Signals were: %s)\n", Arrays.toString(transition.getSignals()));
			} else {
				System.out.println("(No signals)");
			}
			System.out.println();
		}
	}

	/**
	 * Prints help into {@code stdout}.
	 */
	private static void printHelp() {
		System.out.println(
				"Only the first input character is used, input is case-insensitive.\n" +
				"\n" +
				"Possible options:\n" +
				"\n" +
				"B - BTN (button) input\n" +
				"U - T_UP (upper trigger) input\n" +
				"L - T_BOT (lower trigger) input\n" +
				"T - TMR (timer) input\n" +
				"P - PTC (photocell) input\n" +
				"\n" +
				"R - resets the garage\n" +
				"X, Q - exits the application\n" +
				"?, H - shows help\n"
		);
	}

}
