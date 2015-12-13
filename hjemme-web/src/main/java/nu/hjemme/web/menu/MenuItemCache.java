package nu.hjemme.web.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple caching mechanism for a list of {@link MenuItem} by {@link MenuTarget}
 */
public class MenuItemCache {

    private final Map<MenuTarget, List<MenuItem>> menuItemCache;

    public MenuItemCache() {
        menuItemCache = new HashMap<>();
    }

    public boolean isCached(MenuTarget menuTarget) {
        return menuItemCache.containsKey(menuTarget);
    }

    public void cache(MenuTarget menuItemTarget, List<MenuItem> listeOfMenuItems) {
        menuItemCache.put(menuItemTarget, listeOfMenuItems);
    }

    public List<MenuItem> fetchBy(MenuTarget menuItemTarget) {
        return menuItemCache.get(menuItemTarget);
    }
}
