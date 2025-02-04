
import com.verteil.project1.controller.UserController;
import com.verteil.project1.entity.User;
import com.verteil.project1.entity.Message;
import com.verteil.project1.repo.UserRepo;
import com.verteil.project1.service.MessageService;
import com.verteil.project1.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserService userService;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private UserController userController;

    private User user;
    private User user1;

    @BeforeEach
    void setUp() {
        user = new User(1L, "random@gmail.com", "random@123", "Test name", "This is a random bio for the post <Test>", null);


        // Initialize user1
        user1 = new User();
        user1.setId(45L);
        user1.setName("gayle");
    }


    @Test
    void testGetAllUsers() {
        List<User> mockUsers = Arrays.asList(user);
        when(userService.getAllUsers()).thenReturn(mockUsers);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetUserById_Found() {
        when(userService.getUserById(45L)).thenReturn(Optional.of(user1));
        ResponseEntity<User> response = userController.getUserById(45L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("gayle", response.getBody().getName());
    }
    @Test
    void testGetMessagesBetweenUsers_NoMessages() {
        when(messageService.getMessagesBetweenUsers(1L, 3L)).thenReturn(Arrays.asList());
        ResponseEntity<List<Message>> response = userController.getMessagesBetweenUsers(1L, 3L);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }


}