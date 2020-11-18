//package com.direa.zuul.filter;
//
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Bucket4j;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class PlanService {
//    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
//
//    public Bucket resolveBucket(String apiKey) {
//        return cache.computeIfAbsent(apiKey, this::newBucket);
//    }
//
//    private Bucket newBucket(String apiKey) {
//        PricingPlan pricingPlan = PricingPlan.resolvePlanFromApiKey(apiKey);
//        return bucket(pricingPlan.getLimit());
//    }
//
//    private Bucket bucket(Bandwidth limit) {
//        return Bucket4j.builder()
//                .addLimit(limit)
//                .build();
//    }
//}
