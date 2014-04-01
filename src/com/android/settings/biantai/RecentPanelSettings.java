/*
                         $$$$$$$$$$$$$$$
                      $$$$$$        $$  $$$$
                     $$$             $     $$$
                   $$$$$$$          $$$      $$$
                  $$$$ $$$$        $$          $$$
                 $$$      $$       $            $$$
     FFFF       $$$$     $$                $$$    $$
    UUUUUU     $$$  $$$$$$              $$$$$$$$$  $$
               $$$                     $$     $$$  $$
               $$    $$$$$$$$$         $$      $$  $$
                $$ $$  $$$ $$$$$$$$$     $$$$$$    $$
                 $ $$      $$$  $$  $$$           $$
  $$$             $$$              $$  $$        $$$
   $$$$             $$$$$$$            $$      $$$
 $$$$$$$$$$$         $$$  $$$$$       $$     $$$$
    $$$  $$$$$$$$$   $$$     $$$$$   $$  $$$$$
  $$$$        $$$$$$$$$$$$$$$    $$$$$$$$$$$$
 $$$$               $$$     $$$$$$$$$$$
      $$           $$$            $$$
        $$        $$$            $$$
        $$$$$$$$$$$$$$$$$$       $$$
     $$$$$$$$     $$$$$$$$$$$$$  $$
        $$$$      $$$            $$
       $$$        $$            $$$
                  $$            $$$
                  $$$$$$$$$$$$$$$$$
                   $$$$$$$$$$$$$$$
                     $$$       $$$
                    $$$         $$$
                   $$$           $$$
                  $$$             $$$
                 $$$               $$$
                $$$                 $$$
               $$$                   $$$
            $$$$$                    $$$$
           $$$$$$                    $$$
                                   $$$$
*/

package com.android.settings.biantai;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.view.Gravity;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class RecentPanelSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String RECENT_PANEL_LEFTY_MODE = "recent_panel_lefty_mode";
    private static final String RECENT_PANEL_SCALE = "recent_panel_scale";

    private CheckBoxPreference mRecentPanelLeftyMode;
    private ListPreference mRecentPanelScale;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.recent_panel_settings);
        initializeAllPreferences();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mRecentPanelScale) {
            int value = Integer.parseInt((String) newValue);
            Settings.System.putInt(getContentResolver(),
                    Settings.System.RECENT_PANEL_SCALE_FACTOR, value);
            return true;

        } else if (preference == mRecentPanelLeftyMode) {
            Settings.System.putInt(getContentResolver(),
                    Settings.System.RECENT_PANEL_GRAVITY,
                    ((Boolean) newValue) ? Gravity.LEFT : Gravity.RIGHT);
            return true;

        }
        return false;
    }

    private void initializeAllPreferences() {
        mRecentPanelLeftyMode =
                (CheckBoxPreference) findPreference(RECENT_PANEL_LEFTY_MODE);
        mRecentPanelLeftyMode.setOnPreferenceChangeListener(this);

        mRecentPanelScale =
                (ListPreference) findPreference(RECENT_PANEL_SCALE);
        mRecentPanelScale.setOnPreferenceChangeListener(this);
    }

    private void updateSystemPreferences() {
        final boolean recentLeftyMode = Settings.System.getInt(getContentResolver(),
                Settings.System.RECENT_PANEL_GRAVITY, Gravity.RIGHT) == Gravity.LEFT;
        mRecentPanelLeftyMode.setChecked(recentLeftyMode);

        final int recentScale = Settings.System.getInt(getContentResolver(),
                Settings.System.RECENT_PANEL_SCALE_FACTOR, 100);
        mRecentPanelScale.setValue(recentScale + "");
    }
}
