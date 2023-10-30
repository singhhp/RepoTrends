import com.example.repotrends.model.GitHubApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApiService {
    @GET("search/repositories?q=created:>2022-01-01&sort=stars&order=desc")
    fun getTrendingRepos(): Call<GitHubApiResponse>
}
