package hr.nullteam.rsc.ui.fragment.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.profile.EditProfileFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(EditProfileFragmentPresenter.class)
public class EditProfileFragment extends DaggerFragment<EditProfileFragmentPresenter> {

    public static final String TAG = EditProfileFragment.class.getSimpleName();

    private static final String KEY_USER_ID = "key_user_id";

    private long userId;        // TODO

    public static EditProfileFragment newInstance(long userId) {
        EditProfileFragment editProfileFragment = new EditProfileFragment();
        Bundle arguments = new Bundle();
        arguments.putLong(KEY_USER_ID, userId);
        editProfileFragment.setArguments(arguments);
        return editProfileFragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<EditProfileFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            EditProfileFragmentPresenter presenter = superFactory.createPresenter();
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
        userId = arguments.getLong(KEY_USER_ID, -1);        // TODO;
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
