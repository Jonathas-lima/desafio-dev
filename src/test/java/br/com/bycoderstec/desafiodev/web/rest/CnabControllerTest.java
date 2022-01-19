package br.com.bycoderstec.desafiodev.web.rest;

import br.com.bycoderstec.desafiodev.service.ProcessamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {CnabController.class, ProcessamentoService.class})
@AutoConfigureMockMvc
public class CnabControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        String fileContent = "3201903010000019200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA";

        MockMultipartFile file = new MockMultipartFile("file", "hello.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE, fileContent.getBytes()
        );

        this.mockMvc.perform(multipart("/api/upload").file(file))
                .andExpect(status().isOk());
    }
}
