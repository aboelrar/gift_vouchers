package www.gift_vouchers.com.main_screen.ui.user_info.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.UserInfoBinding;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.main_screen.ui.change_password.ui.change_password;
import www.gift_vouchers.com.utils.utils;

import static www.gift_vouchers.com.main_screen.ui.MainActivity.file;
import static www.gift_vouchers.com.utils.utils.yoyo;

/**
 * A simple {@link Fragment} subclass.
 */
public class user_info extends Fragment {
    UserInfoBinding binding;
    int storage_premission_code = 1;

    public user_info() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.user_info, container, false);
        View view = binding.getRoot();

        //SET DATA
        binding.cardNum.setText(new saved_data().get_name(getContext()));
        binding.email.setText(new saved_data().get_email(getContext()));
        binding.phone.setText(new saved_data().get_phone(getContext()));
        Glide.with(getContext()).load(new saved_data().get_picture(getContext())).into(binding.criImg);

        //CLiCK LISTNERS
        click_listners();

        return view;
    }

    void click_listners() {
        binding.changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new utils().Replace_Fragment(new change_password(), R.id.frag, getContext());
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        binding.uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grantedOrNot();
            }
        });

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (binding.cardNum.length() <= 3) {
                    String username_val = getResources().getString(R.string.user_val);
                    binding.cardNum.setError(username_val);
                    yoyo(R.id.card_num, binding.cardNum);
                } else if (binding.email.length() <= 6) {
                    String email_val = getResources().getString(R.string.email_val);
                    binding.email.setError(email_val);
                    yoyo(R.id.email, binding.email);
                } else if (binding.phone.length() <= 6) {
                    String phone_val = getResources().getString(R.string.phone_val);
                    binding.phone.setError(phone_val);
                    yoyo(R.id.phone, binding.phone);
                } else {
                    //CALL PROGRESS DIALOG
                    new utils().set_dialog(getContext());

                    //SEND DATA TO FACTORY
                    UserInfoModelView UserInfoModelView = new ViewModelProvider(user_info.this, new UserInfoModelViewFactory(getContext(),
                            binding.cardNum.getText().toString(), binding.email.getText().toString(),
                            binding.phone.getText().toString(), file)).get(UserInfoModelView.class);

                    //CALL METHOD THAT CALLING API
                    UserInfoModelView.data_1(file, binding.cardNum.getText().toString(),
                            binding.email.getText().toString(), binding.phone.getText().toString());
                }


            }
        });
    }

    //Premission
    private void grantedOrNot() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(getContext()).setTitle("Premission To Open Gallery").setMessage("If you need to upload image you must do this premission").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                //positive
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, storage_premission_code);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(i, "Select Your Photo"), 1);
                }
            }).create().show();
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, storage_premission_code);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == storage_premission_code) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Select Your Photo"), 1);
            } else {
                Toast.makeText(getContext(), "not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
