package hr.nullteam.rsc.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.MapFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;


@RequiresPresenter(MapFragmentPresenter.class)
public class MapFragment extends DaggerFragment<MapFragmentPresenter> {

    public static final String TAG = MapFragment.class.getSimpleName();

    private GoogleMap map;

    public static MapFragment newInstance() {
        MapFragment mapFragment = new MapFragment();
        return mapFragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<MapFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            MapFragmentPresenter presenter = superFactory.createPresenter();
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
        setupMap();
        super.onResume();
    }

    private void setupMap() {
        if(this.map == null) {
            this.map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
        }
        // TODO
    }

    private void extractArguments() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        injectViews(fragmentView);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /*@Override     // TODO ?
    public void onDestroyView() {
        if (map != null) {
            getChildFragmentManager().beginTransaction()
                    .remove(getChildFragmentManager().findFragmentById(R.id.map_fragment)).commit();
            map = null;
        }
        super.onDestroyView();
    }*/


}
