package test.crud.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.crud.repository.UserRepository;
import test.crud.users.User;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/"))
        .andExpect(status.isOk())
        .andExpect(content.string(containsString("Hello, new alien:)")));
    }
    @Test
    void testSaveData() throws Exception {
        User user = new User(4, "HelloKitty", 14, "hellok@gmail.com");
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        mockMvc.perform(post("/addUser"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(user))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(4))
            .andExpect(jsonPath("$.nickname").value("HelloKitty"))
            .andExpect(jsonPath("$.age").value(14))
            .andExpect(jsonPath("$.email").value("hellok@gmail.com"));

    }
    @Test
    void testGetAll() throws Exception {
        List<User> userList = Arrays.asList(
            new User(2, "Saitama", 30, "onepman@gmail.com"),
            new User(6, "Kura", 23, "blackp@gmail.com")
        );
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        mockMvc.perform(get("/getAll"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id").value(2))
            .andExpect(jsonPath("$[1].id").value(6))
            .andExpect(jsonPath("$[0].nickname").value("Saitama"))
            .andExpect(jsonPath("$[1].nickname").value("Kura"))
            .andExpect(jsonPath("$[0].age").value(30))
            .andExpect(jsonPath("$[1].age").value(23))
            .andExpect(jsonPath("$[0].email").value("onepman@gmail.com"))
            .andExpect(jsonPath("$[1].email").value("blackp@gmail.com"));
    }
    @Test
    void testGetUserByID() throws Exception {
        User user = new User(4, "HelloKitty", 14, "hellok@gmail.com");
        Mockito.when(userRepository.findById(4)).thenReturn(Optional.of(user));
        mockMvc.perform(get("/getUser/4"))
            .andExpect(status.isOk())
            .andExpect(jsonPath("$.id").value(4))
            .andExpect(jsonPath("$.nickname").value("HelloKitty"))
            .andExpect(jsonPath("$.age").value(14))
            .andExpect(jsonPath("$.email").value("hellok@gmail.com"));
    }
    @Test
    void testUpdateUser() throws Exception {
        User user = new User(3, "svnflower", 18, "svnflower@gmail.com");
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        mockMvc.perform(put("/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(user)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.nickname").value("svnflower"))
            .andExpect(jsonPath("$.age").value(18))
            .andExpect(jsonPath("$.email").value("svnflower@gmail.com"));
        }
    @Test
    void testDeleteUser() throws Exception {
        User user = new User(4, "HelloKitty", 14, "hellok@gmail.com");
        Mockito.when(userRepository.deleteById(4)).thenReturn(Optional.of(user));
        mockMvc.perform(delete("/removeUser/3"))
            .andExpect((ResultMatcher) content())
            .andExpect(content.string(containsString("Minus 1 person:(")));
    }
}
