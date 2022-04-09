package com.rawtooth.yourride.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager,
    BEHAVIOR_SET_USER_VISIBLE_HINT) {
    private val mFragment=ArrayList<Fragment>()
    private val mFragmentList=ArrayList<String>()
    override fun getCount(): Int {
            return  mFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentList[position]
    }
    fun addFragment(fragment: Fragment,title:String){
            mFragment.add(fragment)
        mFragmentList.add(title)
    }
}