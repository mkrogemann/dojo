package org.rubyspa.dojo.yatzy;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class YatzyTests {

    private enum YatzyCategory {
        ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, YATZY, SMALL_STRAIGHT, LARGE_STRAIGHT
    }

    @Test
    public void category_applies_one_pair() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 1, 5), YatzyCategory.ONE_PAIR));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.ONE_PAIR));
    }

    @Test
    public void category_applies_two_pairs() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 1, 2), YatzyCategory.TWO_PAIRS));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.TWO_PAIRS));
    }

    @Test
    public void category_applies_three_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 1, 1, 3), YatzyCategory.THREE_OF_A_KIND));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.THREE_OF_A_KIND));
    }

    @Test
    public void category_applies_four_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 1, 1, 1), YatzyCategory.FOUR_OF_A_KIND));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.FOUR_OF_A_KIND));
    }

    @Test
    public void category_applies_full_house() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 1, 1, 2, 2), YatzyCategory.FULL_HOUSE));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 1, 2, 2, 5), YatzyCategory.FULL_HOUSE));
    }

    @Test
    public void category_applies_yatzy() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(5, 5, 5, 5, 5), YatzyCategory.YATZY));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 1, 2, 2, 5), YatzyCategory.YATZY));
    }

    @Test
    public void category_applies_small_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.SMALL_STRAIGHT));
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 5, 4), YatzyCategory.SMALL_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 4, 5, 6), YatzyCategory.SMALL_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 3, 5, 6), YatzyCategory.SMALL_STRAIGHT));
    }

    @Test
    public void category_applies_large_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 4, 5, 6), YatzyCategory.LARGE_STRAIGHT));
        assertTrue(yatzyCutdown.categoryApplies(Arrays.asList(3, 2, 6, 5, 4), YatzyCategory.LARGE_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.LARGE_STRAIGHT));
        assertFalse(yatzyCutdown.categoryApplies(Arrays.asList(2, 3, 3, 5, 6), YatzyCategory.LARGE_STRAIGHT));
    }

    @Test
    public void score_one_pair() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(2), yatzyCutdown.score(Arrays.asList(1, 2, 3, 1, 5), YatzyCategory.ONE_PAIR));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.ONE_PAIR));
    }

    @Test
    public void score_two_pairs() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(6), yatzyCutdown.score(Arrays.asList(1, 2, 3, 1, 2), YatzyCategory.TWO_PAIRS));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.TWO_PAIRS));
    }

    @Test
    public void score_three_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(15), yatzyCutdown.score(Arrays.asList(5, 2, 5, 5, 3), YatzyCategory.THREE_OF_A_KIND));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.THREE_OF_A_KIND));
    }

    @Test
    public void score_four_of_a_kind() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(16), yatzyCutdown.score(Arrays.asList(4, 2, 4, 4, 4), YatzyCategory.FOUR_OF_A_KIND));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.FOUR_OF_A_KIND));
    }

    @Test
    public void score_full_house() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(24), yatzyCutdown.score(Arrays.asList(4, 6, 6, 4, 4), YatzyCategory.FULL_HOUSE));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.FULL_HOUSE));
    }

    @Test
    public void score_yatzy() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(25), yatzyCutdown.score(Arrays.asList(5, 5, 5, 5, 5), YatzyCategory.YATZY));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.YATZY));
    }

    @Test
    public void score_small_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(15), yatzyCutdown.score(Arrays.asList(1, 2, 3, 4, 5), YatzyCategory.SMALL_STRAIGHT));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(2, 3, 4, 5, 6), YatzyCategory.SMALL_STRAIGHT));
    }

    @Test
    public void score_large_straight() throws Exception {
        final YatzyCutdown yatzyCutdown = new YatzyCutdown();
        assertEquals(Integer.valueOf(20), yatzyCutdown.score(Arrays.asList(2, 3, 4, 5, 6), YatzyCategory.LARGE_STRAIGHT));
        assertEquals(Integer.valueOf(0), yatzyCutdown.score(Arrays.asList(1, 3, 4, 5, 6), YatzyCategory.LARGE_STRAIGHT));
    }


    private class YatzyCutdown {
        public boolean categoryApplies(List<Integer> rolls, YatzyCategory category) {
            switch (category) {
                case ONE_PAIR:
                    return hasOnePair(rolls);
                case TWO_PAIRS:
                    return hasTwoPairs(rolls);
                case THREE_OF_A_KIND:
                    return hasThreeOfAKind(rolls);
                case FOUR_OF_A_KIND:
                    return hasFourOfAKind(rolls);
                case FULL_HOUSE:
                    return isFullHouse(rolls);
                case YATZY:
                    return isYatzy(rolls);
                case SMALL_STRAIGHT:
                    return isSmallStraight(rolls);
                case LARGE_STRAIGHT:
                    return isLargeStraight(rolls);
                default:
                    throw new IllegalArgumentException("Illegal category: " + category.name());
            }
        }

        private boolean hasOnePair(List<Integer> rolls) {
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            return bucketList.size() == 4;
        }

        private boolean hasTwoPairs(List<Integer> rolls) {
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            return bucketList.stream().filter(b -> b.count == 2).count() == 2 && bucketList.size() == 3;
        }

        private boolean hasThreeOfAKind(List<Integer> rolls) {
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            return bucketList.stream().filter(b -> b.count == 3).count() == 1 && bucketList.size() == 3;
        }

        private boolean hasFourOfAKind(List<Integer> rolls) {
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            return bucketList.stream().filter(b -> b.count == 4).count() == 1 && bucketList.size() == 2;
        }

        private boolean isFullHouse(List<Integer> rolls) {
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            return bucketList.stream().filter(b -> b.count == 3).count() == 1
                    && bucketList.stream().filter(b -> b.count == 2).count() == 1;
        }

        private boolean isYatzy(List<Integer> rolls) {
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            return bucketList.stream().filter(b -> b.count == 5).count() == 1;
        }

        private boolean isSmallStraight(List<Integer> rolls) {
            final Map<Integer, Integer> buckets = new BucketMap(rolls).buckets;
            return buckets.keySet().containsAll(Arrays.asList(1, 2, 3, 4, 5));
        }

        private boolean isLargeStraight(List<Integer> rolls) {
            final Map<Integer, Integer> buckets = new BucketMap(rolls).buckets;
            return buckets.keySet().containsAll(Arrays.asList(2, 3, 4, 5, 6));
        }

        public Integer score(List<Integer> rolls, YatzyCategory category) {
            if (!categoryApplies(rolls, category)) {
                return 0;
            }
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            switch (category) {
                case ONE_PAIR:
                case TWO_PAIRS:
                    return 2 * bucketList.stream().filter(b -> b.count == 2).mapToInt(b -> b.roll).sum();
                case THREE_OF_A_KIND:
                    return 3 * bucketList.stream().filter(b -> b.count == 3).mapToInt(b -> b.roll).sum();
                case FOUR_OF_A_KIND:
                    return 4 * bucketList.stream().filter(b -> b.count == 4).mapToInt(b -> b.roll).sum();
                case FULL_HOUSE:
                case YATZY:
                case SMALL_STRAIGHT:
                case LARGE_STRAIGHT:
                    return rolls.stream().reduce(0, Integer::sum);
                default:
                    throw new IllegalArgumentException("Illegal category: " + category.name());
            }
        }

        private Stream<BucketMap.Bucket> filter(List<Integer> rolls, YatzyCategory category) {
            final List<BucketMap.Bucket> bucketList = new BucketMap(rolls).toBucketList();
            switch (category) {
                case TWO_PAIRS:
                    return bucketList.stream().filter(b -> b.count == 2);
                default:
                    throw new IllegalArgumentException("Illegal category: " + category.name());
            }
        }

        private class BucketMap {
            private Map<Integer, Integer> buckets = new HashMap<>();

            public BucketMap(List<Integer> rolls) {
                for (Integer roll : rolls) {
                    if (buckets.containsKey(roll)) {
                        buckets.put(roll, buckets.get(roll) + 1);
                    } else {
                        buckets.put(roll, 1);
                    }
                }
            }

            public List<Bucket> toBucketList() {
                final ArrayList<Bucket> bucketList = new ArrayList<>();
                for (Map.Entry<Integer, Integer> entry : buckets.entrySet()) {
                    bucketList.add(new Bucket(entry.getKey(), entry.getValue()));
                }
                return bucketList;
            }

            private class Bucket {
                public final Integer roll;
                public final Integer count;

                private Bucket(Integer roll, Integer count) {
                    this.roll = roll;
                    this.count = count;
                }
            }
        }
    }
}


