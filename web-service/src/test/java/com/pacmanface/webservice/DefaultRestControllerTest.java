package com.pacmanface.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class DefaultRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTasks_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getValidTask_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteValidTaskByID_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void postValidData_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/tasks")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Posted_Bob\",\"description\":\"Posted_blablabla\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        getAllTasks_success();
    }

    @Test
    public void patchValidData_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/tasks/15")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Patched_Bob\",\"description\":\"Patched_blablabla\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
