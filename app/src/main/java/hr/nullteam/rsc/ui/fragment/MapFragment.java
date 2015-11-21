package hr.nullteam.rsc.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
        this.map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment)).getMap();

        final LatLng HAMBURG = new LatLng(53.558, 9.927);
        final LatLng KIEL = new LatLng(53.551, 9.993);

        Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
                .title("Merkher"));
        Marker kiel = map.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Marker")
                .snippet("Marher snippet")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_launcher)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
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
