package hr.nullteam.rsc.ui.fragment.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.profile.ProfileDetailsFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ProfileDetailsFragmentPresenter.class)
public class ProfileDetailsFragment extends DaggerFragment<ProfileDetailsFragmentPresenter> {

    public static final String TAG = ProfileDetailsFragment.class.getSimpleName();

    private static final String KEY_USER_ID = "key_user_id";

    private long userId;        // TODO - stavit usera

    public static ProfileDetailsFragment newInstance(long userId) {
        ProfileDetailsFragment profileDetailsFragment = new ProfileDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putLong(KEY_USER_ID, userId);
        profileDetailsFragment.setArguments(arguments);
        return profileDetailsFragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<ProfileDetailsFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            ProfileDetailsFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractArguments();
    }

    private void extractArguments() {
        Bundle arguments = getArguments();
        userId = arguments.getLong(KEY_USER_ID, -1);        // TODO - NOP za usera
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile_details, container, false);
        injectViews(fragmentView);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }



}
