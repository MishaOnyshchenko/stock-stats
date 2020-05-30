package com.github.onyshchenko.stock.utils.mappers;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.data.exceptions.MappingException;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SymbolMapperTest {

    private SymbolMapper mapper = new SymbolMapper();

    @Test
    void shouldHaveNotBeEmpty() {
        //GIVEN
        String symbolsInline = "[{\"symbol\":\"INSU\",\"exchange\":\"ASN\",\"name\":\"iu spsqo aucAi rCsntsCarnAnloc.Iei \",\"date\":\"2020-05-30\",\"type\":\"cs\",\"iexId\":\"IEX_5331544832542D52\",\"region\":\"US\",\"currency\":\"USD\",\"isEnabled\":true,\"figi\":\"N0VHDBHB0G90\",\"cik\":\"1833510\"},{\"symbol\":\"INSUU\",\"exchange\":\"NSA\",\"name\":\"t-  0inAn Crs fs3  oA0couo5 1h CiSqWe.rci.n.U3 +Ist-in .n14ua so 2tp\",\"date\":\"2020-05-30\",\"type\":\"ut\",\"iexId\":\"IEX_534432474B372D52\",\"region\":\"US\",\"currency\":\"USD\",\"isEnabled\":true,\"figi\":\"WNDM1B0GB004\",\"cik\":\"1847820\"}]";
        //WHEN
        Set<Symbol> result = mapper.toSymbols(symbolsInline);
        //THEN
        assertThat(result, not(IsEmptyCollection.empty()));
    }

    @Test
    void shouldGetCorrectSymbolName() {
        //GIVEN
        String symbolsInline = "[{\"symbol\":\"INSU\",\"exchange\":\"ASN\",\"name\":\"iu spsqo aucAi rCsntsCarnAnloc.Iei \",\"date\":\"2020-05-30\",\"type\":\"cs\",\"iexId\":\"IEX_5331544832542D52\",\"region\":\"US\",\"currency\":\"USD\",\"isEnabled\":true,\"figi\":\"N0VHDBHB0G90\",\"cik\":\"1833510\"}]";
        String expected = "INSU";
        //WHEN
        Set<Symbol> symbols = mapper.toSymbols(symbolsInline);
        String result = symbols.iterator().next().getSymbol();
        //THEN
        assertThat(expected, equalTo(result));
    }

    @Test
    void shouldThrowMappingException() {
        //GIVEN
        String wrongLine = "[{\"symbol\":\"INSU\"833510\"}]";
        //WHEN
        Throwable thrown = assertThrows(MappingException.class, () -> mapper.toSymbols(wrongLine));
        //THEN
        assertNotNull(thrown.getMessage());
    }
}