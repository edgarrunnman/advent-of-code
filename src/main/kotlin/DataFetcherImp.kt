import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class DataFetcherImp() {
    private var fetched: List<String> = listOf()
        fun getPuzzleInput(year: Int, day: Int): List<String> {
            if (fetched.isEmpty()) {
                val client = HttpClient.newBuilder().build();
                val request = HttpRequest.newBuilder()
                    .uri (URI.create(this.getUrl(year, day)))
                    .header("cookie", "session=${getToken(dotenv())}")
                    .build();
                val response = client.send(request, HttpResponse.BodyHandlers.ofString());
                fetched = response.body().split("\n").filter { it.isNotBlank() }
            }
            return fetched
        }

        private fun getUrl(year: Number, day: Number): String =
            "https://adventofcode.com/${year}/day/${day}/input"

        private fun getToken(env: Dotenv): String =
            env["AOC_TOKEN"]
}