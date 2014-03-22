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
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class SystemUiSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

    private static final String TAG = "SystemUiSettings";
    // Clear all recents button
    private static final String CLEAR_ALL_RECENTS_BUTTON = "clear_all_recents_button";

    private ListPreference mClearAllRecentsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.system_ui_settings);
        PreferenceScreen prefScreen = getPreferenceScreen();

        // Clear all recents button position/switch
        mClearAllRecentsButton = (ListPreference) findPreference(CLEAR_ALL_RECENTS_BUTTON);
        int clearStatus = Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.CLEAR_ALL_RECENTS_BUTTON, 4);
        mClearAllRecentsButton.setValue(String.valueOf(clearStatus));
        mClearAllRecentsButton.setSummary(mClearAllRecentsButton.getEntry());
        mClearAllRecentsButton.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mClearAllRecentsButton) {
            int value = Integer.valueOf((String) objValue);
            int index = mClearAllRecentsButton.findIndexOfValue((String) objValue);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.CLEAR_ALL_RECENTS_BUTTON, value);
            mClearAllRecentsButton.setSummary(mClearAllRecentsButton.getEntries()[index]);
            return true;
        }
        return false;
    }
}
