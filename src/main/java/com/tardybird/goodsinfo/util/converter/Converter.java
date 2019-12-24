package com.tardybird.goodsinfo.util.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 转换器，主要是PO和Domain层对象的转换
 *
 * @author nick
 */
public class Converter<T, U> {

    /**
     * from T to U
     */
    private Function<T, U> fromPo;

    /**
     * from U to T
     */
    private Function<U, T> fromDomain;

    public Converter(final Function<T, U> fromPo, Function<U, T> fromDomain) {
        this.fromPo = fromPo;
        this.fromDomain = fromDomain;
    }

    public final U converterFromPo(final T po) {
        return fromPo.apply(po);
    }

    public final T converterFromDomain(final U domain) {
        return fromDomain.apply(domain);
    }

    public final List<U> batchConverterFromPo(final List<T> pos) {
        return pos.stream().map(this::converterFromPo).collect(Collectors.toList());
    }

    public final List<T> batchConverterFromDomain(final List<U> domains) {
        return domains.stream().map(this::converterFromDomain).collect(Collectors.toList());
    }
}
