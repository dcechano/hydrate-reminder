package com.hydratereminder.dictionary;

import com.google.gson.Gson;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import static com.hydratereminder.Commons.HYDRATION_REMINDER_BREAKS_FILE;
@UtilityClass
@Slf4j
public class HydrateWelcomeMessageDictionary {

    /**
     * Hydrate Reminder startup welcome text to display
     */
    private static final List<String> HYDRATE_WELCOME_TEXT_LIST =
            Collections.unmodifiableList(
                    new ArrayList<String>() {{
                        add("Don't forget to stay hydrated.");
                        add("Type \"::hr help\" in chat to view available commands.");
                        add("Stay cool. Stay awesome. Stay hydrated.");
                        add("Keep calm and stay hydrated.");
                        add("Cheers to staying hydrated!");
                        add("Keep the geyser titans happy by staying hydrated.");
                        add("Hydration is love. Hydration is life.");
                        add("Out of water? Cast humidify to stay hydrated.");
                        add("It costs zero water runes to stay hydrated.");
                        add("Check out the hydrate commands by typing \"::hr help\" in chat.");
                        add("A hydrated adventurer is an unstoppable adventurer.");
                        add("It's dangerous to go alone. Stay hydrated!");
                        add("Welcome traveler. Nothing hurts morale like dehydration! Remember to drink water.");
                        add("People who don't believe in magic have obviously never had water!");
                        add("You're 70% water. Don't forget to stay hydrated!");
                        add("\"Thousands have lived without love, not one without water.\" - W.J. Auden");
                        add("Having trouble focusing? Sounds like you need to drink some water ;)");
                        add("\"If there is magic on this planet, it is contained in water.\" - Loren Eiseley");
                        add("Feeling low on energy? Drink some water!");
                        add("Nothing like RuneScape and a tall glass of ice cold water!");
                        add("I suppose we'll allow tea... For now.");
                        add("Type \"::hr next\" to view the time remaining until the next hydration break!");
                        add("Save key strokes by using the short hand \"::hr <command>\" instead of \"::hr <command>\"");
                        add("Imagine getting in a sword fight while dehydrated. What. A. Nightmare.");
                        add("Don't forget to stay hydrated while out and about!");
                        add("A wise traveler is a hydrated traveler.");
                        add("Remember to drink plenty of water!");
                        add("I once started a quest without adequate water. NEVER AGAIN.");
                        add("Welcome! Stay hydrated out there!");
                        add("Welcome traveler. Would it be rude to say you look a little thirsty?");
                        add("\"Thousands have lived without love, not one without water\". ― W. H. Auden");
                        add("It's possible to decant water into other containers, resulting in MORE HYDRATION!!!");
                        add("Aaah, nothing like a nice skinna water!");
                        add("It's possible to live several days without water, but not with this plugin");
                    }});

    public static String getRandomWelcomeMessage() {
        // Before returning a welcome message, check if this is a fresh install first!
        if (isFreshInstall()) {
            return "Welcome and thanks for installing or upgrading to the RuneLite Hydrate Reminder plugin v2.0.0!" +
                    "Adjust settings in the plugin configuration menu and type \"::hr help\" in chat to view available commands.";
        }

        final SecureRandom randomGenerator = new SecureRandom();
        final int randomNumber = randomGenerator.nextInt(HYDRATE_WELCOME_TEXT_LIST.size());
        return HYDRATE_WELCOME_TEXT_LIST.get(randomNumber);
    }

    public static boolean isFreshInstall() {
        final Path filePath = Paths.get(HYDRATION_REMINDER_BREAKS_FILE.getPath());
        try {
            final String json = new String(Files.readAllBytes(filePath));
            final Gson gson = new Gson();
            final Map<String, String> map = gson.fromJson(json, Map.class);
            final String flag = map.get("installMessageFlag");
            if (flag == null || flag.equals("0")) {
                map.put("installMessageFlag", "1");
                String update = gson.toJson(map, Map.class);
                Files.write(filePath, update.getBytes());
                return true;
            }
            return false;

        } catch (IOException e) {
            if (log.isWarnEnabled()) {
                log.warn("IOException for file {}: {}", HYDRATION_REMINDER_BREAKS_FILE, e.getMessage());
            }
            return false;
        }
    }

}
