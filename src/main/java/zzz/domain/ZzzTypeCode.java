package zzz.domain;


public enum ZzzTypeCode implements ValueObject<Integer> {

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


    @Override
    public Integer getValue() {
        return code;
    }

    public String getName() {
        return name;
    }
}
