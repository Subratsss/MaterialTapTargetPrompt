/*
 * Copyright (C) 2016-2018 Samuel Wall
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.samuelwall.materialtaptargetprompt.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

/**
 * Prompt example for {@link DialogFragment}.
 */
public class SupportDialogFragmentExample extends DialogFragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_dialog, container);

        getDialog().setTitle("DialogFragment");
        getDialog().setOnShowListener(new DialogInterface.OnShowListener()
        {
            @Override
            public void onShow(DialogInterface dialog)
            {
                showFragmentFabPrompt();
            }
        });
        rootView.findViewById(R.id.button_fab_prompt)
                .setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        showFragmentFabPrompt();
                    }
                });

        ViewPager viewPager = rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), this));
        return rootView;
    }

    public void showFragmentFabPrompt()
    {
        new MaterialTapTargetPrompt.Builder((Fragment) this)
                .setTarget(R.id.fab)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPrimaryText("Clipped to dialog bounds")
                .setSecondaryText("The prompt does not draw outside the dialog")
                .show();
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter
    {
        private static int ITEM_COUNT = 2;
        private DialogFragment dialogFragment;

        public MyPagerAdapter(FragmentManager fragmentManager, DialogFragment fragment)
        {
            super(fragmentManager);
            dialogFragment = fragment;
        }

        @Override
        public int getCount()
        {
            return ITEM_COUNT;
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return ViewPagerFragment.newInstance(dialogFragment);
                case 1:
                    return ViewPagerFragment.newInstance(dialogFragment);
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return "Page " + position;
        }
    }
}
