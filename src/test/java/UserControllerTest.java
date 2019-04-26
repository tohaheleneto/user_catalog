import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import users.RoleRepository;
import users.UserRepository;
import users.controller.UserController;
import users.entity.User;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = users.App.class)
public class UserControllerTest {


    @MockBean
    private RoleRepository roleRepository;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;

    @Test
    public void  testTable() throws Exception {
        when(userRepository.findAll()).thenReturn(null);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("table"));
    }

    @Test
    public void  testAddGet() throws Exception {
        when(userRepository.findAll()).thenReturn(null);
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    @Test
    public void  userPage() throws Exception {
        User user = new User("anton","mamontov","toha");
        when(userRepository.findByid(5)).thenReturn(user);
        mockMvc.perform(get("/userPage").param("id","5"))
                .andExpect(status().isOk())
                .andExpect(view().name("show"));
    }

}
