package com.jsoniter.output;

import com.jsoniter.spi.Encoder;
import javassist.*;

class DynamicCodegen {

	static ClassPool pool = ClassPool.getDefault();

	static {
		pool.insertClassPath(new ClassClassPath(Encoder.class));
	}

	public static Encoder gen(Class clazz, String cacheKey, CodegenResult source)
			throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
		source.flushBuffer();
		CtClass ctClass = pool.makeClass(cacheKey);
		ctClass.setInterfaces(new CtClass[] { pool.get(Encoder.class.getName()) });
		String staticCode = source.toString();
		CtMethod staticMethod = CtNewMethod.make(staticCode, ctClass);
		ctClass.addMethod(staticMethod);
		String wrapperCode = source.generateWrapperCode(clazz);
		if ("true".equals(System.getenv("JSONITER_DEBUG"))) {
			System.out.println(">>> " + cacheKey);
			System.out.println(wrapperCode);
			System.out.println(staticCode);
		}
		CtMethod interfaceMethod = CtNewMethod.make(wrapperCode, ctClass);
		ctClass.addMethod(interfaceMethod);
		if (ctClass.toClass().newInstance() instanceof Encoder)
			return (Encoder) ctClass.toClass().newInstance();
		else
			return null;
	}
}