//package com.direa.zuul.ratelimit;
//
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Bucket4j;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class RatelimitService {
//    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
//
//    public Bucket resolveBucket(String apiKey) {
//        return cache.computeIfAbsent(apiKey, this::newBucket);
//    }
//
//    private Bucket newBucket(String apiKey) {
//        Plan plan = Plan.resolvePlanFromApiKey(apiKey);
//        return Bucket4j.builder()
//                .addLimit(Plan.getLimit())
//                .build();
//    }
//
//}
