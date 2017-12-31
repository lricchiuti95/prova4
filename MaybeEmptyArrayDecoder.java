package com.jsoniter.fuzzy;

import java.io.IOException;

import com.jsoniter.JsonIterator;
import com.jsoniter.ValueType;


/**
 * Public Class MaybeEmptyArrayDecoder.
 * 
 * @author MaxiBon
 *
 */
public class MaybeEmptyArrayDecoder implements com.jsoniter.spi.Decoder {

    @Override
    /**
     * decode
     */
    public Object decode(JsonIterator iter) throws IOException {
        if (iter.whatIsNext() == ValueType.ARRAY) {
            if (iter.readArray()) {
                throw iter.reportError("MaybeEmptyArrayDecoder", "this field is object. if input is array, it can only be empty");
            } else {
                // empty array parsed as null
                return null;
            }
        } else {
            return iter.read(iter);
        }
    }
}
