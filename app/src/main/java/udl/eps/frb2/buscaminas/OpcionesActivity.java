package udl.eps.frb2.buscaminas;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by yatan on 6/14/16.
 */
public class OpcionesActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new OpcionesFragment()).commit();
        //addPreferencesFromResource(R.xml.opciones);
    }
    public static class OpcionesFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.opciones);
        }
    }
}
