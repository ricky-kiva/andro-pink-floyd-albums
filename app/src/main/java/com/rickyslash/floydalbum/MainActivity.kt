package com.rickyslash.floydalbum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyslash.floydalbum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val albumList = ArrayList<Album>()

    private fun showSelectedHero(album: Album) {
        val moveHeroDetailsIntent = Intent(this@MainActivity, AlbumDetailsActivity::class.java)

        moveHeroDetailsIntent.putExtra(AlbumDetailsActivity.EXTRA_ALBUM, Album(album.name, album.desc, album.photo, album.release, album.song))
        startActivity(moveHeroDetailsIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHeroes.setHasFixedSize(true)
        albumList.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val moveAboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveAboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Album> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataRelease = resources.getIntArray(R.array.data_release)
        val dataSong = resources.getStringArray(R.array.data_song)
        val listAlbums = ArrayList<Album>()

        for (i in dataName.indices) {
            val album = Album(dataName[i], dataDesc[i], dataPhoto[i], dataRelease[i], dataSong[i])
            listAlbums.add(album)
        }

        return listAlbums
    }

    private fun showRecyclerList() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)

        val listAlbumAdapter = ListAlbumAdapter(albumList)
        binding.rvHeroes.adapter = listAlbumAdapter

        listAlbumAdapter.setOnItemClickCallback(object : ListAlbumAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Album) {
                showSelectedHero(data)
            }
        })
    }
}

