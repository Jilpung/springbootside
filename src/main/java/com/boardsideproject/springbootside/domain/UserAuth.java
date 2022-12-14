//package com.boardsideproject.springbootside.domain;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//public enum UserAuth {
//    ROLE_USER("ROLE_USER"),
//    ROLE_ADMIN("ROLE_ADMIN");
//
//    private final String abbreviation;
//
//    private static final Map<String, UserAuth> lookup = new HashMap<>();
//
//    static {
//        for(UserAuth auth : UserAuth.values()){
//            lookup.put(auth.abbreviation, auth);
//        }
//    }
//
//    UserAuth(String abbreviation) {
//        this.abbreviation = abbreviation;
//    }
//
//    public String getAbbreviation() {
//        return this.abbreviation;
//    }
//
//    public static UserAuth get(String abbreviation) {
//        return lookup.get(abbreviation);
//    }
//
//    public  static  boolean containsKey(String abbreviation) {
//        return lookup.containsKey(abbreviation);
//    }
//}
