package israa.belghith.findfriends.ui.home;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import israa.belghith.findfriends.Constants;
import israa.belghith.findfriends.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

binding.btnSend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String number=binding.edPhone.getText().toString();
        //envoie sms
        if (Constants.GPS_SMS_PERMISSION_Stats)
        {
            SmsManager manager=SmsManager.getDefault();//sim par défaut
            manager.sendTextMessage(number,null, Constants.MSG_SendMePosition,null,null);
        }
        else Toast.makeText(getActivity(), "Autorisation non accordée", Toast.LENGTH_SHORT).show();


    }
});
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}