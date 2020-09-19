package me.teenyda.fruit_store.model.home.new_fruit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import me.teenyda.fruit_store.R;

/**
 * author: teenyda
 * date: 2020/9/19
 * description:
 */
public class ScreenSlidePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.frag_new_fruit, container, false);
    }
}
