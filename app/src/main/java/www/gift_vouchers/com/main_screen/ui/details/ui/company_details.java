package www.gift_vouchers.com.main_screen.ui.details.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.com.R;
import www.gift_vouchers.com.databinding.CompanyDetailsBinding;
import www.gift_vouchers.com.local_data.saved_data;
import www.gift_vouchers.com.local_data.send_data;
import www.gift_vouchers.com.main_screen.ui.details.model.CompaniniesDetailsBody;
import www.gift_vouchers.com.main_screen.ui.details.model.CompaniniesDetailsCategory;
import www.gift_vouchers.com.main_screen.ui.details.model.CompaniniesDetailsRootClass;
import www.gift_vouchers.com.main_screen.ui.select_gift_design.ui.select_design;
import www.gift_vouchers.com.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class company_details extends Fragment {
    CompanyDetailsBinding binding;
    CompaniniesDetailsBody companiniesDetailsBody;
    CompaniniesDetailsCategory[] CompaniniesDetailsCategory;
    String price;
    String cat_id;
    String type;
    String type_price;
    int total_price;

    public company_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.company_details, container, false);
        View view = binding.getRoot();

        //START ANIMATION
        binding.shimmerViewContainer.startShimmerAnimation();

        //CLICK LISTNERS
        click_listners();

        //GET DATA
        set_data();

        //SET PRICE
        set_price();


        return view;
    }

    //SET ON CLICK LISTNERS
    void click_listners() {

        //SET ON CLICK BACK
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    //SET DATA TEXT
    void set_data() {
        //SEND DATA TO FACTORY
        CompanyDetailsModelView CompanyDetailsModelView = new ViewModelProvider(company_details.this,
                new CompanyDetailsModelViewFactory(getContext(), getArguments().getString("id"))).get(CompanyDetailsModelView.class);

        //CALL METHOD THAT CALLING API
        CompanyDetailsModelView.get_data();

        //SET DATA
        CompanyDetailsModelView.MutableLiveData.observe(this, new Observer<CompaniniesDetailsRootClass>() {
            @Override
            public void onChanged(CompaniniesDetailsRootClass companiniesDetailsRootClass) {
                companiniesDetailsBody = companiniesDetailsRootClass.getBody();

                binding.descripition.setText("" + companiniesDetailsBody.getDescription());
                binding.imageTitle.setText(getString(R.string.voucher_from) + " " + companiniesDetailsBody.getUsername());
                binding.tite.setText(getString(R.string.about) + companiniesDetailsBody.getUsername());

                Glide.with(company_details.this)
                        .load(companiniesDetailsBody.getPicture())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                binding.shimmerViewContainer.stopShimmerAnimation();
                                return false;
                            }
                        })
                        .into(binding.logo);

                //GET CATEGORIES
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<String> ids = new ArrayList<>();
                CompaniniesDetailsCategory = companiniesDetailsBody.getCategory();
                for (int index = 0; index < CompaniniesDetailsCategory.length; index++) {
                    arrayList.add(CompaniniesDetailsCategory[index].getPrice());
                    ids.add(CompaniniesDetailsCategory[index].getId() + "");
                }

                //SET TEXT
                onselect_buttons(arrayList, ids);

                //SET ON CLICK NEXT
                binding.next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (new saved_data().get_login_status(getContext()) == false) {
                            Toasty.warning(getContext(), getString(R.string.please_login), Toasty.LENGTH_SHORT).show();
                        } else if (Integer.parseInt(price) >= 5001) {
                            Toasty.warning(getContext(), getString(R.string.price_must_be_less_than), Toasty.LENGTH_SHORT).show();
                        } else {
                            total_price = Integer.parseInt(type_price) + Integer.parseInt(price);
                            //SET ALL DATA
                            send_data.set_order_name(getContext(), companiniesDetailsBody.getUsername());
                            send_data.price(getContext(), "" + total_price);
                            send_data.cat_id(getContext(), cat_id);
                            send_data.set_logo(getContext(), companiniesDetailsBody.getPicture().toString());
                            send_data.set_type(getContext(), type);

                            new utils().Replace_Fragment(new select_design(), R.id.frag, getContext());
                        }
                    }
                });
            }
        });

    }

    void onselect_buttons(ArrayList arrayList, ArrayList ids) {

        binding.price.setText(arrayList.get(0).toString());
        cat_id = "" + ids.get(0).toString();
        type = getString(R.string.gold);
        type_price = arrayList.get(0).toString();
        binding.giftType.setPosition(0, true);

        binding.giftType.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                if (position == 0) {
                    type = getString(R.string.gold);
                    cat_id = "" + ids.get(0).toString();
                    binding.price.setText(arrayList.get(0).toString());
                    type_price = arrayList.get(0).toString();

                } else if (position == 1) {
                    type = getString(R.string.silver);
                    cat_id = "" + ids.get(1).toString();
                    binding.price.setText(arrayList.get(1).toString());
                    type_price = arrayList.get(1).toString();

                } else {
                    type = getString(R.string.platinum);
                    cat_id = "" + ids.get(2).toString();
                    binding.price.setText(arrayList.get(2).toString());
                    type_price = arrayList.get(2).toString();
                }
            }
        });
    }

    //GET PRICE
    void set_price() {
        price = binding.price1.getText();
        //GET PRICE FROM EDIT TEXT
        binding.otherPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 1) {
                    binding.selectPrice.setVisibility(View.GONE);
                    binding.priceQuantity.setVisibility(View.GONE);
                    price = charSequence.toString();  //GET TEXT
                } else if (charSequence.length() == 0) {
                    binding.selectPrice.setVisibility(View.VISIBLE);
                    binding.priceQuantity.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //GET TEXT FROM SELECT PRICE
        binding.priceQuantity.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                if (position == 0) {
                    price = binding.price1.getText();
                } else if (position == 1) {
                    price = binding.price2.getText();
                } else if (position == 2) {
                    price = binding.price3.getText();
                } else {
                    price = binding.price4.getText();
                }
            }
        });


    }


}
