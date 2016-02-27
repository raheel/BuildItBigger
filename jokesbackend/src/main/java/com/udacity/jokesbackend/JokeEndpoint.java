/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.jokesbackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokesbackend.udacity.com",
                ownerName = "jokesbackend.udacity.com",
                packagePath = ""
        )
)
public class JokeEndpoint {
    static String[] jokes = new String[]{
            "Two bytes meet.  The first byte asks, \"Are you ill?\"\n  The second byte replies, \"No, just feeling a bit off.",
            "Eight bytes walk into a bar.  The bartender asks, \"Can I get you anything?\"\n\"Yeah,\" reply the bytes.  \"Make us a double.\"",
            "Why do programmers always mix up Halloween and Christmas?\n Because Oct 31 equals Dec 25.",
            "There are only 10 kinds of people in this world: those who know binary and those who don't",
            "A programmer walks to the butcher shop and buys a kilo of meat.  An hour later he comes back upset that the butcher shortchanged him by 24 grams.",
            "A man is smoking a cigarette and blowing smoke rings into the air.  His girlfriend becomes irritated with the smoke and says, \"Can't you see the warning on the cigarette pack?  Smoking is hazardous to your health!\nTo which the man replies, \"I am a programmer.  We don't worry about warnings; we only worry about errors.\"",
            "How many programmers does it take to change a light bulb?\n None – It's a hardware problem",
            "A programmer is walking along a beach and finds a lamp.  He rubs the lamp, and a genie appears.  \"I am the most powerful genie in the world.  I can grant you any wish, but only one wish.\"\n" +
                    "\n" +
                    "The programmer pulls out a map, points to it and says, \"I'd want peace in the Middle East.\"\n" +
                    "\n" +
                    "The genie responds, \"Gee, I don't know.  Those people have been fighting for millennia.  I can do just about anything, but this is likely beyond my limits.\"\n" +
                    "\n" +
                    "The programmer then says, \"Well, I am a programmer, and my programs have lots of users.  Please make all my users satisfied with my software and let them ask for sensible changes.\"\n" +
                    "\n" +
                    "At which point the genie responds, \"Um, let me see that map again.\"",
            "The generation of random numbers is too important to be left to chance.",
            "Debugging: Removing the needles from the haystack.",
            "One hundred little bugs in the code\n" +
                    "One hundred little bugs.\n" +
                    "Fix a bug, link the fix in,\n" +
                    "One hundred little bugs in the code."

    };


    private int counter = 0;
    /**
     * Endpoint that returns a joke
     */
    @ApiMethod(name = "tellJoke")
    public JokeBean tellJoke() {
        JokeBean response = new JokeBean();
        response.setData(jokes[counter++%jokes.length]);
        return response;
    }

}
