package com.android.settings.biantai;

import android.content.res.Resources;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

public class StatusBarSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    // Status bar brightness control
    private static final String STATUS_BAR_BRIGHTNESS_CONTROL = "status_bar_brightness_control";
    // Status bar battery style
    private static final String STATUS_BAR_BATTERY_STYLE = "status_bar_battery_style";
    // QuickSettings panel quick pulldown
    private static final String QUICK_PULLDOWN = "quick_pulldown";

    // Status bar brightness control
    private CheckBoxPreference mStatusBarBrightnessControl;
    // Status bar battery style
    private ListPreference mStatusBarBattery;
    // QuickSettings panel quick pulldown
    private ListPreference mQuickPulldown;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.status_bar_settings);

        // Status bar brightness control
        // 通知栏亮度控制
        mStatusBarBrightnessControl = (CheckBoxPreference) getPreferenceScreen().findPreference(STATUS_BAR_BRIGHTNESS_CONTROL);
        mStatusBarBrightnessControl.setChecked((Settings.System.getInt(getActivity().getApplicationContext().getContentResolver(),
                Settings.System.STATUS_BAR_BRIGHTNESS_CONTROL, 0) == 1));
        try {
            if (Settings.System.getInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                mStatusBarBrightnessControl.setEnabled(false);
                mStatusBarBrightnessControl.setSummary(R.string.status_bar_toggle_info);
            }
        } catch (SettingNotFoundException e) {
        }

        // Status bar battery style
        // 通知栏电池样式
        mStatusBarBattery = (ListPreference) findPreference(STATUS_BAR_BATTERY_STYLE);
        mStatusBarBattery.setOnPreferenceChangeListener(this);

        int batteryStyleValue = Settings.System.getInt(getActivity().getApplicationContext().getContentResolver(),
                Settings.System.STATUS_BAR_BATTERY_STYLE, 0);
        mStatusBarBattery.setValue(String.valueOf(batteryStyleValue));
        mStatusBarBattery.setSummary(mStatusBarBattery.getEntry());

        // QuickSettings panel quick pulldown
        // 快捷设置面板快速下拉
        mQuickPulldown = (ListPreference) getPreferenceScreen().findPreference(QUICK_PULLDOWN);
        // Only phones need this feature
        // 只有手机需要这个特性
        if (!Utils.isPhone(getActivity())) {
            if (mQuickPulldown != null) {
                getPreferenceScreen().removePreference(mQuickPulldown);
            }
        } else {
            mQuickPulldown.setOnPreferenceChangeListener(this);
            int quickPulldownValue = Settings.System.getInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.QS_QUICK_PULLDOWN, 0);
            mQuickPulldown.setValue(String.valueOf(quickPulldownValue));
            mQuickPulldown.setSummary(mQuickPulldown.getEntry());
        }
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mStatusBarBattery) {
            int batteryStyleValue = Integer.valueOf((String) objValue);
            int batteryStyleIndex = mStatusBarBattery.findIndexOfValue((String) objValue);
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.STATUS_BAR_BATTERY_STYLE, batteryStyleValue);
            mStatusBarBattery.setSummary(mStatusBarBattery.getEntries()[batteryStyleIndex]);
            return true;

        } else if (preference == mQuickPulldown) {
            int quickPulldownValue = Integer.valueOf((String) objValue);
            int quickPulldownIndex = mQuickPulldown.findIndexOfValue((String) objValue);
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.QS_QUICK_PULLDOWN, quickPulldownValue);
            mQuickPulldown.setSummary(mQuickPulldown.getEntries()[quickPulldownIndex]);
            return true;

        }
        return false;
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;
        if (preference == mStatusBarBrightnessControl) {
            value = mStatusBarBrightnessControl.isChecked();
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(),
                    Settings.System.STATUS_BAR_BRIGHTNESS_CONTROL, value ? 1 : 0);
            return true;
        }
        return false;
    }
}
