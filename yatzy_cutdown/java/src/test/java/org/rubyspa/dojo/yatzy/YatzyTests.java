package org.rubyspa.dojo.yatzy;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class YatzyTests {

    @Test
    public void category_applies_one_pair() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 1, 5), YatzyCutdown.YatzyCategory.ONE_PAIR));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.ONE_PAIR));
    }

    @Test
    public void category_applies_two_pairs() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 1, 2), YatzyCutdown.YatzyCategory.TWO_PAIRS));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.TWO_PAIRS));
    }

    @Test
    public void category_applies_three_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 1, 1, 3), YatzyCutdown.YatzyCategory.THREE_OF_A_KIND));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.THREE_OF_A_KIND));
    }

    @Test
    public void category_applies_four_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 1, 1, 1), YatzyCutdown.YatzyCategory.FOUR_OF_A_KIND));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.FOUR_OF_A_KIND));
    }

    @Test
    public void category_applies_full_house() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 1, 1, 2, 2), YatzyCutdown.YatzyCategory.FULL_HOUSE));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 1, 2, 2, 5), YatzyCutdown.YatzyCategory.FULL_HOUSE));
    }

    @Test
    public void category_applies_yatzy() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(5, 5, 5, 5, 5), YatzyCutdown.YatzyCategory.YATZY));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 1, 2, 2, 5), YatzyCutdown.YatzyCategory.YATZY));
    }

    @Test
    public void category_applies_small_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.SMALL_STRAIGHT));
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 5, 4), YatzyCutdown.YatzyCategory.SMALL_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 4, 5, 6), YatzyCutdown.YatzyCategory.SMALL_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 3, 5, 6), YatzyCutdown.YatzyCategory.SMALL_STRAIGHT));
    }

    @Test
    public void category_applies_large_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 4, 5, 6), YatzyCutdown.YatzyCategory.LARGE_STRAIGHT));
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(3, 2, 6, 5, 4), YatzyCutdown.YatzyCategory.LARGE_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.LARGE_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 3, 5, 6), YatzyCutdown.YatzyCategory.LARGE_STRAIGHT));
    }

    @Test
    public void score_one_pair() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(2), yatzyCutdown.score(Arrays.asList(1, 2, 3, 1, 5), YatzyCutdown.YatzyCategory.ONE_PAIR));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.ONE_PAIR));
    }

    @Test
    public void score_two_pairs() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(6), yatzyCutdown.score(Arrays.asList(1, 2, 3, 1, 2), YatzyCutdown.YatzyCategory.TWO_PAIRS));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.TWO_PAIRS));
    }

    @Test
    public void score_three_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(15), yatzyCutdown.score(Arrays.asList(5, 2, 5, 5, 3), YatzyCutdown.YatzyCategory.THREE_OF_A_KIND));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.THREE_OF_A_KIND));
    }

    @Test
    public void score_four_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(16), yatzyCutdown.score(Arrays.asList(4, 2, 4, 4, 4), YatzyCutdown.YatzyCategory.FOUR_OF_A_KIND));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.FOUR_OF_A_KIND));
    }

    @Test
    public void score_full_house() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(24), yatzyCutdown.score(Arrays.asList(4, 6, 6, 4, 4), YatzyCutdown.YatzyCategory.FULL_HOUSE));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.FULL_HOUSE));
    }

    @Test
    public void score_yatzy() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(50), yatzyCutdown.score(Arrays.asList(5, 5, 5, 5, 5), YatzyCutdown.YatzyCategory.YATZY));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.YATZY));
    }

    @Test
    public void score_small_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(15), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCutdown.YatzyCategory.SMALL_STRAIGHT));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(2, 3, 4, 5, 6), YatzyCutdown.YatzyCategory.SMALL_STRAIGHT));
    }

    @Test
    public void score_large_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(20), yatzyCutdown.score(Arrays.asList(2, 3, 4, 5, 6), YatzyCutdown.YatzyCategory.LARGE_STRAIGHT));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 3, 4, 5, 6), YatzyCutdown.YatzyCategory.LARGE_STRAIGHT));
    }


}


