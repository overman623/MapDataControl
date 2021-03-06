package com.makestorming.mapdatacontrol

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.makestorming.mapdatacontrol.databinding.ActivityMainBinding
import com.makestorming.mapdatacontrol.ui.SearchRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*
    공공 api -> 전국
    aws 데이터 베이스 개설 -> 편의점 정보만 추출.
    node.js 웹 서버 프로그래밍 작성 ->
    -------------------------------------------
    공공 api 전국 관광 정보를 가져와서 -> 처음부터 아예 다 가지고 오는게 말이 되지 않는다.
    안드로이드 데이터 바인딩 ->
    안드로이드 mvvm작성 ->
    안드로이드 레이아웃 작성 ->
    안드로이드 구글맵 적용 ->
    안드로이드 코드 난독화 ->
    안드로이드 테스트 자동화 ->
    안드로이드 출시 스펙 작성
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model : SearchRepositoriesViewModel = ViewModelProvider(this).get(SearchRepositoriesViewModel::class.java)
        val bind : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bind.viewModel = model
        bind.lifecycleOwner = this

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)
        setupScrollListener()

        initAdapter()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
