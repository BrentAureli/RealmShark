package tomato.addons.O3Trigger;

import java.util.ArrayList;
import java.util.List;

public class O3Trigger {
    private O3Loader loader;
    private List<Trigger> triggers;

    public O3Trigger() {
        loader = new O3Loader();
        triggers = new ArrayList<>();

        triggers.add(new Trigger("crumple before me", "Crumple"));
        triggers.add(new Trigger("crumple beneath me", "Crumple"));
        triggers.add(new Trigger("Accept your fate", "Fate_possible_stagger"));
        triggers.add(new Trigger("nowhere to run", "nowhere_to_run"));
        triggers.add(new Trigger("Fleeing is futile", "Fleeing"));
        triggers.add(new Trigger("smash you to pieces", "Shield_Bashes_possible_stagger"));
        triggers.add(new Trigger("escape from my control", "Control"));
        triggers.add(new Trigger("Melt before my fury", "Fury_Melts_possible_stagger"));
        triggers.add(new Trigger("Stand back in cowardice", "cowardice"));
        triggers.add(new Trigger("There is nowhere to hide", "Inner_Rotation"));
        triggers.add(new Trigger("I will strike you down", "Outer_Rotation_possible_stagger"));
        triggers.add(new Trigger("Every slash shall slice", "Slashes"));
        triggers.add(new Trigger("Gaze at my power", "Gaze_possible_stagger"));
        triggers.add(new Trigger("Panic and scream", "Panic_and_Scream_possible_stagger"));
        triggers.add(new Trigger("quakes from my splendor", "Splendor_possible_stagger"));
        triggers.add(new Trigger("cosmos shall rain", "Cosmos"));
        triggers.add(new Trigger("Before crushing the life out of you", "Exalted_Phase"));
        triggers.add(new Trigger("Dance, you helpless creatures!", "Dance_Phase"));
        triggers.add(new Trigger("The heavens are mine to command!", "Heavens_Phase"));
        triggers.add(new Trigger("Kneel to your ruler!", "Celestial_Phase"));
        triggers.add(new Trigger("NO! This cannot be", "Stagger"));
        triggers.add(new Trigger("You are unfit to speak", "Counter"));
        triggers.add(new Trigger("You have trampled my minions", "Starting"));
        triggers.add(new Trigger("oryx_minions_failed", "Realm_is_Shaking"));

    }

    public void checkTrigger(String input) {
        for (Trigger trigger : triggers) {
            if (input.contains(trigger.getSearch())) {
                System.out.println("Matched Trigger! Saying: " + trigger.getSay());
                loader.play(trigger.getSay());
                return;
            }
        }
    }
//    public static void main(String[] args) {
//        O3Trigger trigger = new O3Trigger();
//
//        // Example usage
//        trigger.checkTrigger("xxxKneel to your ruler!xxxx"); // Replace with the actual file name (without extension)
//    }
}
