package com.sadsha.netguard;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

/**
 * SmartProfileManager
 * SadSha NetGuard differentiator feature: Presets for Gaming, Data Saver, and Strict Privacy
 */
public class SmartProfileManager {
    public enum Profile {
        STANDARD("standard", "Standard Mode"),
        GAMING("gaming", "Gaming Mode (Low Latency)"),
        DATA_SAVER("data_saver", "Data Saver Mode"),
        STRICT_PRIVACY("strict_privacy", "Strict Privacy Mode");

        private final String id;
        private final String displayName;

        Profile(String id, String displayName) {
            this.id = id;
            this.displayName = displayName;
        }

        public String getId() {
            return id;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private static final String PREF_ACTIVE_PROFILE = "sadsha_active_profile";

    public static Profile getActiveProfile(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String savedId = prefs.getString(PREF_ACTIVE_PROFILE, Profile.STANDARD.getId());
        for (Profile p : Profile.values()) {
            if (p.getId().equals(savedId)) {
                return p;
            }
        }
        return Profile.STANDARD;
    }

    public static void setActiveProfile(Context context, Profile profile) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(PREF_ACTIVE_PROFILE, profile.getId()).apply();
    }

    public static boolean shouldFilterForGaming(Context context, String packageName) {
        if (getActiveProfile(context) != Profile.GAMING) return false;
        // In gaming mode, throttle/block non-foreground social sync apps to keep latency minimal
        return isHeavySyncApp(packageName);
    }

    public static boolean shouldBlockCellular(Context context, String packageName) {
        if (getActiveProfile(context) != Profile.DATA_SAVER) return false;
        // In data saver mode, block background cellular data
        return !isEssentialApp(packageName);
    }

    private static boolean isHeavySyncApp(String pkg) {
        if (pkg == null) return false;
        return pkg.contains("cloud") || pkg.contains("backup") || pkg.contains("sync") ||
               pkg.contains("tiktok") || pkg.contains("instagram") || pkg.contains("facebook");
    }

    private static boolean isEssentialApp(String pkg) {
        if (pkg == null) return true;
        return pkg.contains("dialer") || pkg.contains("messaging") || pkg.contains("maps") ||
               pkg.contains("android") || pkg.contains("sadsha");
    }
}
