package zzz.domain;


public enum ZzzTypeCode {

    AAA(0, "aaa"),
    BBB(1, "bbb"),
    CCC(2, "ccc"),
    ;

    private final int code;
    private final String name;


    private ZzzTypeCode(int code, String name) {
        this.code = code;
        this.name= name;
    }


    public int getValue() {
        return code;
    }

    public String getName() {
        return name;
    }
}
