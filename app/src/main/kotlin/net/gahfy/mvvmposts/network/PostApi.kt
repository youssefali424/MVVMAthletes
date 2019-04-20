package net.gahfy.mvvmposts.network

import io.reactivex.Observable
import net.gahfy.mvvmposts.model.Athlete
import net.gahfy.mvvmposts.model.Post
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("MohamedWael/1406437f14e9a769a3a572a78906388f/raw/5be50e67c96c5ed1da9fe6344d2dd7befef0ba25/?fbclid=IwAR2MnhGyiynAuATZpengFzYC2K6VWLtErOyuh_WYGl5qAGed1ItfmKgys30")
    fun getPosts(): Observable<Post>
}