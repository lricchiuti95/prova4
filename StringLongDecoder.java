package com.jsoniter.fuzzy;

import com.jsoniter.CodegenAccess;
import com.jsoniter.JsonIterator;
import com.jsoniter.spi.Decoder;

import java.io.IOException;

/**
 * Public Class StringLongDecoder.
 * 
 * @author MaxiBon
 *
 */
public class StringLongDecoder extends Decoder.LongDecoder {

    @Override
    /**
     * decodeLong
     */
    public long decodeLong(JsonIterator iter) throws IOException {
        byte c = CodegenAccess.nextToken(iter);
        if (c != '"') {
            throw iter.reportError("StringLongDecoder", "expect \", but found: " + Byte.toString(c).charAt(0));
        }
        long val = iter.readLong();
        c = CodegenAccess.nextToken(iter);
        if (c != '"') {
            throw iter.reportError("StringLongDecoder", "expect \", but found: " + Byte.toString(c).charAt(0));
        }
        return val;
    }
}
