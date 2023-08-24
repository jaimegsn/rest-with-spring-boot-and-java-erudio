package br.com.erudio.serialization.converter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/x-yaml"));
        //setSerializationInclusion indica os atributos que vamos serializar
        // no caso todos os atributos que não forem nulos
    }
}
