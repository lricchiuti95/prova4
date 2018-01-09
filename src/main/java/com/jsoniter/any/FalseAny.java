package com.jsoniter.any;

import com.jsoniter.ValueType;
import com.jsoniter.output.JsonStream;

import java.io.IOException;
/**
 * @author Maxibon
 *
 */
class FalseAny extends Any {
	/**
	 * public final static FalseAny INSTANCE = new FalseAny();
	 * 
	 * @author MaxiBon
	 *
	 */

	public final static FalseAny INSTANCE = new FalseAny();

	@Override
	/**
	 * valueType.
	 */
	public ValueType valueType() {
		return ValueType.BOOLEAN;
	}

	@Override
	public Boolean object() {
		return Boolean.FALSE;
	}

	@Override
	public boolean toBoolean() {
		return false;
	}

	@Override
	public int toInt() {
		return 0;
	}

	@Override
	public long toLong() {
		return 0;
	}

	@Override
	public float toFloat() {
		return 0;
	}

	@Override
	public double toDouble() {
		return 0;
	}

	@Override
	public String toString() {
		return "false";
	}

	@Override
	public void writeTo(JsonStream stream) throws IOException {
		stream.writeFalse();
	}
}
