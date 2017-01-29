package com.jane.neo4j.eum;

public enum PersonTypeEnum {

	创建者("创建者", 0), 转播者("转播者", 1);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private PersonTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (PersonTypeEnum c : PersonTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
    
    
}
