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
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class DoubleTapSleepSettings extends SettingsPreferenceFragment {

    private static final String DOUBLE_TAP_SLEEP_GLOW_PAD = "double_tap_sleep_glow_pad";
    private static final String DOUBLE_TAP_SLEEP_PATTERN = "double_tap_sleep_pattern";
    private static final String DOUBLE_TAP_SLEEP_PIN_PASSWORD = "double_tap_sleep_pin_password";
    private static final String DOUBLE_TAP_SLEEP_STATUS_BAR = "double_tap_sleep_status_bar";

    private CheckBoxPreference mDoubleTapSleepGlowPad;
    private CheckBoxPreference mDoubleTapSleepPattern;
    private CheckBoxPreference mDoubleTapSleepPinPassword;
    private CheckBoxPreference mDoubleTapSleepStatusBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.double_tap_sleep_settings);

        // Glow pad
        mDoubleTapSleepGlowPad = (CheckBoxPreference) getPreferenceScreen()
                .findPreference(DOUBLE_TAP_SLEEP_GLOW_PAD);
        mDoubleTapSleepGlowPad.setChecked((Settings.System.getInt(getActivity()
                .getApplicationContext().getContentResolver(),
                Settings.System.DOUBLE_TAP_SLEEP_GLOW_PAD, 0) == 1));

        // Pattern
        mDoubleTapSleepPattern = (CheckBoxPreference) getPreferenceScreen()
                .findPreference(DOUBLE_TAP_SLEEP_PATTERN);
        mDoubleTapSleepPattern.setChecked((Settings.System.getInt(getActivity()
                .getApplicationContext().getContentResolver(),
                Settings.System.DOUBLE_TAP_SLEEP_PATTERN, 0) == 1));

        // Pin & password
        mDoubleTapSleepPinPassword = (CheckBoxPreference) getPreferenceScreen()
                .findPreference(DOUBLE_TAP_SLEEP_PIN_PASSWORD);
        mDoubleTapSleepPinPassword.setChecked((Settings.System.getInt(getActivity()
                .getApplicationContext().getContentResolver(),
                Settings.System.DOUBLE_TAP_SLEEP_PIN_PASSWORD, 0) == 1));

        // Status bar
        mDoubleTapSleepStatusBar = (CheckBoxPreference) getPreferenceScreen()
                .findPreference(DOUBLE_TAP_SLEEP_STATUS_BAR);
        mDoubleTapSleepStatusBar.setChecked((Settings.System.getInt(getActivity()
                .getApplicationContext().getContentResolver(),
                Settings.System.DOUBLE_TAP_SLEEP_STATUS_BAR, 0) == 1));
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;

        // Glow pad
        if (preference == mDoubleTapSleepGlowPad) {
            value = mDoubleTapSleepGlowPad.isChecked();
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.DOUBLE_TAP_SLEEP_GLOW_PAD, value ? 1 : 0);
            return true;
        }

        // Pattern
        else if (preference == mDoubleTapSleepPattern) {
            value = mDoubleTapSleepPattern.isChecked();
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.DOUBLE_TAP_SLEEP_PATTERN, value ? 1 : 0);
            return true;
        }

        // Pin & password
        else if (preference == mDoubleTapSleepPinPassword) {
            value = mDoubleTapSleepPinPassword.isChecked();
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.DOUBLE_TAP_SLEEP_PIN_PASSWORD, value ? 1 : 0);
            return true;
        }

        // Status bar
        else if (preference == mDoubleTapSleepStatusBar) {
            value = mDoubleTapSleepStatusBar.isChecked();
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.DOUBLE_TAP_SLEEP_STATUS_BAR, value ? 1 : 0);
            return true;
        }

        return false;
    }
}
