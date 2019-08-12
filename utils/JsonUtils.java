package hyc.upload.dataupload.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *@ClassName JsonUtils
 *@Description TODO
 *@Author DL
 *@Date 2019/6/21 15:39    
 *@Version 1.0
 */
public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    protected JsonUtils() {
    }

    public static String objectToJson(Object object) {
        String ret = null;
        if (object != null) {
            try {
                ret = getObjectMapper().writeValueAsString(object);
            } catch (JsonProcessingException var3) {
                LOGGER.error("Object to json failed!", var3);
            }
        }

        return ret;
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        T ret = null;
        if (!StringUtils.isEmpty(json)) {
            try {
                ret = getObjectMapper().readValue(json, clazz);
            } catch (IOException var4) {
                LOGGER.error("Json {} to object failed!", json, var4);
            }
        }

        return ret;
    }

    public static <T> T jsonToObject(String json, TypeReference<?> typeReference) {
        T ret = null;
        if (!StringUtils.isEmpty(json)) {
            try {
                ret = getObjectMapper().readValue(json, typeReference);
            } catch (IOException var4) {
                LOGGER.error("Json {} to object failed!", json, var4);
            }
        }

        return ret;
    }

    public static <T> T toObject(String content, Class<?> collectionClass, Class... elementClasses) throws Exception {
        if (StringUtils.isEmpty(content)) {
            return null;
        } else {
            try {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
                return objectMapper.readValue(content, javaType);
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static <T> boolean canDeserialize(String json, Class<T> clazz) {
        boolean flag = true;

        try {
            (new ObjectMapper()).readValue(json, clazz);
        } catch (IOException var4) {
            flag = false;
        }

        return flag;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        objectMapper = objectMapper;
    }

    static {
        getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}

