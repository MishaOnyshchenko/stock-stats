package com.github.onyshchenko.stock.utils.mappers;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.data.exceptions.MappingException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Component
public class SymbolMapper {

    public Set<Symbol> toSymbols(String json) {
        Set<Symbol> symbols = null;
        try {
            symbols = new ObjectMapper().readValue(json, new TypeReference<Set<Symbol>>(){});
        } catch (IOException ex) {
            log.error("Error while mapping json to Symbol set: " + ex.getMessage());
            throw new MappingException("Json wasn't mapped to Symbols");
        }
        return symbols;
    }
}
