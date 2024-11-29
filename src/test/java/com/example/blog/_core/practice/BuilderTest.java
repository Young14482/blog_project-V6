package com.example.blog._core.practice;

import org.junit.jupiter.api.Test;

class Member {
    private Integer id;
    private String name;
    private String addr;

    private Member() {}

    public static Member builder() {

        return new Member();
    }

    public Member id(Integer id) {
        this.id = id;
        return this;
    }

    public Member name(String name) {
        this.name = name;
        return this;
    }

    public Member addr(String addr) {
        this.addr = addr;
        return this;
    }

}

public class BuilderTest {

    @Test
    public void new_test() {
        Member m = Member.builder()
                .name("이름")
                .addr("주소");
    }
}
