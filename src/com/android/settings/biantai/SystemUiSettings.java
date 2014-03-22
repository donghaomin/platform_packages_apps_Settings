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
import android.preference.Preference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class SystemUiSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

    private static final String TAG = "SystemUiSettings";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.system_ui_settings);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
