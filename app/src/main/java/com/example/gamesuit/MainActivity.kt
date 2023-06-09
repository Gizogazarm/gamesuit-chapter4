package com.example.gamesuit

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.gamesuit.databinding.ActivityMainBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var pilihanPlayer: String
    private lateinit var hasilSuit: String
    private lateinit var originalText: String
    private var originalColor: Int = 0
    private val pilihanSuit = arrayOf("batu", "kertas", "gunting")
    private val suitHasil = arrayOf("Pemain Menang", "Computer Menang", "Draw")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player = Player()
        val computer = Computer()

        with(binding) {

            originalText = textCenter.text.toString()
            originalColor = textCenter.currentTextColor

            batuPlayer.setOnClickListener {
                batuPlayer.setBackgroundResource(R.drawable.bg_click)
                setEnabledImageView(batuPlayer, kertasPlayer, guntingPlayer, false)
                pilihanPlayer = pilihanSuit[0]
                player.setPilihanPlayer(pilihanPlayer)
                Log.d("coba hasil pilihan player", player.getPilihanPlayer())
                computer.pilihanCom(pilihanSuit)
                computer.setPilihanPlayer(computer.getPilihanCom())
                computer.efekPilihanCom(batuCom, guntingCom, kertasCom)
                Log.d("coba hasil komputer", computer.getPilihanPlayer())
                player.suit(player.getPilihanPlayer(), computer.getPilihanPlayer())
                computer.suit(computer.getPilihanPlayer(), player.getPilihanPlayer())
                Log.d("coba hasil status menang player", "${player.getStatusMenang()}")
                Log.d("coba hasil status menang computer ", "${computer.getStatusMenang()}")
                hasilSuit = hasilSuit(player.getStatusMenang(), computer.getStatusMenang(), suitHasil)
                Log.d("coba hasil status Hasil Suit ", hasilSuit)
                efekHasilSuit(textCenter, hasilSuit, suitHasil)


            }

            kertasPlayer.setOnClickListener {
                kertasPlayer.setBackgroundResource(R.drawable.bg_click)
                setEnabledImageView(batuPlayer, kertasPlayer, guntingPlayer, false)
                pilihanPlayer = pilihanSuit[1]
                player.setPilihanPlayer(pilihanPlayer)
                Log.d("coba hasil pilihan player", player.getPilihanPlayer())
                computer.pilihanCom(pilihanSuit)
                computer.setPilihanPlayer(computer.getPilihanCom())
                computer.efekPilihanCom(batuCom, guntingCom, kertasCom)
                Log.d("coba hasil komputer", computer.getPilihanPlayer())
                player.suit(player.getPilihanPlayer(), computer.getPilihanPlayer())
                computer.suit(computer.getPilihanPlayer(), player.getPilihanPlayer())
                Log.d("coba hasil status menang player", "${player.getStatusMenang()}")
                Log.d("coba hasil status menang computer", "${computer.getStatusMenang()}")
                hasilSuit = hasilSuit(player.getStatusMenang(), computer.getStatusMenang(), suitHasil)
                Log.d("coba hasil status Hasil Suit ", hasilSuit)
                efekHasilSuit(textCenter, hasilSuit, suitHasil)


            }

            guntingPlayer.setOnClickListener {
                guntingPlayer.setBackgroundResource(R.drawable.bg_click)
                setEnabledImageView(batuPlayer, kertasPlayer, guntingPlayer, false)
                pilihanPlayer = pilihanSuit[2]
                player.setPilihanPlayer(pilihanPlayer)
                Log.d("coba hasil pilihan player", player.getPilihanPlayer())
                computer.pilihanCom(pilihanSuit)
                computer.setPilihanPlayer(computer.getPilihanCom())
                computer.efekPilihanCom(batuCom, guntingCom, kertasCom)
                Log.d("coba hasil komputer", computer.getPilihanPlayer())
                player.suit(player.getPilihanPlayer(), computer.getPilihanPlayer())
                computer.suit(computer.getPilihanPlayer(), player.getPilihanPlayer())
                Log.d("coba hasil status menang player", "${player.getStatusMenang()}")
                Log.d("coba hasil status menang computer", "${computer.getStatusMenang()}")
                hasilSuit = hasilSuit(player.getStatusMenang(), computer.getStatusMenang(), suitHasil)
                Log.d("coba hasil status Hasil Suit ", hasilSuit)
                efekHasilSuit(textCenter, hasilSuit, suitHasil)

            }

            btnRefresh.setOnClickListener {
                setEnabledImageView(batuPlayer, kertasPlayer, guntingPlayer, true)
                setClearImageView(batuCom, guntingCom, kertasCom)
                textCenter.text = originalText
                textCenter.setTextColor(originalColor)
                textCenter.setTextSize(TypedValue.COMPLEX_UNIT_SP,64f)
                textCenter.setBackgroundResource(R.drawable.bg_awal_click)
                Toast.makeText(this@MainActivity,"Main lagi Kuy",Toast.LENGTH_SHORT).show()

            }


        }

    }

    private fun setClearImageView(imageView1: ImageView, imageView2: ImageView, imageView3: ImageView) {
        imageView1.setBackgroundResource(R.drawable.bg_awal_click)
        imageView2.setBackgroundResource(R.drawable.bg_awal_click)
        imageView3.setBackgroundResource(R.drawable.bg_awal_click)
    }


    private fun efekHasilSuit(textView: TextView, hasilSuit: String, arrayhasilSuit: Array<String>) {

        when (hasilSuit) {
            arrayhasilSuit[0] ->
                textView.apply {
                    setBackgroundResource(R.drawable.bg_text_center)
                    text = hasilSuit
                    setTextColor(Color.WHITE)
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                    Gravity.CENTER
                }


            arrayhasilSuit[1] ->
                textView.apply {
                    setBackgroundResource(R.drawable.bg_text_center)
                    text = hasilSuit
                    setTextColor(Color.WHITE)
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                    Gravity.CENTER
                }


            arrayhasilSuit[2] ->
                textView.apply {
                    setBackgroundResource(R.drawable.bg_text_center_draw)
                    text = hasilSuit
                    setTextColor(Color.WHITE)
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                    Gravity.CENTER
                }
        }
    }

    private fun hasilSuit(status1: Boolean, status2: Boolean, arrayhasilSuit: Array<String>): String {
        lateinit var nilaiString: String

        if (status1 && !status2) {
            nilaiString = arrayhasilSuit[0]
        } else if (!status1 && status2) {
            nilaiString = arrayhasilSuit[1]
        } else {
            nilaiString = arrayhasilSuit[2]
        }
        return nilaiString
    }

    private fun setEnabledImageView(imageView1: ImageView, imageView2: ImageView, imageView3: ImageView, setNilai: Boolean) {


        if (setNilai) {
            imageView1.isEnabled = setNilai
            imageView1.setBackgroundResource(R.drawable.bg_awal_click)
            imageView2.isEnabled = setNilai
            imageView2.setBackgroundResource(R.drawable.bg_awal_click)
            imageView3.isEnabled = setNilai
            imageView3.setBackgroundResource(R.drawable.bg_awal_click)

        } else {
            imageView1.isEnabled = setNilai
            imageView2.isEnabled = setNilai
            imageView3.isEnabled = setNilai
        }

    }
}