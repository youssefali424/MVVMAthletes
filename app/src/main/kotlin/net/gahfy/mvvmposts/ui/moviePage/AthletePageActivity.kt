package net.gahfy.mvvmposts.ui.moviePage

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import net.gahfy.mvvmposts.R
import net.gahfy.mvvmposts.databinding.ActivityAthletePageBinding
import net.gahfy.mvvmposts.model.Athlete

class AthletePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAthletePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var athlete  = intent?.getSerializableExtra("nowMovie") as? Athlete
//        setContentView(R.layout.activity_movie_page)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_athlete_page)
        val movieViewModel = ViewModelProviders.of(this).get(AthletePageViewModel::class.java)

        if(athlete !=null){
            movieViewModel.bind(athlete)
        }
        binding.model=movieViewModel
    }

}
