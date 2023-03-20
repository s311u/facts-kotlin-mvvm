
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


data class Success(val facts: List<Fact>)
data class Fact(
    @SerializedName("_id")
    val id: String,
    val text: String,
    val type: String,
    val deleted: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)


const val BASE_URL = "https://cat-fact.herokuapp.com"

interface  FactsApi {
    @GET("/facts/random?animal_type=cat&amount=10")//facts/random?animal_type=cat
    //val fact = Gson().fromJson(response, Fact::class.java)
    //val text = fact.text
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
//   fun getInstance(urlString: String): String {
//       val url = URL("https://cat-fact.herokuapp.com")
//       val connection = url.openConnection() as HttpURLConnection
//       connection.requestMethod = "GET"
//
//       val responseCode = connection.responseCode
//       if (responseCode == HttpURLConnection.HTTP_OK) {
//           val inputStream = connection.inputStream
//           val response = inputStream.bufferedReader().use { it.readText() }
//           inputStream.close()
//           return response
//       } else {
//           throw Exception("Failed to make GET request: $responseCode")
//       }
//   }
}