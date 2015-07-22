package com.slobx.www.flagmaster;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


public class SettingsActivity extends PreferenceActivity  {

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
    {


        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            updateSoundPref();
            updateVibratePref();
            updateUnrecognizedPref();
            updateOthersPref();


        }


        public void onSharedPreferenceChanged(
                SharedPreferences sharedPreferences, String key) {

            if(key.equals("prefSoundOnOff")){
                updateSoundPref();
            };

            if(key.equals("prefVibrateOnOff")){
                updateVibratePref();
            };

            if(key.equals("unrecognizedTurnOnOff")){
                updateUnrecognizedPref();
            };

            if(key.equals("othersTurnOnOff")){
                updateOthersPref();
            };
        }

        private void updateSoundPref(){
            CheckBoxPreference preference = (CheckBoxPreference) findPreference("prefSoundOnOff");
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean soundOnOff = sharedPrefs.getBoolean("prefSoundOnOff", true);
            if (soundOnOff){
                preference.setSummary("Sound is on");
            }else if (!soundOnOff){
                preference.setSummary("Sound is off");
            }
        }


        private void updateVibratePref(){
            CheckBoxPreference preference = (CheckBoxPreference) findPreference("prefVibrateOnOff");
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean vibrationOnOff = sharedPrefs.getBoolean("prefVibrateOnOff", true);
            if (vibrationOnOff){
                preference.setSummary("Vibration is on");
            }else if (!vibrationOnOff){
                preference.setSummary("Vibration is off");
            }
        }

        private void updateUnrecognizedPref(){
            CheckBoxPreference preference = (CheckBoxPreference) findPreference("unrecognizedTurnOnOff");
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean unrecognizedOnOff = sharedPrefs.getBoolean("unrecognizedTurnOnOff", true);
            if (unrecognizedOnOff){
                preference.setSummary("Unrecognized countries are enabled in the game");
            }else if (!unrecognizedOnOff){
                preference.setSummary("Unrecognized countries are disabled in the game");
            }
        }

        private void updateOthersPref(){
            CheckBoxPreference preference = (CheckBoxPreference) findPreference("othersTurnOnOff");
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean othersOnOff = sharedPrefs.getBoolean("othersTurnOnOff", true);
            if (othersOnOff){
                preference.setSummary("Other territories are enabled in the game");
            }else if (!othersOnOff){
                preference.setSummary("Other territories are disabled in the game");
            }
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
