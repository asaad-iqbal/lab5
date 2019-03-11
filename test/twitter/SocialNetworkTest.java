/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T03:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T05:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T01:00:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T13:00:00Z");
    
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "urvah", "if we can google @saad anything they why we have to come to class? #ICT", d3);
    private static final Tweet tweet4 = new Tweet(4, "Hassan", "lets party @saad", d4);
    private static final Tweet tweet5 = new Tweet(5, "faizan", "followers @asaad @saad hai.", d5);
    private static final Tweet tweet6 = new Tweet(6, "hammad", "GPA la do @hammad @asaad @inshal ko koi!!", d6);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    } 
    @Test
    public void testInfluencerSix() {
    	Map<String, Set<String>> graphforinfluencer = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6));
        List<String> influencers = SocialNetwork.influencers(graphforinfluencer);
        List<String> Names = Arrays.asList("hammad", "faizan", "urvah", "Hassan", "bbitdiddle", "alyssa");
        assertEquals(Names, influencers);
    }
    @Test
    public void testInfluencerFour() {
    	Map<String, Set<String>> graphforinfluencer = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3,tweet4,tweet5,tweet6));
        List<String> influencers = SocialNetwork.influencers(graphforinfluencer);
        List<String> Names = Arrays.asList("hammad", "faizan", "urvah", "Hassan");
        assertEquals(Names, influencers);
    }
    @Test
    public void testInfluencerTwo() {
    	Map<String, Set<String>> graphforinfluencer = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4,tweet6));
        List<String> influencers = SocialNetwork.influencers(graphforinfluencer);
        List<String> Names = Arrays.asList("hammad", "Hassan");
        assertEquals(Names, influencers);
    }
    @Test
    public void testGuessFollowsGraphSix() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6));
        Set<String> y = new HashSet<String>();
        y.add("@saad");
        assertEquals(y,followsGraph.get("Hassan"));
    }
    @Test
    public void testGuessFollowsGraphSize() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3,tweet4,tweet5,tweet6));
        assertEquals(3,followsGraph.get("hammad").size());
    }
    @Test
    public void testGuessFollowsGraphArray() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3,tweet4,tweet5,tweet6));
        Set<String> x = new HashSet<String>();
        x.add("@hammad");
        x.add("@asaad");
        x.add("@inshal");
        assertEquals(x,followsGraph.get("hammad"));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
