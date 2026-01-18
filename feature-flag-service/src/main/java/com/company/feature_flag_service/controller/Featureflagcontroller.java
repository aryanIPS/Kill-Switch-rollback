package com.company.feature_flag_service.controller;

import com.company.feature_flag_service.service.Featureflagservice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/features")
public class Featureflagcontroller {

    private final Featureflagservice featureflagservice;

    public Featureflagcontroller(Featureflagservice featureflagservice) {
        this.featureflagservice = featureflagservice;
    }



    @PutMapping("/{Key}/enable")
    public String enable(@PathVariable String key) {
        featureflagservice.enableFeature(key);
        return "Feature enabled";
    }

    @PutMapping("/{Key}/disable")
    public String disable(@PathVariable String key) {
        featureflagservice.disableFeature(key);
        return "Feature disabled";
    }



    @GetMapping("/{Key}")
    public boolean check(
            @PathVariable("Key") String Key,
            @RequestParam String env,
            @RequestParam String userId) {

        return featureflagservice.isEnabled(Key, env, userId);
    }



    @GetMapping("/order")
    public String createOrder(@RequestParam String userId) {

        boolean enabled = featureflagservice.isEnabled(
                "New_Order_flow",
                "PROD",
                userId
        );

        if (enabled) {
            return "New order flow enabled";
        }
        return "Old order flow";
    }
}

//package com.company.feature_flag_service.controller;
//import com.company.feature_flag_service.service.Featureflagservice;
//import jakarta.annotation.PostConstruct;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/features")
//public class Featureflagcontroller {
//
//    private final Featureflagservice service;
//
//    public Featureflagcontroller(Featureflagservice service) {
//        this.service = service;
//    }
//
//    @PostConstruct
//    public void init() {
//        System.out.println("🔥🔥🔥 FEATURE FLAG CONTROLLER LOADED 🔥🔥🔥");
//    }
//    // ================= HEALTH CHECK =================
//    // Isse confirm hoga controller register hua hai
//    @GetMapping("/ping")
//    public String ping() {
//        return "pong";
//    }
//
//    // ================= FEATURE CHECK =================
//    // MAIN API
//    @GetMapping("/{key}")
//    public boolean checkFeature(
//            @PathVariable String key,
//            @RequestParam("env") String env,
//            @RequestParam("userId") String userId
//    ) {
//        return service.isEnabled(key, env, userId);
//    }
//
//    // ================= ADMIN OPS =================
//
//    @PutMapping("/{key}/enable")
//    public String enableFeature(@PathVariable String key) {
//        service.enableFeature(key);
//        return "Feature enabled";
//    }
//
//    @PutMapping("/{key}/disable")
//    public String disableFeature(@PathVariable String key) {
//        service.disableFeature(key);
//        return "Feature disabled";
//    }
//}
//
