import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import parser.JsonParser;
import pojo.PostPojo;
import pojo.UserPojo;
import utils.ApiApplicationRequest;
import utils.Constants;
import java.util.List;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApiTest {

    private ApiApplicationRequest apiApplicationRequest;

    @Test
    public void testApi() {
        apiApplicationRequest = new ApiApplicationRequest();
        Logger.getInstance().info("STEP 1: Send GET Request to get all posts (/posts)");
        List<Integer> allPosts = apiApplicationRequest.getAllPosts().stream().map(s -> s.getId()).collect(Collectors.toList());
        List<Integer> sortedAllPosts = allPosts.stream().sorted().collect(Collectors.toList());
        assertEquals(allPosts, sortedAllPosts, "Posts aren't sorted ascending (by id)");
        Logger.getInstance().info("STEP 2: Send GET request to get post with id=99 (/posts/99)");
        assertEquals(apiApplicationRequest.getPost(Constants.CORRECT_POST_NUMBER), JsonParser.parseTestData().get(0), "Post information isn't correct");
        Logger.getInstance().info("STEP 3: Send GET request to get post with id=150 (/posts/150)");
        assertEquals(apiApplicationRequest.getPost(Constants.INCORRECT_POST_NUMBER), new PostPojo(), "Response body isn't empty");
        Logger.getInstance().info("STEP 4: Send POST request to create post with userId=1 and random body and random title (/posts)");
        PostPojo post = new PostPojo(Constants.USER_ID, Constants.ID, RandomStringUtils.randomAlphabetic(Constants.NUMBER_OF_LETTERS), RandomStringUtils.randomAlphabetic(Constants.NUMBER_OF_LETTERS));
        assertTrue(apiApplicationRequest.postEquality(post, apiApplicationRequest.postPost(post)) & apiApplicationRequest.postPost(post).getId() != 0, "Post information isn't correct");
        Logger.getInstance().info("STEP 5: Send GET request to get users (/users)");
        List<Integer> allUsers = apiApplicationRequest.getAllUsers().stream().map(s -> s.getId()).collect(Collectors.toList());
        Integer testUser = allUsers.stream().filter((s) -> s.equals(Constants.GET_USER_ID)).findAny().orElse(null);
        assertEquals(testUser, JsonParser.parseTestData().get(1), "User data isn't equals");
        Logger.getInstance().info("STEP 6: Send GET request to get user with id=5 (/users/5)");
        assertEquals(apiApplicationRequest.getUser(Constants.GET_USER_ID), testUser, "User data isn't match");
    }
}