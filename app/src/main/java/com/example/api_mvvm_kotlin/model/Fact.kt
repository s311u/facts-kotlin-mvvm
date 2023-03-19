import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Success(val facts: List<Fact>)
data class Fact(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean,
)

const val BASE_URL = "https://cat-fact.herokuapp.com"

interface  FactsApi {
    @GET("facts")
    suspend fun getFacts(): List<Fact>

    companion object {
        var factsService: FactsApi? = null

        fun getInstance(): FactsApi {
            if (factsService === null) {
                factsService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(FactsApi::class.java)
            }
            return factsService!!
        }
    }
}