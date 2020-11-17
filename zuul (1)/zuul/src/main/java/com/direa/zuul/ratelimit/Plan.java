//package com.direa.zuul.ratelimit;
//
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Refill;
//
//import java.time.Duration;
//
//public enum Plan {
//    FREE {
//        Bandwidth getLimit() {
//            return Bandwidth.classic(20, Refill.intervally(20, Duration.ofHours(1)));
//        }
//    },
//    BASIC {
//        Bandwidth getLimit() {
//            return Bandwidth.classic(40, Refill.intervally(40, Duration.ofHours(1)));
//        }
//    },
//    PROFESSIONAL {
//        Bandwidth getLimit() {
//            return Bandwidth.classic(100, Refill.intervally(100, Duration.ofHours(1)));
//        }
//    };
//
//    static Plan resolvePlanFromApiKey(String apiKey) {
//        if (apiKey == null || apiKey.isEmpty()) {
//            return FREE;
//        } else if (apiKey.startsWith("PX001-")) {
//            return PROFESSIONAL;
//        } else if (apiKey.startsWith("BX001-")) {
//            return BASIC;
//        }
//        return FREE;
//    }
//}
