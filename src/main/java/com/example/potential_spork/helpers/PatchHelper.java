package com.example.potential_spork.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Component;

@Component
public class PatchHelper {

    private final ObjectMapper objectMapper;

        public PatchHelper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        public <T> T applyPatch(JsonPatch patch, T targetBean, Class<T> beanClass) throws JsonPatchException, JsonProcessingException {
            JsonNode targetNode = objectMapper.convertValue(targetBean, JsonNode.class);
            JsonNode patchedNode = patch.apply(targetNode);
            return objectMapper.treeToValue(patchedNode, beanClass);
        }
    }