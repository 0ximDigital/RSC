package hr.nullteam.rsc.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.TodoFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TodoFragmentPresenter.class)
public final class TodoFragment extends DaggerFragment<TodoFragmentPresenter> {

    @Bind(R.id.todo_text)
    TextView todoText;

    private static final String KEY_IMPORTANT_STUFF = "key_important_stuff";

    private String importantInfo;

    public static TodoFragment newInstance() {
        TodoFragment fragment = new TodoFragment();
        Bundle arguments = new Bundle();
        arguments.putString(KEY_IMPORTANT_STUFF, "Time to kick some ass! :D");
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractArguments();
    }

    private void extractArguments() {
        Bundle arguments = getArguments();
        importantInfo = arguments.getString(KEY_IMPORTANT_STUFF);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_todo, container, false);
        injectViews(fragmentView);

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        todoText.setText(importantInfo);
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<TodoFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            TodoFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
