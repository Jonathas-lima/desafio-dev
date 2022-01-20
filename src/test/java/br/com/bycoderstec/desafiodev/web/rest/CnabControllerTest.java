package br.com.bycoderstec.desafiodev.web.rest;

import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.service.ProcessamentoService;
import br.com.bycoderstec.desafiodev.web.rest.response.UploadFileResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static br.com.bycoderstec.desafiodev.util.JsonUtilsTest.convertToObject;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CnabController.class)
@AutoConfigureMockMvc
public class CnabControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessamentoService processamentoService;

    @Test
    public void deveriaRetornarIdsTransacoesSalvas() throws Exception {

        String fileContent = "3201903010000019200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA";

        MockMultipartFile file = new MockMultipartFile("file", "hello.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE, fileContent.getBytes());

        when(processamentoService.processarDados(any())).thenReturn(getTransacoes());

        MvcResult mvcResult = this.mockMvc.perform(multipart("/api/upload").file(file))
                .andExpect(status().isOk())
                .andReturn();
        String respostaJson = mvcResult.getResponse().getContentAsString();
        UploadFileResponse uploadFileResponse = convertToObject(respostaJson, UploadFileResponse.class);

        assertNotNull(uploadFileResponse);
        assertEquals(2, uploadFileResponse.getIdsTransacoes().size());
        assertIterableEquals(asList(1,2), uploadFileResponse.getIdsTransacoes());
    }

    private List<Transacao> getTransacoes() {
        Transacao transacao1 = Transacao.builder().id(1).build();
        Transacao transacao2 = Transacao.builder().id(2).build();
       return asList(transacao1, transacao2);
    }


}
