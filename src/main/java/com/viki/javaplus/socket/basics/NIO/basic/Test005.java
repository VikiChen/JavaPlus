package com.viki.javaplus.socket.basics.NIO.basic;

import groovy.json.internal.CharBuf;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class Test005
{
    @Test
    public void test001() throws CharacterCodingException {
        //获取编码器
        Charset charset = Charset.forName("GBK");
        //创建编码
        CharsetEncoder encoder = charset.newEncoder();
        //获取解码器
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("厉害啦啦啦啦啦啦啦啦啦啦啦啦");
        charBuffer.flip();
        //编码
        ByteBuffer buBuff = encoder.encode(charBuffer);
        for (int i = 0; i < 12; i++) {
            System.out.println(buBuff.get());
        }
        //解码
        buBuff.flip();
        CharBuffer decode = decoder.decode(buBuff);

        System.out.println(decode.toString());
    }
}
