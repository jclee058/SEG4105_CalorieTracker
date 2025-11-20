package com.example.seg4105_calorietrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Helper class to set up reusable top and bottom navigation.
 */
public class NavHelper {
    public enum NavItem { HOME, CALENDAR, SETTINGS }

    /**
     * Set up Bottom Navigation click listeners.
     * Avoids re-opening the current tab.
     */
    public static void setupBottomNav(AppCompatActivity activity, NavItem current) {

        // Bottom nav containers
        LinearLayout navHome = activity.findViewById(R.id.navHome);
        LinearLayout navCalendar = activity.findViewById(R.id.navCalendar);
        LinearLayout navSettings = activity.findViewById(R.id.navSettings);

        // Go to Home page
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                if (current != NavItem.HOME) {
                    Intent i = new Intent(activity, HomeActivity.class);
                    // Reuse existing activity if possible
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(i);
                }
            });
        }

        // Go to Calendar page
        if (navCalendar != null) {
            navCalendar.setOnClickListener(v -> {
                if (current != NavItem.CALENDAR) {
                    Intent i = new Intent(activity, CalendarActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(i);
                }
            });
        }

        // Go to Settings page
        if (navSettings != null) {
            navSettings.setOnClickListener(v -> {
                if (current != NavItem.SETTINGS) {
                    Intent i = new Intent(activity, SettingsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(i);
                }
            });
        }
    }

    /**
     * Set up Top Navigation (Back / Search / User).
     * Works even if some views are missing (null-safe).
     */
    public static void setupTopNav(Activity activity) {
        // Top nav text buttons
        TextView tvBack = activity.findViewById(R.id.tvBack);
        TextView tvSearch = activity.findViewById(R.id.tvSearch);
        TextView tvUser = activity.findViewById(R.id.tvUser);

        // Back to current screen
        if (tvBack != null) {
            tvBack.setOnClickListener(v -> activity.finish());
        }

        // Search to open Search page
        if (tvSearch != null) {
            tvSearch.setOnClickListener(v ->
                    activity.startActivity(new Intent(activity, SearchActivity.class))
            );
        }

        // To open Profile
        if (tvUser != null) {
            tvUser.setOnClickListener(v ->
                    activity.startActivity(new Intent(activity, ProfileActivity.class))
            );
        }
    }
}
