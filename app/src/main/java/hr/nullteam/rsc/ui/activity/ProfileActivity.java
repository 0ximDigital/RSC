package hr.nullteam.rsc.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.profile.EditProfileFragment;
import hr.nullteam.rsc.ui.fragment.profile.ProfileDetailsFragment;
import hr.nullteam.rsc.ui.module.ActivityComponent;
import hr.nullteam.rsc.ui.presenter.profile.ProfileActivityPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ProfileActivityPresenter.class)
public class ProfileActivity extends PresenterActivity<ProfileActivityPresenter> {

    public static final String KEY_USER_ID = "key_user_id";

    private long userId;        // TODO - objekt usera iz baze

    @Inject
    FragmentManager fragmentManager;

    private ProfileDetailsFragment profileDetailsFragment;
    private EditProfileFragment editProfileFragment;

    public static Intent createIntent(Context context, long profileId) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(KEY_USER_ID, profileId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        extractArguments();

        if(savedInstanceState == null) {
            if(profileDetailsFragment == null) {
                profileDetailsFragment = ProfileDetailsFragment.newInstance(userId);
            }
            fragmentManager.beginTransaction().add(R.id.activity_container, profileDetailsFragment, ProfileDetailsFragment.TAG).commit();
        } else {
            profileDetailsFragment = (ProfileDetailsFragment) fragmentManager.findFragmentByTag(ProfileDetailsFragment.TAG);
            editProfileFragment = (EditProfileFragment) fragmentManager.findFragmentByTag(EditProfileFragment.TAG);
        }
    }

    private void extractArguments() {
        Intent intent = getIntent();
        if(intent == null) {
            userId = -1;
            return;
        }
        userId = intent.getLongExtra(KEY_USER_ID, -1);
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<ProfileActivityPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            ProfileActivityPresenter presenter = superFactory.createPresenter();
            getActivityComponent().inject(presenter);
            return presenter;
        });
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    public void showEditProfileFragment() {
        if(editProfileFragment == null) {
            editProfileFragment = EditProfileFragment.newInstance(userId);
        }
        fragmentManager.beginTransaction().add(R.id.activity_container, editProfileFragment, EditProfileFragment.TAG).commit();
    }
}
