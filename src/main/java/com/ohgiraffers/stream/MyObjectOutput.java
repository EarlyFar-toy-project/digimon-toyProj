package com.ohgiraffers.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutput extends ObjectOutputStream {
    public MyObjectOutput(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        /* 설명. 객체 출력 시 헤더 추가 관련 기능이 없도록 재정의 */
    }
}
