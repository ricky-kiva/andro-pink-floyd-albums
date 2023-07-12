package com.rickyslash.floydalbum

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.rickyslash.floydalbum.databinding.ActivityAlbumDetailsBinding

class AlbumDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailsBinding

    companion object {
        const val EXTRA_ALBUM = "extra_album"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val album = getHeroParcel(intent)

        if (album != null) {
            binding.detailsName.text = album.name
            binding.detailsDesc.text = album.desc
            binding.detailsRelease.text = album.release.toString()
            binding.detailsSong.text = album.song
            Glide.with(this)
                .load(album.photo)
                .into(binding.detailsImg)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val album = getHeroParcel(intent)

        when (item.itemId) {
            R.id.action_share -> {
                if (album != null) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.setType("text/plain")

                    val descSend = (
                            "Pink Floyd – ${album.name}\n" +
                            "\nRelease: ${album.release}\nEssential: ${album.song}\n" +
                            "\n${album.desc}"
                            )

                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, album.name)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, descSend)
                    startActivity(Intent.createChooser(shareIntent, "Share the Hero"))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

private fun getHeroParcel(intent: Intent): Album? {
    return if (Build.VERSION.SDK_INT >= 33) {
        intent.getParcelableExtra(AlbumDetailsActivity.EXTRA_ALBUM, Album::class.java)
    } else {
        @Suppress("DEPRECATION")
        intent.getParcelableExtra(AlbumDetailsActivity.EXTRA_ALBUM)
    }
}