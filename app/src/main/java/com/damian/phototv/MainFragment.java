package com.damian.phototv;

/**
 * Created by damia on 04.06.2018.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends BrowseFragment {
    private static final String TAG = MainFragment.class.getSimpleName();

    private ArrayObjectAdapter mRowsAdapter;
    private static final int GRID_ITEM_WIDTH = 300;
    private static final int GRID_ITEM_HEIGHT = 200;
    private static SimpleBackgroundManager simpleBackgroundManager = null;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        setupUIElements();
        simpleBackgroundManager = new SimpleBackgroundManager(getActivity());
        loadRows();
        setupEventListeners();
    }

    private void setupEventListeners() {
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
            if(item instanceof Movie){
                simpleBackgroundManager.updateBackground(getActivity().getDrawable(((Movie)item).getImage()));
            }else{
                simpleBackgroundManager.clearBackground();
            }
        }
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
        setTitle("Orły 2018"); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void loadRows() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        /* CardPresenter */
        HeaderItem cardPresenterHeader = new HeaderItem(1, "Najlepszy film");
        CardPresenter cardPresenter = new CardPresenter();
        ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(cardPresenter);

        Movie cichanoc = new Movie("Cicha noc", "reż. Piotr Domalewski", R.drawable.cichanoc);
        Movie pokot = new Movie("Pokot", "reż. Agnieszka Holland", R.drawable.pokot);
        Movie vincent = new Movie("Twoj vincent", "reż. Dorota Kobiela, Hugh Welchman", R.drawable.twojvincent);

        cardRowAdapter.add(cichanoc);
        cardRowAdapter.add(pokot);
        cardRowAdapter.add(vincent);

        mRowsAdapter.add(new ListRow(cardPresenterHeader, cardRowAdapter));

        /* CardPresenter */
        HeaderItem cardPresenterHeader1 = new HeaderItem(2, "Najlepszy aktor");
        CardPresenter cardPresenter1 = new CardPresenter();
        ArrayObjectAdapter cardRowAdapter1 = new ArrayObjectAdapter(cardPresenter1);

        Movie ogrodnik = new Movie("Dawid Ogrodnik", "Cicha noc", R.drawable.dawidogrodnik);
        Movie linda = new Movie("Bogusław Linda", "Powidoki", R.drawable.boguslawlinda);
        Movie gierszal = new Movie("Jakub Gierszał", "Najlepszy", R.drawable.jakubgierszal);

        cardRowAdapter1.add(ogrodnik);
        cardRowAdapter1.add(linda);
        cardRowAdapter1.add(gierszal);

        mRowsAdapter.add(new ListRow(cardPresenterHeader1, cardRowAdapter1));

        /* CardPresenter */
        HeaderItem cardPresenterHeader2 = new HeaderItem(3, "Najlepsza aktora");
        CardPresenter cardPresenter2 = new CardPresenter();
        ArrayObjectAdapter cardRowAdapter2 = new ArrayObjectAdapter(cardPresenter2);

        Movie boczarska = new Movie("Magdalena Boczarska", "Sztuka kochania", R.drawable.magdalenaboczarska);
        Movie mandat = new Movie("Agnieszka Mandat", "Pokot", R.drawable.agnieszkamandat);
        Movie gruszka = new Movie("Karolina Gruszka", "Maria Skłodowska-Curie", R.drawable.karolinagruszka);

        cardRowAdapter2.add(boczarska);
        cardRowAdapter2.add(mandat);
        cardRowAdapter2.add(gruszka);

        mRowsAdapter.add(new ListRow(cardPresenterHeader2, cardRowAdapter2));

        /* set */
        setAdapter(mRowsAdapter);
    }

    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {

        }
    }

}
