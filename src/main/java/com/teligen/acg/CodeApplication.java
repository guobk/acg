package com.teligen.acg;

import com.teligen.acg.code.build.TemplateBuilder;

public class CodeApplication {

    public static void main(String[] args) {
        // 调用该方法即可自动生成业务代码
        TemplateBuilder.builder();
    }
}
