package com.gitlab.jactor.rises.web.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class MenuItems {
    private final Map<String, MenuItem> menuItemByTarget = new HashMap<>();

    void addAll(List<MenuItem> menuItems) {
        menuItemByTarget.putAll(menuItems.stream()
                .collect(toMap(MenuItem::getTarget, menuItem -> menuItem))
        );
    }

    List<MenuItem> fetchAll() {
        return new ArrayList<>(menuItemByTarget.values());
    }

    boolean hasTarget(String target) {
        return menuItemByTarget.containsKey(target);
    }

    boolean isNotEmpty() {
        return !menuItemByTarget.isEmpty();
    }
}
