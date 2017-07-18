package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.streetshopes.MainActivity;
import com.streetshopes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.AppConstant;
import utils.Validation;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchKeywordFragment extends Fragment {

    @BindView(R.id.editSearch)
    EditText editSearch;

    private Unbinder unbinder;

    public SearchKeywordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_keyword, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        ((MainActivity)getActivity()).mTitle.setText("Search Store");
        return rootView;
    }

    @OnClick({R.id.btnSearchNow, R.id.btnAdvancedSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearchNow:
                if (Validation.hasText(editSearch)) {
                    ((MainActivity) getActivity()).navigateToReplace(StoreProductListFragment.newInstance(null, editSearch.getText().toString().trim()), null, false);
                }
                break;
            case R.id.btnAdvancedSearch:
                ((MainActivity) getActivity()).navigateToReplace(new AdvancedSearchFragment(), null, true);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
