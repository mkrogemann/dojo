package org.rubyspa.dojo.yatzy;

import java.util.*;

public class YatzyCutdown {
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

    enum YatzyCategory {
        ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, YATZY, SMALL_STRAIGHT, LARGE_STRAIGHT
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

        public List<BucketMap.Bucket> toBucketList() {
            final ArrayList<BucketMap.Bucket> bucketList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : buckets.entrySet()) {
                bucketList.add(new BucketMap.Bucket(entry.getKey(), entry.getValue()));
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
