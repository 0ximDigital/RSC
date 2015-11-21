package hr.nullteam.rsc.ui.fragment.profile;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import hr.nullteam.rsc.R;
import hr.nullteam.rsc.business.api.model.Player;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.profile.ProfileDetailsFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ProfileDetailsFragmentPresenter.class)
public class ProfileDetailsFragment extends DaggerFragment<ProfileDetailsFragmentPresenter> {

    public static final String TAG = ProfileDetailsFragment.class.getSimpleName();
    public static final int USER_ID = -1;

    private static final String KEY_USER_ID = "key_user_id";

    private long userId;
    private Player player;

    @Bind(R.id.email_text)
    TextView email;

    @Bind(R.id.name_text)
    TextView name;

    @Bind(R.id.surname_text)
    TextView surname;

    @Bind(R.id.player_avatar)
    SimpleDraweeView playerAvatar;

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

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    private void extractArguments() {
        Bundle arguments = getArguments();
        userId = arguments.getLong(KEY_USER_ID, USER_ID);
    }

    private void requestData() {
        getPresenter().onUserId(userId);
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

    public void setPlayerData(Player player) {
        this.player = player;
        populateView();
    }

    private void populateView() {
        Uri avatarUri = player.getAvatarUrl() == null ? Uri.EMPTY : Uri.parse(player.getAvatarUrl());
        playerAvatar.setImageURI(avatarUri);
        email.setText(player.getEmail());
        name.setText(player.getName());
        surname.setText(player.getSurname());
    }


}
