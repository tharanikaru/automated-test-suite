import io.restassured.response.Response;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class DevIQTest {
    private String url = "http://localhost:8085";


    @Nested
    class UserServiceTest {

        @Test
        void signUp() {
            String requestBody = "{ \"firstName\": \"Test\"," +
                    "\"lastName\": \"User\" ," +
                    "\"email\": \"test@email.com\", " +
                    "\"password\": \"pw123\"}";
            Response response = given()
                    .body(requestBody)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(url + "/deviq/signup");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertNotNull(response.jsonPath().getString("token"));

        }

        @Test
        void signInWithValidCreds() {
            String requestBody = "{ \"email\": \"tharani@admin.com\"," +
                    "\"password\": \"password\"}";
            Response response = given()
                    .body(requestBody)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(url + "/deviq/signin");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertNotNull(response.jsonPath().getString("token"));
        }

        @Test
        void signInWithInValidCreds() {
            String requestBody = "{ \"email\": \"tharani@admin.com\"," +
                    "\"password\": \"wrong\"}";
            Response response = given()
                    .body(requestBody)
                    .header("Content-Type", "application/json")
                    .when()
                    .post(url + "/deviq/signin");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertNull(response.jsonPath().getString("token"));
        }

    }

    @Nested
    class GitHubIngestorServiceTest {

        @Test
        void listDevelopers() {
            Response response = given()
                    .when()
                    .get(url + "/deviq/developer/list");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertEquals("true", response.jsonPath().getString("status"));
            assertNotNull(response.jsonPath().getString("developers"));
        }


        @Test
        void update() {
            Response response = given()
                    .when()
                    .get(url + "/deviq/update");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertEquals("true", response.jsonPath().getString("status"));
        }

        @Test
        void listCommits() {
            Response response = given()
                    .when()
                    .get(url + "/deviq/developer/commits");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertEquals("true", response.jsonPath().getString("status"));
            assertNotNull(response.jsonPath().getString("developerCommitList"));
        }

        @Test
        void listIssues() {
            Response response = given()
                    .when()
                    .get(url + "/deviq/developer/issues");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertEquals("true", response.jsonPath().getString("status"));
            assertNotNull(response.jsonPath().getString("developerIssues"));
        }

        @Test
        void listPullRequests() {
            Response response = given()
                    .when()
                    .get(url + "/deviq/developer/pull-requests");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertEquals("true", response.jsonPath().getString("status"));
            assertNotNull(response.jsonPath().getString("developerPullRequests"));
        }

        @Test
        void listPullRequestReviews() {
            Response response = given()
                    .when()
                    .get(url + "/deviq/developer/pull-request-reviews");

            // Validate the response status code and other details
            assertEquals(200, response.getStatusCode());
            assertEquals("true", response.jsonPath().getString("status"));
            assertNotNull(response.jsonPath().getString("developerPullRequestReviews"));

        }

    }
}
